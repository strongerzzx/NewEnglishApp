package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.englishapp_bishe.R;

import java.util.ArrayList;
import java.util.List;

import bases.BaseAppciation;
import beans.SelectBookBeans;

/**
 * 作者：zzx on 2020/9/29 14:38
 * <p>
 * 作用： xxxx
 */
public class SelectorBookAdapter extends RecyclerView.Adapter<SelectorBookAdapter.InnerViewHolder> {
    private List<SelectBookBeans.CatesBean.BookListBean> mList=new ArrayList<>();
    private onSelectorItemClickListener mOnSelectorItemClickListener;

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_book_item, parent, false);
        return new InnerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        SelectBookBeans.CatesBean.BookListBean bookListBean = mList.get(position);

        String originName = bookListBean.getBookOrigin().getOriginName();//来源
        String title = bookListBean.getTitle();//书名
        String cover = bookListBean.getCover();//图片
        int size = bookListBean.getWordNum();//词量

        holder.tvWordSum.setText(String.valueOf(size));
        holder.tvSrcName.setText(originName);
        holder.tvTitle.setText(title);

        RequestOptions options=new RequestOptions().skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(BaseAppciation.getContext()).load(cover).apply(options).into(holder.ivTitle);

        if (mOnSelectorItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnSelectorItemClickListener.onSelectorItemClicker(position,title,size);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public void setData(List<SelectBookBeans.CatesBean.BookListBean> bookListBeans) {
        if (bookListBeans != null) {
            this.mList.addAll(bookListBeans);
        }
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivTitle;
        private TextView tvTitle;
        private TextView tvSrcName;
        private TextView tvWordSum;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTitle=itemView.findViewById(R.id.select_iv);
            tvTitle=itemView.findViewById(R.id.select_tv_title);
            tvSrcName=itemView.findViewById(R.id.select_tv_src_name);
            tvWordSum=itemView.findViewById(R.id.select_tv_word_count);
        }
    }


    public void setOnSelectorItemClickListener(onSelectorItemClickListener onSelectorItemClickListener) {
        mOnSelectorItemClickListener = onSelectorItemClickListener;
    }

    public interface onSelectorItemClickListener{
        void  onSelectorItemClicker(int position,String title,int size);
    }
}
