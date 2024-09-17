package com.example.trackit;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Exp {
    @Insert
    public void insert(Expensecard expense);
    @Update
    public void change(Expensecard expense);
    @Delete
    public void delete(Expensecard expense);
    @Query("Select * from expense_table")
    public LiveData<List<Expensecard>> show_all();
}
