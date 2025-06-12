package com.schoolapp.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class ApiService {

    public static final String BASE = "http://10.0.2.2/api/";

    private final Context ctx;
    private final String token;

    public ApiService(Context c, String t) {
        ctx = c;
        token = t;
    }

    public interface Callback {
        void onSuccess(JSONObject obj);

        void onError(String msg);
    }

    public void getStudentSchedule(int uid, Callback cb) {
        sendGet(BASE + "schedule.php?student_id=" + uid, cb);
    }

    public void getStudentMarks(int uid, Callback cb) {
        sendGet(BASE + "marks.php?student_id=" + uid, cb);
    }

    private void sendGet(String url, Callback cb) {
        JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET, url, null,
                cb::onSuccess, e -> cb.onError(e.toString()));
        VolleySingleton.get(ctx).queue().add(r);
    }
}
