package com.codniv.e_transport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codniv.e_transport.MainActivity;
import com.codniv.e_transport.R;
import com.codniv.e_transport.UserListActivity;
import com.codniv.e_transport.db.AppDatabase;
import com.codniv.e_transport.db.Passenger;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private Context context;
    private List Passengerlist;

    private AppDatabase db = AppDatabase.getDbInstance(context);

    public UserListAdapter(Context context , List Passengerlist){
        this.context = context;
        this.Passengerlist = Passengerlist;
    }


    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(((Passenger) Passengerlist.get(position)).id+"");
        holder.start_time.setText(((Passenger) Passengerlist.get(position)).start_time+"");
        holder.end_time.setText(((Passenger) Passengerlist.get(position)).end_time+"");
        holder.distance.setText(((Passenger) Passengerlist.get(position)).distance+"");
        holder.price.setText(((Passenger) Passengerlist.get(position)).fare+"");
        holder.status.setText(((Passenger) Passengerlist.get(position)).status+"");

    }

    @Override
    public int getItemCount() {
        return Passengerlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View

            .OnClickListener{
        public TextView id;
        public TextView start_time;
        public TextView end_time;
        public TextView distance;
        public TextView price;
        public TextView status;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.user_row_id);
            start_time = itemView.findViewById(R.id.user_start_time);
            end_time =itemView.findViewById(R.id.user_row_end_time);
            distance =itemView.findViewById(R.id.user_row_distance);
            price = itemView.findViewById(R.id.user_row_price);
            status = itemView.findViewById(R.id.user_row_status);
            itemView.setOnClickListener(this);

//            status.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Context context = itemView.getContext();
//                    Toast.makeText(context,""+id,Toast.LENGTH_SHORT).show();
//                }
//            });
        }


        @Override
        public void onClick(View view ) {
            int id = getAbsoluteAdapterPosition();
            Context context = itemView.getContext();
            db.passengerDao().deletePassenger(((Passenger)Passengerlist.get(id)).id);
            Toast.makeText(context,((Passenger)Passengerlist.get(id)).id+" Deleted",Toast.LENGTH_SHORT).show();
//            notifyDataSetChanged();
            notifyItemRemoved(id);
        }
    }
}
