package com.schoolapp.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton instance;
    private final RequestQueue q;

    private VolleySingleton(Context c) {
        q = Volley.newRequestQueue(c.getApplicationContext());
    }

    public static synchronized VolleySingleton get(Context c) {
        if (instance == null)
            instance = new VolleySingleton(c);
        return instance;
    }

    public RequestQueue queue() {
        return q;
    }

}
