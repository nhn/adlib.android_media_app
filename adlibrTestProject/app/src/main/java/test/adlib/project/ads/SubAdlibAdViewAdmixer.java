package test.adlib.project.ads;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.mocoplex.adlib.AdlibManager;
import com.mocoplex.adlib.SubAdlibAdViewCore;
import com.nasmedia.admixer.ads.AdEvent;
import com.nasmedia.admixer.ads.AdInfo;
import com.nasmedia.admixer.ads.AdListener;
import com.nasmedia.admixer.ads.AdView;
import com.nasmedia.admixer.ads.InterstitialAd;

public class SubAdlibAdViewAdmixer extends SubAdlibAdViewCore {

	protected AdView ad;
	protected boolean bGotAd = false;

	// 여기에 AdMixer ID 를 입력하세요.
	protected String admixerID = "33kybk8l";
	protected static String admixerInterstitialID = "33kybk8l";

	public SubAdlibAdViewAdmixer(Context context) {
		this(context, null);
	}

	public SubAdlibAdViewAdmixer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAdmixerView();
	}

	public SubAdlibAdViewAdmixer(Context context, int size) {
		super(context, null);
		failed();
	}

	public void initAdmixerView() {
		AdInfo adInfo = new AdInfo.Builder(admixerID).isRetry(false).build();

		ad = new AdView(this.getContext());
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ad.setLayoutParams(params);
		ad.setAdInfo(adInfo);
		ad.setAdViewListener(new AdListener() {

			@Override
			public void onReceivedAd(Object o) {
				Log.d("ADMIXER", "[B_ADMIXER] onReceiveAd ");
				bGotAd = true;
				// 광고를 받아왔으면 이를 알려 화면에 표시합니다.
				gotAd();
			}

			@Override
			public void onFailedToReceiveAd(Object o, int i, String s) {
				bGotAd = true;
				failed();
			}

			@Override
			public void onEventAd(Object o, AdEvent adEvent) {

			}
		});
		this.addView(ad);
	}

	// 스케줄러에의해 자동으로 호출됩니다.
	// 실제로 광고를 보여주기 위하여 요청합니다.
	public void query() {
		bGotAd = false;

		if (ad == null) initAdmixerView();

		queryAd();

		ad.onResume();

		// 3초 이상 리스너 응답이 없으면 다음 플랫폼으로 넘어갑니다.
		Handler adHandler = new Handler();
		adHandler.postDelayed(() -> {
			if (!bGotAd) {
				if (ad != null) ad.onPause();
				failed();
			}
		}, 3000);
	}

	// 광고뷰가 사라지는 경우 호출됩니다.
	public void clearAdView() {
		if (ad != null) {
			this.removeView(ad);
			//ad.destroy();
			ad = null;
		}

		super.clearAdView();
	}

	public void onResume() {
		if (ad != null) {
			ad.onResume();
		}

		super.onResume();
	}

	public void onPause() {
		if (ad != null) {
			ad.onPause();
		}

		super.onPause();
	}

	public void onDestroy() {
		if (ad != null) {
			//ad.destroy();
			ad = null;
		}

		super.onDestroy();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public static void loadInterstitial(Context ctx, final Handler h, final String adlibKey) {
		AdInfo.Builder builder = new AdInfo.Builder(admixerInterstitialID);
		builder.interstitialTimeout(0);  // 초단위로 전면 광고 타이아웃 설정(기본값 : 0, 0 이면 서버 지정 시간으로 처리됨)
		builder.maxRetryCountInSlot(-1); // 리로드 시간 내에 전체 AdNetwork 반복 최대 횟수(-1 : 무한, 0 : 반복 없음, n : n번 반복)
		AdInfo adInfo = new AdInfo.Builder(admixerInterstitialID).build(); // AxKey 값 설정
		// adInfo.setTestMode(true); // 테스트 모드 설정 (SDK v2.1.3 업데이트하면서 사라진 메서드)

		InterstitialAd interstitialAd = new InterstitialAd(ctx);// 전면 광고 생성

		interstitialAd.setAdInfo(adInfo);
		interstitialAd.setAdListener(new AdListener() {

			@Override
			public void onReceivedAd(Object o) {

				try {
					if (h != null) {
						h.sendMessage(Message.obtain(h, AdlibManager.DID_SUCCEED, "ADMIXER"));
					}
				} catch (Exception e) {
				}
			}

			@Override
			public void onFailedToReceiveAd(Object o, int i, String s) {
				try {
					if (h != null) {
						h.sendMessage(Message.obtain(h, AdlibManager.DID_ERROR, "ADMIXER"));
					}
				} catch (Exception e) {
				}
			}

			@Override
			public void onEventAd(Object o, AdEvent adEvent) {
				switch (adEvent) {
					case CLICK:
					case SKIPPED:
					case DISPLAYED:
					case COMPLETION:
					case LEFT_CLICK:
					case RIGHT_CLICK: {
						break;
					}
					case CLOSE: {
						try {
							if (h != null) {
								h.sendMessage(Message.obtain(h, AdlibManager.INTERSTITIAL_CLOSED, "ADMIXER"));
							}
						} catch (Exception e) {
						}
						break;
					}

				}
			}
		});
		/*https://github.com/Nasmedia-Tech/admixer_android_sdk_3.0/blob/main/Guide/README.md
		 * AdMixer SDK 는 LOLLIPOP 이상에서만 동작*/
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			interstitialAd.startInterstitial();
		}
	}
}