package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp_bishe.R;

import java.util.ArrayList;
import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/3 09:07
 * <p>
 * 作用： xxxx
 */
public class PopSuggestAdapter extends RecyclerView.Adapter<PopSuggestAdapter.InnerViewHolder> {
    private List<Words> mList=new ArrayList<>();
    private onSuggestItemClickListener mOnSuggestItemClickListener;

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_item_suggest_rv, parent, false);
        return new InnerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        Words words = mList.get(position);
        holder.chinese.setText(words.getTran());
        holder.english.setText(words.getHeadWord());
        holder.fayin.setText(words.getUkphone());

        if (mOnSuggestItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnSuggestItemClickListener.onSuggestClickListener(position,mList);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public void setData(List<Words> suggestWords) {
        System.out.println(Thread.currentThread().getName());
        mList.clear();
        if (suggestWords != null) {
            mList.addAll(suggestWords);
            notifyDataSetChanged();
        }
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView english;
        private TextView chinese;
        private TextView fayin;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            english=itemView.findViewById(R.id.search_suggest_english);
            chinese=itemView.findViewById(R.id.search_suggest_chinese);
            fayin=itemView.findViewById(R.id.search_suggest_fayin);
        }
    }


    public void setOnSuggestItemClickListener(onSuggestItemClickListener onSuggestItemClickListener) {
        mOnSuggestItemClickListener = onSuggestItemClickListener;
    }

    public interface onSuggestItemClickListener{
        void onSuggestClickListener(int position,List<Words> mWords);
    }
}
