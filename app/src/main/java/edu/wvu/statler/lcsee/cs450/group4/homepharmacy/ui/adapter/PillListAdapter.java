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

import java.util.Collections;
import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Pill;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.pill.PillEditor;

public class PillListAdapter extends RecyclerView.Adapter<PillListAdapter.PillViewHolder> {

    private final Context context;
    private final LayoutInflater layoutInflater;

    private List<Pill> pills = Collections.emptyList();

    public PillListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.pill_recycler_view_item_layout, parent, false);
        return new PillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PillViewHolder pillViewHolder, int position) {
        final Pill current = pills.get(position);

        pillViewHolder.pillText1.setText(current.getName());
        pillViewHolder.pillText2.setText(current.getDescription());
        pillViewHolder.pillText3.setText(String.format("%d", current.getDispenserNumber()));

        pillViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, current.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, PillEditor.class);
                intent.putExtra("uuid", current.uuid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (pills != null) {
            return pills.size();
        } else {
            return 0;
        }
    }

    public void setPills(List<Pill> pills) {
        this.pills = pills;
        notifyDataSetChanged();
    }

    class PillViewHolder extends RecyclerView.ViewHolder {

        private final TextView pillText1;
        private final TextView pillText2;
        private final TextView pillText3;
        private final RelativeLayout relativeLayout;

        private PillViewHolder(View view) {
            super(view);
            pillText1 = view.findViewById(R.id.PillText1);
            pillText2 = view.findViewById(R.id.PillText2);
            pillText3 = view.findViewById(R.id.PillText3);
            relativeLayout = view.findViewById(R.id.PillRecyclerViewItemLayout);
        }

    }

}
