package adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp_bishe.R;

import java.util.ArrayList;
import java.util.List;

import entirys.WordClips;

/**
 * 作者：zzx on 2020/10/4 19:03
 * <p>
 * 作用： xxxx
 */
public class DialogCollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "DialogCollectionAdapter";
    private View mHeaderView;
    private static final int TYPE_HEADER_VIEW=0;
    private static final int TYPE_NORMAL_VIEW=1;
    private OnDialogCollectionHeaderClickListener mOnDialogCollectionHeaderClickListener;
    private onDialogCollectionItemClickListener mOnDialogCollectionItemClickListener;

    private List<WordClips> mClipsList=new ArrayList<>();

    public void setHeaderView(View headerView) {
        this.mHeaderView =headerView;
        notifyItemInserted(0);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (getItemViewType(TYPE_HEADER_VIEW)==viewType &&mHeaderView!=null) {
            return new HeaderViewHolder(mHeaderView);
        }else {
            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_collection_rv_item, parent, false);
            return new NormalViewHolder(normalView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_HEADER_VIEW){
            if (holder instanceof HeaderViewHolder){
                //新建收藏夹
                if (mOnDialogCollectionHeaderClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnDialogCollectionHeaderClickListener.onDialogCollectionHeaderClickListener(position);
                        }
                    });
                }
            }
        }else{
            if (holder instanceof NormalViewHolder){
                //收藏到 --> 点击的收藏夹
                WordClips clips = mClipsList.get(position - 1);
                ((NormalViewHolder) holder).normalTitle.setText(clips.getTitle());
                ((NormalViewHolder) holder).normalSongNum.setText(clips.getWordsNum()+"");
                if (clips.getPic()==null || TextUtils.isEmpty(clips.getPic())){
                    Glide.with(holder.itemView.getContext()).load(R.mipmap.ic_glide_error).into(((NormalViewHolder) holder).normalIv);
                }else{
                    Glide.with(holder.itemView.getContext()).load(clips.getPic()).into(((NormalViewHolder) holder).normalIv);
                }

                if (mOnDialogCollectionItemClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            mOnDialogCollectionItemClickListener.onDialogCollectionItemClick(mClipsList.get(position-1).getId());
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView!=null){
            return mClipsList.size()+1;
        }else{
            return mClipsList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0)
            return TYPE_HEADER_VIEW;
        if (mHeaderView==null)
            return TYPE_NORMAL_VIEW;
        return TYPE_NORMAL_VIEW;
    }

    public void setData(List<WordClips> clipsList) {
        if (clipsList != null) {
            mClipsList.addAll(clipsList);
            notifyDataSetChanged();
        }
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivHeader;
        private TextView tvHeader;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader=itemView.findViewById(R.id.dialog_collection_header_title);
            ivHeader=itemView.findViewById(R.id.dialog_collection_header_iv);
        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder{
        private TextView normalTitle;
        private TextView normalSongNum;
        private ImageView normalIv;
        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            normalIv=itemView.findViewById(R.id.dialog_collection_item_iv);
            normalSongNum=itemView.findViewById(R.id.dialog_collection_item_song_num);
            normalTitle=itemView.findViewById(R.id.dialog_collection_item_title);
        }
    }

    public void setOnDialogCollectionHeaderViewClickListener(OnDialogCollectionHeaderClickListener onDialogCollectionHeaderClickListener) {
        mOnDialogCollectionHeaderClickListener = onDialogCollectionHeaderClickListener;
    }

    public interface OnDialogCollectionHeaderClickListener {
        void onDialogCollectionHeaderClickListener(int position);
    }

    public void setOnDialogCollectionItemClickListener(onDialogCollectionItemClickListener onDialogCollectionItemClickListener) {
        mOnDialogCollectionItemClickListener = onDialogCollectionItemClickListener;
    }

    public interface onDialogCollectionItemClickListener{
        void onDialogCollectionItemClick(int pos);
    }
}
