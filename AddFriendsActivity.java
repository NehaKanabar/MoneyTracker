package com.example.splitmoney;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddFriendsActivity extends AppCompatActivity {

    private EditText editTextFriendName;
    private Button buttonAddFriend, buttonShowFriends;
    private ListView listViewFriends;
    private ArrayList<String> friendsList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends); // Ensure this matches your XML file name

        editTextFriendName = findViewById(R.id.editTextFriendName);
        buttonAddFriend = findViewById(R.id.buttonAddFriend);
        buttonShowFriends = findViewById(R.id.buttonShowFriends);
        listViewFriends = findViewById(R.id.listViewFriends);

        // Initialize the ArrayList and ArrayAdapter
        friendsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friendsList);
        listViewFriends.setAdapter(adapter);

        // Set OnClickListener for Add Friend Button
        buttonAddFriend.setOnClickListener(v -> {
            String friendName = editTextFriendName.getText().toString().trim();

            if (!friendName.isEmpty()) {
                // Add friend to the list
                friendsList.add(friendName);
                adapter.notifyDataSetChanged(); // Notify adapter of data changes
                editTextFriendName.setText(""); // Clear the input field
                Toast.makeText(AddFriendsActivity.this, "Friend Added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddFriendsActivity.this, "Please enter a friend's name", Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for Show Friends Button
        buttonShowFriends.setOnClickListener(v -> {
            if (friendsList.isEmpty()) {
                Toast.makeText(AddFriendsActivity.this, "No friends added yet", Toast.LENGTH_SHORT).show();
            } else {
                // Display a message indicating that the friends list is shown
                Toast.makeText(AddFriendsActivity.this, "Friends list shown above", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
