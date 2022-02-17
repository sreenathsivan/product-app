package com.example.productapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductSearchActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    AppCompatButton b1,b2;
    String getpcode,pname,price;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        dbHelper=new DBHelper(this);
        dbHelper.getWritableDatabase();
        ed1=(EditText) findViewById(R.id.pcode);
        ed2=(EditText) findViewById(R.id.pname);
        ed3=(EditText) findViewById(R.id.price);
        b1=(AppCompatButton) findViewById(R.id.search);
        b2=(AppCompatButton) findViewById(R.id.back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpcode=ed1.getText().toString();
                Cursor c=dbHelper.searchdata(getpcode);
                if(c.getCount()==0)
                {
                    ed2.setText("");
                    ed3.setText("");
                    Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    while(c.moveToNext())
                    {
                        pname=c.getString(2);
                        price=c.getString(3);
                    }

                    ed2.setText(pname);
                    ed3.setText(price);
                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}