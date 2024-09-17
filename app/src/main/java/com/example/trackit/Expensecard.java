package com.example.trackit;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class Expensecard {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String Amount;
    private String Type;

    // No-argument constructor
    public Expensecard() {
    }

    // Constructor with parameters (optional, not used by Room)
    public Expensecard(String amount, String type) {
        this.Amount = amount;
        this.Type = type;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Amount
    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        this.Amount = amount;
    }

    // Getter and Setter for Type
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }
}
