package com.smontiel.kanbanboard;

import android.app.Application;
import android.content.Context;

import ollie.Ollie;

/**
 * Created by Salvador Montiel on 15/11/17.
 */
public class App extends Application {
    private static final int DB_VERSION = 1;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        this.context = this.getApplicationContext();

        Ollie.with(context)
                .setName("Kanban.db")
                .setVersion(DB_VERSION)
                .setLogLevel(Ollie.LogLevel.FULL)
                .setCacheSize(Ollie.DEFAULT_CACHE_SIZE)
                .init();
    }

    public Context getContext() {
        return context;
    }
}
