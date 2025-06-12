package com.schoolapp.ui.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.schoolapp.R;
import com.schoolapp.models.ScheduleItem;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.Holder> {

    private final List<ScheduleItem> list;

    public ScheduleAdapter(List<ScheduleItem> l) {
        list = l;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tvSubject, tvDay, tvTime;

        Holder(View v) {
            super(v);
            tvSubject = v.findViewById(R.id.tvSubject);
            tvDay = v.findViewById(R.id.tvDay);
            tvTime = v.findViewById(R.id.tvTime);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup p, int v) {
        return new Holder(LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_schedule, p, false));
    }

    @Override
    public void onBindViewHolder(Holder h, int i) {
        ScheduleItem s = list.get(i);
        h.tvSubject.setText(s.subject);
        h.tvDay.setText(String.valueOf(s.day));
        h.tvTime.setText(s.start + " - " + s.end);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
