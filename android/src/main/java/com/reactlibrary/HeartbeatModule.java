package com.reactlibrary;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

public class HeartbeatModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "BackgroundProcess";
    private static ReactApplicationContext reactContext;

    public HeartbeatModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Nonnull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void startService(
            String msgTitle,
            String msgBody,
            String smallIcon,
            String largeIcon,
            String colorIcon,
            int interval
    ) {
        Application applicationContext = (Application) reactContext.getApplicationContext();

        final Bundle bundle = new Bundle();

        Intent serviceIntent = new Intent(this.reactContext, HeartbeartService.class);
        serviceIntent.putExtra("msgTitle", msgTitle);
        serviceIntent.putExtra("msgBody", msgBody);
        serviceIntent.putExtra("smallIcon", smallIcon);
        serviceIntent.putExtra("largeIcon", largeIcon);
        serviceIntent.putExtra("colorIcon", colorIcon);
        serviceIntent.putExtra("interval", interval);

        this.reactContext.startService(serviceIntent);
    }

    @ReactMethod
    public void stopService() {
        this.reactContext.stopService(new Intent(this.reactContext, HeartbeartService.class));
    }
}
