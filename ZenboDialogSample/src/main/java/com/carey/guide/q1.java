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

public class q1 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q1);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"登錄個人借閱紀錄的密碼忘了，怎麼辦？","為何只能預約3本書？","我已預約了圖書，請問系統顯示「3之4預約」是什麼意思？"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("系統原先預設的密碼為讀者之個人身分證號，但讀者可利用個人紀錄中更改登入密碼(修改密碼)功能自行修改。 為保障讀者個人隱私，於圖書館管理端看不到讀者個人密碼。如果您忘記密碼，需本人持證件親自到圖書館二樓借還書櫃檯，或寄信至rs@cyut.edu.tw，由服務人員為您重新設定一組密碼，之後再請您自行上網做密碼修改。");
                        break;
                    case 1:
                        robotAPI.robot.speak("為了避免讀者預約後不取書，同時縮短其他讀者等待的時間，本館訂有預約書的冊數上限。");
                        robotAPI.robot.speak("為增進預約書流通率，及降低「預約不取書」情形以維護讀者權益，每人可預約冊數為3冊。");
                        break;
                    case 2:
                        robotAPI.robot.speak("系統顯示的「3之4預約」表示該書預約者目前共有4人，您目前為第3名預約者。待借閱者歸還書後，圖書館將於隔天寄發通知信件至你的E-mail信箱，屆時請你持學生證至圖書館借還書櫃檯取書即可。");
                        break;

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //robotAPI.robot.setExpression(RobotFace.HIDEFACE);

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




    public q1() {
        super(robotCallback, robotListenCallback);
    }


}
