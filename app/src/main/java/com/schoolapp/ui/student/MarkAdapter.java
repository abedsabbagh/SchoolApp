package com.schoolapp.ui.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import com.schoolapp.R;

import java.util.List;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.Holder> {
    private final List<JSONObject> marks;

    public MarkAdapter(List<JSONObject> marks) {
        this.marks = marks;
    }

    @NonNull
    @Override
    public MarkAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mark, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkAdapter.Holder holder, int position) {
        JSONObject obj = marks.get(position);
        holder.tvSubject.setText(obj.optString("subject"));
        holder.tvType.setText(obj.optString("exam_type"));
        holder.tvMark.setText(obj.optString("mark"));
    }

    @Override
    public int getItemCount() {
        return marks.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tvSubject, tvType, tvMark;

        Holder(View v) {
            super(v);
            tvSubject = v.findViewById(R.id.tvSubject);
            tvType = v.findViewById(R.id.tvType);
            tvMark = v.findViewById(R.id.tvMark);

        }

    }
}
