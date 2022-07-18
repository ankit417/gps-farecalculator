package com.codniv.e_transport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codniv.e_transport.db.AppDatabase;
import com.codniv.e_transport.db.User;

public class CreateUserActivity extends AppCompatActivity {

    boolean staffButtonChecked = false;
    String fullName;
    String phoneNumber;

    AppDatabase db = AppDatabase.getDbInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Button createUser = (Button) findViewById(R.id.create_user_create_button);
        EditText nameInput = (EditText) findViewById(R.id.create_user_fullName_input);
        EditText phoneInput =(EditText) findViewById(R.id.create_user_phone_input);
        RadioButton staffButton = (RadioButton) findViewById(R.id.create_user_staff_radio_button);

        Button createUserWriteCard = (Button) findViewById(R.id.create_user_card_button);

        createUserWriteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateUserActivity.this,WriteCardActivity.class);
                intent.putExtra("name",fullName);
                intent.putExtra("phone",phoneNumber);
                intent.putExtra("role",staffButtonChecked);
                startActivity(intent);
            }
        });

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName = nameInput.getText().toString();
                phoneNumber = phoneInput.getText().toString();

                if(fullName == null || fullName.length() <3 || phoneNumber ==null || phoneNumber.length()<10)
                {
                    Toast.makeText(CreateUserActivity.this,"Please Enter the Valid Detail",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CreateUserActivity.this,"Everything's ok",Toast.LENGTH_SHORT).show();

                    User user = new User(fullName,phoneNumber,staffButtonChecked);
                    db.userDao().insertUser(user);

                    createUserWriteCard.setVisibility(View.VISIBLE);

                }

            }
        });

       staffButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               staffButton.setChecked(!staffButtonChecked);
               staffButtonChecked = !staffButtonChecked;
//               Toast.makeText(CreateUserActivity.this,"radio button clicked",Toast.LENGTH_SHORT).show();
           }
       });
        
    }
}