package com.ilm.org.wift.dao;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.ilm.org.wift.database.AppDatabase;
import com.ilm.org.wift.model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ProductDaoTest {
    private AppDatabase db;
    private ProductDao productDao;

    @Before
    public void createDb() throws Exception {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        productDao = db.productDao();

    }

    @After
    public void closeDb() throws Exception {
        db.close();
    }

    @Test
    public void whenInsertEmployeeThenReadTheSameOne() throws Exception {
        Product product = new Product(1, "name", 11, 11, 11, new GregorianCalendar(2019, 2, 22));

        productDao.insert(product);

        assertEquals(1, productDao.getAll().size());
        assertEquals(product, productDao.getAll().get(0));
    }



    @Test
    public void whenDeleteAllThenReadNothing() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "name", 11, 11, 11, new GregorianCalendar(2019, 2, 22)));
        products.add(new Product(2, "name", 11, 11, 11, new GregorianCalendar(2019, 2, 22)));
        productDao.insertAll(products);

        productDao.deleteAll();

        assertTrue(productDao.getAll().isEmpty());
    }

}