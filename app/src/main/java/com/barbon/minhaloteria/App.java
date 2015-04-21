package com.barbon.minhaloteria;

import android.app.Application;
import android.content.Context;
import android.speech.tts.TextToSpeech;

/**
 * Created by Barbon on 20/04/2015.
 */
public class App extends Application implements TextToSpeech.OnInitListener {

    private static Context context;

    @Override
    public void onInit(int status) {

    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context mContext) {
        context = mContext;
    }
}
