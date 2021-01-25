package com.example.itemtracker;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReportMissingBroken extends AppCompatActivity {
    Button btn_add, btn_delete, btn_viewAll;
    EditText et_item_name, et_item_id, et_item_details;
    CheckBox cb_missing, cb_broken;
    ListView lv_display;
    ArrayAdapter itemArrayAdapter;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.missing_broken);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_item_name = findViewById(R.id.et_item_name);
        et_item_id = findViewById(R.id.et_item_id);
        cb_missing = findViewById(R.id.cb_missing);
        cb_broken = findViewById(R.id.cb_broken);
        et_item_details = findViewById(R.id.et_item_details);
        lv_display = findViewById(R.id.lv_display);
        // databaseHelper = new DatabaseHelper(ReportMissingBroken.this);
        // ShowItemsOnListView(databaseHelper);
        //button listeners
        btn_add.setOnClickListener(v -> {

            ReportItemModel reportItemModel;
            try {
                reportItemModel = new ReportItemModel(et_item_name.getText().toString(), et_item_id.getText().toString(), cb_missing.isChecked(), cb_broken.isChecked(), et_item_details.getText().toString());
                Toast.makeText(ReportMissingBroken.this, reportItemModel.toString(), Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Toast.makeText(ReportMissingBroken.this, "Error adding Item.", Toast.LENGTH_SHORT).show();
                reportItemModel = new ReportItemModel("Error", "Error", false, false,"Error");
            }

            databaseHelper = new DatabaseHelper(ReportMissingBroken.this);

            boolean success = databaseHelper.addOne(reportItemModel);
            Toast.makeText(ReportMissingBroken.this, "Success= " + success, Toast.LENGTH_SHORT).show();
            // ShowItemsOnListView(databaseHelper);
        });

        btn_viewAll.setOnClickListener(v -> {
            databaseHelper = new DatabaseHelper(ReportMissingBroken.this);
            ShowItemsOnListView(databaseHelper);
            //Toast.makeText(MainActivity.this, allItems.toString(), Toast.LENGTH_SHORT).show();
        });

        lv_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReportItemModel clickedItem = (ReportItemModel) parent.getItemAtPosition(position);
                btn_delete.setOnClickListener(v -> {
                    databaseHelper.deleteOne(clickedItem);
                    ShowItemsOnListView(databaseHelper);
                    Toast.makeText(ReportMissingBroken.this, "Deleted " + clickedItem, Toast.LENGTH_SHORT).show();
                });
            }
        });

        lv_display.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ReportItemModel clickedItem = (ReportItemModel) parent.getItemAtPosition(position);
                databaseHelper.deleteOne(clickedItem);
                ShowItemsOnListView(databaseHelper);
                Toast.makeText(ReportMissingBroken.this, "Deleted " + clickedItem, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

    private void ShowItemsOnListView(DatabaseHelper databaseHelper1) {
        itemArrayAdapter = new ArrayAdapter<ReportItemModel>(ReportMissingBroken.this, android.R.layout.simple_list_item_1, databaseHelper.getAll());
        lv_display.setAdapter(itemArrayAdapter);
    }

}
