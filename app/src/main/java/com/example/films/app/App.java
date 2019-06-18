package com.example.films.app;

import android.app.Application;
import android.content.Context;
import androidx.room.Room;
import com.example.films.features.films.data.db.FilmDatabase;

public class App extends Application {

    private static FilmDatabase dataBase;
    private static Context context;

    public static FilmDatabase getDataBase() {
        return dataBase;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(getApplicationContext(), FilmDatabase.class, "app-database").build();
        context = getApplicationContext();
    }
}
