package com.schoolapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF = "School_session";
    private static final String K_TOKEN = "jwt", K_UID = "uid", K_ROLE = "role";
    private final SharedPreferences sp;

    public SessionManager(Context c) {
        sp = c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public void save(String t, int uid, String role) {
        sp.edit().putString(K_TOKEN, t).putInt(K_UID, uid).putString(K_ROLE, role).apply();
    }

    public String token() {
        return sp.getString(K_TOKEN, null);
    }

    public int uid() {
        return sp.getInt(K_UID, -1);
    }

    public String role() {
        return sp.getString(K_ROLE, null);
    }
}
