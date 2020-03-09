package com.example.lib_comon.core.globestate;

import org.greenrobot.eventbus.EventBus;

public class MBus {

    private EventBus mEventBus;

    private MBus() {
        mEventBus = EventBus.getDefault();
    }

    private static class Inner {
        private static MBus mBus = new MBus();
    }

    public MBus getBus() {
        return Inner.mBus;
    }

    public void post(Msg msg) {
        mEventBus.post(msg);
    }

    public void postSticky(Msg msg) {
        mEventBus.postSticky(msg);
    }

    public void register(Object obj) {
        mEventBus.register(obj);
    }

    public void unregister(Object obj) {
        mEventBus.unregister(obj);
    }
}
