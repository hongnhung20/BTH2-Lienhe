package com.example.contextmenuactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.ZoneId;

public class NewActivity extends AppCompatActivity {
    Button btnsavenew;
    EditText edtid,edtname;
    String id,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        btnsavenew = (Button) findViewById(R.id.buttonnew);
        edtid =(EditText) findViewById(R.id.edtid);
        edtname=(EditText) findViewById(R.id.edtten);

        btnsavenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id= edtid.getText().toString();
                name = edtname.getText().toString();
                if(id.equals("") || name.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Chưa nhập dữ liệu",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent infomation = new Intent();
                    infomation.putExtra("MaPerson",id);
                    infomation.putExtra("TenPerson",name);
                    setResult(1113,infomation);
                    finish();
                }
            }
        });
    }
}