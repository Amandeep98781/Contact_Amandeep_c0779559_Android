package com.contact_amandeep_c0779559_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Edit_Contact extends AppCompatActivity {
    Button addTodo;
    EditText itemname;
    EditText itemdesc,et_last_name,et_email,et_phone;

    private DAO dbManager;
    long _id=0;
    String firstname,lastname,email,address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__contact);
        setTitle("Manage Contact");
        setContentView(R.layout.activity_add_item);

        itemname = findViewById(R.id.item_name);
        itemdesc = findViewById(R.id.item_desc);
        et_last_name = findViewById(R.id.et_last_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);

        Intent in = getIntent();
        _id = Long.parseLong(in.getStringExtra("id"));
        firstname = in.getStringExtra("firstname");
        lastname = in.getStringExtra("lastname");
        email = in.getStringExtra("email");
        address = in.getStringExtra("address");
        phone = in.getStringExtra("phone");
        itemname.setText(firstname);
        itemdesc.setText(address);
        et_last_name.setText(lastname);
        et_email.setText(email);
        et_phone.setText(phone);


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


                int i =  dbManager.update(_id,name, desc,phone,email,lastname);
                if(i==1) {
                    Toast.makeText(Edit_Contact.this, "Edit Successfully..", Toast.LENGTH_SHORT).show();

                    Intent main = new Intent(Edit_Contact.this, MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(main);
                }
                else {
                    Toast.makeText(Edit_Contact.this, "Some Error Occur.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}