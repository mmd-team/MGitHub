package com.mmdteam.mgithub.components.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmdteam.mgithub.App;
import com.mmdteam.mgithub.AppData;
import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.components.home.core.HomePresenter;
import com.mmdteam.mgithub.components.home.core.HomeView;
import com.mmdteam.mgithub.components.home.dagger.DaggerHomeComponent;
import com.mmdteam.mgithub.components.home.dagger.HomeModule;
import com.mmdteam.mgithub.components.home.list.EventAdapter;
import com.mmdteam.mgithub.model.Event;
import com.mmdteam.mgithub.ui.fragment.base.BaseFragment;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class TabHomeFragment extends BaseFragment {


    @BindView(R.id.home)
    TextView home;

    @BindView(R.id.main_content)
    RecyclerView mainListView;
    private QMUITipDialog tipDialog;

    private EventAdapter adapter;

    @SuppressLint("ValidFragment")
    private TabHomeFragment() {
    }

    @SuppressLint("StaticFieldLeak")
    private static TabHomeFragment instance = new TabHomeFragment();

    public static Fragment instance() {
        if (instance == null) {
            instance = new TabHomeFragment();
        }
        return instance;
    }


    @Inject
    HomeView view;
    @Inject
    HomePresenter presenter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_home;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    protected void initFragment(View view, Bundle savedInstanceState) {
        super.initFragment(view, savedInstanceState);

        tipDialog = new QMUITipDialog.Builder(getActivity())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        adapter = new EventAdapter();
        mainListView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHomeComponent.builder().appComponent(App.getAppComponent()).homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        view.onDestroy();
    }


    @Override
    public void onFragmentShowed() {
        super.onFragmentShowed();
    }

    public void getUserEvents(String token, int id) {
        presenter.getUserEvents(token, id);
    }


    public void events(ArrayList<Event> events) {
        tipDialog.dismiss();
        adapter.swapAdapter(events);
    }

    @OnClick(R.id.home)
    void home() {

        if (AppData.INSTANCE.getAuthUser() != null) {
            home.setText(AppData.INSTANCE.getAccessToken());
            tipDialog.show();
            getUserEvents(AppData.INSTANCE.getAccessToken(), AppData.INSTANCE.getAuthUser().getId());
        }

        JSONObject jsonObject = new JSONObject();
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String time = dateFormat.format(new Date());
            jsonObject.put("start_time_new", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SensorsDataAPI.sharedInstance().track("testTime", jsonObject);
    }

}
