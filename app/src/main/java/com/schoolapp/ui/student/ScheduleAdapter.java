package com.schoolapp.ui.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.schoolapp.R;
import com.schoolapp.models.ScheduleItem;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.Holder> {
    private final List<ScheduleItem> list;

    public ScheduleAdapter(List<ScheduleItem> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ScheduleAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.Holder holder, int position) {
        ScheduleItem item = list.get(position);

        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        holder.tvSubject.setText(item.subject);
        holder.tvDayTime.setText(days[item.day] + " " + item.start + "-" + item.end);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tvSubject, tvDayTime;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvDayTime = itemView.findViewById(R.id.tvDayTime);
        }
    }
}
