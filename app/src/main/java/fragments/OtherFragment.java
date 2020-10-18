package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.englishapp_bishe.Chinese2EnglishActivity;
import com.example.englishapp_bishe.Listen2SelectorActivity;
import com.example.englishapp_bishe.R;
import com.example.englishapp_bishe.SpellWordActivity;

import java.util.ArrayList;
import java.util.List;

import adapters.OtherAdapter;
import beans.OtherBeans;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment {


    private View mInflate;
    private OtherAdapter mOtherAdapter;
    private RecyclerView mOtherRv;
    private List<OtherBeans> mOtherList=new ArrayList<>();

    private int[] imags=new int[]{R.mipmap.ic_selector_chinese_word,R.mipmap.ic_listner_2_yi,R.mipmap.ic_word_pin_xie};
    private String[] title=new String[]{"中文选词","听音选意","填空拼写"};
    private String[] smallTitle=new String[]{"阅读","听力","写作"};


    public OtherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflate = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_other, container, false);

        initView();

        return mInflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

        mOtherAdapter.setData(mOtherList);


        mOtherAdapter.setOnOtherItemClickListner(new OtherAdapter.onOtherItemClickListner() {
            @Override
            public void onOtherItemClick(int position) {
                switch (position) {
                    case 0:
                        Intent intent=new Intent(getActivity(), Chinese2EnglishActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent(getActivity(), Listen2SelectorActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        //TODO:填空拼写
                        Intent intent2=new Intent(getActivity(), SpellWordActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });

    }

    private void initData() {
        for (int i = 0; i < smallTitle.length; i++) {
            OtherBeans otherBeans=new OtherBeans.Builder(title[i])
                        .setSmallTitle(smallTitle[i])
                        .setImag(imags[i])
                        .setCurrentProgress(0)
                        .setFinalyProgress(0).build();
            mOtherList.add(otherBeans);
        }
    }

    private void initView() {
        mOtherRv = mInflate.findViewById(R.id.other_rv);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mOtherRv.setLayoutManager(manager);
        mOtherAdapter = new OtherAdapter();
        mOtherRv.setAdapter(mOtherAdapter);
    }

}
