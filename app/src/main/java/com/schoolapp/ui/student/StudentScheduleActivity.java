package com.schoolapp.ui.student;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.JsonObject;
import com.schoolapp.databinding.ActivityStudentScheduleBinding;
import com.schoolapp.models.ScheduleItem;
import com.schoolapp.network.ApiService;
import com.schoolapp.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentScheduleActivity extends AppCompatActivity {
    private ActivityStudentScheduleBinding vb;
    private final List<ScheduleItem> data = new ArrayList<>();
    private ScheduleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vb = ActivityStudentScheduleBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        adapter = new ScheduleAdapter(data);
        vb.rvSchedule.setLayoutManager(new LinearLayoutManager(this));
        vb.rvSchedule.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        SessionManager sm = new SessionManager(this);
        ApiService api = new ApiService(this, sm.token());

        api.getStudentSchedule(sm.uid(), new ApiService.Callback() {

            @Override
            public void onSuccess(JSONObject result) {
                data.clear();

                JSONArray arr = result.optJSONArray("schedule");
                if (arr != null) {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject o = arr.optJSONObject(i);
                        data.add(new ScheduleItem(
                                o.optString("subject"),
                                o.optInt("day"),
                                o.optString("start"),
                                o.optString("end")
                        ));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(StudentScheduleActivity.this, msg, Toast.LENGTH_SHORT).show();

            }


        });
    }
}

