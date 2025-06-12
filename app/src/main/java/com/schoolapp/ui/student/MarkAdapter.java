package com.schoolapp.ui.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.schoolapp.R;
import com.schoolapp.models.MarkItem;

import java.util.List;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.Holder> {

    private final List<MarkItem> list;

    public MarkAdapter(List<MarkItem> l) {
        list = l;
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

    @Override
    public Holder onCreateViewHolder(ViewGroup p, int v) {
        return new Holder(LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_mark, p, false));
    }

    @Override
    public void onBindViewHolder(Holder h, int i) {
        MarkItem m = list.get(i);
        h.tvSubject.setText(m.subject);
        h.tvType.setText(m.type);
        h.tvMark.setText(m.mark);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
