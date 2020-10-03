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
 * 作者：zzx on 2020/10/2 18:53
 * <p>
 * 作用： xxxx
 */
public class CikuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CIKU_HEADER=0;
    private static final int TYPE_CIKU_NORMAL=1;
    private static final String TAG = "CikuAdapter";

    private List<Words> mList=new ArrayList<>();
    private View mHeaderView;
    private onCiKuItemClickListener mOnCiKuItemClickListener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_CIKU_HEADER && mHeaderView!=null){
            return new HeaderView(mHeaderView);
        }else {
            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ci_ku_recyler_item, parent, false);
            return new NormalView(normalView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_CIKU_HEADER){
            if (holder instanceof HeaderView){
                ((HeaderView) holder).tvHeader.setText("词库展示");
            }
        }else if (getItemViewType(position)==TYPE_CIKU_NORMAL){
            if (holder instanceof NormalView){
                Words words = mList.get(position-1); //因为是带有Header的Rv 所以0被占用了
                ((NormalView) holder).cikuCollection.setImageResource(R.mipmap.ic_collection_normal);
                ((NormalView) holder).cikuChinese.setText(position+"-"+words.getTran());
                ((NormalView) holder).cikuEnglish.setText(words.getHeadWord());

                if (mOnCiKuItemClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnCiKuItemClickListener.onCiKuClickListener(position);
                        }
                    });
                }

                //收藏单词 --> 变色
                ((NormalView) holder).cikuCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((NormalView) holder).cikuCollection.setImageResource(R.mipmap.ic_collection_press);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView!=null){
            return mList.size()+1;
        }else{
           return mList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            return TYPE_CIKU_HEADER;
        }
        if (mHeaderView==null){
            return TYPE_CIKU_NORMAL;
        }
        return TYPE_CIKU_NORMAL;
    }

    public void setHeaderView(View header) {
        this.mHeaderView=header;
        notifyItemInserted(0);
    }

    public void setData(List<Words> wordsList) {
        if (wordsList != null) {
            mList.addAll(wordsList);
        }
        notifyDataSetChanged();
    }


    public class NormalView extends RecyclerView.ViewHolder{
        private TextView cikuEnglish;
        private TextView cikuChinese;
        private ImageView cikuCollection;
        public NormalView(@NonNull View itemView) {
            super(itemView);
            if (mHeaderView==null)
                return;
            cikuChinese=itemView.findViewById(R.id.ciku_item_chinese);
            cikuEnglish=itemView.findViewById(R.id.ciku_item_english);
            cikuCollection=itemView.findViewById(R.id.ciku_item_collection_iv);
        }
    }

    public class HeaderView extends RecyclerView.ViewHolder{
        private TextView tvHeader;
        public HeaderView(@NonNull View itemView) {
            super(itemView);
            tvHeader=itemView.findViewById(R.id.ci_ku_title);
        }
    }

    public void setOnCiKuItemClickListener(onCiKuItemClickListener onCiKuItemClickListener) {
        mOnCiKuItemClickListener = onCiKuItemClickListener;
    }

    public interface onCiKuItemClickListener{
        void onCiKuClickListener(int position);
    }
}
