package com.ilm.org.wift.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public int kg;
    public int gram;
    public int quantity;
    @TypeConverters({Converters.class})
    public Calendar calendar;
}
