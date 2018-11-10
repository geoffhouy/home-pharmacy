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

import java.util.Collections;
import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.User;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.user.UserEditor;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private final Context context;
    private final LayoutInflater layoutInflater;

    private List<User> users = Collections.emptyList();

    public UserListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.user_recycler_view_item_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        final User current = users.get(position);

        userViewHolder.userText1.setText(current.getName());
        userViewHolder.userText2.setText(current.getPin());

        userViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, current.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, UserEditor.class);
                intent.putExtra("uuid", current.uuid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 0;
        }

    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private final TextView userText1;
        private final TextView userText2;
        private final RelativeLayout relativeLayout;

        private UserViewHolder(View view) {
            super(view);
            userText1 = view.findViewById(R.id.UserText1);
            userText2 = view.findViewById(R.id.UserText2);
            relativeLayout = view.findViewById(R.id.UserRecyclerViewItemLayout);
        }

    }

}
