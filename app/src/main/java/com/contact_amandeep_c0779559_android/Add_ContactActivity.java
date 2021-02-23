package com.contact_amandeep_c0779559_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Add_ContactActivity extends AppCompatActivity {

     Button addTodo;
     EditText itemname;
    EditText itemdesc,et_last_name,et_email,et_phone;

    private DAO dbManager;

    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Contact");
        setContentView(R.layout.activity_add_item);

        itemname = findViewById(R.id.item_name);
        itemdesc = findViewById(R.id.item_desc);
        et_last_name = findViewById(R.id.et_last_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);


        addTodo = findViewById(R.id.add_record);

        dbManager = new DAO(this);
        dbManager.open();

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemname.getText().toString();
                 String desc = itemdesc.getText().toString();
                 String lastname = et_last_name.getText().toString();
                 String email = et_email.getText().toString();
                 String phone = et_phone.getText().toString();


                dbManager.insert(name, desc,phone,email,lastname);

                Intent main = new Intent(Add_ContactActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);

            }
        });
    }
}
