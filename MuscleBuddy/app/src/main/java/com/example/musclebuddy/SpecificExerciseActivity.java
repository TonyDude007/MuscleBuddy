package com.example.musclebuddy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musclebuddy.entity.Exercise;
import com.example.musclebuddy.entity.Weight;
import com.example.musclebuddy.manager.ExerciseManager;
import com.example.musclebuddy.manager.WeightManager;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class SpecificExerciseActivity extends AppCompatActivity {

    Context context;
    TextView nameExercise;
    ImageView imageExercise;
    TextView descriptionExercise;
    Button btnBack;
    LinearLayout listOfWeights;
    EditText inputWeight;
    EditText inputNbReps;
    LocalDate currentDate;
    Button addSet;
    WeightManager weightManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_exercise);

        context = this;
        weightManager = new WeightManager();

        Intent lastPageIntent = getIntent();
        int exerciseID = lastPageIntent.getIntExtra("exerciseId", -1);
        int userID = lastPageIntent.getIntExtra("userId", -1);

        ExerciseManager exerciseManager = new ExerciseManager();
        Exercise currentExercise = exerciseManager.getById(context, exerciseID);

        nameExercise = findViewById(R.id.nameExercise);
        nameExercise.setText(currentExercise.getName());

        imageExercise = findViewById(R.id.imageExercise);
        try {
            InputStream inputStream = getAssets().open("exercise/" + currentExercise.getImgName());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageExercise.setImageDrawable(drawable);
        } catch (IOException e) {
            try {
                InputStream inputStreamBackUp = getAssets().open("exercise/noPictureWasFound.jpg");
                Drawable drawable = Drawable.createFromStream(inputStreamBackUp, null);
                imageExercise.setImageDrawable(drawable);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

        descriptionExercise = findViewById(R.id.descriptionExercise);
        descriptionExercise.setText(currentExercise.getDescription());

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener((view) -> {
            Intent intent = new Intent(context, ExerciseActivity.class);
            intent.putExtra("exerciseId", exerciseID);
            intent.putExtra("userId", userID);
            startActivity(intent);
            finish();
        });

        inputWeight = findViewById(R.id.inputWeight);
        inputNbReps = findViewById(R.id.inputNbReps);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }

        ArrayList<Weight> weights = weightManager.getByExerciseAndUserId(context,exerciseID,userID);
        listOfWeights = findViewById(R.id.listOfWeights);

        for (int i = 0; i < weights.size(); i++) {

            LinearLayout newLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.weight_rep_date_layout, null);
            TextView newWeight = newLayout.findViewById(R.id.weight);
            TextView newRep = newLayout.findViewById(R.id.rep);
            TextView newDate = newLayout.findViewById(R.id.date);

            newWeight.setText(String.valueOf(weights.get(i).getLastWeight()));
            newRep.setText(String.valueOf(weights.get(i).getNbReps()));
            newDate.setText(String.valueOf(weights.get(i).getDate()));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // width
                    LinearLayout.LayoutParams.WRAP_CONTENT  // height
            );
            layoutParams.setMargins(0, 10, 0, 10);
            newLayout.setLayoutParams(layoutParams);

            listOfWeights.addView(newLayout);
        }

        addSet = findViewById(R.id.addSet);
        addSet.setOnClickListener((view) -> {
            try {
                Weight weight = new Weight();
                weight.setExeciseId(Integer.valueOf(String.valueOf(currentExercise.getId())));
                weight.setLastWeight(Integer.valueOf(String.valueOf(inputWeight.getText())));
                weight.setNbReps(Integer.valueOf(String.valueOf(inputNbReps.getText())));
                weight.setDate(String.valueOf(currentDate));
                weight.setUserId(userID);
                weightManager.add(context, weight);

                listOfWeights = findViewById(R.id.listOfWeights);
                listOfWeights.removeAllViews();

                Intent intent = new Intent(context, SpecificExerciseActivity.class);
                intent.putExtra("exerciseId", exerciseID);
                intent.putExtra("userId", userID);
                startActivity(intent);
                finish();

            } catch (Exception e) {
                Toast.makeText(context, "Enter Values Before Submit", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
