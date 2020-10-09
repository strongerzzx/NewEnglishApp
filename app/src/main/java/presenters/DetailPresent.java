package presenters;

import java.util.ArrayList;
import java.util.List;

import entirys.Words;
import interfaces.IDeatilCallback;
import interfaces.IDeatilPresent;

/**
 * 作者：zzx on 2020/10/4 15:51
 * <p>
 * 作用： xxxx
 */
public class DetailPresent implements IDeatilPresent {

    private static final String TAG = "DetailPresent";
    private List<IDeatilCallback> mCallbackList=new ArrayList<>();
    private int mCurrentPos;
    private List<Words> mCurrentWords =new ArrayList<>();

    private DetailPresent(){
    }

    private  volatile static DetailPresent sPresent;

    public static DetailPresent getPresent() {
        if (sPresent==null){
            synchronized (DetailPresent.class){
                if (sPresent==null){
                    sPresent=new DetailPresent();
                }
            }
        }
        return sPresent;
    }

    @Override
    public void getCikuData(int clickPosition, List<Words> currentWords) {
        //词库中是有HeadView的Recy

        this.mCurrentPos=clickPosition-1;
        this.mCurrentWords =currentWords;
    }

    @Override
    public void getData() {
        Words words = mCurrentWords.get(mCurrentPos);
        if (words != null) {
            for (IDeatilCallback iDeatilCallback : mCallbackList) {
                iDeatilCallback.showData(words);
            }
        }
    }

    @Override
    public void regesiterView(IDeatilCallback callbak) {
        if (mCallbackList != null && !mCallbackList.contains(callbak)) {
            mCallbackList.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(IDeatilCallback callback) {
        if (mCallbackList != null) {
            mCallbackList.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {

    }
}
