package com.codniv.e_transport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.codniv.e_transport.adapter.JourneyAdapter;
import com.codniv.e_transport.db.AppDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewJourneyActivity extends AppCompatActivity {

//    private AppDatabase db;
    private RecyclerView journeyRecyclerView;
    private JourneyAdapter journeyRecyclerViewAdapter;
    private ArrayAdapter<String> journeyAdapter;
    private ArrayList journeyList = new ArrayList<>();

    private int JourneyListDataSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_journey);
        AppDatabase db = AppDatabase.getDbInstance(this);

         journeyList =(ArrayList) db.journeyDao().getAllJourney();
         JourneyListDataSize = journeyList.size();


//         Button button = findViewById(R.id.view_journey_delete_button);
//         button.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 db.journeyDao().deleteAllJourney(22);
//                 Toast.makeText(ViewJourneyActivity.this, "id 21 deleted", Toast.LENGTH_SHORT).show();
//
//             }
//         });



        journeyRecyclerView = findViewById(R.id.journey_recyclerView);
        journeyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        journeyRecyclerViewAdapter = new JourneyAdapter(ViewJourneyActivity.this, journeyList);
        journeyRecyclerView.setAdapter(journeyRecyclerViewAdapter);

//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                journeyList = (ArrayList) db.journeyDao().getAllJourney();
//               if(journeyList.size()>JourneyListDataSize)
//               {
//                   recreate();
//               }
//                handler.postDelayed(this, 10000);
//               Log.i("lat","runnable running");
//            }
//        };

//        handler.post(runnable);
    }

}