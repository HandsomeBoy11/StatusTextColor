package com.xrd.statustextcolor.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by WJ on 2019/4/29.
 */

public abstract class BaseFragment extends Fragment {
    private View rootView;
    private Unbinder unbinder;
    public Activity mActicity;
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        if(context instanceof Activity){
            mActicity= ((Activity) context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    //获取布局文件
    protected abstract int getLayoutResource();
    //初始化view
    protected abstract void initView();
    protected  void initData(){};

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
