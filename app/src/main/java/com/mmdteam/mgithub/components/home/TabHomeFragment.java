package com.mmdteam.mgithub.components.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mmdteam.mgithub.App;
import com.mmdteam.mgithub.AppData;
import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.components.home.core.HomePresenter;
import com.mmdteam.mgithub.components.home.core.HomeView;
import com.mmdteam.mgithub.components.home.dagger.DaggerHomeComponent;
import com.mmdteam.mgithub.components.home.dagger.HomeModule;
import com.mmdteam.mgithub.model.Event;
import com.mmdteam.mgithub.ui.fragment.base.BaseFragment;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class TabHomeFragment extends BaseFragment {


    @Inject
    HomeView view;
    @Inject
    HomePresenter presenter;
    @BindView(R.id.home)
    TextView home;

    private QMUITipDialog tipDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_home;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        tipDialog = new QMUITipDialog.Builder(getActivity())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHomeComponent.builder().appComponent(App.getAppComponent()).homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @OnClick(R.id.home)
    void home(View view) {
        if (AppData.INSTANCE.getAuthUser() != null) {
            home.setText(AppData.INSTANCE.getAccessToken());
            tipDialog.show();
            presenter.getUserEvents(AppData.INSTANCE.getAccessToken(), AppData.INSTANCE.getAuthUser().getId());
        }
    }

    @Override
    public void onFragmentShowed() {
        super.onFragmentShowed();
    }

    public void getEvents(ArrayList<Event> events) {
        tipDialog.dismiss();
        home.setText(events.toString());
    }
}
