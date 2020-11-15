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
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/5 20:42
 * <p>
 * 作用： xxxx
 */
public class CollectionManagerAdapter extends RecyclerView.Adapter<CollectionManagerAdapter.InnerViewHolder> {
    private static final String TAG = "CollectionManagerAdapter";
    private List<WordClips> mClips=new ArrayList<>();
    private onIvCollectionManagerClickListener mIvOnCollectionManagerClickListener;
    private onCollectionManagerClickListener mOnCollectionManagerClickListener;
    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_collection_rv_manager_item, parent, false);
        return new InnerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        WordClips clips = mClips.get(position);
        holder.managerTitle.setText(clips.getTitle());
        holder.managerNum.setText("单词量:"+clips.getWordsNum()+"");

        String pic = clips.getPic();
        if (pic!=null && !TextUtils.isEmpty(pic)){
            Glide.with(holder.itemView.getContext()).load(pic).into(holder.managerIv);
        }else{
            Glide.with(holder.itemView.getContext()).load(R.mipmap.ic_glide_error).into(holder.managerIv);
        }

        if (mIvOnCollectionManagerClickListener != null) {
            LogUtil.d(TAG,"pos --> "+position);
            holder.managerMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIvOnCollectionManagerClickListener.onCollectionManagerClick(position,holder.itemView);
                }
            });
        }

        if (mOnCollectionManagerClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mOnCollectionManagerClickListener.onCollectionManagerClick(mClips.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mClips==null?0:mClips.size();
    }

    public void setData(List<WordClips> wordClips) {
        mClips.clear();
        if (wordClips != null) {
            mClips.addAll(wordClips);
        }
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private ImageView managerIv;
        private TextView managerTitle;
        private TextView managerNum;
        private ImageView managerMore;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);

            managerTitle=itemView.findViewById(R.id.collection_manager_title);
            managerNum=itemView.findViewById(R.id.collection_manager_words_num);
            managerIv=itemView.findViewById(R.id.collection_manager_more_pic);
            managerMore =itemView.findViewById(R.id.collection_manager_more);
        }
    }


    public void setOnCollectionManagerClickListener(onCollectionManagerClickListener onCollectionManagerClickListener) {
        mOnCollectionManagerClickListener = onCollectionManagerClickListener;
    }

    public void setIvOnCollectionManagerClickListener(onIvCollectionManagerClickListener ivOnCollectionManagerClickListener) {
        mIvOnCollectionManagerClickListener = ivOnCollectionManagerClickListener;
    }

    //点击弹出PopWinodw
    public interface onIvCollectionManagerClickListener {
        void onCollectionManagerClick(int pos,View view);
    }

    //点击获取收藏夹的ID
    public interface onCollectionManagerClickListener{
        void onCollectionManagerClick(int clipsID);
    }
}
