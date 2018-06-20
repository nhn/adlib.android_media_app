# ADLib AOS SDK 적용 가이드
> 애드립 AOS SDK를 사용하여 애드립 광고를 노출하는 방법을 제공합니다.<br>
> 또한 기타 광고 플랫폼을 사용하여 미디에이션 기능을 사용하는 방법을 제공합니다.


## 지원 광고 플랫폼
- AdMixer v1.4.2
- Adfit(Adam) v3.0.2
- Admob   Google Play Version
- Amazon  v5.8.2
- Axonix(Mobclix) v4.4.0
- Cauly   v3.4.15
- FaceBook v4.27.0
- Inmobi  v7.0.2
- MezzoMedia(A-plus)  v2.0(20161207)
- MillennialMedia v6.6.1
- Mobfox  v3.3.0
- Mopub   v4.19
- ShallWeAd   v1.9_20171120
- T-ad(Syrup Ad/Dawin Click)  v3.16.3
- TNK v6.37
- UPlaus AD


## 개발환경
- 최소 SDK Version : Android 11
- Compile SDK : Android 22 이상
- Build Tool : Android Stdio 권장
<br><br>

## 프로젝트 연동

### 기본 설정

#### 단계1. AndroidManifest 설정
```XML
<!-- 애드립 사용을 위한 필수 -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.GET_PACKAGE_SIZE"/>

<application
  android:icon="@mipmap/ic_launcher"
  android:label="@string/app_name">

  <activity
    android:name="com.mocoplex.adlib.AdlibDialogActivity"
    android:configChanges="orientation|screenSize|keyboard|keyboardHidden"
    android:theme="@android:style/Theme.Translucent"/>
  <activity
    android:name="com.mocoplex.adlib.AdlibVideoPlayer"
    android:configChanges="orientation|screenSize|keyboard|keyboardHidden"
    android:theme="@android:style/Theme.NoTitleBar"/>
</application>
```


#### 단계2. AdlibManager 선언

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

#### 단계3. 광고 스케쥴 관련 정보 
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
    AdlibConfig.getInstance().bindPlatform("UPLUSAD", "test.adlib.project.ads.SubAdlibAdViewUPlusAD");
}
```

<br>

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

// 배너 스케쥴 요청 실패 시 대기 시간동안 노출되는 View 설정
// View backFill = new View(this);
// backFill.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
// adlibManager.setBannerBackfillView(backFill);

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
<br>

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
<br>

### 다이얼로그 광고
- 애드립에서 제공되는 다이얼로그 형태의 광고

<div>
	<table style="width:100%">
		<tbody>
			<tr>
				<td style="border:0;">
					<img src="http://developer.adlibr.com/img/sdk_and_dialog.png" width=300px>
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
- Proguard를 이용하여 암호화 하는 경우 proguard configuration 파일 수정이 필요합니다.<br>
자세한 구현 내용은 샘플 프로젝트의 `proguard.cfg ` 파일 또는 [proguard-rules.pro](./app/proguard-rules.pro) 참고해 주세요.<br>

<br>