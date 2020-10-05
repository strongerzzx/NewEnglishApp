package views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp_bishe.CikuDetailActivity;
import com.example.englishapp_bishe.R;

import java.util.List;

import adapters.PopSuggestAdapter;
import entirys.Words;
import presenters.DetailPresent;

/**
 * 作者：zzx on 2020/10/3 09:02
 * <p>
 * 作用： xxxx
 */
public class SuggestPopWindow extends PopupWindow {

    private final View mPopView;
    private PopSuggestAdapter mSuggestAdapter;

    public SuggestPopWindow(Context context) {
        super(context);

        mPopView = LayoutInflater.from(context).inflate(R.layout.pop_suggest_window, null);
        setContentView(mPopView);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setOutsideTouchable(false);

        initChildView(context);
    }

    private void initChildView(Context context) {
        RecyclerView popSuggestRv =mPopView.findViewById(R.id.pop_suggest);
        popSuggestRv.setLayoutManager(new LinearLayoutManager(context));
        mSuggestAdapter = new PopSuggestAdapter();
        popSuggestRv.setAdapter(mSuggestAdapter);

        //TODO: -->到详情页
        mSuggestAdapter.setOnSuggestItemClickListener(new PopSuggestAdapter.onSuggestItemClickListener() {
            @Override
            public void onSuggestClickListener(int position,List<Words> mWords) {
                Intent intent=new Intent(context, CikuDetailActivity.class);
                DetailPresent.getPresent().getCikuData(position+1,mWords);
                context.startActivity(intent);
            }
        });
    }

    public void getWords(List<Words> suggestWords) {
        if (suggestWords != null) {
            mSuggestAdapter.setData(suggestWords);
        }
    }
}
