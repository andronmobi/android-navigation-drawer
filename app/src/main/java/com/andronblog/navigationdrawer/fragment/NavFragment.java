package com.andronblog.navigationdrawer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andronblog.navigationdrawer.NavFragmentActivity;


public abstract class NavFragment extends Fragment {

    public interface OnFragmentCreateViewListener {
        void onFragmentCreateViewDone(NavFragment fragment);
    }

    private OnFragmentCreateViewListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavFragmentActivity) {
            mListener = (OnFragmentCreateViewListener) context;
        }
    }

    protected abstract int getLayoutResId();
    public abstract String getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        mListener.onFragmentCreateViewDone(this);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
