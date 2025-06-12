package com.schoolapp.ui.student;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.schoolapp.databinding.ActivityStudentBinding;

public class StudentActivity extends AppCompatActivity {

    private ActivityStudentBinding vb;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        vb = ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        vb.btnSchedule.setOnClickListener(v ->
                startActivity(new Intent(this, StudentScheduleActivity.class)));

        vb.btnMarks.setOnClickListener(v ->
                startActivity(new Intent(this, StudentMarksActivity.class)));

        vb.btnSubmit.setOnClickListener(v ->
                startActivity(new Intent(this, SubmitAssignmentActivity.class)));
    }
}
