package adapters;

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

import beans.OtherBeans;

/**
 * 作者：zzx on 2020/10/13 20:04
 * <p>
 * 作用： xxxx
 */
public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.InnerViewHolder> {

    private List<OtherBeans> mOtherList=new ArrayList<>();
    private onOtherItemClickListner mOnOtherItemClickListner;

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_item_rv_view, parent, false);
        return new InnerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        OtherBeans otherBeans = mOtherList.get(position);
        holder.smallTitle.setText(otherBeans.getSmallTitle());
        holder.title.setText(otherBeans.getTitle());

        //TODO:显示当前任务完成进度
        holder.currentProgress.setText(otherBeans.getCurrentProgress()+"");
        holder.finialProgress.setText("/"+otherBeans.getFinalyProgress());

        Glide.with(holder.itemView.getContext()).load(otherBeans.getImag()).into(holder.otherIv);



        if (mOnOtherItemClickListner != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnOtherItemClickListner.onOtherItemClick(position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mOtherList==null?0:mOtherList.size();
    }

    public void setData(List<OtherBeans> otherList) {
        if (otherList != null) {
            mOtherList.addAll(otherList);
        }
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView title;//正常内容
        private TextView smallTitle;//小标题
        private TextView currentProgress;//当前点击的次数
        private TextView finialProgress;//一共可点击的次数
        private ImageView otherIv;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.other_item_content_title);
            smallTitle=itemView.findViewById(R.id.other_item_content_small_title);
            currentProgress=itemView.findViewById(R.id.other_item_content_currnt_progress);
            finialProgress=itemView.findViewById(R.id.other_item_content_finaly_progress);
            otherIv=itemView.findViewById(R.id.other_item_iv);
        }
    }

    public void setOnOtherItemClickListner(onOtherItemClickListner onOtherItemClickListner) {
        mOnOtherItemClickListner = onOtherItemClickListner;
    }

    public interface onOtherItemClickListner{
        void onOtherItemClick(int position);
    }
}
