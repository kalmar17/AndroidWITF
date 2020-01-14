package com.ilm.org.wift.validator;

public interface Validator<T> {

    boolean isValid(T value);

    String getDescription();
}