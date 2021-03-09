package test.adlib.project.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.mocoplex.adlib.AdlibManager;
import com.mocoplex.adlib.SubAdlibAdViewCore;

public class SubAdlibAdViewFacebook extends SubAdlibAdViewCore {

	protected AdView ad;
	protected boolean bGotAd = false;

	// 여기에 FACEBOOK ID 를 입력하세요. (여기서 FACEBOOK ID는 Audience Network의 Placement ID를 지칭합니다.)
	protected String facebookID = "FACEBOOK_ID";
	protected static String facebookInterstitialID = "FACEBOOK_Interstitial_ID";

    public SubAdlibAdViewFacebook(Context context) {
		this(context,null);
	}

	public SubAdlibAdViewFacebook(Context context, AttributeSet attrs) {
		super(context, attrs);
		initFacebookView();
	}

	public SubAdlibAdViewFacebook(Context context, int size) {
		super(context, null);
		failed();
	}

	public void initFacebookView() {
		ad = new AdView(getContext(), facebookID, AdSize.BANNER_HEIGHT_50);
	}

	// 스케줄러에의해 자동으로 호출됩니다.
	// 실제로 광고를 보여주기 위하여 요청합니다.
	public void query() {
		if(ad == null)
			initFacebookView();

        this.removeAllViews();
		this.addView(ad);

		ad.loadAd(ad.buildLoadAdConfig().withAdListener(new AdListener() {
			@Override
			public void onError(Ad ad, AdError adError) {
				bGotAd = true;
				failed();
			}

			@Override
			public void onAdLoaded(Ad ad) {
				bGotAd = true;
				queryAd();
				// 광고를 받아왔으면 이를 알려 화면에 표시합니다.
				gotAd();
			}

			@Override
			public void onAdClicked(Ad ad) {}

			@Override
			public void onLoggingImpression(Ad ad) {}
		}).build());

        // 5초 이상 리스너 응답이 없으면 다음 플랫폼으로 넘어갑니다.
		Handler adHandler = new Handler();
		adHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				if(bGotAd){
					return;
				}else{
					failed();
					if(ad != null) {
                    	SubAdlibAdViewFacebook.this.removeView(ad);
                    	ad.destroy();
                    	ad = null;
                    }
                    bGotAd = false;
				}
			}

		}, 5000);
	}

	public void onDestroy() {
		if(ad != null){
			this.removeView(ad);
			ad.destroy();
			ad = null;
		}

		super.onDestroy();
	}

	public void clearAdView() {
		if(ad != null){
        	this.removeView(ad);
		}

        super.clearAdView();
	}

	public void onResume() {
        super.onResume();
	}

	public void onPause() {
        super.onPause();
	}

	public static void loadInterstitial(Context ctx, final Handler h, final String adlibKey) {
		final InterstitialAd interstitialAd = new InterstitialAd(ctx, facebookInterstitialID);

		AdSettings.setTestMode(true);

		// Set a listener to get notified on changes or when the user interact with the ad.
		interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
			@Override
			public void onInterstitialDisplayed(Ad ad) {

			}

			@Override
			public void onInterstitialDismissed(Ad ad) {
				try{
					if(h != null){
						h.sendMessage(Message.obtain(h, AdlibManager.INTERSTITIAL_CLOSED, "FACEBOOK"));
					}
				}catch(Exception e){
				}
			}

			@Override
			public void onError(Ad ad, AdError adError) {
				try{
					if(h != null){
						h.sendMessage(Message.obtain(h, AdlibManager.DID_ERROR, "FACEBOOK"));
					}
				}catch(Exception e){
				}
			}

			@Override
			public void onAdLoaded(Ad ad) {
				try{
					if (interstitialAd != null && interstitialAd.isAdLoaded()) {
						if(h != null){
							h.sendMessage(Message.obtain(h, AdlibManager.DID_SUCCEED, "FACEBOOK"));
						}
						interstitialAd.show();
					}
				}catch(Exception e){
				}
			}

			@Override
			public void onAdClicked(Ad ad) {

			}

			@Override
			public void onLoggingImpression(Ad ad) {

			}
		}).build());
	}

}