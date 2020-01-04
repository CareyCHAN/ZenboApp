package com.carey.guide;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.robot.carey.robotactivity.RobotActivity;

import org.json.JSONObject;

public class srvtime extends RobotActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.srvtime);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        robotAPI.robot.setExpression(RobotFace.HAPPY);
        robotAPI.robot.speak("本館的服務時間是星期一到星期五早上八點半到晚上十點，而五樓多媒體區只到晚上八點，閱覽室則是早上八點一直開到晚上十一點唷~");
        robotAPI.robot.speak("星期六是早上八點半到下午五點，而五樓多媒體區只到下午三點，閱覽室則是不開放的");
        robotAPI.robot.speak("星期日是下午兩點到晚上八點，而五樓多媒體區只到傍晚六點，閱覽室則是從早上八點開到下午三點~");
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




    public srvtime() {
        super(robotCallback, robotListenCallback);
    }


}
