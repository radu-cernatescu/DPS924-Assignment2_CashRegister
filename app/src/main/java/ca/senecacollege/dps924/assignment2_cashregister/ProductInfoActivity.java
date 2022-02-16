package ca.senecacollege.dps924.assignment2_cashregister;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProductInfoActivity extends AppCompatActivity {
    TextView productInfoText;
    TextView productInfoPrice;
    TextView productInfoDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info_page);

        productInfoText = findViewById(R.id.product_info_text);
        productInfoPrice = findViewById(R.id.product_info_price);
        productInfoDate = findViewById(R.id.product_info_date);

        Item item = getIntent().getExtras().getParcelable("items");

        if (item != null) {
            productInfoText.setText("Product: " + item.product_type);
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
            decimalFormat.setMinimumFractionDigits(2);
            String totalCostFormatted = decimalFormat.format(item.amount);
            productInfoPrice.setText("Price: " + totalCostFormatted);
            productInfoDate.setText("Purchase Date: " + item.purchaseDate);
        }

    }
}
