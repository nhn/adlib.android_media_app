package test.adlib.project.ads;

import android.content.Context;
import android.util.AttributeSet;

import com.mezzomedia.man.AdConfig;
import com.mezzomedia.man.AdListener;
import com.mezzomedia.man.data.AdData;
import com.mezzomedia.man.view.AdManView;
import com.nhnace.adlib.SubAdlibAdViewCore;

public class SubAdlibAdViewMezzo extends SubAdlibAdViewCore {
    protected AdManView adView = null;

    // 발급받은 코드가 34,227,479 형식이면 순서대로 publisherCode, mediaCode, sectionCode에 입력.
    protected int publisherCode = 100;
    protected int mediaCode = 200;
    protected int sectionCode = 300;

    public SubAdlibAdViewMezzo(Context context) {
        this(context, null);
    }

    public SubAdlibAdViewMezzo(Context context, AttributeSet attrs) {
        super(context, attrs);

        AdManView.init(context, null);
        String packageName = context.getPackageName();
        String appName = "AdlibSampleApp";

        AdData adData = new AdData();
        adData.major("testBanner", AdConfig.API_BANNER, publisherCode, mediaCode, sectionCode, "http://www.storeurl.com", packageName, appName, 320, 50);
        adData.setUserAgeLevel(0); // 0 - 어린이(만 13세 이하) / 1 - 청소년,성인(만 13세 이상)
        adData.isPermission(AdConfig.NOT_USED, AdConfig.NOT_USED);
        adData.setApiModule(AdConfig.NOT_USED, AdConfig.NOT_USED);

        adView = new AdManView(context);
        adView.setData(adData, new AdListener() {
            // 광고 호출 성공
            @Override
            public void onAdSuccessCode(Object o, String s, String s1, String s2, String s3) {
                // type == AdResponseCode.Type.HOUSE : 무료광고
                // type == AdResponseCode.Type.GUARANTEE : 유료광고

                queryAd();
                gotAd();
            }

            // 광고 호출 실패
            @Override
            public void onAdFailCode(Object o, String s, String s1, String s2, String s3) {
                if (adView != null) {
                    failed();
                }
            }

            // 광고 호출 실패 (웹뷰 에러)
            @Override
            public void onAdErrorCode(Object o, String s, String s1, String s2, String s3) {

            }

            // 광고에서 발생하는 이벤트
            @Override
            public void onAdEvent(Object o, String s, String s1, String s2, String s3) {

            }

            // 광고 퍼미션(위치) 설정 요청 이벤트 - 사용자 단말기 설정 페이지에 있는 앱 권한을 받기 위한 이벤트
            @Override
            public void onPermissionSetting(Object o, String s) {

            }
        });
    }

    public SubAdlibAdViewMezzo(Context context, int size) {
        super(context, null);
        failed();
    }

    // 스케줄러에의해 자동으로 호출됩니다.
    // 실제로 광고를 보여주기 위하여 요청합니다.
    public void query() {
        this.removeAllViews();
        this.addView(adView);

        adView.request(null);
    }

    // 광고뷰가 사라지는 경우 호출됩니다.
    public void clearAdView() {
        if (adView != null) {
            this.removeView(adView);
        }
        super.clearAdView();
    }

    public void onResume() {
        if (adView != null) {
            adView.request(null);
        }
        super.onResume();
    }

    public void onPause() {
        if (adView != null) {
            adView.onPause();
        }
        super.onPause();
    }

    public void onDestroy() {
        if (adView != null) {
            this.removeView(adView);
            adView.onDestroy();
        }
        super.onDestroy();
    }

}