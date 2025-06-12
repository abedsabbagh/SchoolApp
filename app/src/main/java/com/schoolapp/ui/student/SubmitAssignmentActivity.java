package com.schoolapp.ui.student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.schoolapp.R;
import com.schoolapp.network.ApiService;

public class SubmitAssignmentActivity extends AppCompatActivity {

    Button btnSelectFile, btnSubmit;
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_assignment);

        btnSelectFile = findViewById(R.id.btnSelectFile);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSelectFile.setOnClickListener(v -> selectFile());
        btnSubmit.setOnClickListener(v -> submitFile());
    }

    private void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            fileUri = data.getData();
            Toast.makeText(this, "File selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void submitFile() {
        if (fileUri == null) {
            Toast.makeText(this, "Please select a file first", Toast.LENGTH_SHORT).show();
            return;
        }

        // هنا بالعادة نستخدم MultipartRequest - للتبسيط نعرض فقط رسالة
        Toast.makeText(this, "File submitted (simulate)", Toast.LENGTH_SHORT).show();
    }
}
