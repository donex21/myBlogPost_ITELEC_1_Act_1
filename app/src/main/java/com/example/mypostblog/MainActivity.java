package com.example.mypostblog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<String> postTextArray;
    //List<String> postDateArray;
    ArrayAdapter arrayAdapter;
    EditText editext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postTextArray = new ArrayList<>();
        //postDateArray = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.row, postTextArray);
        listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        editext = findViewById(R.id.editText);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int delItem = position;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.clear_foreground)
                        .setTitle("Are You Sure")
                        .setMessage("Do you want to Delete this Post")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                postTextArray.remove(delItem);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No",null)
                        .show();
                return true;
            }
        });
    }

    public void AddPost(View view) {
        if(!editext.getText().toString().equals("")){
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            postTextArray.add("Posted: " + date.toString() + "\n"+ editext.getText().toString());
            //postDateArray.add(date.toString());
            arrayAdapter.notifyDataSetChanged();
            editext.setText("");
            date = "";
        }else
        {
            Toast.makeText(MainActivity.this,
                    "Pls Fill Up", Toast.LENGTH_LONG).show();
        }

    }
}