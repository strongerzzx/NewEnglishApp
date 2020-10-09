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
 * 作者：zzx on 2020/10/9 12:30
 * <p>
 * 作用： xxxx
 */
public class ManagerDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Words> mList=new ArrayList<>();
    private onManagerDetailItemClickListner mOnManagerDetailItemClickListner;
    private onManagerDetailIteLongmClickListner mManagerDetailIteLongmClickListner;

    private View mHeaderView;

    private static final int TYPE_VIEW_HEADER=0;
    private static final int TYPE_VIEW_NORMAL=1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_VIEW_HEADER && mHeaderView!=null){
            return new HeaderViewHolder(mHeaderView);
        }else{
            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.manager_detail_recyler_item, parent, false);
            return new NormalViewHolder(normalView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_VIEW_HEADER){
            if (holder instanceof ManagerDetailAdapter.HeaderViewHolder){
                ((HeaderViewHolder) holder).mHeaderTitle.setText("收藏夹内单词");
            }
        }else if (getItemViewType(position)==TYPE_VIEW_NORMAL){
            if (holder instanceof NormalViewHolder){
                Words words = mList.get(position-1);
                ((NormalViewHolder) holder).mDeatilEnglish.setText(words.getHeadWord());
                ((NormalViewHolder) holder).mDetailChinese.setText(words.getTran());


                if (mOnManagerDetailItemClickListner!=null){
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnManagerDetailItemClickListner.onManagerDetailItemClick(position,mList);
                        }
                    });
                }

                if (mManagerDetailIteLongmClickListner != null) {
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            mManagerDetailIteLongmClickListner.onManagerDetailItemLongClick(words.getId(),mList,holder.itemView);
                            return true;
                        }
                    });
                }
            }
        }


    }


    @Override
    public int getItemViewType(int position) {
        if (position==0)
            return TYPE_VIEW_HEADER;
        if (mHeaderView==null)
            return TYPE_VIEW_NORMAL;
        return TYPE_VIEW_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView!=null)
            return mList.size()+1;
        return mList.size();
    }


    public void setData(List<Words> mWords) {
        mList.clear();
        if (mWords != null) {
            mList.addAll(mWords);
            notifyDataSetChanged();
        }
    }

    public void setHeaderView(View headerView) {
        this.mHeaderView=headerView;
        notifyItemInserted(0);
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        private ImageView mHeaderFinish;
        private TextView mHeaderTitle;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            mHeaderFinish=itemView.findViewById(R.id.manager_detail_header_finish_iv);
            mHeaderTitle=itemView.findViewById(R.id.manager_detail_header_title);
        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        private TextView mDeatilEnglish;
        private TextView mDetailChinese;
        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);

            mDeatilEnglish=itemView.findViewById(R.id.manager_detail_item_english);
            mDetailChinese=itemView.findViewById(R.id.manager_detail_item_chinese);
        }
    }



    public void setOnManagerDetailItemClickListner(onManagerDetailItemClickListner onManagerDetailItemClickListner) {
        mOnManagerDetailItemClickListner = onManagerDetailItemClickListner;
    }

    //点击后 --> 详情页
    public interface onManagerDetailItemClickListner{
        void onManagerDetailItemClick(int pos,List<Words> mWords);
    }

    //长按 --> 删除

    public void setManagerDetailIteLongmClickListner(onManagerDetailIteLongmClickListner managerDetailIteLongmClickListner) {
        mManagerDetailIteLongmClickListner = managerDetailIteLongmClickListner;
    }

    public interface onManagerDetailIteLongmClickListner{
        void onManagerDetailItemLongClick(int pos, List<Words> mWords,View itemView);
    }
}
