package com.carey.guide;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.robot.carey.robotactivity.RobotActivity;

import org.json.JSONObject;

public class q4 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q4);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"多媒體資料是如何排列的呢？","個人的包包為何不能攜帶進入多媒體區？","為什麼不能拿自己的片子到五樓多媒體區觀賞呢？","五樓多媒體區的資料如何查詢？","五樓休閒圖書區的休閒書是否可以外借呢？"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("圖書館的多媒體資料是先按照資料類型(DVD、CD…等)分區典藏，再依照分類號以及條碼號的先後次序做排列的，建議在找多媒體資料前先以「館藏查詢系統」查得索書號，這種方法將有效且快速的找到所需要的資料。");
                        break;
                    case 1:
                        robotAPI.robot.speak("由於本區座位過於擁擠，且為確保館內設備及資料的完整性，因此多媒體區禁止攜帶包包進入。");
                        break;
                    case 2:
                        robotAPI.robot.speak("根據著作權法明令規定，圖書館為公共場所，所使用之資料需為”公開播映版”，而一般讀者所購買之資料則大都屬家用版，所以若讀者拿自己的資料到五樓多媒體區來觀賞，恐有觸犯法律之嫌。因此多媒體區不提供讀者使用本區設備，來觀賞自己攜帶的片子。");
                        break;
                    case 3:
                        robotAPI.robot.speak("1. 讀者可到圖書館的網站上查詢，先到「查找資料」→點選「館藏查詢」系統，讀者可於「書名/書刊名」中鍵入想要查詢的資料名稱→再於限制查詢條件的下拉選單中點選「多媒體資料」進行查詢。" +
                                "2. 讀者在五樓多媒體區時可根據架上側面封板的類別標示來尋找所需資料，或可直接請五樓多媒體區服務人員幫忙。");
                        break;
                    case 4:
                        robotAPI.robot.speak("休閒圖書區提供讀者休閒及熱門的坊間讀物，因此本區陳列之書籍以書店的暢銷書為主。為了讓每位讀者都可以充份享受到本館最即時的服務，因此本區休閒書是不外借的。");
                        break;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        robotAPI.robot.setExpression(RobotFace.HIDEFACE);

    }

    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);
        }

        @Override
        public void initComplete() {
            super.initComplete();

        }
    };

    public static RobotCallback.Listen robotListenCallback = new RobotCallback.Listen() {
        @Override
        public void onFinishRegister() {

        }

        @Override
        public void onVoiceDetect(JSONObject jsonObject) {

        }

        @Override
        public void onSpeakComplete(String s, String s1) {

        }

        @Override
        public void onEventUserUtterance(JSONObject jsonObject) {

        }

        @Override
        public void onResult(JSONObject jsonObject) {

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };




    public q4() {
        super(robotCallback, robotListenCallback);
    }


}
