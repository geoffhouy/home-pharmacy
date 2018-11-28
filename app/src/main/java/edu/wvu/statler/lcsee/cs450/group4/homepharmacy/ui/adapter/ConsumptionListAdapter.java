package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Consumption;
//import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.user.UserEditor;

public class ConsumptionListAdapter extends RecyclerView.Adapter<ConsumptionListAdapter.ConsumptionViewHolder> {

    private final Context context;
    private final LayoutInflater layoutInflater;

    private List<Consumption> consumptions = Collections.emptyList();

    public ConsumptionListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ConsumptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.consumption_recycler_view_item_layout, parent, false);
        return new ConsumptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumptionViewHolder consumptionViewHolder, int position) {
        final Consumption current = consumptions.get(position);

        final Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTimeInMillis(current.getTimestamp());

        String myFormat = "MM/dd/yy   HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        consumptionViewHolder.consumptionText1.setText(sdf.format(myCalendar.getTime()));
        consumptionViewHolder.consumptionText2.setText(current.getUserName());
        consumptionViewHolder.consumptionText3.setText(current.getPillName());
        consumptionViewHolder.consumptionText4.setText(current.isConsumed() ? "Yes" : "No");
		/*
        userViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, current.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, UserEditor.class);
                intent.putExtra("uuid", current.uuid);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        if (consumptions != null) {
            return consumptions.size();
        } else {
            return 0;
        }

    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
        notifyDataSetChanged();
    }

    class ConsumptionViewHolder extends RecyclerView.ViewHolder {

        private final TextView consumptionText1;
        private final TextView consumptionText2;
        private final TextView consumptionText3;
        private final TextView consumptionText4;
        private final RelativeLayout relativeLayout;

        private ConsumptionViewHolder(View view) {
            super(view);
            consumptionText1 = view.findViewById(R.id.ConsumptionText1);
            consumptionText2 = view.findViewById(R.id.ConsumptionText2);
            consumptionText3 = view.findViewById(R.id.ConsumptionText3);
            consumptionText4 = view.findViewById(R.id.ConsumptionText4);
            relativeLayout = view.findViewById(R.id.ConsumptionRecyclerViewItemLayout);
        }

    }

}