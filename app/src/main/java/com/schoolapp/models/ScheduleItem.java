package com.schoolapp.models;

public class ScheduleItem {
    public final String subject;
    public final int day;
    public final String start;
    public final String end;

    public ScheduleItem(String sub, int d, String s, String e) {
        subject = sub;
        day = d;
        start = s;
        end = e;
    }
}
