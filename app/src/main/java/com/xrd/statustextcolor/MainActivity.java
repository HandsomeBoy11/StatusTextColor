package com.xrd.statustextcolor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.xrd.statustextcolor.Base.BaseAcitivity;
import com.xrd.statustextcolor.Base.BaseFragment;
import com.xrd.statustextcolor.Ui.MainFragment.MainHomeFragment;
import com.xrd.statustextcolor.Ui.MainFragment.MainSeconedFragment;
import com.xrd.statustextcolor.Utitls.StatusBarUtil;
import com.xrd.statustextcolor.weight.TabBar;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseAcitivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.tab_bar)
    TabBar tabBar;
    private ArrayList<BaseFragment> fragments;
    private int currentTabPosition;
    private MainHomeFragment homeFragment;
    private MainSeconedFragment seconedFragment;
    private MainHomeFragment threadFragment;
    private MainSeconedFragment fourFragment;

    @Override
    public int getLayoutResuout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initFragment(savedInstanceState);
        tabBar.setOnItemChangedListener(new TabBar.OnItemChangedListener() {
            @Override
            public void onItemChecked(int position) {
            changeFragment(position);
        }
    });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tabIndex", currentTabPosition);
    }

    /**
     * 初始化fragment
     */
    private void initFragment(Bundle savedInstanceState) {
        fragments = new ArrayList<>();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        currentTabPosition = 0;
        if (savedInstanceState != null) {
            homeFragment = ((MainHomeFragment) manager.findFragmentByTag("one"));
            seconedFragment = ((MainSeconedFragment) manager.findFragmentByTag("two"));
            threadFragment = ((MainHomeFragment) manager.findFragmentByTag("three"));
            fourFragment = ((MainSeconedFragment) manager.findFragmentByTag("four"));
            currentTabPosition = (int) savedInstanceState.get("tabIndex");
        } else {
            homeFragment = new MainHomeFragment();
            seconedFragment = new MainSeconedFragment();
            threadFragment = new MainHomeFragment();
            fourFragment = new MainSeconedFragment();
            fragments.add(homeFragment);
            fragments.add(seconedFragment);
            fragments.add(threadFragment);
            fragments.add(fourFragment);
            transaction.add(R.id.fl_container, homeFragment, "one");
            transaction.add(R.id.fl_container, seconedFragment, "two");
            transaction.add(R.id.fl_container, threadFragment, "three");
            transaction.add(R.id.fl_container, fourFragment, "four");
        }
        transaction.commit();
        changeFragment(currentTabPosition);
    }

    /**
     * 切换fragment
     *
     * @param position
     */
    public void changeFragment(int position) {
        if(position%2==0){
//                    StatusBarUtil.setStatusBarColor(MainActivity.this,ContextCompat.getColor(MainActivity.this,R.color.main_color));
            StatusBarUtil.setStatusBarDarkTheme(MainActivity.this,false);
        }else{
//                    StatusBarUtil.setStatusBarColor(MainActivity.this,ContextCompat.getColor(MainActivity.this,R.color.white));
            StatusBarUtil.setStatusBarDarkTheme(MainActivity.this,true);
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (position == i) {
                transaction.show(fragments.get(i));
            } else {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.commitAllowingStateLoss();
        currentTabPosition = position;
    }
}
