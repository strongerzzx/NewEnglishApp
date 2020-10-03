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

public class HomeActivity extends AppCompatActivity {
    private ViewPager mHomePager;
    private BottomNavigationView mBottomNav;

    List<Fragment> mFragmentList=new ArrayList<>();
    private HomePagerAdapter mAdapter;
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
    }

    private void initData() {
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new CollectionFragment());
        mFragmentList.add(new OtherFragment());
    }

    private void initView() {
        mHomePager = findViewById(R.id.home_view_pager);
        mBottomNav = findViewById(R.id.bottom_nav);
        mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mAdapter.setList(mFragmentList);
        mHomePager.setAdapter(mAdapter);

    }
}
