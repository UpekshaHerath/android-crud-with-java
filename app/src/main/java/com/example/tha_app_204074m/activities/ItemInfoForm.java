package com.example.tha_app_204074m.activities;

import static com.example.tha_app_204074m.utils.Validator.DecimalPointValidator;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tha_app_204074m.R;
import com.example.tha_app_204074m.utils.Validator;

public class ItemInfoForm extends AppCompatActivity {
    EditText title;
    EditText description;
    EditText price;
    Button edit;
    Button delete;
    Button add;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info_form);

        title = findViewById(R.id.et_fm_title);
        description = findViewById(R.id.et_fm_description);
        price = findViewById(R.id.et_fm_price);
        edit = findViewById(R.id.btn_fm_update);
        delete = findViewById(R.id.btn_fm_delete);
        add = findViewById(R.id.btn_fm_add);

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = price.getText().toString();
                if (str.isEmpty()) return;
                String str2 = DecimalPointValidator(str, 2);

                if (!str2.equals(str)) {
                    price.setText(str2);
                    price.setSelection(str2.length());
                }
            }
        });

        Intent i = getIntent();
        if (i.getExtras().getInt("type") == 1) {
            edit.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
            add.setVisibility(View.VISIBLE);
            add.setOnClickListener(v -> {
                try {
                    Validator.InputsValidator(title.getText().toString(), description.getText().toString(), price.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("Title", title.getText().toString());
                    intent.putExtra("Description", description.getText().toString());
                    intent.putExtra("Price", Double.valueOf(price.getText().toString()));
                    setResult(1, intent);
                    finish();
                } catch (RuntimeException e) {
                    alert = new AlertDialog.Builder(this);
                    alert.setTitle("Information");
                    alert.setMessage(e.getMessage());
                    alert.setPositiveButton("OK", (dialog, which) -> {
                        dialog.dismiss();
                    });
                    alert.show();
                }
            });
        } else {
            title.setText(i.getExtras().getString("Title"));
            description.setText(i.getExtras().getString("Description"));
            price.setText(String.valueOf(i.getExtras().getDouble("Price")));
            edit.setOnClickListener(v -> {
                try{
                    Validator.InputsValidator(title.getText().toString(), description.getText().toString(), price.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("Title", title.getText().toString());
                    intent.putExtra("Description", description.getText().toString());
                    intent.putExtra("Price", Double.valueOf(price.getText().toString()));
                    intent.putExtra("Position", getIntent().getIntExtra("Position", 0));
                    intent.putExtra("ID", getIntent().getIntExtra("ID", 0));
                    setResult(2, intent);
                    finish();
                }catch (Exception e){
                    alert = new AlertDialog.Builder(this);
                    alert.setTitle("Information");
                    alert.setMessage(e.getMessage());
                    alert.setPositiveButton("OK", (dialog, which) -> {
                        dialog.dismiss();
                    });
                    alert.show();
                }
            });

            delete.setOnClickListener(v -> {
                alert = new AlertDialog.Builder(this);
                alert.setTitle("Warning");
                alert.setMessage("Do you want delete this item? This action cannot be undone");
                alert.setPositiveButton("Yes", (dialog, which) -> {
                    Intent intent = new Intent();
                    intent.putExtra("Position", getIntent().getIntExtra("Position", 0));
                    setResult(3, intent);
                    finish();
                    dialog.dismiss();
                });
                alert.setNegativeButton("No", (dialog, which) -> {
                    finish();
                    dialog.dismiss();
                });
                alert.show();
            });
        }
    }
}