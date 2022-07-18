package com.codniv.e_transport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codniv.e_transport.R;
import com.codniv.e_transport.db.AppDatabase;
import com.codniv.e_transport.db.Journey;
import com.codniv.e_transport.db.Passenger;

import java.util.List;

public class  JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.ViewHolder> {
    private Context context;
    private List journeyList;

    private AppDatabase db = AppDatabase.getDbInstance(context);
    public JourneyAdapter(Context context, List journeyList) {
        this.context = context;
        this.journeyList = journeyList;
    }
    @NonNull
    @Override
    public JourneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journey_list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Journey journey =(Journey) journeyList.get(position);
        holder.uid.setText(((Journey) journeyList.get(position)).id+"");
        holder.longitude.setText(((Journey) journeyList.get(position)).longitude+"");
        holder.j_date.setText(((Journey) journeyList.get(position)).journey_date+"");
        holder.latitude.setText( ((Journey) journeyList.get(position)).latitude+"");
        holder.distance.setText(((Journey) journeyList.get(position)).distance+"");
        holder.journey.setText(((Journey) journeyList.get(position)).journey+"");

    }

    @Override
    public int getItemCount() {
        return journeyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View

            .OnClickListener{
        public TextView uid;
        public TextView j_date;
        public TextView longitude;
        public TextView latitude;
        public TextView distance;
        public TextView journey;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            uid = itemView.findViewById(R.id.journey_row_id_text);
            j_date = itemView.findViewById(R.id.journey_row_date_text);
            longitude = itemView.findViewById(R.id.journey_row_longitude_text);
            latitude = itemView.findViewById(R.id.journey_row_latitude_text);
            distance = itemView.findViewById(R.id.journey_row_distance_text);
            journey = itemView.findViewById(R.id.jorney_row_journey_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = getAbsoluteAdapterPosition();
            Context context = itemView.getContext();
//            db.journeyDao().deleteJourney((Journey) journeyList);
//            db.journeyDao().deleteAllJourney(((Journey) journeyList.get(id)).id);
            db.journeyDao().deleteJourney();
//            System.out.println("Going to delete on click");
            Toast.makeText(context,((Journey)journeyList.get(id)).id+" Deleted",Toast.LENGTH_SHORT).show();
            notifyItemRemoved(id);

//            Toast.makeText(context,"hello world",Toast.LENGTH_SHORT).show();

        }
    }
}
