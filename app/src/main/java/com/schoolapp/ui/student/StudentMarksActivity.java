package com.schoolapp.ui.student;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.schoolapp.databinding.ActivityStudentMarksBinding;
import com.schoolapp.network.ApiService;
import com.schoolapp.models.MarkItem;
import com.schoolapp.utils.SessionManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class StudentMarksActivity extends AppCompatActivity {

    private ActivityStudentMarksBinding vb;
    private final List<MarkItem> list = new ArrayList<>();
    private MarkAdapter ad;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        vb = ActivityStudentMarksBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        ad = new MarkAdapter(list);
        vb.rvMarks.setLayoutManager(new LinearLayoutManager(this));
        vb.rvMarks.setAdapter(ad);

        SessionManager sm = new SessionManager(this);
        ApiService api = new ApiService(this, sm.getToken());

        api.getStudentMarks(sm.getUid(), new ApiService.Callback() {
            @Override
            public void onSuccess(org.json.JSONObject res) {
                list.clear();
                JSONArray arr = res.optJSONArray("marks");
                if (arr != null) {
                    for (int i = 0; i < arr.length(); i++) {
                        var o = arr.optJSONObject(i);
                        list.add(new MarkItem(
                                o.optString("subject"),
                                o.optString("exam_type"),
                                o.optString("mark")
                        ));
                    }
                }
                ad.notifyDataSetChanged();
            }

            @Override
            public void onError(String e) {
                Toast.makeText(StudentMarksActivity.this, e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
