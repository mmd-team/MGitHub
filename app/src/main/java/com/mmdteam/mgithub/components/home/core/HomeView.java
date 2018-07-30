package com.mmdteam.mgithub.components.home.core;

import com.mmdteam.mgithub.components.home.TabHomeFragment;
import com.mmdteam.mgithub.model.Event;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeView {


    private TabHomeFragment homeFragment;

    @Inject
    public HomeView(TabHomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public void getEvents(ArrayList<Event> events) {
        homeFragment.getEvents(events);
    }
}
