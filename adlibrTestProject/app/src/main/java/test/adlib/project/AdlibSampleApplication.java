package test.adlib.project;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class AdlibSampleApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        // 페이스북 AudienceNetworkSDK v5.3.0 이상을 사용하는 경우에만 명시적인 초기화 필요.
        // 아래는 AudienceNetwork 가이드에 제시된 Helper 클래스를 사용한 예시.
        AudienceNetworkInitializeHelper.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
