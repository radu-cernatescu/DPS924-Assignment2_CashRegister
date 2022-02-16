package ca.senecacollege.dps924.assignment2_cashregister;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManagerPanelActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_panel);

        Item itemArray = getIntent().getExtras().getParcelable("items");

        if (itemArray != null) {
            Log.e("test",itemArray.product_type);
        }
    }
}
