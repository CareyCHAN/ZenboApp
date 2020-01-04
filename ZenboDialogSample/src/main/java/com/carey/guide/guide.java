package com.carey.guide;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.asus.robotframework.API.RobotUtil;
import com.asus.robotframework.API.SpeakConfig;
import com.asus.robotframework.API.results.RoomInfo;
import com.robot.carey.robotactivity.RobotActivity;
import com.asus.robotframework.API.RobotCommand;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class guide extends RobotActivity {

    public final static String TAG = "ZenboDialogSample";
    public final static String DOMAIN = "6644756538CF41BCA96FEA374E8C1B38";
    private static Context mContext;
    private static String flag = "0";
    static String URL = "http://120.110.40.95:8080/api/v1/location";
//    static String URL = "http://192.168.1.188:8080/api/v1/location";

    // request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    // robotAPI flags
    private static boolean isRobotApiInitialed = false;

    // all roomInfo string
    private static String sFirstRoom;
    private static String sSecondRoom;
    private static String sThirdRoom;

    // textViews
    private static TextView mTextViewRoomKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        mContext = this;

        // textViews
        mTextViewRoomKeyword = (TextView) findViewById(R.id.textView_permission_status);


//        mButtonGoTo = (Button) findViewById(R.id.button_goTo);
//        mButtonGoTo.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(!sFirstRoom.equals("")) {
//
//                    if(isRobotApiInitialed) {
//                        // use robotAPI to go to the position "keyword":
//                        robotAPI.motion.goTo(sFirstRoom);
//                    }
//
//                }
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // initial params
        robotAPI.robot.setExpression(RobotFace.HAPPY);
        requestPermission();
        try {
            // use robotAPI to get all room info:
            ArrayList<RoomInfo> arrayListRooms = robotAPI.contacts.room.getAllRoomInfo();
            sFirstRoom = arrayListRooms.get(0).keyword;
            sSecondRoom = arrayListRooms.get(1).keyword;
            sThirdRoom = arrayListRooms.get(2).keyword;
        } catch (Exception e) {
            Log.d("ZenboGoToLocation", "get room info result exception = " + e);
        }

        // jump dialog domain
        robotAPI.robot.jumpToPlan(DOMAIN, "lanuchHelloWolrd_Plan");

        // listen user utterance
        robotAPI.robot.speakAndListen("請對我說開始導覽", new SpeakConfig().timeout(20));
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
        public void initComplete() {
            super.initComplete();
            Log.d("ZenboGoToLocation", "initComplete()");
            isRobotApiInitialed = true;
        }

        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);
            if (flag== "1" && RobotCommand.getRobotCommand(cmd).name() == "GOTO_LOCATION" && state == RobotCmdState.SUCCEED && err_code == RobotErrorCode.NO_ERROR ) {
                A();
                robotAPI.robot.speak( "百大專業經典圖書區，是由各系所教師協助，在茫茫書海中選介學生必讀之專業經典圖書，希望於教科書之外，給予學生明確的延伸閱讀指引。這是本館相當特色的典藏區域。");
                flag = "2";
            }
            if (flag== "2" && RobotCommand.getRobotCommand(cmd).name() == "SPEAK" && state == RobotCmdState.SUCCEED && err_code == RobotErrorCode.NO_ERROR ) {
                robotAPI.motion.goTo(sFirstRoom);
                flag ="3";
            }
            if (flag== "3" && RobotCommand.getRobotCommand(cmd).name() == "GOTO_LOCATION" && state == RobotCmdState.SUCCEED && err_code == RobotErrorCode.NO_ERROR ) {
                B();
                robotAPI.robot.speak("此處是本館的新書展示區，本館持續採購新書，新到館的圖書都會放置在此處，讓同學瀏覽，學生如果看到有需要的資料，就可以直接外借，可以提高新書的借閱率。");
                flag ="4";
            }
            if (flag== "4" && RobotCommand.getRobotCommand(cmd).name() == "SPEAK" && state == RobotCmdState.SUCCEED && err_code == RobotErrorCode.NO_ERROR ) {
                robotAPI.motion.goTo(sSecondRoom);
                flag ="5";
            }
            if (flag== "5" && RobotCommand.getRobotCommand(cmd).name() == "GOTO_LOCATION" && state == RobotCmdState.SUCCEED && err_code == RobotErrorCode.NO_ERROR ) {
                C();
                robotAPI.robot.speak("圖書館有豐富的館藏資源，為方便同學利用館藏，圖書館各樓層都有提供電腦供學生查詢利用。此區為本樓層的資訊檢索區，供同學使用各項電子資源，還有提供印表機及掃瞄器供學生使用。");
                flag ="6";
            }
            if (flag== "6" && RobotCommand.getRobotCommand(cmd).name() == "SPEAK" && state == RobotCmdState.SUCCEED && err_code == RobotErrorCode.NO_ERROR ) {
                robotAPI.robot.speak("導覽結束");
                flag = "0";
            }
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

            if (sIntentionID.equals("guidearea")) {
                String sSluResult = RobotUtil.queryListenResultJson(jsonObject, "area_key", null);
                Log.d(TAG, "Result = " + sSluResult);
                if (sSluResult.equals("beginguide")) {
                    robotAPI.motion.goTo(sThirdRoom);
                    flag = "1";
                }

            }

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };


    public guide() {
        super(robotCallback, robotListenCallback);
    }

    private void requestPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                this.checkSelfPermission(Manifest.permission.READ_CONTACTS) ==
                        PackageManager.PERMISSION_GRANTED) {
            // Android version is lesser than 6.0 or the permission is already granted.
            Log.d("ZenboGoToLocation", "permission is already granted");
            return;
        }

        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            //showMessageOKCancel("You need to allow access to Contacts",
            //        new DialogInterface.OnClickListener() {
            //            @Override
            //            public void onClick(DialogInterface dialog, int which) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
            //            }
            //        });
        }
    }

    public static void A() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject postparams = new JSONObject();
        try {
            postparams.put("location", sThirdRoom);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("REST Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        Log.d("REST Response", error.toString());
                    }
                });
        requestQueue.add(jsonObjReq);
    }
    public static void B() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject postparams = new JSONObject();
        try {
            postparams.put("location", sFirstRoom);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("REST Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        Log.d("REST Response", error.toString());
                    }
                });
        requestQueue.add(jsonObjReq);
    }
    public static void C() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject postparams = new JSONObject();
        try {
            postparams.put("location", sSecondRoom);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("REST Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        Log.d("REST Response", error.toString());
                    }
                });
        requestQueue.add(jsonObjReq);
    }

    /*
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    */
}
