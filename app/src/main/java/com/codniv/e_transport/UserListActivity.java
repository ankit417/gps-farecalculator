package com.codniv.e_transport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codniv.e_transport.adapter.SystemUserListAdapter;
import com.codniv.e_transport.adapter.UserListAdapter;
import com.codniv.e_transport.db.AppDatabase;
import com.codniv.e_transport.db.Passenger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView PassengerRecyclerView;
    private SystemUserListAdapter passengerListAdapter;
    private ArrayList passengerList = new ArrayList<>();
    private ArrayList allUserList = new ArrayList<>();

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    String currentTime = formatter.format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Log.i("date",currentTime+"");
        AppDatabase db = AppDatabase.getDbInstance(this);

        passengerList = (ArrayList) db.passengerDao().getAllPassenger();
        allUserList = (ArrayList) db.userDao().getAllUsers();

//        Button addUserButton = findViewById(R.id.user_activity_add_user_button);
//        addUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Passenger passenger = new Passenger(currentTime,1,2,"1:07",4.45,500d,"completed");
//                db.passengerDao().insertPassenger(passenger);
//                recreate();
////                Intent intent = new Intent(this,UserListActivity.class);
////                finish();
////                startActivity(intent);
//
//                Log.i("db","inserted to db");
//            }
//        });

        PassengerRecyclerView = findViewById(R.id.user_list_recyclerView);
        PassengerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        passengerListAdapter = new UserListAdapter(UserListActivity.this,passengerList);
        passengerListAdapter = new SystemUserListAdapter(UserListActivity.this,allUserList);
//        PassengerRecyclerView.setAdapter(passengerListAdapter);
        PassengerRecyclerView.setAdapter(passengerListAdapter);


    }

}