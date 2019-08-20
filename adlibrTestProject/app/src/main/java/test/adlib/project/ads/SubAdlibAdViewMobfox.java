package test.adlib.project.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;

import com.mobfox.sdk.banner.Banner;
import com.mobfox.sdk.interstitial.Interstitial;
import com.mobfox.sdk.interstitial.InterstitialListener;
import com.mocoplex.adlib.AdlibManager;
import com.mocoplex.adlib.SubAdlibAdViewCore;

public class SubAdlibAdViewMobfox extends SubAdlibAdViewCore {

    protected boolean bGotAd = false;

    private Banner banner;

    // 여기에 MOBFOX ID 를 입력하세요.
    protected String mofoxBannerID = "8769bb5eb962eb39170fc5d8930706a9";
    protected static String mofoxInterstitialID = "MOBFOX_INTERSTITIAL_ID";

    public SubAdlibAdViewMobfox(Context context) {
        this(context, null);
    }

    public SubAdlibAdViewMobfox(Context context, AttributeSet attrs) {
        super(context, attrs);

        initMobfoxView();
    }

    public SubAdlibAdViewMobfox(Context context, int size) {
        super(context, null);
        failed();
    }

    public void initMobfoxView() {
        Banner.Listener bannerListener = new Banner.Listener() {
            @Override
            public void onBannerError(Banner banner, Exception e) {
                bGotAd = true;
                failed();
            }

            @Override
            public void onBannerLoaded(Banner banner) {
                bGotAd = true;
                // 광고를 받아왔으면 이를 알려 화면에 표시합니다.
                gotAd();
            }

            @Override
            public void onBannerClosed(Banner banner) {
            }

            @Override
            public void onBannerFinished() {
            }

            @Override
            public void onBannerClicked(Banner banner) {
            }

            @Override
            public void onNoFill(Banner banner) {
                bGotAd = true;
                failed();
            }
        };
        banner = new Banner(getContext(), 320, 50, mofoxBannerID, bannerListener);
        banner.setRefresh(0);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        banner.setLayoutParams(params);
        this.setGravity(Gravity.CENTER);
        this.addView(banner);
    }

    // 스케줄러에의해 자동으로 호출됩니다.
    // 실제로 광고를 보여주기 위하여 요청합니다.
    public void query() {
        bGotAd = false;
        if (banner == null)
            initMobfoxView();

        banner.load();
        queryAd();

        // 3초 이상 리스너 응답이 없으면 다음 플랫폼으로 넘어갑니다.
        Handler adHandler = new Handler();
        adHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (bGotAd) {
                    return;
                } else {
                    if (banner != null)
                        banner.onPause();
                    failed();
                }
            }

        }, 3000);
    }

    // 광고뷰가 사라지는 경우 호출됩니다.
    public void clearAdView() {
        if (banner != null) {
            this.removeView(banner);
            banner.onPause();
            banner = null;
        }

        super.clearAdView();
    }

    public void onResume() {
        if (banner != null) {
            banner.onResume();
        }
        super.onResume();
    }

    public void onPause() {
        if (banner != null) {
            banner.onPause();
        }
        super.onPause();
    }

    public void onDestroy() {
        if (banner != null) {
            banner = null;
        }
        this.removeAllViews();

        super.onDestroy();
    }

    public static void loadInterstitial(Context ctx, final Handler h, final String adlibKey) {
        Interstitial interstitial = new Interstitial(ctx, mofoxInterstitialID, new InterstitialListener() {
            @Override
            public void onInterstitialLoaded(Interstitial interstitial) {
                try {
                    if (h != null) {
                        h.sendMessage(Message.obtain(h, AdlibManager.DID_SUCCEED, "MOBFOX"));
                    }
                } catch (Exception e) {
                }
                interstitial.show();
            }

            @Override
            public void onInterstitialFailed(String e) {
                try {
                    if (h != null) {
                        h.sendMessage(Message.obtain(h, AdlibManager.DID_ERROR, "MOBFOX"));
                    }
                } catch (Exception exception) {
                }
            }

            @Override
            public void onInterstitialClosed() {
                try {
                    if (h != null) {
                        h.sendMessage(Message.obtain(h, AdlibManager.INTERSTITIAL_CLOSED, "MOBFOX"));
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onInterstitialClicked() {
            }

            @Override
            public void onInterstitialShown() {
            }

            @Override
            public void onInterstitialFinished() {
            }
        });
        interstitial.load();
    }
}
