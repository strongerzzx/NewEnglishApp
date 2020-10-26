package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/24 13:09
 * <p>
 * 作用： xxxx
 */
public interface ISpeechPresent extends BaseInterface<ISpeechCallback> {

    //语音翻译回调

    //开始识别
    void doSpeechRecogize();

    void doCalSoundfb();
}

