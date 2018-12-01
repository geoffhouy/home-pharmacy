package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.schedule.ScheduleEditor;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder> {

    private final Context context;
    private final LayoutInflater layoutInflater;

    private List<Schedule> schedules = Collections.emptyList();

    public ScheduleListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.schedule_recycler_view_item_layout, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder scheduleViewHolder, int position) {
        final Schedule current = schedules.get(position);

        scheduleViewHolder.scheduleText1.setText(current.getName());
        scheduleViewHolder.scheduleText2.setText(current.getPillName());
        scheduleViewHolder.scheduleText3.setText(String.format("%d", current.getDispenserNumber()));

        scheduleViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, current.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ScheduleEditor.class);
                intent.putExtra("uuid", current.uuid);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (schedules != null) {
            return schedules.size();
        } else {
            return 0;
        }
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
        notifyDataSetChanged();
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private final TextView scheduleText1;
        private final TextView scheduleText2;
        private final TextView scheduleText3;
        private final LinearLayout layout;

        private ScheduleViewHolder(View view) {
            super(view);
            scheduleText1 = view.findViewById(R.id.ScheduleText1);
            scheduleText2 = view.findViewById(R.id.ScheduleText2);
            scheduleText3 = view.findViewById(R.id.ScheduleText3);
            layout = view.findViewById(R.id.ScheduleRecyclerViewItemLayout);
        }

    }

}
