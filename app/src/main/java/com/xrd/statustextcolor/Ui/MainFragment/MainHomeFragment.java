package com.xrd.statustextcolor.Ui.MainFragment;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xrd.statustextcolor.Base.BaseFragment;
import com.xrd.statustextcolor.R;

import butterknife.BindView;


/**
 * Created by WJ on 2019/4/29.
 */

public class MainHomeFragment extends BaseFragment {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv1)
    ImageView iv1;

    @Override
    protected int getLayoutResource() {
        return R.layout.main_home_fragment;
    }

    @Override
    protected void initView() {
        Glide.with(getActivity()).load("http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg").into(iv);
        Glide.with(getActivity()).load("http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg").into(iv1);
    }

}
