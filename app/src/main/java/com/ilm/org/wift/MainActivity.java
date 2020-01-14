package com.ilm.org.wift;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.ilm.org.wift.dao.ProductDao;
import com.ilm.org.wift.database.AppDatabase;
import com.ilm.org.wift.model.Product;
import com.ilm.org.wift.validator.EmptyValidator;
import com.ilm.org.wift.validator.NoTextValidator;
import com.ilm.org.wift.validator.Validator;
import com.ilm.org.wift.validator.ValidatorsComposer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText mDisplayDate;
    private EditText productName;
    private EditText productKg;
    private EditText productGram;
    private EditText productQuatity;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private Product product;
    boolean b = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productName = findViewById(R.id.edit_product_name);
        productKg = findViewById(R.id.edit_kg);
        productGram = findViewById(R.id.edit_gram);
        productQuatity = findViewById(R.id.edit_quatity);
        mDisplayDate = findViewById(R.id.edit_shelf_life);
        product = (Product) getIntent().getSerializableExtra("key");

        if (product != null) {
            b = true;
            String date = product.getCalendar().get(Calendar.DAY_OF_MONTH) + "." +
                    (product.getCalendar().get(Calendar.MONTH)+1) + "." +
                    product.getCalendar().get(Calendar.YEAR);

            productName.setText(product.getName());
            productKg.setText(String.valueOf(product.getKg()));
            productGram.setText(String.valueOf(product.getGram()));
            productQuatity.setText(String.valueOf(product.getQuantity()));
            mDisplayDate.setText(date);
        } else {
            product = new Product();
        }
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Calendar cal = Calendar.getInstance();
                    if (!mDisplayDate.getText().toString().equals(""))
                        cal.setTime(Objects.requireNonNull(sdf.parse(mDisplayDate.getText().toString())));

                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateSetListener,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
                Log.d("MainActivity", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "." + (month+1) + "." + year;
                mDisplayDate.setText(date);
            }
        };
        Button close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });
        Button save = findViewById(R.id.save);
        final Validator<EditText> viewTextValidator = new EmptyValidator();
        final List<EditText> editEmptyList = Arrays.asList(productName, productKg, productGram, productQuatity, mDisplayDate);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean foo = true;
                    for (EditText editText : editEmptyList) {
                        if (!viewTextValidator.isValid(editText)) {
                            editText.setError(viewTextValidator.getDescription());
                            foo = false;
                        }
                    }
                    if (foo) {
                        product.setName(productName.getText().toString());
                        product.setKg(Integer.parseInt(productKg.getText().toString()));
                        product.setGram(Integer.parseInt(productGram.getText().toString()));
                        product.setQuantity(Integer.parseInt(productQuatity.getText().toString()));
                        Date date = sdf.parse(mDisplayDate.getText().toString());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));

                        product.setCalendar(cal);

                        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
                        ProductDao productDao = db.productDao();
                        if (b) {
                            productDao.update(product);
                        } else {
                            productDao.insert(product);
                        }
                        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(intent);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
