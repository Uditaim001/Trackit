package com.example.trackit;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Exprepo {
    private Exp expdao;
    private LiveData<List<Expensecard>> Expense_list;

    public Exprepo(Application application) {
        ExpenseDatabase expenseDatabase= ExpenseDatabase.getInstance(application);
        expdao=expenseDatabase.exp();
        Expense_list=expdao.show_all();
    }
    public void insertdata(Expensecard expense){new InsertTask(expdao).execute(expense);};
    public void changedata(Expensecard expense){new DeleteTask(expdao).execute(expense);};
    public void deletedata(Expensecard expense){new ChangeTask(expdao).execute(expense);};
    public LiveData<List<Expensecard>> show_all_data(){
        return Expense_list;
    };
    private static class InsertTask extends AsyncTask<Expensecard,Void,Void>{
        private Exp exp;

        public InsertTask(Exp exp) {
            this.exp = exp;
        }

        @Override
        protected Void doInBackground(Expensecard... expensecards) {
            exp.insert(expensecards[0]);
            Log.d("Exprepo", "Inserted into DB: " + expensecards[0].getType() + ", " + expensecards[0].getAmount());

            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<Expensecard,Void,Void>{
        private Exp exp;

        public DeleteTask(Exp exp) {
            this.exp = exp;
        }

        @Override
        protected Void doInBackground(Expensecard... expensecards) {
            exp.delete(expensecards[0]);
            return null;
        }
    }
    private static class ChangeTask extends AsyncTask<Expensecard,Void,Void>{
        private Exp exp;

        public ChangeTask(Exp exp) {
            this.exp = exp;
        }

        @Override
        protected Void doInBackground(Expensecard... expensecards) {
            exp.change(expensecards[0]);
            return null;
        }
    }
}
