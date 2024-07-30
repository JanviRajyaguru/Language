package com.example.language;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressActivity extends AppCompatActivity {

    private TextView lessonsCompletedTextView;
    private TextView quizScoreTextView;
    private TextView overallProgressTextView;
    private Button refreshProgressButton;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        lessonsCompletedTextView = findViewById(R.id.lessonsCompleted);
        quizScoreTextView = findViewById(R.id.quizScore);
        overallProgressTextView = findViewById(R.id.overallProgress);
        refreshProgressButton = findViewById(R.id.refreshProgressButton);

        sharedPreferences = getSharedPreferences("LanguageLearningPrefs", MODE_PRIVATE);

        refreshProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProgress();
            }
        });

        // Load initial progress
        updateProgress();
    }

    private void updateProgress() {
        int lessonsCompleted = sharedPreferences.getInt("lessonsCompleted", 0);
        int totalLessons = 10; // Adjust based on total number of lessons
        int quizzesTaken = sharedPreferences.getInt("quizzesTaken", 0);
        int quizzesTotal = 5; // Adjust based on total number of quizzes
        int correctAnswers = sharedPreferences.getInt("correctAnswers", 0);
        int totalQuestions = quizzesTotal * 5; // Assuming each quiz has 5 questions

        // Update TextViews with progress
        lessonsCompletedTextView.setText("Lessons Completed: " + lessonsCompleted);
        quizScoreTextView.setText("Quiz Score: " + correctAnswers + "/" + totalQuestions);

        // Calculate overall progress as a percentage
        int progressPercentage = (int) (((float) lessonsCompleted / totalLessons) * 100);
        overallProgressTextView.setText("Overall Progress: " + progressPercentage + "%");
    }
}
