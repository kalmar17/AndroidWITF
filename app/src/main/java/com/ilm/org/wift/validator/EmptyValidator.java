package com.ilm.org.wift.validator;

import android.text.TextUtils;
import android.widget.EditText;

public class EmptyValidator implements Validator<EditText> {
    private String description;
    @Override
    public boolean isValid(EditText value) {
        if(TextUtils.isEmpty(value.getText().toString())){
            description = "Field must not be empty";
            return false;
        }

        return true;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
