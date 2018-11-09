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
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Pill;

public class PillListAdapter extends RecyclerView.Adapter<PillListAdapter.PillViewHolder> {

    class PillViewHolder extends RecyclerView.ViewHolder {

        private final TextView pillTextView;

        private PillViewHolder(View view) {
            super(view);
            pillTextView = view.findViewById(R.id.textView);
        }

    }

    private final LayoutInflater layoutInflater;
    private List<Pill> pills = Collections.emptyList();

    public PillListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_user, parent, false);
        return new PillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PillViewHolder pillViewHolder, int position) {
        if (pills != null) {
            Pill current = pills.get(position);
            pillViewHolder.pillTextView.setText("UUID: " + current.uuid
                    + "\nName: " + current.getName()
                    + "\nDescription: " + current.getDescription()
                    + "\nCount: " + current.getCount()
                    + "\nOwner UUID: " + current.getOwnerUuid()
                    + "\nDispenser# " + current.getDispenserNumber());
        } else {
            pillViewHolder.pillTextView.setText("empty");
        }
    }

    public void setPills(List<Pill> pills) {
        this.pills = pills;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (pills != null) {
            return pills.size();
        } else {
            return 0;
        }
    }

}
