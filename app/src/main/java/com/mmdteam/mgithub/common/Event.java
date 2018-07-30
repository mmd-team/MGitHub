package com.mmdteam.mgithub.common;

public class Event {

    public static class NetChangedEvent {

        int preNetStatus;
        int curNetStatus;

        public NetChangedEvent(int preNetStatus, int curNetStatus) {
            this.preNetStatus = preNetStatus;
            this.curNetStatus = curNetStatus;
        }
    }

}
