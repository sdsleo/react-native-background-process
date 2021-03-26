package br.com.loreweb.backgroundprocess;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

public class BackgroundProcessEventService extends HeadlessJsTaskService {
    @Nullable
    protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        Bundle extras = intent.getExtras();
        WritableMap data = Arguments.createMap();
        return new HeadlessJsTaskConfig(
            "BackgroundProcess",
            extras != null ? Arguments.fromBundle(extras) : data,
            20000,
            true
        );
    }
}