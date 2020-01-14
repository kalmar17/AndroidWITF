package com.ilm.org.wift;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ilm.org.wift.dao.ProductDao;
import com.ilm.org.wift.database.AppDatabase;
import com.ilm.org.wift.model.User;
import com.ilm.org.wift.validator.EmptyValidator;
import com.ilm.org.wift.validator.Validator;

import java.util.Arrays;
import java.util.List;

public class AuthActivity extends AppCompatActivity {
    EditText login;
    EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        ProductDao productDao = appDatabase.productDao();
        final User user = new User("Petr", "qwerty123", productDao.getAll());
        login = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        final Validator<EditText> viewTextValidator = new EmptyValidator();
        final List<EditText> editEmptyList = Arrays.asList(login, password);

        final Intent intent = new Intent(this, Main2Activity.class);
        Button b = findViewById(R.id.button_sing_in);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean foo = true;
                for (EditText editText : editEmptyList) {
                    if (!viewTextValidator.isValid(editText)) {
                        editText.setError(viewTextValidator.getDescription());
                        foo = false;
                    }
                }
                if (foo) {
                    if (login.getText().toString().equals(user.getName()))
                        if (password.getText().toString().equals(user.getPassword()))
                            startActivity(intent);
                        else {
                            password.setError("Error");
                        }
                    else
                        login.setError("Error");
                }
            }
        });


    }
}
