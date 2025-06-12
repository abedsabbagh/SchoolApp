package com.schoolapp.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton inst;
    private final RequestQueue q;

    private VolleySingleton(Context c) {
        q = Volley.newRequestQueue(c.getApplicationContext());
    }

    public static synchronized VolleySingleton get(Context c) {
        if (inst == null) inst = new VolleySingleton(c);
        return inst;
    }

    public RequestQueue queue() {
        return q;
    }
}
