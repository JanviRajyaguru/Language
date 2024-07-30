package com.example.language;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.language.Lesson3Activity;
import com.example.language.Lesson5Activity;
import com.example.language.R;

public class Lesson4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4);

        Button nextLessonButton = findViewById( R.id.nextLessonButton);
        Button previousLessonButton = findViewById(R.id.previousLessonButton);

        nextLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lesson4Activity.this,
                        Lesson5Activity.class);
                startActivity(intent);
            }
        });

        previousLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lesson4Activity.this, Lesson3Activity.class);
                startActivity(intent);
            }
        });
    }
}
