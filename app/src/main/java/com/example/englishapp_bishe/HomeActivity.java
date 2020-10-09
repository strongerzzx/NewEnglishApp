package com.example.englishapp_bishe;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import adapters.HomePagerAdapter;
import fragments.CollectionFragment;
import fragments.HomeFragment;
import fragments.OtherFragment;
import interfaces.IHomeCallback;
import presenters.HomePresent;
import utils.LogUtil;

public class HomeActivity extends AppCompatActivity implements IHomeCallback {
    private static final String TAG = "HomeActivity";
    private ViewPager mHomePager;
    private BottomNavigationView mBottomNav;

    List<Fragment> mFragmentList=new ArrayList<>();
    private HomePagerAdapter mAdapter;
    private HomeFragment mHomeFragment;
    private CollectionFragment mCollectionFragment;
    private OtherFragment mOtherFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initData();

        initView();



        initEvent();
    }

    private void initEvent() {
        //pager动 --> bottomNav跟随动
        mHomePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNav.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //点击bottomNav 改变pager
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                switch (itemId) {
                    case R.id.homeFragment:
                        mHomePager.setCurrentItem(0);
                        break;
                    case R.id.collectionFragment:
                        mHomePager.setCurrentItem(1);
                        break;
                    case R.id.otherFragment:
                        mHomePager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });


        HomePresent.getPresent().regesiterHomeView(this);

    }

    private void initData() {
        mHomeFragment = new HomeFragment();
        mCollectionFragment = new CollectionFragment();
        mOtherFragment = new OtherFragment();
        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mCollectionFragment);
        mFragmentList.add(mOtherFragment);
    }

    private void initView() {
        mHomePager = findViewById(R.id.home_view_pager);
        mBottomNav = findViewById(R.id.bottom_nav);
        mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mAdapter.setList(mFragmentList);
        mHomePager.setAdapter(mAdapter);

    }

    @Override
    public void showSingle(String english, String chinese) {

    }

    @Override
    public void showBookName(String bookName) {
        LogUtil.d(TAG,"bookname -->"+bookName);
    }
}
