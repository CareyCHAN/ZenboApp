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

public class q8 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q8);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"如何薦購圖書？","為什麼「圖書薦購系統」只能使用學校的E-mail？","為什麼沒有收到圖書薦購的回函通知？","薦購時有設定書採購入館後請協助預約，為什麼沒有幫我預約？","薦購期刊，才剛開學，卻收到「已無經費」通知？","請問薦購系統上薦購的圖書，多久會通知到館呢？"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("薦購圖書路徑：圖書館首頁>讀者服務>圖書薦購");
                        break;
                    case 1:
                        robotAPI.robot.speak("本薦購系統僅供在校教職員生薦購，故以學校的E-mail帳號來進行身份確認。");
                        break;
                    case 2:
                        robotAPI.robot.speak("原因一：鍵錯E-mail信箱被退件。例如： s9600123鍵成96001232。" +
                                "原因二：沒有收學校信箱或信箱爆滿被退件。");
                        break;
                    case 3:
                        robotAPI.robot.speak("1.所薦購之資料為休閒書區圖書或多媒體資料，恕不接受設定為預約書。" +
                                "2.如您的預約書已超過3冊，或有逾期未還圖書及罰款，將不會為您設定為預約書。");
                        break;
                    case 4:
                        robotAPI.robot.speak("期刊屬年度刊物，故經費在新的學年度開始前即已執行完畢，因此專業期刊可直接向系上反應需求，由系上在學年度的圖博經費中提列，若為一般期刊，則可列入下學年度參考。");
                        break;
                    case 5:
                        robotAPI.robot.speak("作業時間視資料取得管道而不同，中文書約需1至2個月，大陸書約需2至3個月、西文書約需2至4個月、視聽資料約需1至4個月。");
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




    public q8() {
        super(robotCallback, robotListenCallback);
    }


}
