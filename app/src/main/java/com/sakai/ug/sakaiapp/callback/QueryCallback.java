package com.sakai.ug.sakaiapp.callback;


import android.support.annotation.Nullable;

public interface QueryCallback<Type> {

    default void onInit() {

    }

    void onError(@Nullable String errorMessage);

    void onSuccess(@Nullable Type response);
}
