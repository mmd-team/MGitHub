package com.mmdteam.mgithub.components.home.core;

import android.support.annotation.NonNull;
import android.view.View;

import com.mmdteam.mgithub.components.home.TabHomeFragment;
import com.mmdteam.mgithub.model.Event;

import java.util.ArrayList;

import javax.inject.Inject;

import io.objectbox.BoxStore;

public class HomeView {


    private TabHomeFragment homeFragment;

    @Inject
    public HomeView(TabHomeFragment homeFragment, BoxStore boxStore) {
        this.homeFragment = homeFragment;

    }


    public void onCreate(@NonNull View view) {

    }

    public void onDestroy() {

    }


    public void getEvents(ArrayList<Event> events) {
        homeFragment.events(events);
    }
}
