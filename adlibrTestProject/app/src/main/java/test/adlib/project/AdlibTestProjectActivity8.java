package test.adlib.project;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mocoplex.adlib.AdlibConfig;
import com.mocoplex.adlib.AdlibManager;
import com.mocoplex.adlib.platform.nativeads.AdlibNativeAdListener;
import com.mocoplex.adlib.platform.nativeads.AdlibNativeHelper;
import com.mocoplex.adlib.platform.nativeads.AdlibNativeItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import test.adlib.project.util.bitmap.ImageCache;
import test.adlib.project.util.bitmap.ImageFetcher;

public class AdlibTestProjectActivity8 extends Activity {

    // 일반 Activity 에서의 adlib 연동
    private AdlibManager _amanager;

    private ArrayList<Object> mList = new ArrayList<Object>();

    private ListView listView;
    private Adapter listAdapter;

    // 광고 뷰 표출에 도움을 주는 클래스
    private AdlibNativeHelper anh = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main8_list);

        initSampleFeedData();

        listView = (ListView) findViewById(R.id.list);

        listAdapter = new Adapter(this, mList);
        listView.setAdapter(listAdapter);

        anh = new AdlibNativeHelper(this, listView);

        AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                anh.onScrollStateChanged(view, scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                anh.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        };

        listView.setOnScrollListener(scrollListener);

        // 각 애드립 액티비티에 애드립 앱 키값을 필수로 넣어주어야 합니다.
        _amanager = new AdlibManager(AdlibTestProjectConstants.ADLIB_API_KEY);
        _amanager.onCreate(this);
        // 테스트 광고 노출로, 상용일 경우 꼭 제거해야 합니다.
        _amanager.setAdlibTestMode(AdlibTestProjectConstants.ADLIB_TEST_MODE);

        // 네이티브 광고를 받고싶다면 아래의 코드를 호출 합니다.
        // 1. 타입 : 광고 유형을 지정합니다. (ALL : 비디오와 이미지, VIDEO : 비디오만 해당, IMAGE : 이미지만 해당)
        // 2. 이벤트 리스너 : 성공과 실패를 알기 위한 리스너를 지정합니다.

        _amanager.loadNativeAd(AdlibConfig.ContentType.VIDEO, new AdlibNativeAdListener() {
            @Override
            public void onReceiveAd(AdlibNativeItem item) {
                Log.d("ADLIB-Native", "onReceiveAd");

                // 광고를 수신하면 리스트에 넣습니다.
                // Log.d("ADLIB-Native", i + " -> Content Type : " + item.getContentType());
                // Log.d("ADLIB-Native", i + " -> Title : " + item.getTitle());
                // Log.d("ADLIB-Native", i + " -> SubTitle : " + item.getSubTitle());
                // Log.d("ADLIB-Native", i + " -> Icon Url : " + item.getIconUrl());
                // Log.d("ADLIB-Native", i + " -> Description : " + item.getDescription());
                // Log.d("ADLIB-Native", i + " -> GoButton Text : " + item.getBtnText());
                if (item != null) {
                    mList.add(mList.size() / 2, item);
                    listAdapter.notifyDataSetChanged();
                    anh.update();
                }
            }

            @Override
            public void onError(int errorCode) {
                Log.d("ADLIB-Native", "onError ::: error code : " + errorCode);

                Toast.makeText(AdlibTestProjectActivity8.this, "광고수신 실패 :)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        _amanager.onResume(this);
        anh.onResume();
        super.onResume();
    }

    protected void onPause() {
        _amanager.onPause(this);
        anh.onPause();
        super.onPause();
    }

    protected void onDestroy() {
        _amanager.onDestroy(this);
        anh.onDestroy();
        super.onDestroy();
    }

    private void initSampleFeedData() {
        try {
            JSONObject json = new JSONObject(loadJSONFromAsset());
            JSONArray arr = json.getJSONArray("feed");
            int size = arr.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = arr.getJSONObject(i);
                sampleFeedData child = new sampleFeedData(obj);
                mList.add(child);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("sample_feed.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    private class sampleFeedData {
        private int id;
        private String name;
        private String img;
        private String status;
        private String profilePic;
        private String timeStamp;
        private String url;
        private int width;
        private int height;

        public sampleFeedData(JSONObject obj) {
            init(obj);
        }

        private void init(JSONObject obj) {
            try {
                id = obj.getInt("id");
                name = obj.getString("name");
                img = obj.getString("image");
                status = obj.getString("status");
                profilePic = obj.getString("profilePic");
                timeStamp = obj.getString("timeStamp");
                url = obj.getString("url");
                width = obj.getInt("width");
                height = obj.getInt("height");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public String getStatus() {
            return status;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public String getUrl() {
            return url;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public class Adapter extends BaseAdapter {

        private ArrayList<Object> mList = new ArrayList<Object>();

        // 이미지 로딩을 위한 안드로이드 오픈 소스 사용
        private final String IMAGE_CACHE_DIR = "images";
        private ImageFetcher imageFetcher;
        // 이미지 로딩을 위한 안드로이드 오픈 소스 사용

        // ---------- 리스트 아이템의 레이아웃 재정의 ---------- //

        private final int VIEW_TYPE = 0;
        private final int VIEW_TYPE_AD = 1;

        @Override
        public int getItemViewType(int position) {

            if (mList.get(position) instanceof AdlibNativeItem) {
                // 광고 타입을 추가합니다.
                return VIEW_TYPE_AD;
            } else {
                return VIEW_TYPE;
            }
        }

        @Override
        public int getViewTypeCount() {
            int viewTypeCount = super.getViewTypeCount();
            return viewTypeCount + 1; // 기존 레이아웃의 타입에 1을 더합니다.
        }

        // ---------- 리스트 아이템의 레이아웃 재정의 ---------- //

        public Adapter(Context context, ArrayList<Object> objects) {
            mList = objects;

            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(context, IMAGE_CACHE_DIR);
            cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory
            imageFetcher = new ImageFetcher(context, dm.widthPixels, dm.heightPixels);
            imageFetcher.addImageCache(cacheParams);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int location) {
            return mList.get(location);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int type = getItemViewType(position);

            if (type == VIEW_TYPE) {

                // 기본 뷰에 대해 처리를 합니다.
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.main8_item_sample, null);
                }
                sampleFeedData data = (sampleFeedData) mList.get(position);
                ImageView profileImg = (ImageView) convertView.findViewById(R.id.profilePic);
                loadImage(data.getProfilePic(), profileImg, 100, 100);

                ImageView mainImg = (ImageView) convertView.findViewById(R.id.img);
                loadImage(data.getImg(), mainImg, data.getWidth(), data.getHeight());

                TextView nameTxt = (TextView) convertView.findViewById(R.id.name);
                nameTxt.setText(data.getName());

                TextView statusTxt = (TextView) convertView.findViewById(R.id.status);
                statusTxt.setText(data.getStatus());

            } else if (type == VIEW_TYPE_AD) {

                // 광고 뷰에 대해 처리를 합니다.
                AdlibNativeItem item = (AdlibNativeItem) mList.get(position);
                return anh.getView(convertView, item, R.layout.main8_item);
            }

            return convertView;
        }

        // 이미지 로딩을 위한 안드로이드 오픈 소스 사용
        private void loadImage(String url, ImageView view, int width, int height) {

            if (imageFetcher != null) {
                imageFetcher.loadImage(url, view);
            }
        }
    }
}