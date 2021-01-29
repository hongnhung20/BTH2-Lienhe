package com.example.contextmenuactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //RequestCode
    public static final int NEW_ITEM=1111;
    public static final int EDIT_ITEM=1112;
    //ResultCode
    public static final int SAVE_NEW_ITEM=1113;
    public static final int SAVE_EDIT_ITEM=1114;
    Button btnds;
    ListView lvds;
    ArrayList<Person> arraycourse= new ArrayList<Person>();
    int indexofitem = -1;
    ArrayAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnds = (Button) findViewById(R.id.buttonds);
        lvds = (ListView) findViewById(R.id.Listviewdanhsach);
        btnds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cái nút làm cho vui thôi bấm làm gì =) ", Toast.LENGTH_SHORT).show();
            }
        });
        arraycourse.add(new Person(1,"nhung"));
        arraycourse.add(new Person(2,"Heo"));
        arraycourse.add(new Person(3,"Nam"));

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1,arraycourse);
        lvds.setAdapter(adapter);
        lvds.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexofitem = i;
                return false;
            }
        });
        //dang ky view context menu
        //registerForContextMenu(btnds);
        registerForContextMenu(lvds);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.menu_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemthem:
                doCreate();
                break;
            case R.id.itemsua:
                doEdit();
                break;
            case R.id.itemxoa:
                Dodelete();
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void doCreate()
    {
        Intent intentNewPerson = new Intent(getApplicationContext(),NewActivity.class);
        startActivityForResult(intentNewPerson,NEW_ITEM);
    }

    public void doEdit()
    {
        Intent intentEditPerson = new Intent(getApplicationContext(),EditActivity.class);
        Person infoForEdit = arraycourse.get(indexofitem);
        intentEditPerson.putExtra("idForEdit",infoForEdit.getId()+"");
        intentEditPerson.putExtra("nameForEdit",infoForEdit.getName());
        startActivityForResult(intentEditPerson,EDIT_ITEM);
    }
    public void Dodelete()
    {
        AlertDialog.Builder ADBXoa = new AlertDialog.Builder(this);
        ADBXoa.setTitle("Xác nhận xóa");
        ADBXoa.setMessage("Bạn muốn xóa " +arraycourse.get(indexofitem));
        ADBXoa.setPositiveButton("Chắc chắn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arraycourse.remove(indexofitem);
                adapter.notifyDataSetChanged();
            }
        });
        ADBXoa.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog ADXoa = ADBXoa.create();
        ADXoa.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case NEW_ITEM:
                if(resultCode == SAVE_NEW_ITEM) {
                    int MaID = Integer.parseInt(data.getStringExtra("MaPerson"));
                    String Ten = data.getStringExtra("TenPerson");
                    arraycourse.add(new Person(MaID,Ten));

                    adapter.notifyDataSetChanged();
                }
                break;
            case EDIT_ITEM:
                if(resultCode == SAVE_EDIT_ITEM)
                {
                    String id = data.getStringExtra("idAfterEdit");
                    String Ten = data.getStringExtra("nameAfterEdit");
                    Person infoAfterEdit = new Person(Integer.parseInt(id),Ten);
                    arraycourse.set(indexofitem,infoAfterEdit);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}