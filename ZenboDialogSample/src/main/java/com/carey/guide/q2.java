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

public class q2 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q2);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"為什麼圖書到館的時間需要花費那麼久？","接受各系所圖書推薦時間為何？","如何取得每學年度各系所推薦採買入館的書單？","為什麼各系所推薦圖書館購買的書目資料一定要有ISBN？","為什麼各系所推薦書單時要排列出採購順序？"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("蒐集：請各系所推薦書單(依各系所處理時間)--各學院彙整送圖書資訊處進行複本查核(查核完送複本報表給系所，複本書基本上不再訂購，若系所欲增訂仍送估價。)(3-4星期工作天)--送圖書代理商估價(3-4星期工作天)。");
                        robotAPI.robot.speak("估價：核對代理商之估價與原始書目資料是否有誤(3個工作天)，另查核系所經費是否足夠，再彙整估價單進行經費動支申請。(2~4星期工作天)");
                        robotAPI.robot.speak("訂書：已核定案通知代理商訂書，西、日文書: 訂書及分編處理等需[3-6個月工作天]，中文書：訂書及分編處理等需[2-4個月工作天]");
                        robotAPI.robot.speak("到書：新書到館後點收並驗證(1-3星期工作天)，無誤後即可上架供全校師生使用。故從接受各系所推薦圖書至書能採買到進入館藏，所需作業時間甚多。");
                        break;
                    case 1:
                        robotAPI.robot.speak("通常為每年的5月、12月通知各系所進行當學年的第1、2次圖書推薦，以利採購作業的進行。");
                        break;
                    case 2:
                        robotAPI.robot.speak("請由「本館首頁➔查找資料➔系所薦購查詢➔薦購到館清單」輸入查詢值即可查詢截至目前為止的新到館館藏書目資料。");
                        break;
                    case 3:
                        robotAPI.robot.speak("每一本書都有作者、出版地、出版年、出版者、ISBN(國際標準書號)等描述圖書的基本資料。ISBN有10碼及13碼，當發生書名相同，作者、出版地…不同的時候，就可以用ISBN來加以區別。系所推薦書單若能附上ISBN資料，在進行採購作業時，就能精確分辨出老師真正要推薦的書。");
                        break;
                    case 4:
                        robotAPI.robot.speak("排列圖書採購的優先順序，圖書館採購人員便能即時購置系所推薦的專業圖書，及掌控各系所經費的運用。");
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




    public q2() {
        super(robotCallback, robotListenCallback);
    }


}
