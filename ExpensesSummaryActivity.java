package com.example.splitmoney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ExpensesSummaryActivity extends AppCompatActivity {

    private RecyclerView recyclerViewExpenses;
    private ExpensesAdapter expensesAdapter;
    private List<ExpenseItem> expenseItems;
    private Button buttonSplitExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_summary);

        recyclerViewExpenses = findViewById(R.id.recyclerViewExpenses);
        buttonSplitExpenses = findViewById(R.id.buttonSplitExpenses);

        // Example expense data
        expenseItems = new ArrayList<>();
        expenseItems.add(new ExpenseItem("Dinner", 300));
        expenseItems.add(new ExpenseItem("Transport", 150));
        expenseItems.add(new ExpenseItem("Hotel", 500));
        expenseItems.add(new ExpenseItem("Activities", 200));

        // Set up RecyclerView
        expensesAdapter = new ExpensesAdapter(expenseItems);
        recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewExpenses.setAdapter(expensesAdapter);

        // Set click listener for the Split Expenses button
        buttonSplitExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement split expenses logic
            }
        });
    }

    private static class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {
        private final List<ExpenseItem> expenseItems;

        public ExpensesAdapter(List<ExpenseItem> expenseItems) {
            this.expenseItems = expenseItems;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ExpenseItem expenseItem = expenseItems.get(position);
            holder.textViewDescription.setText(expenseItem.getDescription());
            holder.textViewAmount.setText("₹" + expenseItem.getAmount());
        }

        @Override
        public int getItemCount() {
            return expenseItems.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewDescription;
            TextView textViewAmount;

            ViewHolder(View itemView) {
                super(itemView);
                textViewDescription = itemView.findViewById(R.id.textViewDescription);
                textViewAmount = itemView.findViewById(R.id.textViewAmount);
            }
        }
    }

    private static class ExpenseItem {
        private final String description;
        private final int amount;

        public ExpenseItem(String description, int amount) {
            this.description = description;
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public int getAmount() {
            return amount;
        }
    }
}
