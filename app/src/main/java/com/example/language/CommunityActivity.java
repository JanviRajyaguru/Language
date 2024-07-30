package com.example.language;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity {

    private EditText postInput;
    private Button addPostButton;
    private ListView postsListView;
    private ArrayAdapter<String> postsAdapter;
    private ArrayList<String> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        postInput = findViewById(R.id.postInput);
        addPostButton = findViewById(R.id.addPostButton);
        postsListView = findViewById(R.id.postsListView);

        postsList = new ArrayList<>();
        postsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postsList);
        postsListView.setAdapter(postsAdapter);

        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPost();
            }
        });

        postsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CommunityActivity.this, "Post clicked: " + postsList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addNewPost() {
        String newPost = postInput.getText().toString().trim();
        if (newPost.isEmpty()) {
            Toast.makeText(this, "Please enter a post", Toast.LENGTH_SHORT).show();
        } else {
            postsList.add(newPost);
            postsAdapter.notifyDataSetChanged();
            postInput.setText("");
            Toast.makeText(this, "Post added", Toast.LENGTH_SHORT).show();
        }
    }
}
