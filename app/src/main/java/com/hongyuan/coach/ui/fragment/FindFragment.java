package com.hongyuan.coach.ui.fragment;
import android.view.View;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.CustomFragment;
import com.hongyuan.coach.ui.activity.EditPostActivity;
import com.hongyuan.coach.ui.view_page_adapter.FindViewPagerAdapter;

public class FindFragment extends CustomFragment {

    private TabLayout layoutMenu;
    private ViewPager mViewPager;

    private FindViewPagerAdapter meunAdapter;

    private ImageView releasePost;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView(View mView) {
        layoutMenu = mView.findViewById(R.id.layoutMenu);
        mViewPager = mView.findViewById(R.id.mViewPager);
        releasePost = mView.findViewById(R.id.releasePost);

        meunAdapter = new FindViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(1);
        releasePost.setOnClickListener(v -> startActivity(EditPostActivity.class,null));
    }
}
