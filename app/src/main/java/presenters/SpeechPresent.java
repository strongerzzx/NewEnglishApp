package presenters;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bases.BaseAppciation;
import interfaces.ISpeechCallback;
import interfaces.ISpeechPresent;
import utils.JsonParser;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/24 13:11
 * <p>
 * 作用： xxxx
 */
public class SpeechPresent implements ISpeechPresent, InitListener, RecognizerListener {

    private static final String TAG = "SpeechPresent";
    private List<ISpeechCallback> mCallbacks=new ArrayList<>();
    private final SpeechRecognizer mIat;
    private Map<String,String> mMap=new HashMap<>();//用来存储识别的句子

    private final int SAMPLE_RATE_IN_HZ=8000;
    private final int BUFFER_SIZE= AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT,AudioFormat.ENCODING_PCM_16BIT);
    private AudioRecord mAudioRecord;
    private boolean isGetVolic;

    private SpeechPresent() {

        //初始化
        SpeechUtility.createUtility(BaseAppciation.getContext(), SpeechConstant.APPID+"=5f968a16");
        mIat = SpeechRecognizer.createRecognizer(BaseAppciation.getContext(), this);

        if (mAudioRecord == null) {
            mAudioRecord=new AudioRecord(MediaRecorder.AudioSource.MIC,SAMPLE_RATE_IN_HZ,AudioFormat.CHANNEL_IN_DEFAULT,AudioFormat.ENCODING_PCM_16BIT,BUFFER_SIZE);
            isGetVolic=true;
        }

    }

    private static volatile SpeechPresent sPresent;

    public static SpeechPresent getPresent() {
        if (sPresent==null){
            synchronized (SpeechPresent.class){
                if (sPresent==null){
                    sPresent=new SpeechPresent();
                }
            }
        }
        return sPresent;
    }

    @Override
    public void doSpeechRecogize() {

        //设置参数
        mIat.setParameter(SpeechConstant.PARAMS, "iat");      //应用领域
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn"); //语音
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin"); //普通话 //mandarin
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);//引擎
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");//返回结果格式
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS,"1000");
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");

        //开始识别，并设置监听器
        mIat.startListening(this);


    }

    //计算声音分贝
    @Override
    public void doCalSoundfb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAudioRecord.startRecording();
                short[] buffer=new short[BUFFER_SIZE];
                while (isGetVolic){
                    //实际读取的长度
                    int len = mAudioRecord.read(buffer, 0, BUFFER_SIZE);
                    long v=0;
                    for (int i = 0; i < buffer.length; i++) {
                        //将buffer中内容取出，近行平方和运算
                        v+=buffer[i]*buffer[i];
                    }
                    //平方和除以数据总长 --> 音量大小
                    double mean = v / (double)len;
                    //音量大小 --> 分贝
                    double fb = 10 * Math.log10(mean);
                    LogUtil.d(TAG,"分贝值 --> "+fb);
                }
            }
        }).start();
    }

    @Override
    public void regesiterView(ISpeechCallback callbak) {
        if (mCallbacks != null && !mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(ISpeechCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }

        mIat.cancel();
        mIat.destroy();

        isGetVolic=false;
        mAudioRecord.stop();
        mAudioRecord.release();
    }

    @Override
    public void getBookNum(int currentBookNum) {

    }

    @Override
    public void onInit(int i) {
        LogUtil.d(TAG,"初始化状态码 --> "+i);
    }

    @Override
    public void onVolumeChanged(int i, byte[] bytes) {

    }

    @Override
    public void onBeginOfSpeech() {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onResult(RecognizerResult recognizerResult, boolean b) {
        //获取json内容
        String result = printResult(recognizerResult);

        for (ISpeechCallback callback : mCallbacks) {
            callback.showRecongize(result);
        }

        LogUtil.d(TAG,"result --> "+result);
    }

    private String printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn=null;

        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn=resultJson.optString("sn");
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMap.put(sn,text);

        StringBuffer sb=new StringBuffer();

        for (String s : mMap.keySet()) {
            sb.append(mMap.get(s));
        }
        return sb.toString();
    }

    @Override
    public void onError(SpeechError speechError) {
        int errorCode = speechError.getErrorCode();
        String errorDescription = speechError.getErrorDescription();
        LogUtil.d(TAG,"error --> "+errorCode+":"+errorDescription);
    }

    @Override
    public void onEvent(int i, int i1, int i2, Bundle bundle) {

    }
}
