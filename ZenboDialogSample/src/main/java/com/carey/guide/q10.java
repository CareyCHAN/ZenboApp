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

public class q10 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q10);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"請問隨時都可以捐書嗎?有規定一定要什麼樣的書才能捐嗎?","如何贈書給圖書館?"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("以下是收書原則：" +
                                "(一)符合智慧財產權者。" +
                                "(二)內容需符合本館館藏政策。" +
                                "(三)學位論文、技術報告及研討會論文集。" +
                                "(四)公播版之視聽資料。" +
                                "(五)珍本秘笈。" +
                                "(六)破損、掉頁、受潮或發霉、有蛀蟲之書不予蒐藏。" +
                                "(七)小至專科之教科書、參考自修、筆記、非本校畢業紀念冊及校刊、佛書、農民曆、及其他宗教宣傳品、期刊雜誌、報紙及少於50頁之小冊子不予蒐藏。");
                        break;
                    case 1:
                        robotAPI.robot.speak("(一)如果到學校，可直接將書籍交給圖書館借還書櫃台。" +
                                "(二)若不方便到學校，可直接將書籍郵寄到學校圖書資訊處採編組。");

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




    public q10() {
        super(robotCallback, robotListenCallback);
    }


}
