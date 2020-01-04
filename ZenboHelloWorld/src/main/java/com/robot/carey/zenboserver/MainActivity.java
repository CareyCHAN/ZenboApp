package com.robot.carey.zenboserver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.robotframework.API.MotionControl;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.robot.carey.robotactivity.RobotActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends RobotActivity {
    private TextView editText_1;
    private ServerSocket serverSocket = null;
    StringBuffer stringBuffer = new StringBuffer();

    private InputStream inputStream;

    public Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 1:
                    editText_1.setText(msg.obj.toString());
                    break;
                case 2:
                    if(msg.obj.toString().equals("forward")){
                        robotAPI.robot.speak("前進");
                        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.FORWARD);
                    }
                    if(msg.obj.toString().equals("stop")){
                        robotAPI.robot.speak("停止");
                        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.STOP);
                    }
                    if(msg.obj.toString().equals("left")){
                        robotAPI.robot.speak("左轉");
                        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.TURN_LEFT);
                    }
                    if(msg.obj.toString().equals("right")){
                        robotAPI.robot.speak("右轉");
                        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.TURN_RIGHT);
                    }
                    if(msg.obj.toString().equals("backward")){
                        robotAPI.robot.speak("後退");
                        robotAPI.motion.remoteControlBody(MotionControl.Direction.Body.BACKWARD);
                    }
                    stringBuffer.setLength(0);
                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_1 = (TextView) findViewById(R.id.et_1);
        receiveData();
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


    public MainActivity() {
        super(robotCallback, robotListenCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        robotAPI.robot.speak("Welcome to use ZenboRemoteControl");
    }
    public void receiveData(){

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    serverSocket = new ServerSocket(8000);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                GetIpAddress.getLocalIpAddress(serverSocket);

                Message message_1 = handler.obtainMessage();
                message_1.what = 1;
                message_1.obj = "IP:" + GetIpAddress.getIP() + " PORT: " + GetIpAddress.getPort();
                handler.sendMessage(message_1);

                while (true){
                    Socket socket = null;
                    try {
                        socket = serverSocket.accept();
                        inputStream  = socket.getInputStream();
                        robotAPI.robot.speak("配對成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    new ServerThread(socket,inputStream).start();

                }
            }
        };
        thread.start();

    }
    class ServerThread extends Thread{

        private Socket socket;
        private InputStream   inputStream;
        private StringBuffer stringBuffer = MainActivity.this.stringBuffer;


        public ServerThread(Socket socket,InputStream inputStream){
            this.socket = socket;
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            int len;
            byte[] bytes = new byte[20];
            boolean isString = false;

            try {
                while ((len = inputStream.read(bytes)) != -1) {
                    for(int i=0; i<len; i++){
                        if(bytes[i] != '\0'){
                            stringBuffer.append((char)bytes[i]);
                        }else {
                            isString = true;
                            break;
                        }
                    }
                    if(isString){
                        Message message_2 = handler.obtainMessage();
                        message_2.what = 2;
                        message_2.obj = stringBuffer;
                        handler.sendMessage(message_2);
                        isString = false;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    inputStream.close();
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
