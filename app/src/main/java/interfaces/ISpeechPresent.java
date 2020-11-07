package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/24 13:09
 * <p>
 * 作用： xxxx
 */
public interface ISpeechPresent extends BaseInterface<ISpeechCallback> {


    //开始识别
    void doSpeechRecogize();

    //计算分贝
    void doCalSoundfb();

    //请求数据
    void doQueryData();

    //取消计算和录音
    void onDestrioyRecord();
}

