package com.ilm.org.wift.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ilm.org.wift.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE id = :id")
    Product getById(long id);

    @Insert
    void insert(Product product);

    @Insert
    void insertAll(List<Product> products);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM product")
    void deleteAll();
}
