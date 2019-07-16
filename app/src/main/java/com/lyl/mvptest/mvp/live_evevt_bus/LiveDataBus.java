package com.lyl.mvptest.mvp.live_evevt_bus;

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
 * User: lyl
 * Date: 2019-07-16 09:20
 */
public class LiveDataBus {

    private static LiveDataBus liveDataBus = new LiveDataBus();

    private Map<String, MutableLiveData<Object>> bus;

    public static LiveDataBus getInstance() {
        return liveDataBus;
    }

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    public synchronized <T> MutableLiveData<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new MutableLiveData<>());
        }
        return (MutableLiveData<T>) bus.get(key);
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

            Class<LiveData> liveDataClass = LiveData.class;

            Field mObserverField = liveDataClass.getDeclaredField("mObservers");

            mObserverField.setAccessible(true);

            Object mObservers = mObserverField.get(this);

            Class<?> aClass = mObservers.getClass();

            Method get = aClass.getDeclaredMethod("get", Object.class);

            get.setAccessible(true);

            Object invokeEntry = get.invoke(mObservers, observer);

            Object observrtWrapper = null;

            if (invokeEntry != null && invokeEntry instanceof Map.Entry) {
                observrtWrapper = ((Map.Entry) invokeEntry).getValue();
            }

            if (observrtWrapper == null) {
                throw new NullPointerException("observrtWrapper 不能为空");
            }

            Class<?> superclass = observrtWrapper.getClass().getSuperclass();

            Field mLastVersion = superclass.getDeclaredField("mLastVersion");

            mLastVersion.setAccessible(true);

            Field mVersion = liveDataClass.getDeclaredField("mVersion");

            mVersion.setAccessible(true);

            Object o = mVersion.get(this);

            mLastVersion.set(observrtWrapper, o);

        }

    }
}
