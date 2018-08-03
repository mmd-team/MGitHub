package com.mmdteam.mgithub.components.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.components.find.TabFindFragment;
import com.mmdteam.mgithub.components.home.TabHomeFragment;
import com.mmdteam.mgithub.components.mine.TabMineFragment;
import com.mmdteam.mgithub.ui.activity.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.homeTitle)
    QMUITopBar homeTitle;
    @BindView(R.id.homePages)
    ViewPager homePages;
    @BindView(R.id.homeTabs)
    QMUITabSegment homeTabs;


    @Override
    protected int getContent() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        homeTitle.setTitle(R.string.app_name);
        homeTabs.addTab(new QMUITabSegment.Tab(
                getAppComponent().getIcon().getDrawable(getString(R.string.icon_home)).sizeDp(20),
                getAppComponent().getIcon().getDrawable(getString(R.string.icon_home)).sizeDp(20),
                "首页",
                true));
        homeTabs.addTab(new QMUITabSegment.Tab(
                getAppComponent().getIcon().getDrawable(getString(R.string.icon_find)).sizeDp(20),
                getAppComponent().getIcon().getDrawable(getString(R.string.icon_find)).sizeDp(20),
                "发现",
                true));
        homeTabs.addTab(new QMUITabSegment.Tab(
                getAppComponent().getIcon().getDrawable(getString(R.string.icon_me)).sizeDp(20),
                getAppComponent().getIcon().getDrawable(getString(R.string.icon_me)).sizeDp(20),
                "我",
                true));


        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(TabHomeFragment.instance());
        fragments.add(new TabFindFragment());
        fragments.add(new TabMineFragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        homePages.setAdapter(adapter);

        homeTabs.setupWithViewPager(homePages, false);

    }


}
