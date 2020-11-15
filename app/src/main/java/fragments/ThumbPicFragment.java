package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.englishapp_bishe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ThumbPicFragment extends Fragment {

    private View mInflate;
    private Unbinder mBind;

    @BindView(R.id.thumb_show_pic)
    public ImageView mThumbPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflate = LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_thumb_pic, container, false);

        mBind = ButterKnife.bind(this, mInflate);

        return mInflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}