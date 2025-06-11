package com.schoolapp.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiService {
    private static final String BASE = "https://10.0.2.2/api/";
    private final Context ctx;
    private final String token;

    public ApiService(Context ctx, String token) {
        this.ctx = ctx;
        this.token = token;
    }

    public void getStudentMarks(int uid, Object marks) {
    }

    public interface Callback {
        void onSuccess(JSONObject data);

        void onError(String msg);

    }

    public void getStudentSchedule(int uid, Callback cb) {
        String url = BASE + "Schedule/user/" + uid + ".php";

        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET, url, null,
                rsp -> {
                    if (rsp.optBoolean("Success"))
                        cb.onSuccess(rsp.optJSONObject("data"));
                    else cb.onError(rsp.optString("message"));
                },
                err -> cb.onError(err.toString())
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> h = new HashMap<>();
                h.put("Content-Type", "application/json");
                if (token != null)
                    h.put("Authorization", "Bearer" + token);
                return h;
            }
        };
    }
}
