package com.andronblog.navigationdrawer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andronblog.navigationdrawer.NavFragmentActivity;
import com.andronblog.navigationdrawer.R;

public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        view.findViewById(R.id.btn_fragment_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavFragmentActivity)getActivity()).displayFragment(FragmentTwo.class);
            }
        });
        return view;
    }
}
