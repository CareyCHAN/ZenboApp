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

public class q3 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q3);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"如何查詢館藏期刊？","如何了解現期期刊是否已到館？","如何分辨期刊館藏位置?","期刊資料為什麼不能外借？","為什麼專業的紙本期刊好像不是很多?","我想知道某期刊有否被SCI、SSCI或EI收錄？","我要查國外期刊的排名及影響指數，要如何進行？"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("紙本期刊請利用「館藏查詢」系統查詢，電子期刊請利用「電子資源」系統查詢。系統詳細查詢方式請參見圖書館首頁〉查找資料秘技。");
                        break;
                    case 1:
                        robotAPI.robot.speak("利用「館藏查詢」系統查到該種期刊後，點選「最近到館刊期」，即能從點收箱中看到各卷期的到館狀態。");
                        break;
                    case 2:
                        robotAPI.robot.speak("期刊資料請至本館三樓期刊區取用" +
                                "如為現期期刊(當年度期刊)，請抄下現刊架號，按號索刊:" +
                                "1.中文期刊：架號開頭為C。" +
                                "2.西文期刊：架號開頭為E。" +
                                "3.日文期刊：架號開頭為 J。" +
                                "例如：利用期刊查詢系統查出某期刊之架號為C 10-50，'C'表中文期刊，'10'表第十列書架，'50'即中文期刊第十列書架之第50本期刊，餘類推。" +
                                "如為過期期刊(非當年度期刊)，請按刊名首字英文字母或中文筆劃索刊：" +
                                "1.中文期刊：依數字、英文字母、中文字筆劃的順序排序。" +
                                "2.西文期刊：依數字、英文字母的順序排序。" +
                                "3.日文期刊：依數字、英文字母、漢字筆劃的順序排序。");
                        break;
                    case 3:
                        robotAPI.robot.speak("期刊通常是許多新知或最新資訊發表的管道，使用率很高，因此為方便讀者隨時可以取用期刊，增加期刊使用頻率，故僅限館內閱覽。此外，期刊的發行方式與書略有不同，期刊通常是當月發行量賣完為止，少有再版情形，故期刊過期後即形同絕版，其珍貴性自不在話下。因此期刊如採一般圖書外借的程序，則遺失率與損毀率勢必增加，當讀者有期刊需求時，則必須大老遠到其他圖書館使用或者花費申請文獻傳遞服務之費用，因此期刊僅限館內閱覽，歡迎讀者多加利用!");
                        break;
                    case 4:
                        robotAPI.robot.speak("本館專業期刊皆源自於各系所老師們的專業建議，其採購經費亦來自於各系圖書預算。由於期刊每年皆會漲價，且部份期刊已改訂電子版，造成紙本期刊看起來似乎不多，不過，電子期刊有近三萬種喔！建議多多利用電子期刊。");
                        break;
                    case 5:
                        robotAPI.robot.speak("尚無答覆!!");
                        break;
                    case 6:
                        robotAPI.robot.speak("尚無答覆!!");
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




    public q3() {
        super(robotCallback, robotListenCallback);
    }


}
