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

public class q9 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q9);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"什麼是「圖書急編服務」？","申請圖書急編服務，多久可拿到書？","圖書的「處理狀態」，若為「移送讀服」，可申請急編服務嗎？"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("透過書目資料庫查詢，凡已進館的資料，若該資料的「處理狀態」為「編目處理中」，即可利用「圖書急編服務」申請單，透過電子郵件，申請優先處理。");
                        break;
                    case 1:
                        robotAPI.robot.speak("1. 接到讀者申請時，如無其他因素，將儘可能於1個工作天內處理完成，使讀者可很快的獲取資料。若經3個工作天仍未能處理完成將主動告知讀者原因。" +
                                "2. 讀者服務組會將分編好的資料設定為讀者的預約書，並通知讀者取書。讀者亦可自行查詢館藏查詢系統，若處理狀況已由「編目處理中」改為「可外借」，表示所欲借的資料已完成編目，可逕至二樓借還書服務台借閱。如為不外借之館藏，則可逕至該筆資料之典藏地取閱。");
                        break;
                    case 2:
                        robotAPI.robot.speak("書籍的「處理狀態」為「移送讀服」，不適用此服務，請逕洽讀者服務組，校內分機3151或3152。");

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




    public q9() {
        super(robotCallback, robotListenCallback);
    }


}
