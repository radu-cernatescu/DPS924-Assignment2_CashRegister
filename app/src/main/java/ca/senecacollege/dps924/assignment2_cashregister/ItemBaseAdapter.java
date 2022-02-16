package ca.senecacollege.dps924.assignment2_cashregister;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ItemBaseAdapter extends BaseAdapter {
    ArrayList<Item> items;
    Context context;

    public ItemBaseAdapter(ArrayList<Item> listOfItems, Context context) {
        this.items = listOfItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int i) {
        return items.get(i);
    }

    public Item getItemByName(String name) {
        for (Item item: items) {
            if (item.product_type.equals(name)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.base_adapter_row_layout,null);

        TextView productType = view.findViewById(R.id.list_product_type);
        TextView productCost = view.findViewById(R.id.list_product_cost);
        TextView productQuantity = view.findViewById(R.id.list_product_quantity);

        productType.setText(items.get(i).product_type);
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
        decimalFormat.setMinimumFractionDigits(2);
        String totalCostFormatted = decimalFormat.format(items.get(i).amount);
        productCost.setText(totalCostFormatted);
        productQuantity.setText(String.valueOf(items.get(i).quantity));

        return view;
    }
}
