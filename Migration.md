# ADLIB SDK Repackaging 😊

## 변동 사항

- Jar 형태로만 제공 하던 SDK 를 AAR 형태로 제공(AAR, JAR 모두 제공)
- Maven Repository 를 통한 연동
- 패키지 이름의 변동
  - com.mocoplex.adlid => com.nhnace.adlid
- 버전 변경
  - 이전 버전 5.1.5 -> 1.0.2 으로 변경

## Package 이름 변경에 따른 유의할 사항들

### * Adlib 에서 제공하는 Class 를 import 하는경우

- import 하는 AdlibManager, SubAdlibAdViewCore 두 클래스 경로 변경
- 변경전
  ```
     import com.mocoplex.adlib.AdlibManager;
     import com.mocoplex.adlib.SubAdlibAdViewCore;
  ```
- 변경후
   ```
      import com.nhnace.adlib.AdlibManager;
      import com.nhnace.adlib.SubAdlibAdViewCore;
  ```
    
### * xml 로 Adlib의 뷰를 정의 하는 고객사
- 변경전
  ```
    <!-- adlib adview -->
      <com.mocoplex.adlib.AdlibAdViewContainer
          android:id="@+id/ads"
          android:layout_width="match_parent"
          android:layout_height="50dp"/>
  ```

- 변경후
  ```
    <!-- adlib adview -->
      <com.nhnace.adlib.AdlibAdViewContainer
          android:id="@+id/ads"
          android:layout_width="match_parent"
          android:layout_height="50dp"/>
  ```
  
### * Manifest 에 Adlib Activity 가 선언이 되어 있는 경우
  - 변경전
  ```
        <!-- 애드립 사용을 위해 꼭 추가해주세요. -->
        <activity
            android:name="com.mocoplex.adlib.AdlibDialogActivity"
            android:configChanges="orientation|screenSize|keyboard|keyboardHidden"
            android:theme="@android:style/Theme.Translucent"/>
        <!-- 애드립 사용을 위해 꼭 추가해주세요. -->
  ```

  - 변경후
    ```
          <!-- 애드립 사용을 위해 꼭 추가해주세요. -->
          <activity
              android:name="com.nhnace.adlib.AdlibDialogActivity"
              android:configChanges="orientation|screenSize|keyboard|keyboardHidden"
              android:theme="@android:style/Theme.Translucent"/>
          <!-- 애드립 사용을 위해 꼭 추가해주세요. -->
    ```

### * 미디에이션을 사용하는 경우
- 아래 클래스를 작성 하고 사용하는 경우
  - SubAdlibAdViewAdam.java
  - SubAdlibAdViewAdmixer.java
  - SubAdlibAdViewAdmob.java
  - SubAdlibAdViewAmazon.java
  - SubAdlibAdViewCauly.java
  - SubAdlibAdViewFacebook.java
  - SubAdlibAdViewInmobi.java
  - SubAdlibAdViewMezzo.java
  - SubAdlibAdViewMMedia.java
  - SubAdlibAdViewMobfox.java
  - SubAdlibAdViewTAD.java
  - SubAdlibAdViewTNK.java
- 변경전
  ```
     import com.mocoplex.adlib.AdlibManager;
     import com.mocoplex.adlib.SubAdlibAdViewCore;
  ```
- 변경후
   ```
      import com.nhnace.adlib.AdlibManager;
      import com.nhnace.adlib.SubAdlibAdViewCore;
  ```
### * proguard-rules.pro 내 Adlib 관련 설정 수정  
- 변경전
  ```
  -keep class com.mokoplex.adlib.** { *; }
  -dontwarn com.mokoplex.adlib.**
  ```
- 변경후
   ```
   -keep class com.nhnace.adlib.** { *; }
   -dontwarn com.nhnace.adlib.**
  ```

### * 간단 요약
```
* import할 때 경로 mokoplex -> nhnace 로 변경
* xml 로 뷰 정의 할때 com.mocoplex.adlib.AdlibAdViewContainer -> com.nhnace.adlib.AdlibAdViewContainer
* Manifest Activity 정의시 com.mocoplex.adlib.AdlibDialogActivity -> com.nhnace.adlib.AdlibDialogActivity 
```
### * 초간단 요약
```
* mokoplex -> nhnace
```

## Gradle 이용한 Adlib SDK 연동

### 기존 방식
- Jar를 제공

### 변경 방식
- aar을 제공(Jar도 해당 Git 페이지에서 제공)
- ```
  // 프로젝트 수준의 build.gradle 파일에 아래 처럼  mavenCentral() 레포지토리 추가

    repositories {
        ...
        mavenCentral()
        ...
    } 
  
- ```
  // module 수준의 build.gradle 파일에 아래 처럼 선언
  
  dependencies {
        ...
        implementation "com.nhnace:adlib:${version}"
        ex) implementation "com.nhnace:adlib:$1.0.1"
        ...
  }
  

