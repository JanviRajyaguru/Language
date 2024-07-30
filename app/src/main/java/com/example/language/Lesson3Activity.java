package com.example.language;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Lesson3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);

        Button nextLessonButton = findViewById(R.id.nextLessonButton);
        Button previousLessonButton = findViewById(R.id.previousLessonButton);

        nextLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lesson3Activity.this, com.example.language.Lesson4Activity.class);
                startActivity(intent);
            }
        });

        previousLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lesson3Activity.this, Lesson2Activity.class);
                startActivity(intent);
            }
        });
    }
}
