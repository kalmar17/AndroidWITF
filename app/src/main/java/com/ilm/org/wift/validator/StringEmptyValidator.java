package com.ilm.org.wift.validator;


public class StringEmptyValidator implements Validator<String>{
    private String description;

    @Override
    public boolean isValid(String value) {
        if(value.isEmpty()) {
            description = "Value is empty";
            return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
