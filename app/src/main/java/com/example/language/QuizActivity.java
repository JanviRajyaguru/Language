package com.example.language;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup quizOptionsGroup;
    private Button submitQuizButton;
    private Button nextQuestionButton;
    private Button previousQuestionButton;
    private TextView quizResult;
    private TextView quizQuestionNumber;
    private TextView quizQuestion;

    private String[] questions;
    private String[][] options;
    private String[] answers;

    private int currentQuestionIndex = 0;
    private int correctAnswersCount = 0; // Track the number of correct answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizOptionsGroup = findViewById(R.id.quizOptionsGroup);
        submitQuizButton = findViewById(R.id.submitQuizButton);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);
        previousQuestionButton = findViewById(R.id.previousQuestionButton);
        quizResult = findViewById(R.id.quizResult);
        quizQuestionNumber = findViewById(R.id.quizQuestionNumber);
        quizQuestion = findViewById(R.id.quizQuestion);

        // Initialize questions, options, and answers
        initializeQuizData();

        // Set up listeners
        submitQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                // After checking the answer, save quiz data
                saveQuizData();
            }
        });

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuestion();
            }
        });

        previousQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPreviousQuestion();
            }
        });

        // Load the first question
        loadQuestion();
    }

    private void initializeQuizData() {
        List<String> questionsList = Arrays.asList(
                // Lesson 1: Basics
                "What is the English word for 'Hola' in Spanish?",
                "What is the English word for 'Merci' in French?",

                // Lesson 2: Vocabulary
                "What is the meaning of the word 'apple'?",
                "Which word means 'a place where books are kept'?",

                // Lesson 3: Grammar
                "Which is a correct sentence?",
                "Which word is a verb?",

                // Lesson 4: Tenses
                "Which sentence is in past tense?",
                "Which sentence is in future tense?",

                // Lesson 5: Advanced Grammar
                "Which is a complex sentence?",
                "Which is a passive voice sentence?",

                // Lesson 6: Idioms and Phrases
                "What does the idiom 'Break a leg' mean?",
                "What does the phrase 'It's raining cats and dogs' mean?",

                // Additional questions
                "What is the synonym of 'happy'?",
                "What is the antonym of 'dark'?",
                "Which word means 'a person who writes books'?"
        );

        List<String>[] optionsList = new List[]{
                Arrays.asList("Hello", "Goodbye", "Please", "Thank you"),
                Arrays.asList("Sorry", "Thank you", "Yes", "No"),

                Arrays.asList("A fruit", "A vegetable", "A color", "A vehicle"),
                Arrays.asList("Library", "School", "Museum", "Hospital"),

                Arrays.asList("He go to school.", "He goes to school.", "He going to school.", "He gone to school."),
                Arrays.asList("Run", "Quickly", "Beautiful", "Happy"),

                Arrays.asList("I went to the park.", "I go to the park.", "I will go to the park.", "I am going to the park."),
                Arrays.asList("I will eat dinner.", "I eat dinner.", "I ate dinner.", "I am eating dinner."),

                Arrays.asList("Although it was raining, we went for a walk.", "It is raining.", "We went for a walk.", "It will rain."),
                Arrays.asList("The cake was eaten by John.", "John eats the cake.", "John is eating the cake.", "John will eat the cake."),

                Arrays.asList("Good luck", "Bad luck", "Try harder", "Stop trying"),
                Arrays.asList("It's raining very heavily", "It's raining lightly", "It's not raining", "It's snowing"),

                Arrays.asList("Sad", "Angry", "Joyful", "Scared"),
                Arrays.asList("Bright", "Hot", "Warm", "Cold"),
                Arrays.asList("Teacher", "Author", "Painter", "Doctor")
        };

        List<String> answersList = Arrays.asList(
                "Hello",
                "Thank you",

                "A fruit",
                "Library",

                "He goes to school.",
                "Run",

                "I went to the park.",
                "I will eat dinner.",

                "Although it was raining, we went for a walk.",
                "The cake was eaten by John.",

                "Good luck",
                "It's raining very heavily",

                "Joyful",
                "Bright",
                "Author"
        );

        questions = questionsList.toArray(new String[0]);
        options = new String[optionsList.length][];
        for (int i = 0; i < optionsList.length; i++) {
            options[i] = optionsList[i].toArray(new String[0]);
        }
        answers = answersList.toArray(new String[0]);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < 0 || currentQuestionIndex >= questions.length) {
            return;
        }

        quizQuestionNumber.setText("Question " + (currentQuestionIndex + 1));
        quizQuestion.setText(questions[currentQuestionIndex]);

        RadioButton optionA = findViewById(R.id.optionA);
        RadioButton optionB = findViewById(R.id.optionB);
        RadioButton optionC = findViewById(R.id.optionC);
        RadioButton optionD = findViewById(R.id.optionD);

        List<String> shuffledOptions = new ArrayList<>(Arrays.asList(options[currentQuestionIndex]));
        Random random = new Random();
        for (int i = shuffledOptions.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            String temp = shuffledOptions.get(index);
            shuffledOptions.set(index, shuffledOptions.get(i));
            shuffledOptions.set(i, temp);
        }

        optionA.setText(shuffledOptions.get(0));
        optionB.setText(shuffledOptions.get(1));
        optionC.setText(shuffledOptions.get(2));
        optionD.setText(shuffledOptions.get(3));

        quizResult.setText("");
    }

    private void checkAnswer() {
        int selectedId = quizOptionsGroup.getCheckedRadioButtonId();
        RadioButton selectedOption = findViewById(selectedId);

        if (selectedOption == null) {
            quizResult.setText("Please select an option.");
            return;
        }

        String selectedAnswer = selectedOption.getText().toString();
        if (selectedAnswer.equals(answers[currentQuestionIndex])) {
            quizResult.setText("Correct! Well done.");
            correctAnswersCount++; // Increment correct answers count
        } else {
            quizResult.setText("Incorrect. The correct answer is " + answers[currentQuestionIndex] + ".");
        }
    }

    private void goToNextQuestion() {
        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            loadQuestion();
        }
    }

    private void goToPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            loadQuestion();
        }
    }

    private void saveQuizData() {
        SharedPreferences sharedPreferences = getSharedPreferences("LanguageLearningPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int quizzesTaken = sharedPreferences.getInt("quizzesTaken", 0);
        int previousCorrectAnswers = sharedPreferences.getInt("correctAnswers", 0);

        editor.putInt("quizzesTaken", quizzesTaken + 1);
        editor.putInt("correctAnswers", previousCorrectAnswers + correctAnswersCount);
        editor.apply();
    }
}
