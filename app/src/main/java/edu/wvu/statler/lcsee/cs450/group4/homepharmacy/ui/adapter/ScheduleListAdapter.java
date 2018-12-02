package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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

        final Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTimeInMillis(current.getTimestamp());

        String myFormat = "MM/dd/yy   hh:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        String interval = "";
        if(current.getInterval()==28800000){
            interval = "every 8 Hours";
        }else if(current.getInterval()==43200000) {
            interval = "every 12 hours";
        }else if(current.getInterval()==86400000){
            interval = "every 24 hours";
        }else if(current.getInterval()==172800000){
            interval = "every 48 hours";
        }

        scheduleViewHolder.scheduleText1.setText(Html.fromHtml("<b>Schedule Name: </b>")+current.getName());
        scheduleViewHolder.scheduleText4.setText(Html.fromHtml("<b>Schedule date/time: </b>")+sdf.format(myCalendar.getTime()));
        scheduleViewHolder.scheduleText5.setText(Html.fromHtml("<b>Interval: </b>")+interval);
        scheduleViewHolder.scheduleText2.setText(Html.fromHtml("<b>Pill Name: </b>")+current.getPillName());
        scheduleViewHolder.scheduleText3.setText(Html.fromHtml("<b>Dispenser: </b>")+String.format("%d", current.getDispenserNumber()));

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
        private final TextView scheduleText4;
        private final TextView scheduleText5;
        private final LinearLayout layout;

        private ScheduleViewHolder(View view) {
            super(view);
            scheduleText1 = view.findViewById(R.id.ScheduleText1);
            scheduleText2 = view.findViewById(R.id.ScheduleText2);
            scheduleText3 = view.findViewById(R.id.ScheduleText3);
            scheduleText4 = view.findViewById(R.id.ScheduleText4);
            scheduleText5 = view.findViewById(R.id.ScheduleText5);
            layout = view.findViewById(R.id.ScheduleRecyclerViewItemLayout);
        }

    }

}
