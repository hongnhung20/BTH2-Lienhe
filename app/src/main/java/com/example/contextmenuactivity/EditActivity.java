package com.example.contextmenuactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText edtTenedit,edtidedit;
    Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        btnEdit = (Button) findViewById(R.id.buttonedit);
        edtTenedit = (EditText) findViewById(R.id.edtTenedit);
        edtidedit = (EditText) findViewById(R.id.edtidedit);
        Intent inputinfo = getIntent();
        String idedit = inputinfo.getStringExtra("idForEdit");
        String Tenedit = inputinfo.getStringExtra("nameForEdit");
        edtidedit.setText(idedit);
        edtTenedit.setText(Tenedit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoEdit = new Intent();
                infoEdit.putExtra("idAfterEdit",edtidedit.getText().toString());
                infoEdit.putExtra("nameAfterEdit",edtTenedit.getText().toString());
                setResult(1114,infoEdit);
                finish();
            }
        });
    }
}