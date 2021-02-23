package com.contact_amandeep_c0779559_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DAO dbManager;

    private ListView listView;
    myadapter myad;
    EditText searchET;
    ArrayList<Contacts_details> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myad=new myadapter();
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        searchET=findViewById(R.id.searchEt);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals(""))
                {
                    fetchall();
                }
                for(int i=0;i<arrayList.size();i++)
                {
                    if(arrayList.get(i).getFirstname().toLowerCase().contains(s.toString().toLowerCase()))
                    {

                    }
                    else
                    {
                        arrayList.remove(i);
                        myad.notifyDataSetChanged();
                    }

                }
            }
        });

        fetchall();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this, Add_ContactActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
    class myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            convertView =  layoutInflater.inflate(R.layout.activity_view_record, parent,false);
LinearLayout lv_1 = convertView.findViewById(R.id.lv1);
            TextView t_id = convertView.findViewById(R.id.it_id);
            TextView t_name = convertView.findViewById(R.id.it_name);
            TextView t_desc = convertView.findViewById(R.id.it_desc);
            TextView it_email = convertView.findViewById(R.id.it_email);
            TextView it_phoneno = convertView.findViewById(R.id.it_phoneno);

            ImageView btdel = convertView.findViewById(R.id.ic_delete);
            ImageView ic_pencil = convertView.findViewById(R.id.ic_pencil);

            final Contacts_details obj = arrayList.get(i);
            t_id.setText(obj.get_id()+"");
            t_name.setText(obj.firstname +" "+obj.lastname);
            t_desc.setText(obj.getAddress());
            it_email.setText(obj.getEmail());
            it_phoneno.setText(obj.getPhoneno());








            btdel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long  _id = Long.parseLong(obj._id);
                    dbManager.delete(_id);
                    fetchall();
                }
            });

            lv_1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("Choose Any operation ?");
                            alertDialogBuilder.setPositiveButton("Call",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            Uri u = Uri.parse("tel:" + obj.phoneno);
                                            Intent i = new Intent(Intent.ACTION_DIAL, u);
                                            try
                                            {
                                                startActivity(i);
                                            }
                                            catch (SecurityException s)
                                            {
                                                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_LONG)
                                                        .show();
                                            }
                                        }
                                    });

                    alertDialogBuilder.setNegativeButton("Close",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    return true;
                }
            });

            ic_pencil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getApplicationContext(), Edit_Contact.class);
                    in.putExtra("id",obj._id+"");
                    in.putExtra("firstname",obj.firstname+"");
                    in.putExtra("lastname",obj.lastname+"");
                    in.putExtra("email",obj.email+"");
                    in.putExtra("address",obj.address+"");
                    in.putExtra("phone",obj.phoneno+"");

                    startActivity(in);


                }
            });



            return convertView;
        }
    }

    public void fetchall(){
        arrayList.clear();
        dbManager = new DAO(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        for (int i = 0; i < cursor.getCount(); i++) {

            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            String phoneno = cursor.getString(3);
            String email = cursor.getString(4);
            String lastname = cursor.getString(5);
            arrayList.add(new Contacts_details(id,name,address,lastname,email,phoneno));



            cursor.moveToNext();
        }

        listView.setAdapter(myad);
        myad.notifyDataSetChanged();


    }


}
