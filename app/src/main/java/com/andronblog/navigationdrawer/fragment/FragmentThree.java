package com.andronblog.navigationdrawer.fragment;

import com.andronblog.navigationdrawer.R;

public class FragmentThree extends NavFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_three;
    }

    public String getName() {
        return getResources().getString(R.string.fragment3);
    }
}