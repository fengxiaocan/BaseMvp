package com.app.mvp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MvpStore {

    private final HashMap<String, IBasePresenter> mMap = new HashMap<>();

    final void put(String key, IBasePresenter p) {
        IBasePresenter oldP = mMap.put(key, p);
        if (oldP != null) {
            oldP.detachView();
        }
    }

    final IBasePresenter get(String key) {
        return mMap.get(key);
    }

    Set<String> keys() {
        return mMap.keySet();
    }

    public final void clear() {
        mMap.clear();
    }


}
