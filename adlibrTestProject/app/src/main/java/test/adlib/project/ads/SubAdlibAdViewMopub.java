package test.adlib.project.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;

import com.mocoplex.adlib.AdlibManager;
import com.mocoplex.adlib.SubAdlibAdViewCore;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;

public class SubAdlibAdViewMopub extends SubAdlibAdViewCore {

	protected boolean bGotAd = false;
	protected MoPubView ad;
	protected boolean isInitSDK = false;
	protected static boolean isInitIntersSDK = false;

	// 여기에 MOPUB ID 를 입력하세요.
	protected String mopubID = "MOPUB_ID";
	protected static String mopubInterstitialID = "MOPUB_INTERSTITIAL_ID";
	
	protected void initMobpubView() {
		isInitSDK = true;
		ad = new MoPubView(getContext());
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ad.setLayoutParams(params);
		
		ad.setAdUnitId(mopubID); // Enter your Ad Unit ID from www.mopub.com
		
		// 광고 뷰의 위치 속성을 제어할 수 있습니다.
		this.setGravity(Gravity.CENTER);
		
		ad.setBannerAdListener(new MoPubView.BannerAdListener(){

			@Override
			public void onBannerLoaded(MoPubView banner) {
				queryAd();
				bGotAd = true;
				// 광고를 받아왔으면 이를 알려 화면에 표시합니다.
				gotAd();
			}
			
			@Override
			public void onBannerFailed(MoPubView arg0, MoPubErrorCode arg1) {
				bGotAd = true;
				failed();
			}
			
			@Override
			public void onBannerClicked(MoPubView banner) {
			}

			@Override
			public void onBannerExpanded(MoPubView banner) {
			}

			@Override
			public void onBannerCollapsed(MoPubView banner) {
			}
		});		
	}

	public SubAdlibAdViewMopub(Context context) {
		this(context,null);		
	}	
	
	public SubAdlibAdViewMopub(Context context, AttributeSet attrs) {
		super(context, attrs);

		SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(mopubID).build();
		MoPub.initializeSdk(getContext(), sdkConfiguration, new SdkInitializationListener() {
			@Override
			public void onInitializationFinished() {
				// MoPub 초기화 후 바로 MoPubView 초기화 하는 경우 정상 동작 안하는 문제로 100ms 딜레이
				Handler adHandler = new Handler();
				adHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						initMobpubView();
					}
				}, 100);
			}
		});
	}
	
	// 스케줄러에의해 자동으로 호출됩니다.
	// 실제로 광고를 보여주기 위하여 요청합니다.
	public void query() {
		if (!isInitSDK) {
			Handler mHandler = new Handler();
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					query();
				}
			}, 100);
			return;
		}
		bGotAd = false;
		if(ad == null)
			initMobpubView();
		
		this.removeAllViews();
		this.addView(ad);
		
		ad.loadAd();
		
		// 3초 이상 리스너 응답이 없으면 다음 플랫폼으로 넘어갑니다.
		Handler adHandler = new Handler();
		adHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				if(bGotAd){
					return;
				}else{
					failed();
				}
			}
				
		}, 3000);
	}

	// 광고뷰가 사라지는 경우 호출됩니다. 
	public void clearAdView() {
		if(ad != null){
			this.removeView(ad);
			ad.destroy();
			ad = null;
		}

		super.clearAdView();
	}
	
	public void onResume() {		
        super.onResume();
	}
	
	public void onPause() {
        super.onPause();
	}
	
	public void onDestroy() {
		if(ad != null){
			ad.destroy();
			ad = null;
		}
		
		this.removeAllViews();
		
        super.onDestroy();
	}

	// 전면광고가 호출되는 경우
	public static void loadInterstitial(final Context ctx, final Handler h, final String adlibKey) {
		if (!isInitIntersSDK) {
			SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(mopubInterstitialID).build();
			MoPub.initializeSdk(ctx, sdkConfiguration, new SdkInitializationListener() {
				@Override
				public void onInitializationFinished() {
					// MoPub 초기화 후 바로 MoPubInterstitial 초기화 하는 경우 정상 동작 안하는 문제로 100ms 딜레이
					Handler adHandler = new Handler();
					adHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
							isInitIntersSDK = true;
							loadInterstitial(ctx, h, adlibKey);
						}
					}, 100);
				}
			});
			return;
		}
		final MoPubInterstitial mInterstitial = new MoPubInterstitial((Activity) ctx, mopubInterstitialID);
		
	    mInterstitial.setInterstitialAdListener( new MoPubInterstitial.InterstitialAdListener() {

			@Override
			public void onInterstitialLoaded(MoPubInterstitial interstitial) {
				try{
					if(h != null){
	 					h.sendMessage(Message.obtain(h, AdlibManager.DID_SUCCEED, "MOPUB"));
	 				}
					
					if(mInterstitial.isReady()){
						mInterstitial.show();
					}
				}catch(Exception e){
				}
			}

			@Override
			public void onInterstitialFailed( MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
				try{
					if(h != null){
	 					h.sendMessage(Message.obtain(h, AdlibManager.DID_ERROR, "MOPUB"));
	 				}
					
					if(mInterstitial != null){
						mInterstitial.destroy();
					}
				}catch(Exception e){
				}
			}

			@Override
			public void onInterstitialShown(MoPubInterstitial interstitial) {
			}

			@Override
			public void onInterstitialClicked(MoPubInterstitial interstitial) {
			}

			@Override
			public void onInterstitialDismissed(MoPubInterstitial interstitial) {
				try{
					if(h != null){
	 					h.sendMessage(Message.obtain(h, AdlibManager.INTERSTITIAL_CLOSED, "MOPUB"));
	 				}
					
					if(mInterstitial != null){
						mInterstitial.destroy();
					}
				}catch(Exception e){
				}
			}
	    });
	    
	    mInterstitial.load();	
	}
}