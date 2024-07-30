package com.example.language;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Lesson6Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6);

        Button nextLessonButton = findViewById(R.id.nextLessonButton);
        Button previousLessonButton = findViewById(R.id.previousLessonButton);

        nextLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lesson6Activity.this, CourseCompletionActivity.class);
                startActivity(intent);
            }
        });

        previousLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lesson6Activity.this, Lesson5Activity.class);
                startActivity(intent);
            }
        });
    }
}
