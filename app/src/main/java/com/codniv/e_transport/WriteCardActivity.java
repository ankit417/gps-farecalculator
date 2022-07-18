package com.codniv.e_transport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WriteCardActivity extends AppCompatActivity {
    String fullName_extra;
    String phoneNumber_extra;
    Boolean role_extra;

    String fareInputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_card);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
             fullName_extra = extras.getString("name");
             phoneNumber_extra = extras.getString("phone");
             role_extra = extras.getBoolean("role");
        }

        TextView fullName = (TextView) findViewById(R.id.write_card_name_value_textview);
        TextView phoneNumber = (TextView) findViewById(R.id.write_card_phone_value_textview);
        TextView role = (TextView) findViewById(R.id.write_card_user_type_value_textview);
        Button writeCardButton = (Button) findViewById(R.id.write_card_write_button);

        String user_role_type = role_extra? "Staff" : "Passenger";
        fullName.setText(fullName_extra);
        phoneNumber.setText(phoneNumber_extra);
        role.setText(user_role_type);

        EditText fareInput = (EditText) findViewById(R.id.write_card_fare_input);



        writeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fareInputValue = fareInput.getText().toString();
                    System.out.println("fare input value"+fareInputValue);
                    Toast.makeText(WriteCardActivity.this, "gg"+fareInputValue, Toast.LENGTH_SHORT).show();
            }
        });


    }
}