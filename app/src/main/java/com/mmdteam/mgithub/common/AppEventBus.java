package com.mmdteam.mgithub.common;

import org.greenrobot.eventbus.EventBus;

/**
 * 事件总线
 */
public enum AppEventBus {

    INSTANCE;

    AppEventBus() {
        init();
    }

    private EventBus eventBus;

    private void init() {
        eventBus = EventBus.builder().installDefaultEventBus();
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
