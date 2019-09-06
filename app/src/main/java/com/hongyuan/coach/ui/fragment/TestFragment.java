package com.hongyuan.coach.ui.fragment;

import android.view.View;

import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomFragment;

public class TestFragment extends CustomFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initView(View mView) {
        setPromtView(SHOW_EMPTY);
    }
}
