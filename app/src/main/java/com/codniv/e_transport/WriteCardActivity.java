package com.codniv.e_transport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
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
    public static final String ERROR_DETECTED = "No NFC Tag Detected";
    public static final String WRITE_SUCCESS = "Card Created Successful";
    public static final String WRITE_ERROR = "Error Writing NFC , Try Again";
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writingTagFilters;
    boolean writeMode;
    Tag myTag;
    Context context;


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
                int fareInputParsed = Integer.parseInt(fareInputValue);

                if(fareInputValue == null || fareInputParsed == 0 || fareInputParsed <0)
                {
                    Toast.makeText(WriteCardActivity.this, "Enter the valid value", Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(WriteCardActivity.this, "Successful", Toast.LENGTH_SHORT).show();
//                    SAVE IN DATABASE
                }

            }
        });


    }
}