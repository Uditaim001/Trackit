package com.example.trackit;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Expensecard.class, version=1)
public abstract class ExpenseDatabase extends RoomDatabase {
    private static ExpenseDatabase instance;
    public abstract Exp exp();
    public static synchronized ExpenseDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),ExpenseDatabase.class,"Expense_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
