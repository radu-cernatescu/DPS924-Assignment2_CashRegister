package ca.senecacollege.dps924.assignment2_cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ItemBaseAdapter adapter;
    ItemManager manager;
    ArrayList<Item> itemArray;
    ListView itemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page);

        manager = ((MyApp)getApplication()).manager;
        itemList = findViewById(R.id.item_list);

        itemArray = manager.purchasedItems;
        adapter = new ItemBaseAdapter(itemArray, this);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item temp = itemArray.get(i);

                Intent myIntent = new Intent(view.getContext(), ProductInfoActivity.class);
                myIntent.putExtra("items", temp);
                startActivity(myIntent);
            }
        });
    }


}
