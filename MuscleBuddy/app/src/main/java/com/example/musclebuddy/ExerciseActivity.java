package com.example.musclebuddy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musclebuddy.entity.Exercise;
import com.example.musclebuddy.entity.User;
import com.example.musclebuddy.manager.ExerciseManager;
import com.example.musclebuddy.manager.UserManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    Context context;
    Spinner filtreMuscle;
    EditText searchFiltre;
    LinearLayout exerciseLinearLayout;
    Button btnProfile;
    Button btnHome;
    ArrayList<Exercise> listDesExecises = new ArrayList<>();
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent lastPageIntent = getIntent();
        userId = lastPageIntent.getIntExtra("userId", -1);

        context = this;

        UserManager userManager = new UserManager();
        User user = userManager.getById(context,userId);
        TextView title = findViewById(R.id.title);
        title.setText(" Account : " + user.getName());

        filtreMuscle = findViewById(R.id.filtreMuscle);
        searchFiltre = findViewById(R.id.searchFiltre);
        exerciseLinearLayout = findViewById(R.id.exerciseLinearLayout);
        btnProfile = findViewById(R.id.btnProfile);
        btnHome = findViewById(R.id.btnHome);

        btnProfile.setOnClickListener((view) -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
            finish();
        });

        btnHome.setOnClickListener((view) -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();
        });

        ExerciseManager exerciseManager = new ExerciseManager();
        listDesExecises = exerciseManager.getAll(context);

        setList(exerciseLinearLayout, listDesExecises);

        filtreMuscle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMuscle = parent.getItemAtPosition(position).toString();


                if (selectedMuscle.equals("All")) {
                    ArrayList<Exercise> listDesFav = exerciseManager.getAll(context);
                    setList(exerciseLinearLayout,listDesFav);

                } else if (selectedMuscle.equals("Favorite")) {
                    ArrayList<Exercise> listDesFav = exerciseManager.favorite(context);
                    setList(exerciseLinearLayout,listDesFav);
                } else {
                    ArrayList<Exercise> listDesExecisesByMuscle = exerciseManager.getByMuscle(context, selectedMuscle);
                    setList(exerciseLinearLayout, listDesExecisesByMuscle);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchFiltre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               String search = searchFiltre.getText().toString();
                ArrayList<Exercise> searchResults = new ArrayList<>();
                ExerciseManager exerciseManager = new ExerciseManager();

                if (!search.isEmpty()) {
                        String currentSearchText = search;
                        ArrayList<Exercise> searchResult = exerciseManager.searchByKeyword(context, currentSearchText);
                        searchResults.addAll(searchResult);
                        setList(exerciseLinearLayout, searchResult);
                }
            }
        });
    }

    public void setList(LinearLayout destination, ArrayList<Exercise> listExecises) {

        destination.removeAllViews();

        for (int i = 0; i < listExecises.size(); i++) {

            LinearLayout exerciseList = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.exercise_layout, null);
            ImageView imgExercise = exerciseList.findViewById(R.id.imgExercise);
            TextView textExercise = exerciseList.findViewById(R.id.textExercise);
            ImageView fav = exerciseList.findViewById(R.id.fav);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // width
                    LinearLayout.LayoutParams.WRAP_CONTENT  // height
            );
            layoutParams.setMargins(60, 10, 60, 10);

            exerciseList.setPadding(25, 50, 25, 50);
            exerciseList.setLayoutParams(layoutParams);

            int finalI = i;
            exerciseList.setOnClickListener((view) -> {
                Intent intent = new Intent(context, SpecificExerciseActivity.class);
                intent.putExtra("exerciseId", listExecises.get(finalI).getId());
                intent.putExtra("userId",userId);
                startActivity(intent);
                finish();
            });

            destination.addView(exerciseList);

            try {
                InputStream inputStream = getAssets().open("exercise/" + listExecises.get(i).getImgName());
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                imgExercise.setImageDrawable(drawable);
            } catch (IOException e) {
                try {
                    InputStream inputStreamBackUp = getAssets().open("exercise/noPic.jpg");
                    Drawable drawable = Drawable.createFromStream(inputStreamBackUp, null);
                    imgExercise.setImageDrawable(drawable);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

            textExercise.setText(listExecises.get(i).getName());

            ArrayList<Exercise> finalListDesExecises = listExecises;
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fav.setSelected(!fav.isSelected());

                    ExerciseManager exerciseManager = new ExerciseManager();

                    if (fav.isSelected()) {
                        if (finalListDesExecises.get(finalI).getFav().equals("true")) {
                            fav.setBackgroundResource(R.drawable.baseline_favorite_border_24);
                            finalListDesExecises.get(finalI).setFav("false");
                            exerciseManager.update(context, finalListDesExecises.get(finalI));
                        } else {
                            fav.setBackgroundResource(R.drawable.baseline_favorite_24);
                            finalListDesExecises.get(finalI).setFav("true");
                            exerciseManager.update(context, finalListDesExecises.get(finalI));
                        }
                    }
                }
            });

            if (listExecises.get(i).getFav().equals("true")) {
                fav.setBackgroundResource(R.drawable.baseline_favorite_24);
            } else {
                fav.setBackgroundResource(R.drawable.baseline_favorite_border_24);
            }

        }

    }

}
