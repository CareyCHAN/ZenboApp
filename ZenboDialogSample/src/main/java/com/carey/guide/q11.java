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

public class q11 extends RobotActivity {
    private TextView textView;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q11);

        textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/wt009.ttf"));

        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"弄丟書，如何處理？","當在圖書館的書架上找不到書時，該怎辦？","圖書歸還後，上架作業流程說明。","在圖書館閱讀時，遇到其他學生喧嘩時，該怎麼處理？","何時可辦理圖書長期借閱？","校友如何辦證借閱？","館友如何辦證借閱？"});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        robotAPI.robot.speak("1. 書有遺失狀況，請先向借還書櫃台通報圖書掛失，如果是在到期日之前通報，則不會產生逾期罰款；如果是在到期日後才通報，那麼除了賠書，尚需繳納逾期罰款。" +
                                "2. 賠書：" +
                                "(1)根據本館圖書借閱規則第20條：「讀者如遺失本館圖書資料，需自行購買與原資料題名、編著者、出版處所、裝訂版次相同或最新版之新書賠償；讀者賠償後，若尋獲原件，亦不得要求更換取回。」" +
                                "(2)因此當你向借還書櫃台通報圖書掛失時，請向櫃檯人員索取書目資料，以供購書時參考。" +
                                "3. 圖書掛失後，請於1個月內，自行購買遺失的書來賠償；若1個月內仍無法買到，請到圖書館說明一下狀況，否則暫時停止讀者借閱權利，直到讀者前來說明為止。若無法買到一樣的書，請讀者在購買其他書前，先將欲購買的圖書資料抄錄下來後詢問館員，待本館同意後再購買為宜。 (原則上，所賠之類似書，金額不得低於遺失的書，出版年要較遺失的書還新，且是館藏尚未有的書)。" +
                                "4. 若讀者希望所賠償的書籍於編目完成後再借出，請在賠償的當時向館員提出需求﹔如此一來，當該書編目完成移送書庫時，本館館員會將該書設為賠償者的預約書，等待讀者前來借閱。");
                        break;
                    case 1:
                        robotAPI.robot.speak("圖書不在架上，請先參考館藏查詢系統的處理狀態欄顯示的訊息。" +
                                "1.若為「到期 11-21-08」，表此書目前外借中，該讀者到期日為2008年11月21日，您可以直接線上辦理預約。" +
                                "" +
                                "2.若為「編目處理中」，請至圖書館網頁「圖書急編服務」填寫申請單，我們將儘快為您處理。" +
                                "3.若為「可借閱」時，請至二樓借還書櫃台填寫「代尋書」申請單，工作人員會幫忙找書，找到後將設為預約書，隔日系統會發送E-mail通知。" +
                                "" +
                                "4.若為「圖書待上架」，表此書可能剛歸還正上架中，若架上找不到，可在樓層入口右側圖書待上架區尋找。");
                        break;
                    case 2:
                        robotAPI.robot.speak("讀者歸還的圖書，72小時內系統上的狀態是「圖書待上架」；72小時後變為「可借閱」。" +
                                "若系統上顯示為「圖書待上架」，可先至4、6樓書庫入口右側的「圖書待上架區」尋找。" +
                                "若系統上顯示為「可借閱」，在書架上卻找不到書，請至二樓借還書櫃檯向工作人員反應，之後處理流程如下：" +
                                "1.櫃檯有人手，則立即幫讀者尋書。" +
                                "2.櫃檯忙碌時，請讀者填寫「代尋書」申請單；之後讀者服務組會請工讀生於書架上尋找圖書；尋獲後設為預約書，隔天開館前發出E-mail通知信，通知讀者至二樓借還書櫃檯借書。" +
                                "3.尋找五次後，仍找不到的書，本館會將該書暫時設定為”遺失”。");
                        break;
                    case 3:
                        robotAPI.robot.speak("圖書館是一個安靜的閱讀場所，讀者應共同維護本館的安寧；本館每天均有館員不定時巡視館舍，會針對各種讀者們不宜的行為加以解決或是勸導。當讀者在閱覽室或其他地點閱讀遇到喧嘩情況時，可以透過每樓層出口處的電話，撥打3151或3152，本館將立即派員處理。");
                        break;
                    case 4:
                        robotAPI.robot.speak("依現行法規之規定，本館只在寒暑假期間提供圖書長期借閱服務，至於借閱冊數則維持不變。");
                        break;
                    case 5:
                        robotAPI.robot.speak("凡是本校校友持校友證，即可返校至圖書館辦理圖書資料借閱。 唯首次借閱者，須至二樓借還書櫃臺辦理個人資料登錄。相關規定如下：" +
                                "1.圖書借閱：10冊、15天、無人預約情況下可續借3次。" +
                                "2.視聽資料：5個登錄號、3小時、限館內閱覽。" +
                                "3.其他圖書資料限館內閱覽。" +
                                "4.辦理校友證請至行政大樓四樓校友會(校內分機3670)辦理；辦證請備2張照片，填表與繳費即可。" +
                                "5.校友證辦理請參閱相關網頁說明。");
                        break;
                    case 6:
                        robotAPI.robot.speak("除短期進修學員外，有效期限為1年，到期以身分證或服務證明展延有效期限。相關規定如下：" +
                                "1.退休教職員：憑身分證辦理借書。" +
                                "2.長億與興農企業及駐校廠商員工：憑身分證及服務單位證明書辦理。" +
                                "3.短期進修學員：憑身分證、學員證及繳費收據辦理；有效期限同修課期間。" +
                                "4.本校高中、職策略聯盟學校之師生：憑身分證及該校核發之服務證或學生證辦理。" +
                                "5.社區民眾：設籍中部縣市(含台中縣市、彰化縣、南投縣、雲林縣)年滿18歲之居民或年滿12歲之本校教職員眷屬，憑身分證辦理並繳交保證金新台幣1仟元。");
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




    public q11() {
        super(robotCallback, robotListenCallback);
    }


}
