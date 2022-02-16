package ca.senecacollege.dps924.assignment2_cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManagerPanelActivity extends AppCompatActivity implements View.OnClickListener {
    Button historyBtn;
    Button restockBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_panel);
        historyBtn = findViewById(R.id.history_btn);
        restockBtn = findViewById(R.id.restock_btn);
        historyBtn.setOnClickListener(this);
        restockBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.history_btn:
                Intent myIntent = new Intent(this, HistoryActivity.class);
                startActivity(myIntent);
                break;
            case R.id.restock_btn:
                Intent restockIntent = new Intent(this, RestockActivity.class);
                startActivity(restockIntent);
                break;
        }
    }
}
