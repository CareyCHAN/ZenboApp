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

public class q6 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q6);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"電子資源系統的帳號密碼忘了，怎麼辦？","在校外可以使用本館電子資源嗎？","校外人士可以到本館使用電子資源嗎？","要在哪裏查找電子資源呢？","資料庫無法連線時，怎麼辦？","單機版的資料庫要在哪裡使用呢?"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("電子資源系統預設帳號為您的學號或教職員證號，密碼預設值為您的身分證號，此組帳號密碼與您的個人借閱記錄查詢相同，若您曾更改過密碼，請使用修改後的密碼登入。如仍無法登入系統，請洽讀者服務組(校內分機3146或rs@cyut.edu.tw)。");
                        break;
                    case 1:
                        robotAPI.robot.speak("凡是朝陽科技大學在職的教職員或在學的學生，皆可在通過電子資源系統身分認證後，自校外連線使用本館網路版電子資源。電子資源系統預設帳號為您的學號或教職員證號，密碼預設值為您的身分證號，此組帳號密碼與您的個人借閱記錄查詢相同，若您曾更改過密碼，請使用修改後的密碼登入。如仍無法登入系統，請洽讀者服務組(校內分機3146或rs@cyut.edu.tw)。");
                        break;
                    case 2:
                        robotAPI.robot.speak("可以，請至本館二樓服務台洽詢。");
                        break;
                    case 3:
                        robotAPI.robot.speak("本館館藏之電子期刊、電子資料庫及電子書等多項電子資源，均可在本館電子資源系統中查得，如有任何問題，歡迎洽詢讀者服務組(校內分機3146或rs@cyut.edu.tw)。");
                        break;
                    case 4:
                        robotAPI.robot.speak("請洽詢讀者服務組(校內分機3146或rs@cyut.edu.tw)。");
                        break;
                    case 5:
                        robotAPI.robot.speak("目前本館的單機版資料庫，均安裝於圖書館二樓資訊檢索區電腦中，部分光碟則需至服務台登記借閱，使用前請先至電子資源系統查詢各資料庫簡介。請參考本館單機版資料庫清單。");
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




    public q6() {
        super(robotCallback, robotListenCallback);
    }


}
