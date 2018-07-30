package com.mmdteam.mgithub.components.mine;

import android.content.Intent;
import android.os.Bundle;

import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.components.login.LoginActivity;
import com.mmdteam.mgithub.ui.fragment.base.BaseFragment;

import butterknife.OnClick;

public class TabMineFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_mine;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @OnClick(R.id.login)
    void login() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().overridePendingTransition(R.anim.slide_in_right, 0);
    }

    @Override
    public void onFragmentShowed() {
        super.onFragmentShowed();
    }
}
