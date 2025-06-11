package com.schoolapp.ui.student;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;

import com.schoolapp.databinding.ActivitySubmitAssignmentBinding;
import com.schoolapp.network.MultipartRequest;
import com.schoolapp.network.ApiService;
import com.schoolapp.network.VolleySingleton;
import com.schoolapp.utils.SessionManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class SubmitAssignmentActivity extends AppCompatActivity {

    private ActivitySubmitAssignmentBinding vb;
    private ActivityResultLauncher<Intent> filePicker;
    private File tmpFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = ActivitySubmitAssignmentBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        // صلاحية Android 13+
        if (Build.VERSION.SDK_INT >= 33 &&
                checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 100);
        }

        // ActivityResult API
        filePicker = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri == null) return;
                        String name = getName(uri);
                        vb.tvFile.setText(name);
                        tmpFile = copyToCache(uri, name);
                    }
                });

        vb.btnChoose.setOnClickListener(v -> {
            Intent it = new Intent(Intent.ACTION_GET_CONTENT).setType("*/*");
            filePicker.launch(it);
        });

        vb.btnSend.setOnClickListener(v -> upload());
    }

    private String getName(Uri u) {
        Cursor c = getContentResolver().query(u, null, null, null, null);
        String n = "file";
        if (c != null) {
            int i = c.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (i >= 0 && c.moveToFirst()) n = c.getString(i);
            c.close();
        }
        return n;
    }

    private File copyToCache(Uri u, String name) {
        try (InputStream in = getContentResolver().openInputStream(u)) {
            File f = new File(getCacheDir(), name);
            try (OutputStream out = new FileOutputStream(f)) {
                byte[] buf = new byte[4096];
                int len;
                while ((len = in.read(buf)) != -1) out.write(buf, 0, len);
            }
            return f;
        } catch (Exception e) {
            return null;
        }
    }

    private void upload() {
        if (tmpFile == null) {
            Toast.makeText(this, "Choose file", Toast.LENGTH_SHORT).show();
            return;
        }
        String idStr = vb.etAssignmentId.getText().toString().trim();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Enter assignment ID", Toast.LENGTH_SHORT).show();
            return;
        }
        int aid = Integer.parseInt(idStr);

        SessionManager sm = new SessionManager(this);
        Map<String, String> p = new HashMap<>();
        p.put("assignment_id", String.valueOf(aid));
        p.put("student_id", String.valueOf(sm.uid()));

        String url = "http://10.0.2.2/api/assignments/submit.php";   // استخدم ثابت أو اتركه هكذا
        MultipartRequest req = new MultipartRequest(
                url, tmpFile, p, sm.token(),
                rsp -> Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show(),
                err -> Toast.makeText(this, err.toString(), Toast.LENGTH_SHORT).show()
        );
        VolleySingleton.get(this).queue().add(req);
    }
}
