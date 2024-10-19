package com.example.splitmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpensesActivity extends AppCompatActivity {

    private EditText editTextExpenseDescription, editTextExpenseAmount;
    private Spinner spinnerWhoPaid;
    private Button buttonAddExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        editTextExpenseDescription = findViewById(R.id.editTextExpenseDescription);
        editTextExpenseAmount = findViewById(R.id.editTextExpenseAmount);
        spinnerWhoPaid = findViewById(R.id.spinnerWhoPaid);
        buttonAddExpense = findViewById(R.id.buttonAddExpense);

        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editTextExpenseDescription.getText().toString();
                String amount = editTextExpenseAmount.getText().toString();
                String whoPaid = spinnerWhoPaid.getSelectedItem().toString();

                if (!description.isEmpty() && !amount.isEmpty()) {
                    // Logic to add expense details to trip (you'll implement splitting logic later)
                    Toast.makeText(AddExpensesActivity.this, "Expense Added", Toast.LENGTH_SHORT).show();

                    // Reset fields for next entry
                    editTextExpenseDescription.setText("");
                    editTextExpenseAmount.setText("");
                    spinnerWhoPaid.setSelection(0);

                    // Navigate to ExpensesSummaryActivity
                    Intent intent = new Intent(AddExpensesActivity.this, ExpensesSummaryActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddExpensesActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
