package com.example.trackit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.trackit.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ExpenseViewModel expenseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        expenseViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory)ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ExpenseViewModel.class);

        binding.RV.setLayoutManager(new LinearLayoutManager(this));
        binding.RV.setHasFixedSize(true);
        Rvadapter rvadapter = new Rvadapter();
        binding.RV.setAdapter(rvadapter);

        expenseViewModel.getdata().observe(this, new Observer<List<Expensecard>>() {
            @Override
            public void onChanged(List<Expensecard> expensecards) {
                String size=String.valueOf(expensecards.size());
                Log.d("MainActivity", "LiveData changed: " + expensecards.toString());
                Toast.makeText(MainActivity.this, size, Toast.LENGTH_SHORT).show();
                rvadapter.submitList(expensecards);
            }
        });


        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String type = data.getStringExtra("Type");
            String amount = data.getStringExtra("Amount");
            Expensecard expensecard = new Expensecard(amount, type);
            expenseViewModel.Insert(expensecard);
            Toast.makeText(this,  "Inserted: " + amount + ", " + type, Toast.LENGTH_SHORT).show();
        }
    }
}
