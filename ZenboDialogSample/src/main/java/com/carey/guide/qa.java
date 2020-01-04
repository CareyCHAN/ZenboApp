package com.carey.guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.robot.carey.robotactivity.RobotActivity;

import org.json.JSONObject;

public class qa extends RobotActivity {

    private ListView listView;
    private ListAdapter listAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qa);

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"館藏檢索(Web OPAC)","系所薦購資料","三樓期刊資料","多媒體服務","檢索教室","電子資源","文獻傳遞","個人圖書薦購","急編服務","贈書","其他類"});
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch( position )
                {
                    case 0:
                        q1();
                        break;
                    case 1:
                        q2();
                        break;
                    case 2:
                        q3();
                        break;
                    case 3:
                        q4();
                        break;
                    case 4:
                        q5();
                        break;
                    case 5:
                        q6();
                        break;
                    case 6:
                        q7();
                        break;
                    case 7:
                        q8();
                        break;
                    case 8:
                        q9();
                        break;
                    case 9:
                        q10();
                        break;
                    case 10:
                        q11();
                        break;
                }
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        robotAPI.robot.setExpression(RobotFace.HIDEFACE);
        robotAPI.robot.speak("請選擇問題類別唷~");
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
    public  void q1() {
        Intent intent = new Intent(this, q1.class);
        this.startActivity(intent);
    }
    public  void q2() {
        Intent intent = new Intent(this, q2.class);
        this.startActivity(intent);
    }
    public  void q3() {
        Intent intent = new Intent(this, q3.class);
        this.startActivity(intent);
    }
    public  void q4() {
        Intent intent = new Intent(this, q4.class);
        this.startActivity(intent);
    }
    public  void q5() {
        Intent intent = new Intent(this, q5.class);
        this.startActivity(intent);
    }
    public  void q6() {
        Intent intent = new Intent(this, q6.class);
        this.startActivity(intent);
    }
    public  void q7() {
        Intent intent = new Intent(this, q7.class);
        this.startActivity(intent);
    }
    public  void q8() {
        Intent intent = new Intent(this, q8.class);
        this.startActivity(intent);
    }
    public  void q9() {
        Intent intent = new Intent(this, q9.class);
        this.startActivity(intent);
    }
    public  void q10() {
        Intent intent = new Intent(this, q10.class);
        this.startActivity(intent);
    }
    public  void q11() {
        Intent intent = new Intent(this, q1.class);
        this.startActivity(intent);
    }




    public qa() {
        super(robotCallback, robotListenCallback);
    }


}
