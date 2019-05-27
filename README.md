# SDK Version History

|버전|내용|
|---|---|
|5.0.0.2<br/>(2019.04.05)|구글 플레이스토어 앱 마켓 랜딩 개선<br>구형 단말기에서 전면배너 뷰의 잘림현상 개선<br>같은 플랫폼에서 광고가 연속적으로 노출될 때 리스너를 다시 주지 않는 현상 수정<br>|
|5.0.0.1<br/>(2018.12.10)|이용자 단말로부터 수집하는 데이터 항목 축소<br>|
|5.0.0.0<br/>(2018.06.12)|미디에이션 워터폴 방식 변경(광고 노출 성공하는 경우 스케쥴 index 초기화) <br>중지된 미디에이션 삭제 <br>샘플 프로젝트 업데이트 <br>|

# ADLib AOS SDK 적용 가이드

애드립 AOS SDK를 사용하여 애드립 광고를 노출하는 방법을 제공합니다.<br/>또한 기타 광고 플랫폼을 사용하여 미디에이션 기능을 사용하는 방법을 제공합니다.

## 미디에이션 플랫폼 SDK 지원

각 플랫폼의 SDK 버전은 API call 방식이 변경되지 않는한 기존의 애드립 SubView를 통해 상위, 하위 호환이 가능합니다.
실제로 각 플랫폼의 마이너 버전 업데이트가 일어나더라도 애드립 구현부의 변경없이 업데이트 된 SDK 만 새롭게 적용하면 그대로 사용가능하며, 최소한의 구현부 수정으로 큰 수정없이 적용할 수 있도록 구성되었습니다.

|플랫폼|버전|SDK 다운로드|
|---|:---:|:---:|
|AdMixer|v1.4.6|<a href="http://www.admixer.co.kr/" target="_blank">다운로드</a>|
|Adfit(Adam)|v3.0.8|<a href="https://github.com/adfit/adfit-android-sdk" target="_blank">다운로드</a>|
|Admob|Google Play Version|<a href="https://developers.google.com/admob/android/quick-start" target="_blank">다운로드</a>|
|Amazon|v5.9.0|<a href="https://developer.amazon.com/public/resources/development-tools/sdk" target="_blank">다운로드</a>|
|Axonix(Mobclix)|v4.4.0|<a href="https://github.com/AxonixRTB/axonix-android-sdk-releases" target="_blank">다운로드</a>|
|Cauly|v3.4.23|<a href="https://github.com/cauly/Android-SDK" target="_blank">다운로드</a>|
|FaceBook|v5.3.0|<a href="https://developers.facebook.com/docs/audience-network/getting-started?locale=ko_KR" target="_blank">다운로드</a>|
|Inmobi|v7.2.7|<a href="http://www.inmobi.com/products/sdk/" target="_blank">다운로드</a>|
|MillennialMedia|v6.8.3|<a href="http://docs.onemobilesdk.aol.com/android-ad-sdk/" target="_blank">다운로드</a>|
|Mobfox|v3.6.9|<a href="https://github.com/mobfox/MobFox-Android-SDK" target="_blank">다운로드</a>|
|Mopub|v5.6.0|<a href="https://developers.mopub.com/docs/android/getting-started/" target="_blank">다운로드</a>|
|ShallWeAd|v1.12_20181031|<a href="http://www.shallweadcorp.com/main/developer_01?pageNum=2&subNum=1" target="_blank">다운로드</a>|
|T-ad(Syrup Ad/Dawin Click)|v3.16.7|<a href="https://click.dawin.tv/poc/#/sdk" target="_blank">다운로드</a>|
|TNK|v6.50|<a href="http://docs.tnkad.net/tnk-ad-sdk/sdk-guide" target="_blank">다운로드</a>|
|Mezzo MANPLUS|v1.0.6|<a href="https://publisher.man-plus.com/Service/about/about_aplus_advertiser" target="_blank">다운로드</a>|


## 개발환경
- 최소 SDK Version : Android 19
- Compile SDK : Android 26 이상
- Build Tool : Android Studio 권장

## 프로젝트 연동

### 기본 설정

#### 단계1. Gradle 설정
- 애드립에서 제공하는 샘플 프로젝트는 Gradle 환경의 안드로이드 스튜디오에서 빌드하기에 최적화 되어 있습니다.
```XML
[build.gradle]
android {
  defaultConfig {
    ...
    multiDexEnabled true
  }
  dexOptions {
    jumboMode = true
    javaMaxHeapSize "4g"
  }
}
```
- com.android.dex.DexIndexOverflowException: method ID not in [0, 0xffff]: 65536 에러가 발생하는 경우 defaultConfig의 multiDexEnable 상태를 꼭 확인해주시기 바랍니다.
- dexOptions은 빌드 시 OutOfMemoryError가 발생할 경우, 확인해주시기 바랍니다.
- 안정적인 빌드를 위해 가능하면 프로가드를 사용하도록 권장합니다.

#### 단계2. 라이브러리 추가
- 광고 설정을 위해 gradle 파일의 dependencies를 추가합니다. 
```XML
dependencies {
  implementation fileTree(dir: 'libs', include: ['*.jar'])
  implementation 'com.android.support:multidex:1.0.3'
}
```
- 'com.android.support:multidex:1.0.3' : 안드로이드의 메서드 제한 오류를 해결하기 위한 라이브러리 추가.

#### 단계3. AndroidManifest 설정
```XML
<!-- 애드립 사용을 위한 필수 -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<application
  android:icon="@mipmap/ic_launcher"
  android:usesCleartextTraffic="true" // targetSdkVersion 28 부터 필수 추가
  android:label="@string/app_name">

  <activity
    android:name="com.mocoplex.adlib.AdlibDialogActivity"
    android:configChanges="orientation|screenSize|keyboard|keyboardHidden"
    android:theme="@android:style/Theme.Translucent"/>
</application>
```
- Android 9.0 에서 보안 개선을 위해 TLS 네트워크를 기본으로 합니다. 
- Android 9.0 이상을 대상으로 하는 경우 usesCleartextTraffic 을 true로 설정 해 주셔야 HTTP 통신이 가능합니다.(Adlib은 기본적으로 HTTP 통신을 사용합니다)

#### 단계4. AdlibManager 선언
- AdlibManager 생성 및 초기화 이후 미디에이션 관련 설정을 진행
- Activity Life Cycle에 맞게 AdlibManager 호출

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ...

    // adlibr.com 에서 발급받은 api 키를 입력합니다.
    // ADLIB - API - KEY 설정
    // 각 애드립 액티비티에 애드립 앱 키값을 필수로 넣어주어야 합니다.
    adlibManager = new AdlibManager(AdlibTestProjectConstants.ADLIB_API_KEY);
    adlibManager.onCreate(this);
    // 테스트 광고 노출로, 상용일 경우 꼭 제거해야 합니다.
    adlibManager.setAdlibTestMode(AdlibTestProjectConstants.ADLIB_TEST_MODE);

    // 미디에이션 스케쥴 관련 설정
    bindPlatform();

    ...
}

protected void onResume() {
    adlibManager.onResume(this);
    super.onResume();
}

protected void onPause() {
    adlibManager.onPause(this);
    super.onPause();
}

protected void onDestroy() {
    adlibManager.onDestroy(this);
    super.onDestroy();
}

```

#### 단계5. 광고 스케쥴 관련 정보
```java
private void bindPlatform() {
    // 광고 스케줄링 설정 - 전면, 띠 배너 동일
    // AdlibManager 생성 및 onCreate() 이후
    // 광고 요청 이전에 해당 스케쥴 관련 타 플랫폼 정보 등록
    // 첫번 째 AdlibManager 생성 시에 호출
    // 광고 subview 의 패키지 경로를 설정 (실제로 작성된 패키지 경로로 변경)

    // 쓰지 않을 광고 플랫폼은 삭제해주세요.
    AdlibConfig.getInstance().bindPlatform("ADMIXER", "test.adlib.project.ads.SubAdlibAdViewAdmixer");
    AdlibConfig.getInstance().bindPlatform("ADAM", "test.adlib.project.ads.SubAdlibAdViewAdam");
    AdlibConfig.getInstance().bindPlatform("ADMOB", "test.adlib.project.ads.SubAdlibAdViewAdmob");
    AdlibConfig.getInstance().bindPlatform("AMAZON", "test.adlib.project.ads.SubAdlibAdViewAmazon");
    AdlibConfig.getInstance().bindPlatform("MOBCLIX", "test.adlib.project.ads.SubAdlibAdViewMobclix");
    AdlibConfig.getInstance().bindPlatform("CAULY", "test.adlib.project.ads.SubAdlibAdViewCauly");
    AdlibConfig.getInstance().bindPlatform("FACEBOOK", "test.adlib.project.ads.SubAdlibAdViewFacebook");
    AdlibConfig.getInstance().bindPlatform("INMOBI", "test.adlib.project.ads.SubAdlibAdViewInmobi");
    AdlibConfig.getInstance().bindPlatform("MEZZO", "test.adlib.project.ads.SubAdlibAdViewMezzo");
    AdlibConfig.getInstance().bindPlatform("MMEDIA", "test.adlib.project.ads.SubAdlibAdViewMMedia");
    AdlibConfig.getInstance().bindPlatform("MOBFOX", "test.adlib.project.ads.SubAdlibAdViewMobfox");
    AdlibConfig.getInstance().bindPlatform("MOPUB", "test.adlib.project.ads.SubAdlibAdViewMopub");
    AdlibConfig.getInstance().bindPlatform("SHALLWEAD", "test.adlib.project.ads.SubAdlibAdViewShallWeAd");
    AdlibConfig.getInstance().bindPlatform("TAD", "test.adlib.project.ads.SubAdlibAdViewTAD");
    AdlibConfig.getInstance().bindPlatform("TNK", "test.adlib.project.ads.SubAdlibAdViewTNK");
}
```

### 띠 배너 연동

- 애드립 기본 띠배너

```java
// 각 애드립 액티비티에 애드립 앱 키값을 필수로 넣어주어야 합니다.
adlibManager = new AdlibManager(AdlibTestProjectConstants.ADLIB_API_KEY);
adlibManager.onCreate(this);
// 테스트 광고 노출로, 상용일 경우 꼭 제거해야 합니다.
adlibManager.setAdlibTestMode(AdlibTestProjectConstants.ADLIB_TEST_MODE);
// 배너 스케쥴에 등록된 광고 모두 광고 요청 실패 시 대기 시간 설정(단위:초, 기본:10초, 최소:1초)
// adlibManager.setBannerFailDelayTime(10);

// 이벤트 핸들러 등록
adlibManager.setAdsHandler(new Handler() {
    public void handleMessage(Message message) {
        try {
            switch (message.what) {
                case AdlibManager.DID_SUCCEED:
                    Log.d("ADLIBr", "[Banner] onReceiveAd " + (String) message.obj);
                    break;
                case AdlibManager.DID_ERROR:
                    Log.d("ADLIBr", "[Banner] onFailedToReceiveAd " + (String) message.obj);
                    break;
                case AdlibManager.BANNER_FAILED:
                    Log.d("ADLIBr", "[Banner] All Failed.");
                    break;
            }
        } catch (Exception e) {

        }
    }
});

adlibManager.setAdsContainer(R.id.ads);
```

### 전면 배너 연동

- 애드립 기본 전면 배너

```java
// 각 애드립 액티비티에 애드립 앱 키값을 필수로 넣어주어야 합니다.
adlibManager = new AdlibManager(AdlibTestProjectConstants.ADLIB_API_KEY);
adlibManager.onCreate(this);
// 테스트 광고 노출로, 상용일 경우 꼭 제거해야 합니다.
adlibManager.setAdlibTestMode(AdlibTestProjectConstants.ADLIB_TEST_MODE);

// 전면광고를 호출합니다. (미디에이션 전면배너 요청)
//adlibManager.loadFullInterstitialAd(this);

// optional : 전면광고의 수신 성공, 실패 이벤트 처리가 필요한 경우엔 handler를 이용하실 수 있습니다. (미디에이션 전면배너 요청)
adlibManager.loadFullInterstitialAd(this, new Handler() {
    public void handleMessage(Message message) {
        try {
            switch (message.what) {
                case AdlibManager.DID_SUCCEED:
                    Log.d("ADLIBr", "[Interstitial] onReceiveAd " + (String) message.obj);
                    break;
                // 전면배너 스케줄링 사용시, 각각의 플랫폼의 수신 실패 이벤트를 받습니다.
                case AdlibManager.DID_ERROR:
                    Log.d("ADLIBr", "[Interstitial] onFailedToReceiveAd " + (String) message.obj);
                    break;
                // 전면배너 스케줄로 설정되어있는 모든 플랫폼의 수신이 실패했을 경우 이벤트를 받습니다.
                case AdlibManager.INTERSTITIAL_FAILED:
                    Toast.makeText(AdlibIntersMediationActivity.this, "광고수신 실패 :)", Toast.LENGTH_SHORT).show();
                    Log.d("ADLIBr", "[Interstitial] All Failed.");
                    break;
                case AdlibManager.INTERSTITIAL_CLOSED:
                    Log.d("ADLIBr", "[Interstitial] onClosedAd " + (String) message.obj);
                    break;
            }
        } catch (Exception e) {
        }
    }
});
```


### 다이얼로그 광고

- 애드립에서 제공되는 다이얼로그 형태의 광고

<div>
	<table style="width:100%">
		<tbody>
			<tr>
				<td style="border:0;">
					<img src="http://developer.adlibr.com/share/img/sdk_and_dialog.png" width=300px>
				</td>
				<td class="dialog_tdata" style="border:0;">
					Activity에서 requestAdDialog() 매서드를 통해 광고를 요청합니다.<br><br>
					이후 광고 노출이 필요한 시점에 showAdDialog() 메서드를 통해 dialog를 이용하여 광고를 노출할 수 있습니다.<br><br>
					AdlibDialogAdListener() 로 버튼 액션에 대한 기능을 추가할 수 있습니다.<br><br>
					자세한 내용은 샘플 프로젝트 AdlibTestProjectActivity.java 파일을 참고해주세요. <br>
					샘플 프로젝트에서는 종료 대화상자 광고를 예시로 구현하였습니다.<br><br>
					(종료 dialog 광고는 세로 화면일 경우에만 적용됩니다.)
				</td>
			</tr>
		</tbody>
	</table>
</div>

#### 단계1. 광고 요청
```java
// 다이얼로그 광고 요청
adlibManager.requestAdDialog(new Handler() {
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case AdlibManager.DID_SUCCEED:
                break;

            case AdlibManager.DID_ERROR:
                break;
        }
    }
});
```

#### 단계2. 다이얼로그 노출
```java
// 다이얼로그 광고 사용 가능 여부 확인
// 광고가 없는 경우 타 플랫폼이나 매체 내부 동작 처리가 필요한 경우 사용가능한 함수
// adlibManager.isAvailableAdDialog();

// 종료 대화상자 광고를 노출하기 위해서 호출합니다.
adlibManager.showAdDialog("취소", "확인", "종료하시겠습니까?");
```
<br>

### 주의 사항

- Proguard를 적용하는 경우 proguard configuration 파일 수정이 필요합니다.

자세한 구현 내용은 샘플 프로젝트의 `proguard.cfg ` 파일 또는 [proguard-rules.pro](./adlibrTestProject/app/proguard-rules.pro) 참고해 주세요.