package com.lyl.wanandroid.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Create By: lyl
 * Date: 2019-07-16 09:20
 */
public class LiveDataBus {

    private static LiveDataBus liveDataBus = new LiveDataBus();

    private Map<String, BusMutableLiveData<Object>> bus;

    public static LiveDataBus getInstance() {
        return liveDataBus;
    }

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    public synchronized <T> BusMutableLiveData<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new BusMutableLiveData<>());
        }
        return (BusMutableLiveData<T>) bus.get(key);
    }

    public static class BusMutableLiveData<T> extends MutableLiveData {
        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
            super.observe(owner, observer);
            try {
                hook(observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void hook(Observer observer) throws Exception {
            Class liveDataClass = LiveData.class;
            Field mObservers = liveDataClass.getDeclaredField("mObservers");
            mObservers.setAccessible(true);

            Class<?> mObserversClass = mObservers.get(this).getClass();

            Method mapget = mObserversClass.getDeclaredMethod("get", Object.class);
            mapget.setAccessible(true);

            Object invokeEntry = mapget.invoke(mObservers.get(this), observer);

            Object mapvalue = null;
            if (invokeEntry != null && invokeEntry instanceof Map.Entry) {
                mapvalue=((Map.Entry)invokeEntry).getValue();
            }

            if (mapvalue==null){
                throw new NullPointerException("null");
            }

            //todo 不知道为什么要getSuperclass
            Class<?> calss=mapvalue.getClass().getSuperclass();

            Field mLastVersion=calss.getDeclaredField("mLastVersion");
            mLastVersion.setAccessible(true);

            Field mVersion=liveDataClass.getDeclaredField("mVersion");
            mVersion.setAccessible(true);
            Object o=mVersion.get(this);

            mLastVersion.set(mapvalue,o);
        }

    }
}
