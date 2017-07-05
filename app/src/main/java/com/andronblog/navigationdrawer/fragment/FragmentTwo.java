package com.andronblog.navigationdrawer.fragment;


import com.andronblog.navigationdrawer.R;

public class FragmentTwo extends NavFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_two;
    }

    public String getName() {
        return getResources().getString(R.string.fragment2);
    }
}
