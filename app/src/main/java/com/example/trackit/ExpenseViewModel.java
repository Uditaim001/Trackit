package com.example.trackit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private Exprepo exprepo;
    private LiveData<List<Expensecard>> expenselist;
    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        exprepo= new Exprepo(application);
        expenselist= exprepo.show_all_data();
    }
    public void Insert(Expensecard expensecard){
        exprepo.insertdata(expensecard);
    }
    public void Delete(Expensecard expensecard){
        exprepo.deletedata(expensecard);
    }
    public void Change(Expensecard expensecard){
        exprepo.changedata(expensecard);
    }
    public LiveData<List<Expensecard>> getdata(){
        return expenselist;
    }

}
