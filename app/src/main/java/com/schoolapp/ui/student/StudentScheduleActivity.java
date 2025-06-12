package com.schoolapp.ui.student;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.schoolapp.databinding.ActivityStudentScheduleBinding;
import com.schoolapp.network.ApiService;
import com.schoolapp.models.ScheduleItem;
import com.schoolapp.utils.SessionManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class StudentScheduleActivity extends AppCompatActivity {

    private ActivityStudentScheduleBinding vb;
    private final List<ScheduleItem> list = new ArrayList<>();
    private ScheduleAdapter ad;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        vb = ActivityStudentScheduleBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        ad = new ScheduleAdapter(list);
        vb.rvSchedule.setLayoutManager(new LinearLayoutManager(this));
        vb.rvSchedule.setAdapter(ad);

        SessionManager sm = new SessionManager(this);
        ApiService api = new ApiService(this, sm.getToken());

        api.getStudentSchedule(sm.getUid(), new ApiService.Callback() {
            @Override
            public void onSuccess(org.json.JSONObject res) {
                list.clear();
                JSONArray arr = res.optJSONArray("schedule");
                if (arr != null) {
                    for (int i = 0; i < arr.length(); i++) {
                        var o = arr.optJSONObject(i);
                        list.add(new ScheduleItem(
                                o.optString("subject"),
                                o.optInt("day"),
                                o.optString("start"),
                                o.optString("end")
                        ));
                    }
                }
                ad.notifyDataSetChanged();
            }

            @Override
            public void onError(String e) {
                Toast.makeText(StudentScheduleActivity.this, e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
