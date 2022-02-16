package ca.senecacollege.dps924.assignment2_cashregister;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
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

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
    ArrayList<Item> purchasedItems;
    ItemManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = ((MyApp)getApplication()).manager;

        itemList = findViewById(R.id.item_list);
        totalText = findViewById(R.id.total_text);
        quantityText = findViewById(R.id.quantity_text);
        numberPicker = findViewById(R.id.num_picker);
        buyBtn = findViewById(R.id.buy_btn);
        managerBtn = findViewById(R.id.manager_btn);
        productTypeText = findViewById(R.id.product_type_text);

        buyBtn.setOnClickListener(this);
        managerBtn.setOnClickListener(this);

        purchasedItems = new ArrayList<>();
        itemArray = manager.allItems;
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
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setMinimumFractionDigits(2);
                    String totalCostFormatted = decimalFormat.format(totalCost);
                    totalText.setText(totalCostFormatted);
                }
            }
        });

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(99);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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
                    int amtPurchased = Integer.parseInt(quantityText.getText().toString());
                    double totalCost = Double.parseDouble(totalText.getText().toString());
                    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
                    decimalFormat.setMinimumFractionDigits(2);
                    String totalCostFormatted = decimalFormat.format(totalCost);
                    String productType = productTypeText.getText().toString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    manager.addPurchasedItem(new Item(totalCost, productType, amtPurchased, LocalDateTime.now().format(formatter).toString()));
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("Thank You for your purchase!");
                    alert.setMessage("Your purchase is " + quantityText.getText().toString() + " " + productType + " for " + totalCostFormatted);
                    alert.show();
                    currentItem.quantity -= amtPurchased;
                    adapter.notifyDataSetChanged();
                    productTypeText.setText("Please tap an item below...");
                    numberPicker.setValue(0);
                    quantityText.setText("0");
                    totalText.setText("Total");
                }
                break;

            case R.id.manager_btn:
                Intent myIntent = new Intent(this, ManagerPanelActivity.class);
                startActivity(myIntent);
                break;
        }
    }
}