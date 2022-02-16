package ca.senecacollege.dps924.assignment2_cashregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ItemBaseAdapter adapter;
    ListView itemList;
    TextView totalText;
    TextView quantityText;
    NumberPicker numberPicker;
    Button buyBtn;
    Button managerBtn;
    TextView productTypeText;

    ArrayList<Item> itemArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = findViewById(R.id.item_list);
        totalText = findViewById(R.id.total_text);
        quantityText = findViewById(R.id.quantity_text);
        numberPicker = findViewById(R.id.num_picker);
        buyBtn = findViewById(R.id.buy_btn);
        managerBtn = findViewById(R.id.manager_btn);
        productTypeText = findViewById(R.id.product_type_text);

        buyBtn.setOnClickListener(this);
        managerBtn.setOnClickListener(this);

        itemArray = new ArrayList<>();
        itemArray.add(new Item(3.99, "Berries", 75));
        itemArray.add(new Item(4.99, "Cherries", 15));
        itemArray.add(new Item(14.99, "Vodka", 25));
        itemArray.add(new Item(19.99, "Rum", 5));

        adapter = new ItemBaseAdapter(itemArray, this);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item temp = adapter.getItem(i);
                productTypeText.setText(temp.product_type);
                quantityText.setText(String.valueOf(0));
                numberPicker.setValue(0);
                totalText.setText("Total");
            }
        });

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Item temp = adapter.getItemByName(productTypeText.getText().toString());
                quantityText.setText(String.valueOf(i1));
                if (temp != null) {
                    double totalCost = temp.amount * i1;
                    totalText.setText(String.valueOf(totalCost));
                }
            }
        });

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(99);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buy_btn:
                Item currentItem = adapter.getItemByName(productTypeText.getText().toString());
                if (currentItem == null || Integer.parseInt(quantityText.getText().toString()) == 0 || totalText.getText().toString().equals("Total")) {
                    Toast.makeText(this, "Error: All fields are required!", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(quantityText.getText().toString()) > currentItem.quantity) {
                    Toast.makeText(this, "Error: Not enough quantity in stock!", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("Thank You for your purchase!");
                    alert.setMessage("Your purchase is " + quantityText.getText().toString() + " " + productTypeText.getText().toString() + " for " + totalText.getText().toString());
                    alert.show();
                    currentItem.quantity -= Integer.parseInt(quantityText.getText().toString());
                    adapter.notifyDataSetChanged();
                    productTypeText.setText("Please tap an item below...");
                    numberPicker.setValue(0);
                    quantityText.setText("0");
                    totalText.setText("Total");
                }
                break;

            case R.id.manager_btn:
                Intent myIntent = new Intent(this, ManagerPanelActivity.class);
                myIntent.putExtra("items", itemArray);
                startActivity(myIntent);
                break;
        }
    }


}