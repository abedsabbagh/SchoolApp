package com.schoolapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.schoolapp.databinding.ActivityMainBinding;
import com.schoolapp.ui.student.StudentMarksActivity;
import com.schoolapp.ui.student.StudentScheduleActivity;
import com.schoolapp.ui.student.SubmitAssignmentActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        vb.btnSchedule.setOnClickListener(v ->
                startActivity(new Intent(this, StudentScheduleActivity.class)));

        vb.btnMarks.setOnClickListener(v ->
                startActivity(new Intent(this, StudentMarksActivity.class)));

        vb.btnSubmit.setOnClickListener(v ->
                startActivity(new Intent(this, SubmitAssignmentActivity.class)));

    }

}