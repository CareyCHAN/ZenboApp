package com.carey.guide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.robot.carey.robotactivity.RobotActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Main extends RobotActivity {
    public final static String TAG = "ZenboDialogSample";
    public final static String DOMAIN = "6644756538CF41BCA96FEA374E8C1B38";
    static String URL = "http://120.110.40.95:8080/api/v1/activitys";
//    static String URL = "http://192.168.1.188:8080/api/v1/activitys";

    private static Context mContext;

    private Button btn_guide ;
    private Button btn_advisory;
    private Button btn_lib_search;
    private  Button btn_libspace;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView=(TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        btn_guide = (Button)findViewById(R.id.btn_guide) ;
        btn_advisory = (Button)findViewById(R.id.btn_advisory) ;
        btn_lib_search = (Button)findViewById(R.id.btn_lib_search);
        btn_libspace = (Button)findViewById(R.id.btn_libspace);

        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceA();
            }
        });
        btn_advisory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceB();
            }
        });
        btn_lib_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceC();
            }
        });
        btn_libspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceD();
            }
        });

        mContext=this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        robotAPI.robot.setExpression(RobotFace.DEFAULT);

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

            if(sIntentionID.equals("service")) {
                String sSluResult = RobotUtil.queryListenResultJson(jsonObject, "service_key", null);
                Log.d(TAG, "Result = " + sSluResult);

                if(sSluResult.equals("serviceA")) {
                    serviceA();
                }
                if(sSluResult.equals("serviceB")) {
                    serviceB();
                }
                if(sSluResult.equals("serviceC")) {
                    serviceC();
                }
                if(sSluResult.equals("serviceD")) {
                    serviceD();
                }
            }

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };

    public static void serviceA() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject postparams = new JSONObject();
        try {
            postparams.put("service", "導覽");
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
        Intent intent = new Intent(mContext, guide.class);
        mContext.startActivity(intent);
    }
    public static void serviceB() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject postparams = new JSONObject();
        try {
            postparams.put("service", "定點諮詢");
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
        Intent intent = new Intent(mContext, advisory.class);
        mContext.startActivity(intent);
    }
    public static void serviceC(){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject postparams = new JSONObject();
        try {
            postparams.put("service", "館藏查詢");
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
        Uri uri = Uri.parse("http://millennium.lib.cyut.edu.tw/search*cht/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }
    public static void serviceD(){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject postparams = new JSONObject();
        try {
            postparams.put("service", "空間預約");
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
        Uri uri = Uri.parse("https://libspace.cyut.edu.tw/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    public Main() {
        super(robotCallback, robotListenCallback);
    }

}

