package com.ilm.org.wift;

import com.ilm.org.wift.validator.StringEmptyValidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidateTest {
String string ;

    @Before
    public void initTest() {
        string = "";
    }
    @After
    public void afterTest() {
        string = null;
    }
    @Test
    public void testOne(){
        StringEmptyValidator validator = new StringEmptyValidator();
        boolean b;
        if(validator.isValid(string))
            b=false;
        else
            b=true;
        assertTrue(b);
    }

}
