package com.wizy.testapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());

    }
}
