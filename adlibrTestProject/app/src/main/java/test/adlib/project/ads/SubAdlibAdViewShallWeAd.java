package test.adlib.project.ads;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.co.shallwead.sdk.ShallWeAdBanner.ShallWeAdBannerListener;
import com.co.shallwead.sdk.ShallWeAdBanner;
import com.mocoplex.adlib.SubAdlibAdViewCore;

public class SubAdlibAdViewShallWeAd extends SubAdlibAdViewCore {
	
	protected ShallWeAdBanner ad;
	protected boolean bGotAd = false;
	
	public SubAdlibAdViewShallWeAd(Context context) {
		this(context, null);
	}	
	
	public SubAdlibAdViewShallWeAd(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initSwaView();
	}
	
	public void initSwaView() {
		ad = new ShallWeAdBanner(getContext());
		ad.setShallWeAdBannerListener(new ShallWeAdBannerListener() {
			@Override
			public void onShowBannerResult(boolean pResult) {
				bGotAd = true;
				if(!pResult) {
					failed();
				} else {
					gotAd();
				}
			}
		});
		this.addView(ad);
	}

	// 스케줄러에의해 자동으로 호출됩니다.
	// 실제로 광고를 보여주기 위하여 요청합니다.	
	public void query() {
		bGotAd = false;
		
		if(ad == null)
			initSwaView();
		
		queryAd();
		
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
						SubAdlibAdViewShallWeAd.this.removeAllViews();
						ad = null;
					}
					bGotAd = false;
				}
			}
				
		}, 5000);
	}

	// 광고뷰를 삭제하는 경우 호출됩니다. 
	public void clearAdView() {
		if(ad != null){
			SubAdlibAdViewShallWeAd.this.removeAllViews();
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
			SubAdlibAdViewShallWeAd.this.removeAllViews();
			ad = null;
		}
		super.onDestroy();
	}
}