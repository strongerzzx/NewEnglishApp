package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp_bishe.R;

import java.util.ArrayList;
import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/2 22:31
 * <p>
 * 作用： xxxx
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.InnerViewHolder> {
    private List<Words> mWords=new ArrayList<>();
    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.ci_ku_recyler_item, parent, false);
        return new InnerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        Words words = mWords.get(position);
        holder.cikuChinese.setText(position+"-"+words.getTran());
        holder.cikuEnglish.setText(words.getHeadWord());
        holder.cikuCollection.setImageResource(R.mipmap.ic_collection_normal);
    }

    @Override
    public int getItemCount() {
        return mWords==null?0:mWords.size();
    }

    public void setData(List<Words> currentResult) {
        mWords.clear();
        if (currentResult != null) {
            mWords.addAll(currentResult);
            notifyDataSetChanged();
        }
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView cikuEnglish;
        private TextView cikuChinese;
        private ImageView cikuCollection;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            cikuChinese=itemView.findViewById(R.id.ciku_item_chinese);
            cikuEnglish=itemView.findViewById(R.id.ciku_item_english);
            cikuCollection=itemView.findViewById(R.id.ciku_item_collection_iv);
        }
    }
}
