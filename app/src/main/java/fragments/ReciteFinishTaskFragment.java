package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.englishapp_bishe.HomeActivity;
import com.example.englishapp_bishe.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import presenters.ReciteWordPresent;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/11 18:48
 * <p>
 * 作用： xxxx
 */
public class ReciteFinishTaskFragment extends Fragment {

    private static final String TAG = "ReciteFinishTaskFragment";
    private View mInflate;
    private TextView mFinishNum;
    private TextView mFinishDate;
    private TextView mFinishBtn;
    private EditText mEtInput;
    private ReciteWordPresent mReciteP;

    public ReciteFinishTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = LayoutInflater.from(container.getContext()).inflate(R.layout.recite_word_finish_task, null);

        initView();

        mReciteP = ReciteWordPresent.getPresent();

        return mInflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //显示完成的数目
        Bundle bundle = getArguments();
        String range = bundle.getString("range");
        mFinishNum.setText(range);

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        String compltetTime = format.format(date);
        mFinishDate.setText(compltetTime);

        //输入完成感言
        String input = mEtInput.getText().toString();
        LogUtil.d(TAG,"input --> "+input);

        //点击后返回主界面
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        //传递任务完成的信息
        mReciteP.getFinishInfo(range,compltetTime,input);

        //TODO:完成任务
        mReciteP.doFinishTask();
    }

    private void initView() {
        mFinishNum = mInflate.findViewById(R.id.recite_finish_words_num);
        mFinishDate = mInflate.findViewById(R.id.recite_finish_date);
        mFinishBtn = mInflate.findViewById(R.id.recite_btn_return);
        mEtInput = mInflate.findViewById(R.id.recite_finish_input_ganyan);
    }


}
