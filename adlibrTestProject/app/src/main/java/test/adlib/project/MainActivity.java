package test.adlib.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mocoplex.adlib.AdlibConfig;

import java.util.ArrayList;

import test.adlib.project.banner.AdlibBannerDynamicActivity;
import test.adlib.project.banner.AdlibBannerMediationActivity;
import test.adlib.project.interstitial.AdlibIntersDynamicActivity;
import test.adlib.project.interstitial.AdlibIntersMediationActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();

        bindPlatform();
    }

    private void initLayout() {

        ArrayList<AdlibTestListItem> itemList = new ArrayList<AdlibTestListItem>();

        // 띠배너 샘플
        itemList.add(AdlibTestListItem.BANNER_HEADER);
        itemList.add(AdlibTestListItem.BANNER_MEDIATION);
        itemList.add(AdlibTestListItem.BANNER_DYNAMIC);

        // 전면배너 샘플
        itemList.add(AdlibTestListItem.INTERSTITIAL_HEADER);
        itemList.add(AdlibTestListItem.INTERSTITIAL_MEDIATION);
        itemList.add(AdlibTestListItem.INTERSTITIAL_DYNAMIC);

        AdlibTestListAdapter adapter = new AdlibTestListAdapter(this, itemList);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;

                AdlibTestListItem item = (AdlibTestListItem) view.getTag();
                switch (item) {
                    case BANNER_MEDIATION:
                        intent = new Intent(MainActivity.this, AdlibBannerMediationActivity.class);
                        break;
                    case BANNER_DYNAMIC:
                        intent = new Intent(MainActivity.this, AdlibBannerDynamicActivity.class);
                        break;

                    case INTERSTITIAL_MEDIATION:
                        intent = new Intent(MainActivity.this, AdlibIntersMediationActivity.class);
                        break;
                    case INTERSTITIAL_DYNAMIC:
                        intent = new Intent(MainActivity.this, AdlibIntersDynamicActivity.class);
                        break;
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    public class AdlibTestListAdapter extends BaseAdapter {
        private final int HEADER = 0;
        private final int ITEM = 1;

        private Context context;
        private ArrayList<AdlibTestListItem> list;

        public AdlibTestListAdapter(Context context, ArrayList<AdlibTestListItem> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {

            if (list.get(position).isHeader()) {
                return HEADER;
            } else {
                return ITEM;
            }
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AdlibTestListItem data = list.get(position);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(getItemViewType(position) == HEADER ? R.layout.main_list_header : R.layout.main_list_item, null);
            }

            TextView txtItem = (TextView) convertView.findViewById(R.id.text);
            txtItem.setText(data.getValue());
            convertView.setTag(data);
            return convertView;
        }
    }

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
}
