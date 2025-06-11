package com.schoolapp.ui.student;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.schoolapp.databinding.ActivityStudentMarksBinding;
import com.schoolapp.network.ApiService;
import com.schoolapp.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentMarksActivity extends AppCompatActivity {
    private ActivityStudentMarksBinding vb;
    private final List<JSONObject> data = new ArrayList<>();
    private MarkAdapter adapter;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        vb = ActivityStudentMarksBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        adapter = new MarkAdapter(data);
        vb.rvMarks.setLayoutManager(new LinearLayoutManager(this));
        vb.rvMarks.setAdapter(adapter);

        loadData();
    }


    private void loadData() {
        SessionManager sm = new SessionManager(this);
        ApiService api = new ApiService(this, sm.token());

        api.getStudentMarks(sm.uid(), new ApiService.Callback() {
            @Override
            public void onSuccess(JSONObject result) {
                data.clear();
                JSONArray arr = result.optJSONArray("marks");
                if (arr != null) {
                    for (int i = 0; i < arr.length(); i++) {
                        data.add(arr.optJSONObject(i));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(StudentMarksActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

