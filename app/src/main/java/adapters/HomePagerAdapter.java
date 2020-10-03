package adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zzx on 2020/10/2 15:37
 * <p>
 * 作用： xxxx
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mList=new ArrayList<>();
    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //一定要注释掉这个 不然每次都会重新刷新Fragment
        //super.destroyItem(container, position, object);
    }

    public void setList(List<Fragment> fragmentList) {
        if (fragmentList != null) {
            mList.addAll(fragmentList);
        }
    }
}
