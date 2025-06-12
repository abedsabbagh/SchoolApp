package com.schoolapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor ed;

    public SessionManager(Context c) {
        prefs = c.getSharedPreferences("session_prefs", Context.MODE_PRIVATE);
        ed = prefs.edit();
    }

    public void saveSession(int uid, String token) {
        ed.putInt("uid", uid);
        ed.putString("token", token);
        ed.apply();
    }

    public boolean isLoggedIn() {
        return prefs.contains("token");
    }

    public int getUid() {
        return prefs.getInt("uid", -1);
    }

    public String getToken() {
        return prefs.getString("token", "");
    }

    public void clear() {
        ed.clear().apply();
    }
}
