package test.adlib.project.ads;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nhnace.adlib.AdlibManager;
import com.nhnace.adlib.SubAdlibAdViewCore;
import org.jetbrains.annotations.NotNull;

public class SubAdlibAdViewAdmob extends SubAdlibAdViewCore {

    protected AdView ad;
    protected boolean bGotAd = false;

    // 여기에 ADMOB ID 를 입력하세요. (여기서 ADMOB ID는 AD_UNIT_ID, 광고 단위 ID를 지칭합니다.)
    protected String admobID = "ca-app-pub-3940256099942544/6300978111";
    protected static String admobInterstitialID = "ca-app-pub-3940256099942544/1033173712";

    private AdRequest request = new AdRequest.Builder().build();

    public SubAdlibAdViewAdmob(Context context) {
        this(context, null);
    }

    public SubAdlibAdViewAdmob(Context context, AttributeSet attrs) {
        super(context, attrs);
        bannerSize = SIZE_BANNER;
        initAdmobView();
    }

    public SubAdlibAdViewAdmob(Context context, int size) {
        super(context, null);
        bannerSize = size;
        initAdmobView();
    }

    public void initAdmobView() {
        ad = new AdView(getContext());
        ad.setAdUnitId(admobID);
        if (bannerSize == SIZE_HALF) {
            ad.setAdSize(AdSize.MEDIUM_RECTANGLE);
        } else {
            ad.setAdSize(AdSize.BANNER);
        }

        // 광고 뷰의 위치 속성을 제어할 수 있습니다.
        this.setGravity(Gravity.CENTER);
        ad.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                bGotAd = true;
                failed();
            }
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                bGotAd = true;
                queryAd();
                // 광고를 받아왔으면 이를 알려 화면에 표시합니다.
                gotAd();
            }

            @Override
            public void onAdOpened() {
            }
        });
    }

    // 스케줄러에의해 자동으로 호출됩니다.
    // 실제로 광고를 보여주기 위하여 요청합니다.
    public void query() {
        if (ad == null) initAdmobView();

        this.removeAllViews();
        this.addView(ad);

        ad.loadAd(request);

        // 5초 이상 리스너 응답이 없으면 다음 플랫폼으로 넘어갑니다.
        Handler adHandler = new Handler();
        adHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (bGotAd) {
                    return;
                } else {
                    failed();
                    if (ad != null) {
                        SubAdlibAdViewAdmob.this.removeView(ad);
                        ad.destroy();
                        ad = null;
                    }
                    bGotAd = false;
                }
            }

        }, 5000);
    }

    public void onDestroy() {
        if (ad != null) {
            this.removeView(ad);
            ad.destroy();
            ad = null;
        }

        super.onDestroy();
    }

    public void clearAdView() {
        if (ad != null) {
            this.removeView(ad);
        }

        super.clearAdView();
    }

    public void onResume() {
        ad.resume();
        super.onResume();
    }

    public void onPause() {
        ad.pause();
        super.onPause();
    }

    public static void loadInterstitial(Context ctx, final Handler h, final String adlibKey) {
        // Create ad request
        AdRequest adRequest = new AdRequest.Builder().build();

        // Begin loading your interstitial
        InterstitialAd.load(ctx, admobInterstitialID, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull @NotNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                try {
                    if (h != null) {
                        h.sendMessage(Message.obtain(h, AdlibManager.DID_ERROR, "ADMOB"));
                    }
                } catch (Exception ignored) {
                }
            }

            @Override
            public void onAdLoaded(@NonNull @NotNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        try {
                            if (h != null) {
                                h.sendMessage(Message.obtain(h, AdlibManager.INTERSTITIAL_CLOSED, "ADMOB"));
                            }
                        } catch (Exception ignored) {
                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull @NotNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                    }
                });

                try {
                    if (h != null) {
                        h.sendMessage(Message.obtain(h, AdlibManager.DID_SUCCEED, "ADMOB"));
                    }
                    interstitialAd.show((Activity) ctx);
                } catch (Exception e) {
                }
            }
        });
    }
}