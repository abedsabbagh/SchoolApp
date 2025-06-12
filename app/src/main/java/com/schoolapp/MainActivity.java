package com.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.schoolapp.databinding.ActivityMainBinding;
import com.schoolapp.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding vb;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        vb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        SessionManager sm = new SessionManager(this);

        vb.btnLogin.setOnClickListener(v -> {
            // لأغراض الاختبار فقط نقبل أي شيء وندخل مباشرة
            sm.saveSession(1, "DUMMY_TOKEN");
            startActivity(new Intent(this, com.schoolapp.ui.student.StudentActivity.class));
            finish();
        });
    }
}
