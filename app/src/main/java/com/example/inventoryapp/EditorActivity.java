package com.example.inventoryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryapp.data.ItemViewModel;

public class EditorActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_NAME = "com.codinginflow.architectureexample.EXTRA_NAME";
    public static final String EXTRA_PRICE = "com.codinginflow.architectureexample.EXTRA_PRICE";
    public static final String EXTRA_QUANTITY = "com.codinginflow.architectureexample.EXTRA_QUANTITY";
    public static final String EXTRA_SUPPLIER = "com.codinginflow.architectureexample.EXTRA_SUPPLIER";

    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextSupplier;
    private ImageView imageView;
    private NumberPicker quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editTextName = findViewById(R.id.name_editText);
        editTextPrice = findViewById(R.id.price_editText);
        editTextSupplier = findViewById(R.id.supplier_editText);
        imageView = findViewById(R.id.itemImage);
        quantity = findViewById(R.id.number_picker);
        quantity.setMinValue(1);
        quantity.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextPrice.setText(String.valueOf(intent.getIntExtra(EXTRA_PRICE, 1)));
            editTextSupplier.setText(intent.getStringExtra(EXTRA_SUPPLIER));
            quantity.setValue(intent.getIntExtra(EXTRA_QUANTITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.editor_activity_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_item:
                saveNote();
                return true;
            case R.id.delete_item_btn:
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to delete this item?");
                // add the buttons
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteNote();
                        Toast.makeText(EditorActivity.this, "Item is deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String name = editTextName.getText().toString();
        String priceString = editTextPrice.getText().toString();
        String supplier = editTextSupplier.getText().toString();
        int quantityValue = quantity.getValue();
        if (name.trim().isEmpty() || supplier.trim().isEmpty() || priceString.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        int price = Integer.parseInt(priceString);
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_PRICE, price);
        data.putExtra(EXTRA_QUANTITY, quantityValue);
        data.putExtra(EXTRA_SUPPLIER, supplier);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    private void deleteNote(){

    }
}
