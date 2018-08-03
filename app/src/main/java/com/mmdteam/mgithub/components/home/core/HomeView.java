package com.mmdteam.mgithub.components.home.core;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmdteam.mgithub.AppData;
import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.components.home.TabHomeFragment;
import com.mmdteam.mgithub.components.home.list.EventAdapter;
import com.mmdteam.mgithub.dao.object.LoginUserDao;
import com.mmdteam.mgithub.model.Event;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.objectbox.BoxStore;

public class HomeView {


    private Unbinder unbinder;
    private TabHomeFragment homeFragment;


    @BindView(R.id.home)
    TextView home;

    @BindView(R.id.main_content)
    RecyclerView mainListView;
    private QMUITipDialog tipDialog;

    private EventAdapter adapter;
    private LoginUserDao userDao;

    @Inject
    public HomeView(TabHomeFragment homeFragment, BoxStore boxStore) {
        this.homeFragment = homeFragment;
        userDao = new LoginUserDao(boxStore);
    }


    public void onCreate(@NonNull View view) {
        unbinder = ButterKnife.bind(this, view);
        tipDialog = new QMUITipDialog.Builder(homeFragment.getActivity())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        adapter = new EventAdapter();
        mainListView.setAdapter(adapter);
    }

    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @OnClick(R.id.home)
    void home() {

        if (AppData.INSTANCE.getAuthUser() != null) {
            home.setText(AppData.INSTANCE.getAccessToken());
            home.setText(userDao.getQuery().findFirst().getPassword());
            tipDialog.show();
            homeFragment.getUserEvents(AppData.INSTANCE.getAccessToken(), AppData.INSTANCE.getAuthUser().getId());
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


    public void getEvents(ArrayList<Event> events) {
        tipDialog.dismiss();
        adapter.swapAdapter(events);
    }
}
