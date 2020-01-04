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

public class q7 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q7);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"圖書館沒有我需要的書和期刊時怎麼辦？","什麼是全國文獻傳遞服務？","如何申請館際複印／互借？","申請文獻傳遞服務大概需要多久時間可以拿到?","申請館際合作文件該如何付費？","申請館際複印時，圖書、期刊或博碩士論文可以整本複印嗎？"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("若圖書館沒有您需要的書刊、博碩士論文，您可先利用全國期刊聯合目錄或全國圖書書目資訊網查得何館有您所需書刊，再透過全國文獻傳遞服務系統向其他圖書館申請複印文獻或借書，亦可申請辦理中部大學圖書館聯盟、中區技專校院校際聯盟或本館個別簽訂的圖書互借聯盟圖書館借書證，親自前往借閱。詳細辦法，請參考本館網頁〉館際合作與跨校借書之說明。");
                        break;
                    case 1:
                        robotAPI.robot.speak("全國文獻傳遞服務是一項館際文獻複印、圖書互借的服務，當您需要的資料本館並無館藏時，即可透過此項服務取得文獻。第一次使用必須先申請帳號，申請程序及相關說明，請先參考全國文獻傳遞服務簡介。");
                        break;
                    case 2:
                        robotAPI.robot.speak("申請前先查詢館藏查詢系統與電子資源系統，確認本館無此館藏後，利用期刊聯合目錄或圖書聯合目錄，找尋申請單位，再透過全國文獻傳遞服務系統（第一次使用必須先申請帳號）填寫複印申請單或借書申請單。如有任何問題，請洽讀者服務組(校內分機3146或rs@cyut.edu.tw)。");
                        break;
                    case 3:
                        robotAPI.robot.speak("一般文獻傳遞服務申請件的處理流程包括：本館審核送出、被申請館查找館藏及複印寄出、郵遞時間、本館收到後之登記作業，約為3-5個工作天；若讀者申請傳遞方式為Ariel電子傳遞，則可省去郵遞時間。另外，若讀者所填寫之書目資訊有誤、或是被申請館無該筆館藏，則可能會退件給本館或者轉至下一個被申請館處理，如此必會延長申請件的處理時間。 整體而言，申請件在本館處理的階段原則上會在1個工作天內完成，至於他館處理以及郵寄的過程並非本館可掌控，但如有申請件寄出超過1週未收到，本館均會進行追蹤及處理。");
                        break;
                    case 4:
                        robotAPI.robot.speak("請至圖書館二樓服務台取件並付款。");
                        break;
                    case 5:
                        robotAPI.robot.speak("因應著作權法規定，圖書館只能提供整本書刊或博碩士論文之部份複印服務，有些學校另提供博碩士論文外借，申請前可先參考各校館藏系統。");
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




    public q7() {
        super(robotCallback, robotListenCallback);
    }


}
