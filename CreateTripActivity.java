package com.example.splitmoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class CreateTripActivity extends AppCompatActivity {

    private EditText editTextTripName, editTextStartDate, editTextEndDate, editTextTripDescription;
    private Button buttonAddFriends, buttonCreateTrip, buttonShowTripSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        editTextTripName = findViewById(R.id.editTextTripName);
        editTextStartDate = findViewById(R.id.editTextStartDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextTripDescription = findViewById(R.id.editTextTripDescription);
        buttonAddFriends = findViewById(R.id.buttonAddFriends);
        buttonCreateTrip = findViewById(R.id.buttonCreateTrip);
        buttonShowTripSummary = findViewById(R.id.buttonShowTripSummary);

        // DatePicker for Start Date
        editTextStartDate.setOnClickListener(v -> showDatePickerDialog(editTextStartDate));

        // DatePicker for End Date
        editTextEndDate.setOnClickListener(v -> showDatePickerDialog(editTextEndDate));

        buttonAddFriends.setOnClickListener(v -> {
            // Open friend list or allow user to add friends
            Intent intent = new Intent(CreateTripActivity.this, AddFriendsActivity.class);
            startActivity(intent);
        });

        buttonCreateTrip.setOnClickListener(v -> {
            String tripName = editTextTripName.getText().toString().trim();
            String startDate = editTextStartDate.getText().toString().trim();
            String endDate = editTextEndDate.getText().toString().trim();

            if (!tripName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                // Logic to create trip and save details
                Toast.makeText(CreateTripActivity.this, "Trip Created", Toast.LENGTH_SHORT).show();

                // Redirect to Add Expenses Activity
                Intent intent = new Intent(CreateTripActivity.this, AddExpensesActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(CreateTripActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            }
        });

        buttonShowTripSummary.setOnClickListener(v -> showTripSummary());
    }

    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) ->
                        editText.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear),
                year, month, day);
        datePickerDialog.show();
    }

    private void showTripSummary() {
        String tripName = editTextTripName.getText().toString().trim();
        String startDate = editTextStartDate.getText().toString().trim();
        String endDate = editTextEndDate.getText().toString().trim();
        String tripDescription = editTextTripDescription.getText().toString().trim();

        // Create a summary message
        String summary = "Trip Name: " + tripName +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate +
                (tripDescription.isEmpty() ? "" : "\nDescription: " + tripDescription);

        // Show the summary in a Toast
        Toast.makeText(this, summary, Toast.LENGTH_LONG).show();
    }
}
