package ca.senecacollege.dps924.assignment2_cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity implements View.OnClickListener {
    EditText newQuantity;
    Button okBtn;
    Button cancelBtn;
    ArrayList<Item> itemArray;
    ItemManager manager;
    ItemBaseAdapter adapter;
    ListView itemList;
    Item selectedItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restock_page);

        selectedItem = null;
        newQuantity = findViewById(R.id.new_quantity_field);
        itemList = findViewById(R.id.restock_list);
        okBtn = findViewById(R.id.ok_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        manager = ((MyApp)getApplication()).manager;
        itemArray = manager.allItems;
        adapter = new ItemBaseAdapter(itemArray, this);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = itemArray.get(i);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ok_btn:
                if (selectedItem != null) {
                    if (!newQuantity.getText().toString().equals("")) {
                        int newquantity = Integer.parseInt(newQuantity.getText().toString());
                        if (newquantity < 1) {
                            Toast.makeText(this, "Error: Please enter at least 1 new product!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            selectedItem.quantity += newquantity;
                            adapter.notifyDataSetChanged();
                            selectedItem = null;
                            newQuantity.setText("");
                        }
                    }
                    else {
                        Toast.makeText(this, "Error: Please enter amount to add.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Error: Please select an item.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancel_btn:
                Intent restockIntent = new Intent(this, ManagerPanelActivity.class);
                startActivity(restockIntent);
                break;
        }
    }
}
