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
import java.util.HashMap;
import java.util.List;

public class SplitCalculationActivity extends AppCompatActivity {

    private TextView textViewTotalAmount;
    private Button buttonDone;
    private RecyclerView recyclerViewSplitExpenses;
    private SplitExpensesAdapter expensesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_calculation);

        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        buttonDone = findViewById(R.id.buttonDone);
        recyclerViewSplitExpenses = findViewById(R.id.recyclerViewSplitExpenses);

        // Example expense data
        HashMap<String, Integer> expenses = new HashMap<>();
        expenses.put("A", 300);
        expenses.put("B", 100);
        expenses.put("C", 50);
        expenses.put("D", 250);
        expenses.put("E", 150);
        // Calculate total and determine how much each person owes or is owed
        List<SplitResult> splitResults = calculateSplit(expenses);

        // Calculate and set the total amount
        int totalSpent = 0;
        for (int amount : expenses.values()) {
            totalSpent += amount;
        }
        textViewTotalAmount.setText("Total Amount: ₹" + totalSpent);

        // Set up RecyclerView
        expensesAdapter = new SplitExpensesAdapter(splitResults);
        recyclerViewSplitExpenses.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSplitExpenses.setAdapter(expensesAdapter);

        // Set click listener for the Done button
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });
    }

    private List<SplitResult> calculateSplit(HashMap<String, Integer> expenses) {
        List<SplitResult> results = new ArrayList<>();
        int totalSpent = 0;
        int numberOfPeople = expenses.size();

        // Calculate total spent
        for (int amount : expenses.values()) {
            totalSpent += amount;
        }

        // Calculate amount each person should pay
        int amountPerPerson = totalSpent / numberOfPeople;

        // Determine how much each person owes or is owed
        for (String person : expenses.keySet()) {
            int balance = expenses.get(person) - amountPerPerson;
            if (balance > 0) {
                results.add(new SplitResult(person, "is owed ₹" + balance));
            } else if (balance < 0) {
                results.add(new SplitResult(person, "owes ₹" + -balance));
            } else {
                results.add(new SplitResult(person, "is settled"));
            }
        }

        return results;
    }

    private static class SplitExpensesAdapter extends RecyclerView.Adapter<SplitExpensesAdapter.ViewHolder> {
        private final List<SplitResult> splitResults;

        public SplitExpensesAdapter(List<SplitResult> splitResults) {
            this.splitResults = splitResults;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_split_result, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            SplitResult splitResult = splitResults.get(position);
            holder.textViewPerson.setText(splitResult.getPerson());
            holder.textViewStatus.setText(splitResult.getStatus());
        }

        @Override
        public int getItemCount() {
            return splitResults.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewPerson;
            TextView textViewStatus;

            ViewHolder(View itemView) {
                super(itemView);
                textViewPerson = itemView.findViewById(R.id.textViewPerson);
                textViewStatus = itemView.findViewById(R.id.textViewStatus);
            }
        }
    }

    private static class SplitResult {
        private final String person;
        private final String status;

        public SplitResult(String person, String status) {
            this.person = person;
            this.status = status;
        }

        public String getPerson() {
            return person;
        }

        public String getStatus() {
            return status;
        }
    }
}
