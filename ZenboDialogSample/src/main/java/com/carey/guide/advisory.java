package com.carey.guide;

import android.content.Context;
import android.content.Intent;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.asus.robotframework.API.RobotUtil;
import com.asus.robotframework.API.SpeakConfig;
import com.robot.carey.robotactivity.RobotActivity;

import org.json.JSONObject;

public class advisory extends RobotActivity {
    public final static String TAG = "ZenboDialogSample";
    public final static String DOMAIN = "6644756538CF41BCA96FEA374E8C1B38";

    private TextView textView;
    private static Context mContext;
    private Button btn_qa;
    private Button btn_srvtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisory);
        textView = (TextView) findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));
        btn_qa = (Button)findViewById(R.id.btn_qa);
        btn_srvtime = (Button)findViewById(R.id.btn_srvtime);
        btn_qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qa();
            }
        });
        btn_srvtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                srvtime();
            }
        });

        mContext=this;

    }

    @Override
    protected void onResume() {
        super.onResume();

        robotAPI.robot.setExpression(RobotFace.ACTIVE);

        // jump dialog domain
        robotAPI.robot.jumpToPlan(DOMAIN, "lanuchHelloWolrd_Plan");

        // listen user utterance
        robotAPI.robot.speakAndListen("請對我說需要的服務項目喔!", new SpeakConfig().timeout(20));




    }


    @Override
    protected void onPause() {
        super.onPause();

        //stop listen user utterance
        robotAPI.robot.stopSpeakAndListen();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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
            String text;
            text = "onEventUserUtterance: " + jsonObject.toString();
            Log.d(TAG, text);
        }

        @Override
        public void onResult(JSONObject jsonObject) {
            String text;
            text = "onResult: " + jsonObject.toString();
            Log.d(TAG, text);


            String sIntentionID = RobotUtil.queryListenResultJson(jsonObject, "IntentionId");
            Log.d(TAG, "Intention Id = " + sIntentionID);

            if(sIntentionID.equals("advisory")) {
                String sSluResult = RobotUtil.queryListenResultJson(jsonObject, "advisory_key", null);
                Log.d(TAG, "Result = " + sSluResult);

                if(sSluResult.equals("qa")) {
                    qa();
                }
                if(sSluResult.equals("srvtime")) {
                    srvtime();
                }
            }

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };
    public static void qa() {
        Intent intent = new Intent(mContext, qa.class);
        mContext.startActivity(intent);
    }
    public static void srvtime() {
        Intent intent = new Intent(mContext, srvtime.class);
        mContext.startActivity(intent);
    }

    public advisory() {
        super(robotCallback, robotListenCallback);
    }


}

