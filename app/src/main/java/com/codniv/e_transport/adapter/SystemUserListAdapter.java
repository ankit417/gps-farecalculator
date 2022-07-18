package com.codniv.e_transport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codniv.e_transport.R;
import com.codniv.e_transport.db.AppDatabase;
import com.codniv.e_transport.db.User;

import java.util.List;

public class SystemUserListAdapter extends RecyclerView.Adapter<SystemUserListAdapter.ViewHolder> {

    private Context context;
    private List SystemUserList;

    private AppDatabase db = AppDatabase.getDbInstance(context);

    public SystemUserListAdapter(Context context , List SystemUserList) {
        this.context = context;
        this.SystemUserList = SystemUserList;
    }

    @NonNull
    @Override
    public SystemUserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.system_user_list ,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder , int position){
        holder.Name.setText(((User)SystemUserList.get(position)).full_name);
        holder.PhoneNumber.setText(((User)SystemUserList.get(position)).phone_number);
        holder.Roles.setText(((User)SystemUserList.get(position)).staff +"");


    }

    @Override
    public int getItemCount(){
        return SystemUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        public TextView id;
        public TextView Name;
        public TextView PhoneNumber;
        public TextView Roles;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            Name = itemView.findViewById(R.id.system_user_fullname);
            PhoneNumber = itemView.findViewById(R.id.system_user_phone);
            Roles = itemView.findViewById(R.id.system_user_role);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int id = getAbsoluteAdapterPosition();
            Context context = itemView.getContext();

        }
    }
}
