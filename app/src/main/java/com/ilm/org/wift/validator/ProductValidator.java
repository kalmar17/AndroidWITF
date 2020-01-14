package com.ilm.org.wift.validator;

import android.text.TextUtils;

import com.ilm.org.wift.model.Product;

public class ProductValidator implements Validator<Product> {
    private String description;

    @Override
    public boolean isValid(Product value) {
        if (value == null) {
            description = "product is empty!";
            return false;
        }
        if (TextUtils.isEmpty(value.getName())) {
            description = "product name is empty!";
            return false;
        }
        final Integer kg = value.getKg();
        if (String.valueOf(value.getKg()).equals(""))
            if (value.getKg() < 0) {

            }
        return true;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
