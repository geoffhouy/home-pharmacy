package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder> {

    class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private final TextView scheduleTextView;

        private ScheduleViewHolder(View view) {
            super(view);
            scheduleTextView = view.findViewById(R.id.textView);
        }

    }

    private final LayoutInflater layoutInflater;
    private List<Schedule> schedules = Collections.emptyList();

    public ScheduleListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_user, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder scheduleViewHolder, int position) {
        if (schedules != null) {
            Schedule current = schedules.get(position);
            scheduleViewHolder.scheduleTextView.setText("UUID: " + current.uuid
                    + "\nName: " + current.getName()
                    + "\nTimestamp: " + current.getTimestamp()
                    + "\n#Pills: " + current.getNumPillsToTake()
                    + "\nPill Name: " + current.getPillName()
                    + "\nDispenser#: " + current.getDispenserNumber()
                    + "\nUser: " + current.getUserName());
        } else {
            scheduleViewHolder.scheduleTextView.setText("empty");
        }
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (schedules != null) {
            return schedules.size();
        } else {
            return 0;
        }
    }

}
