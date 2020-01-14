package com.ilm.org.wift;

import com.ilm.org.wift.model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ProductUnitTest {

    Product product;
    @Before
    public void initTest() {
        product = new Product();
    }

    @After
    public void afterTest() {
        product = null;
    }
    @Test
    public void creatyProduct(){
        product = new Product();
        String test = "test";
        int kg = 10;
        int gram = 100;
        int quantity = 100;
        Calendar calendar = new GregorianCalendar(2019,2,1);
        product.setKg(kg);
        product.setName(test);
        product.setGram(gram);
        product.setQuantity(quantity);
        product.setCalendar(calendar);
        assertTrue(product.getName().equals(test));
        assertTrue(product.getKg()==kg);
        assertTrue(product.getGram()==gram);
        assertTrue(product.getQuantity()==quantity);
        assertTrue(product.getCalendar()==calendar);

    }

}