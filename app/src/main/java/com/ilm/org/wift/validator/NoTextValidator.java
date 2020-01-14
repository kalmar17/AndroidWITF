package com.ilm.org.wift.validator;

import android.widget.EditText;

import java.util.regex.Pattern;

public class NoTextValidator implements Validator<String> {
    private String description;
    @Override
    public boolean isValid(String value) {
        if(Pattern.compile("[0-9]").matcher(value).matches()){
            description = "View should be in \\'0-9\\' format";
            return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
