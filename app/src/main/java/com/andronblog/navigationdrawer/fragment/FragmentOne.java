package com.andronblog.navigationdrawer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andronblog.navigationdrawer.NavFragmentActivity;
import com.andronblog.navigationdrawer.R;

public class FragmentOne extends NavFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_one;
    }

    public String getName() {
        //return getResources().getString(R.string.fragment1);
        return null; // The app name will be displayed by default
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.findViewById(R.id.btn_fragment_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavFragmentActivity)getActivity()).displayFragment(FragmentTwo.class);
            }
        });
        return view;
    }
}
