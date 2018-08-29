# ADLib Android SDK 적용 샘플 프로젝트

애드립 광고 연동 및 애드립에서 제공하는 광고 플랫폼 미디에이션 연동을 적용하기위한 샘플 프로젝트를 포함합니다.

# 프로젝트 설정 및 가이드 
* [프로젝트 설정 가이드 링크](http://developer.adlibr.com/guide/Android)

# SDK Version History

|버전|내용|
|---|---|
|5.0.0.0<br/>(2018.06.12)|미디에이션 워터폴 방식 변경(광고 노출 성공하는 경우 스케쥴 index 초기화) <br>중지된 미디에이션 삭제 <br>샘플 프로젝트 업데이트 <br>|
|4.5.1.8<br/>(2018.02.21)|마이너 버그 수정|
|4.5.1.5<br/>(2017.12.22)|광고플랫폼 라이브러리 및 SubView 업데이트<br>마이너 버그 수정|
|4.5.0.5<br/>(2017.04.05)|샘플 프로젝트 개선<br>광고플랫폼 라이브러리 및 SubView 업데이트<br>마이너 버그 수정|
|4.5.0.2<br/>(2017.03.07)|샘플 업데이트<br>마이너 버그 수정|
|4.5.0.1<br/>(2017.02.22)|네이티브 비디오 광고 개선<br>마이너 버그 수정|
|4.5.0.0<br/>(2017.02.21)|네이티브 비디오 광고 개선<br>BackFill 기능 추가<br>마이너 버그 수정|
|4.4.0.2<br/>(2016.11.14)|다이얼로그 광고 로직 변경<br>띠배너 미디에이션 관련 수정<br>전면 배너 클릭 관련 수정<br>패키지 수집 관련 항목 변경<br>gapping 광고 관련 수정|
|4.4.0.0<br/>(2016.08.18)|NHN TX 연동을 위한 최적화 작업<br>마이너 버그 수정|
|4.3.0.9<br/>(2016.08.03)|전면 동영상광고 레이아웃 및 로직 변경|
|4.3.0.7<br/>(2016.07.26)|앱 광고,동영상 광고상품 추가(띠/전면)<br>libpng 보안이슈 업데이트|
|4.3.0.4<br/>(2016.06.16)|3D 광고 성능 개선<br>띠배너 롤링 개선 및 기타 오류 수정<br/>mezzoMedia 라이브러리 및 SubView 업데이트|
|4.3.0.2<br/>(2016.04.12)|안드로이드 6.0 권한 문제로 내부 메모리 사용<br>아이콘 상품에 타겟팅 광고 노출<br>ShallWeAd(1.5) 라이브러리 및 SubView 업데이트|
|4.3.0.0<br/>(2016.03.17)|Google Play Services SDK 포함시키지 않아도 사용 가능<br>(애드몹,아담 등 반드시 필요한 SDK를 사용할 때는 포함 필요)<br/>광고 클릭시 브라우저 선택창 뜨지 않도록 수정<br>Inmobi(5.2.3) 라이브러리 및 SubView 업데이트|
|4.2.5.4<br/>(2016.01.22)|아이콘 광고 추가|
|4.2.5.3<br/>(2016.01.14)|광고 플랫폼 라이브러리 및 SubView 업데이트<br>Cauly(3.4.6), Admixer(1.3.12), ShallWeAd(v1_20160111)|
|4.2.5.3<br/>(2015.12.16)|비디오 광고 성능 개선<br>광고 플랫폼의 라이브러리 업데이트<br/>Inmobi(5.1.1), T-ad(3.13.0), Admixer (1.3.11)|
|4.2.5.2<br/>(2015.12.03)|3d 광고 노출 최적화|
|4.2.5.1<br/>(2015.11.02)|광고 플랫폼/SubView 추가(Facebook Audience Network)|
|4.2.5.0<br/>(2015.09.25)|구글 플레이 보안 취약 경고 대응(WebViewClient.onReceivedSslError handler 수정)|
|4.2.4.3<br/>(2015.09.24)|네이티브 광고 유형(Single) 추가<br>광고플랫폼 라이브러리 및 SubView 업데이트<br/>Cauly(3.3.30), Syrup AD(T-ad)(3.11.0), Inmobi(4.5.6), MillennialMedia(6.1), Axonix(Mobclix)(4.3.0), MezzoMedia(MAN)(3.1), Mopub(3.12.0), Admixer(1.3.9), TNK(6.12)|
|4.2.4.2<br/>(2015.09.03)|광고 플랫폼/SubView 추가(TNK)|
|4.2.4.1<br/>(2015.07.27)|3D 광고 리포트 개선(4.2.1.0 이하의 버전을 쓰는 경우에는 최신버전으로 업데이트 진행해주시기 바랍니다.)|
|4.2.4.0<br/>(2015.07.10)|64비트 단말의 3D 광고 지원<br>기타 버그 수정(ClassCastException)|
|4.2.3.0<br/>(2015.07.03)|4.2.2.0 에서의 전면배너 미디에이션 안정화<br>미디에이션 SubView Update전면배너 호출 함수 변경(loadInterstitial 의 인자값 추가)<br>미디에이션 통계를 위한 호출 함수 변경(Admob, Inmobi, Millennial Media, Mobfox, Mopub)|
|4.2.2.0<br/>(2015.07.02)|네이티브 지면 광고 기능 추가<br>미디에이션 통계 정보 수집을 위한 수정(SubView 업데이트 필요)<br>java.lang.IllegalStateException 관련 예외처리<br>SDK 사이즈 경량화<br>NaverAdPost 플랫폼 제외<br/>광고 플랫폼의 라이브러리 업데이트<br/>AmazonMobileAds(5.6.20), Mopub (3.8.0), Admixer(1.3.5)|
|4.2.1.3<br/>(2015.06.11)|SamsungAdHub 플랫폼 제외(6월 25일자 서비스 종료)<br>전면배너 뷰 요청에 대한 기능 추가|
|4.2.1.2<br/>(2015.06.04)|유니티 관련 수정|
|4.2.1.1<br/>(2015.06.03)|유니티에 대한 3D 및 비디오 광고 지원<br>프리로드 관련 이벤트 핸들러 적용<br>비디오 및 3D 광고 관련 캐시 디렉토리 변경<br>필수 이미지 적용|
|4.2.1.0<br/>(2015.05.30)|서버 지연 발생시 비동기 및 웹뷰에 영향을 끼치는 부분 수정<br>3D 광고 관련 프리로드 적용|
|4.2.0.2<br/>(2015.05.22)|3D 광고 관련 NullPointerException 예외상황 수정|
|4.2.0.1<br/>(2015.05.20)|비디오 띠배너에서 영상이 오버레이되는 현상 수정<br>3D 광고 전면배너에서 간혈적으로 깜박임 발생 관련 수정<br>Wifi 상태 체크 관련 기능 추가(비디오, 3D 광고)|
|4.2.0.0<br/>(2015.05.15)|3D 광고상품 출시(전면광고)<br>광고 플랫폼의 라이브러리 업데이트<br/>Cauly(3.3.21), T-ad(3.10.1), ShallweAd(2.6.1), Inmobi(4.5.3), AmazoneMobileAds(5.5.149), Admixer(1.3.4)|
|4.1.1.0<br/>(2015.05.08)|Service 에서 연동시 발생하는 오류 수정(4.1.0.6 이하의 버전을 쓰는 경우에는 최신버전으로 업데이트 진행해주시기 바랍니다.)|
|4.1.0.9<br/>(2015.04.03)|타플랫폼(구글 애드몹 등) 미디에이션의 하나로 애드립 사용 가능(띠, 전면 모두 사용가능)<br>샘플 프로젝트의 test.adlib.project.custom 패키지 참고|
|4.1.0.7<br/>(2015.03.20)|단말의 최신 크롬브라우저(v40)에서 구글플레이 스토어로 이동안되는 오류 수정|
|4.1.0.6<br/>(2015.03.18)|동영상 전면광고의 로딩바 추가<br>웹뷰에서 흰색 배경 표출되는 오류 수정|
|4.1.0.5<br/>(2015.03.17)|타플랫폼 연동시 띠배너의 비디오뷰 사라지지 않는 현상 수정<br>비디오뷰에서 영상 플레이 안되는 현상 수정<br>웹뷰에서 광고 노출에 대한 개선|
|4.1.0.4<br/>(2015.03.11)|띠배너의 광고뷰(비디오) 동적 생성시 깜빡임 현상 개선|
|4.1.0.3<br/>(2015.03.11)|동영상 광고 타깃 안드로이드 버전 변경(Android 4.0(ICS)이상)|
|4.1.0.2<br/>(2015.03.10)|심플 애드립 관련(AdlibAdView 추가)|
|4.1.0.1<br/>(2015.03.10)|동영상 광고 타깃 안드로이드 버전 변경(Android 4.1(젤리빈)이상)|
|4.1.0.0<br/>(2015.03.10)|동영상 광고 플랫폼 적용|
|4.0.1.6<br/>(2015.01.16)|proguard 버전 변경 적용|
|4.0.1.5<br/>(2015.01.13)|광고 플랫폼/SubView 추가(Admixer)|
|4.0.1.4<br/>(2015.01.06)|Cauly, inmobi, AmazonMobileAds, U+AD, Mopub의 SubView Update로 최신 SDK를 지원.|
|4.0.1.3<br/>(2014.12.03)|T-ad SubView Update(최신 SDK(v3.8.1)를 지원)|
|4.0.1.3<br/>(2014.11.17)|유니티 전면배너 프리로드 실패에 대한 처리|
|4.0.1.2<br/>(2014.11.10)|4.0.1.1 버그 수정(유니티에서 타플랫폼의 광고 표출이 안되는 오류 수정)|
|4.0.1.1<br/>(2014.11.06)|4.0.1.0 버그 수정(일부 앱의 광고 클릭시 ANR 발생 현상에 대한 해결)|
|4.0.1.0<br/>(2014.11.04)|애드립 전면배너 프리로드 기능 추가<br>유니티 관련 프로젝트를 샘플 프로젝트에 통합(유니티 가이드 참고)<br>일반 플랫폼 추가(Mobfox, Mopub)<br>리포팅 기능 추가(Subview Update)(Admob, Inmobi, Millennial Media, Mobfox, Mopub)|
|4.0.0.3<br/>(2014.09.15)|앱 종료시 애드립외 플랫폼의 전면배너 표출 안되는 현상 수정|
|4.0.0.2<br/>(2014.09.11)|일반 플랫폼 추가(Mediba Ad)<br>기타 버그 수정|
|4.0.0.1<br/>(2014.09.05)|Millennial Media SubView Update(최신 SDK(v5.3.0)를 지원)<br/>Millennial Media 전면광고 지원|
|4.0.0.0<br/>(2014.07.25)|Google Advertising ID, 영구적인 기기식별자(WIFI MAC, ANDROID_ID, IMEI 등) 불필요한 퍼미션을 제거|
|3.9.8<br/>(2014.07.25)|Google Advertising ID 를 적용(Google Play Services SDK 사용)|
|3.9.7<br/>(2014.07.23)|ad@m SubView Update(최신 SDK(v2.3.0)를 지원)<br>Amazon SubView Update(최신 SDK(v5.4.78)를 지원)|
|3.9.7<br/>(2014.07.03)|T-ad SubView Update(최신 SDK(v3.5.3.6)를 지원)|
|3.9.7<br/>(2014.06.30)|리워드 배너 관련 기능이 개선(슬라이더 관련 배너 사용시 동작이 가능)<br>NaverAdPost SubView Update(광고 뷰로 2개 이상의 Activity 사용 중 전환 과정에서 발생하는 자동 업데이트 멈춤 현상을 수정)|
|3.9.6<br/>(2014.06.10)|Cauly SubView Update(최신 SDK(v3.2.4)를 지원)|
|3.9.6<br/>(2014.05.26)|Cauly SubView Update(Cauly만 설정하여 사용시 onResume에 광고가 갱신이 되지 않는 문제 수정)|
|3.9.6<br/>(2014.05.14)|T-ad SubView Update(최신 SDK(v3.4.3.6)를 지원)|
|3.9.6<br/>(2014.05.06)|NullPointer Exception 관련 예외처리|
|3.9.5<br/>(2014.02.18)|UnityPlugin Update(t-ad, inmobi 전면배너 지원)|
|3.9.5<br/>(2014.02.17)|일부 OS에서 팝배너 이미지로딩 오류가 있어 수정|
|3.9.4<br/>(2014.02.10)|팝 배너에서 발생하는 비트맵 관련 오류를 수정|
|3.9.3<br/>(2014.02.07)|유니티 플러그인 지원을 위해 jar에 포함된 리소스 제거|
|3.9.2<br/>(2014.01.03)|애드립 팝 배너가 close 될 때 특정 디바이스에서 앱이 죽는 에러 수정|
|3.9.1<br/>(2014.01.02)|애드립 팝 배너 관련 오류가 수정|
|3.9.0<br/>(2013.12.26)|애드립 팝 서비스를 지원(관련 서비스 내용은 공지 참고)<br>Full Size의 애드립 전면광고를 지원|
|3.8.1<br/>(2013.12.23)|T-ad SubView Update(최신 SDK(v3.3.3.6)를 지원)|
|3.8.1<br/>(2013.12.13)|NullPointer Exception 관련 예외처리|
|3.8.0(2013.12.03)|SamsungAdHub SubView Update(최신 SDK(v3.0.0)를 지원)|
|3.8.0<br/>(2013.11.26)|리치미디어 광고를 지원<br/>비디오 광고를 지원(AndroidManifest.xml에 Activity 추가 필요)|
|3.7.1<br/>(2013.11.23)|SubView 추가(Samsung AdHub)|
|3.7.1<br/>(2013.11.14)|일반 플랫폼 추가(Amazon Mobile Ads)<br/>Amazon SubView 추가|
|3.7.a<br/>(2013.11.13)|cauly SubView Update(일부 전면광고에 대해 리스너를 받아오지 못하는 문제 수정)|
|3.7.a<br/>(2013.11.06)|일반 플랫폼 추가(MezzoMedia MAN)<br>Mezzo SubView 추가|
|3.7.a<br/>(2013.10.28)|NullPointer Exception 관련 예외처리|
|3.7.0<br/>(2013.10.07)|전면광고 스케줄링 기능 추가(현재 adam, admob, cauly 지원 - 관련 subview update 필요) <br>shallwead SubView Update|
|3.6.3<br/>(2013.09.15)|광고 초기 로딩전의 하우스배너 노출 여부를 설정할 수 있게 수정(자세한 설명은 애드립 매뉴얼 9번째, 11번째 슬라이드 참고)|
|3.6.2<br/>(2013.09.11)|일반 플랫폼 추가(U+AD)<br>U+AD SubView 추가|
|3.6.2<br/>(2013.09.04)|adpost SubView Update<br>첫 로딩 때 지구모양 배너가 보이지 않게 수정|
|3.6.2<br/>(2013.08.31)|SubView 추가(admob-eCPM floor(v3.6.2 부터 사용 가능))|
|3.6.2<br/>(2013.08.29)|광고 초기 로딩 속도 개선<br>리워드링크 노출 방식이 보다 최적화<br>admob-ecpm floor 네트워크가 추가(SubView 업데이트 예정)<br>admob SubView Update|
|3.6.0<br/>(2013.08.24)|일반 플랫폼 추가(mobclix(v3.6 부터 사용 가능합니다.))|
|3.6.0<br/>(2013.08.02)|background request를 지원하지 않는 플랫폼도 광고 스위칭이 매끄러워졌습니다<br/>리워드 배너 노출 최적화<br>다양한 글로벌 네트워크 추가(SubView 업데이트 예정)<br>모든 SubView Update(반드시 모든 SubView 업데이트 필요)|
|3.5.6<br/>(2013.07.30)|일부 디바이스에서 WebView의 JavascriptInterface method를 찾지 못하는 에러를 수정|
|3.5.5<br/>(2013.07.19)|전면광고 관련 API를 제공<br>일반 플랫폼 추가(MillenialMedia)|
|3.5.0<br/>(2013.06.24)|리워드 링크 관련 오류 수정<br>전면광고를 지원(곧 오픈 예정)|
|3.4.0<br/>(2013.06.18)|모든 SubView Update(리스너 응답 지연으로 플랫폼 전환시 광고 호출을 pause 시키도록 수정)|
|3.4.0<br/>(2013.06.14)|리워드 배너 개선 및 내부적인 성능 향상으로 스케줄링이 최적화<br>SMART* dialog의 백버튼 종료를 막아 미처 보지 못하고 닫아버리는 문제를 방지|
|3.3.a<br/>(2013.06.07)|cauly SubView Update(광고뷰가 특정시간 이상 노출되어있지 않으면 갱신이 되지않아 뷰가 사라질때 destroy를 시키도록 수정)|
|3.3.a<br/>(2013.05.24)|리워드 링크의 NullPointer Exception 관련 예외처리 추가|
|3.3.0<br/>(2013.05.23)|admob SubView Update(ad.stopLoading() 이 간헐적으로 모든 WebView를 멈추는 문제가 있어 제거)|
|3.3.0<br/>(2013.05.22)|리워드 링크 서비스를 지원(관련 서비스 내용은 공지 예정)<br>고해상도 디바이스에서 SMART* dialog의 사이즈 오류 수정|
|3.2.2<br/>(2013.05.06)|모든 SubView Update(리스너 응답이 지연될 경우 빠르게 다음 플랫폼으로 전환)|
|3.2.2<br/>(2013.04.01)|내부 브라우저 최적화<br/>SMART* dialog의 노출횟수를 daily 단위로 설정할 수 있게 수정|
|3.2.1<br/>(2013.03.05)|NullPointer Exception 관련 예외처리 추가|
|3.2.0<br/>(2013.02.09)|리워드 배너 서비스를 지원(관련 서비스 내용 공지 예정)|
|3.1.2<br/>(2013.01.28)|Activity의 생명주기에 따라 생성했던 모든 SubView의 status method를 호출<br/>on background(WebViewCoreThread cpu 문제 관련 방어코드 추가)|
|3.1.1<br/>(2013.01.21)|내부 브라우저 탑재로 광고 클릭시 빠른 전환이 가능<br/>사용자 배너의 기존 애드립 로고의 크기 및 모양이 변경(Manifest.xml 을 참조하여 변경내용 반영 필요-Activity 추가)|
|3.1.0<br/>(2013.01.09)|수익 극대화를 위해 설정한 CPC 단가 이상의 제휴사 광고가 우선 노출될 수 있도록 기능 추가<br/>사용자 광고를 포함한 caching 동작이 최적화<br/>플랫폼별 스위칭 알고리즘이 개선되어 보다 효율적인 노출 가능<br/>시작시 사용자 전면 광고 및 Dialog 노출 기능 개선|
|3.0.1<br/>(2012.12.14)|SMART* mediation / dialog 동작이 최적화<br/>각 플랫폼의 SubView 동작이 최적화|
|3.0.0<br/>(2012.11.18)|SMART* dialog / SMART* AD 업데이트|
|2.5.0.a<br/>(2012.09.25)|추가 리포트 기능을 지원(누적 사용자, 활성 사용자수, 일별 사용자수)|
|2.5.0<br/>(2012.09.19)|Earning Maximizer 기능 지원<br/>제휴 플랫폼의 스케줄을 자동으로 최적화하여 수익 극대화|
|2.3.5<br/>(2012.08.23)|광고효과 리포트 통계 개선<br/>액티비티별 스케줄링 문제 패치|
|2.3.0<br/>(2012.08.12)|여러개의 사용자 배너 스케줄링 지원|
|2.2.2<br/>(2012.08.09)|동적 AdlibManager 생성/해제 최적화|
|2.2.1<br/>(2012.08.05)|ShallWeAd 지원<br>사용자배너 일부 버그 수정|
|2.2.0<br/>(2012.07.24)|일부 ICS 단말기 OPENGL 관련 오류 수정<br/>내부 광고 스위칭 알고리즘 개선(모든 SubView 업데이트 필요)|
|2.1.0<br/>(2012.06.27)|일반 플랫폼 추가(NaverAdPost)|


# ADLib AOS SDK 적용 가이드

애드립 AOS SDK를 사용하여 애드립 광고를 노출하는 방법을 제공합니다.<br/>또한 기타 광고 플랫폼을 사용하여 미디에이션 기능을 사용하는 방법을 제공합니다.

## 미디에이션 플랫폼 SDK 지원

각 플랫폼의 SDK 버전은 API call 방식이 변경되지 않는한 기존의 애드립 SubView를 통해 상위, 하위 호환이 가능합니다.
실제로 각 플랫폼의 마이너 버전 업데이트가 일어나더라도 애드립 구현부의 변경없이 업데이트 된 SDK 만 새롭게 적용하면 그대로 사용가능하며, 최소한의 구현부 수정으로 큰 수정없이 적용할 수 있도록 구성되었습니다.


|플랫폼|버전|SDK 다운로드|
|---|:---:|:---:|
|AdMixer|v1.4.5|<a href="http://www.admixer.co.kr/" target="_blank">다운로드</a>|
|Adfit(Adam)|v3.0.4|<a href="https://github.com/adfit/adfit-android-sdk" target="_blank">다운로드</a>|
|Admob|Google Play Version|<a href="https://developers.google.com/admob/android/quick-start" target="_blank">다운로드</a>|
|Amazon|v5.8.2|<a href="https://developer.amazon.com/public/resources/development-tools/sdk" target="_blank">다운로드</a>|
|Axonix(Mobclix)|v4.4.0|<a href="https://github.com/AxonixRTB/axonix-android-sdk-releases" target="_blank">다운로드</a>|
|Cauly|v3.4.21|<a href="https://github.com/cauly/Android-SDK" target="_blank">다운로드</a>|
|FaceBook|v4.99.0|<a href="https://developers.facebook.com/docs/audience-network/getting-started?locale=ko_KR" target="_blank">다운로드</a>|
|Inmobi|v7.1.1|<a href="http://www.inmobi.com/products/sdk/" target="_blank">다운로드</a>|
|MillennialMedia|v6.8.1|<a href="http://docs.onemobilesdk.aol.com/android-ad-sdk/" target="_blank">다운로드</a>|
|Mobfox|v3.6.0|<a href="https://github.com/mobfox/MobFox-Android-SDK" target="_blank">다운로드</a>|
|Mopub|v5.0|<a href="https://developers.mopub.com/docs/android/getting-started/" target="_blank">다운로드</a>|
|ShallWeAd|v1.9_20180612|<a href="http://www.shallweadcorp.com/main/developer_01?pageNum=2&subNum=1" target="_blank">다운로드</a>|
|T-ad(Syrup Ad/Dawin Click)|v3.16.3|<a href="http://www.syrupad.co.kr/publisher/guide_sdk" target="_blank">다운로드</a>|
|TNK|v6.42|<a href="http://docs.tnkad.net/tnk-ad-sdk/sdk-guide" target="_blank">다운로드</a>|


## 개발환경
- 최소 SDK Version : Android 11
- Compile SDK : Android 22 이상
- Build Tool : Android Stdio 권장

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

자세한 구현 내용은 샘플 프로젝트의 `proguard.cfg ` 파일 또는 [proguard-rules.pro](./app/proguard-rules.pro) 참고해 주세요.
