package com.example.musclebuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musclebuddy.entity.User;
import com.example.musclebuddy.manager.UserManager;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    Context context;
    TextView title;
    EditText nameInput;
    EditText ageInput;
    EditText emailInput;
    EditText inputCurrentWeight;
    EditText inputGoalWeight;
    Spinner spinnerGoal;
    Button btnSave;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        context = this;

        Intent lastPageIntent = getIntent();
        int idUser = lastPageIntent.getIntExtra("userId",-1);

        UserManager userManager = new UserManager();
        User user = userManager.getById(context,idUser);

        title = findViewById(R.id.title);
        title.setText( "Welcome " + user.getName());

        nameInput = findViewById(R.id.nameInput);
        nameInput.setText(user.getName());

        ageInput = findViewById(R.id.ageInput);
        ageInput.setText(String.valueOf(user.getAge()));

        emailInput = findViewById(R.id.emailInput);
        emailInput.setText(user.getEmail());

        inputCurrentWeight = findViewById(R.id.inputCurrentWeight);
        inputCurrentWeight.setText(String.valueOf(user.getCurrentWeight()));

        inputGoalWeight = findViewById(R.id.inputGoalWeight);
        inputGoalWeight.setText(String.valueOf(user.getGoalWeight()));

        spinnerGoal = findViewById(R.id.spinnerGoal);
        String[] goals = getResources().getStringArray(R.array.goal_input);
        String searchQuery = user.getGoal();
        for (int i = 0; i < goals.length; i++) {
            if (goals[i].equalsIgnoreCase(searchQuery)) {
                spinnerGoal.setSelection(i);
            }
        }

        spinnerGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = (String) adapterView.getSelectedItem();

                user.setGoal(selectedItem);

                String[] goals = getResources().getStringArray(R.array.goal_input);
                String searchQuery = user.getGoal();
                for (int i = 0; i < goals.length; i++) {
                    if (goals[i].equalsIgnoreCase(searchQuery)) {
                        spinnerGoal.setSelection(i);
                    }
                }
                //Toast.makeText(context, "New Goal : " + selectedItem , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener((view) -> {
            user.setName(String.valueOf(nameInput.getText()));
            user.setAge(Integer.valueOf(String.valueOf(ageInput.getText())));
            user.setEmail(String.valueOf(emailInput.getText()));
            user.setCurrentWeight(Float.valueOf(String.valueOf(inputCurrentWeight.getText())));
            user.setGoalWeight(Float.valueOf(String.valueOf(inputGoalWeight.getText())));
            user.setGoal(String.valueOf(spinnerGoal.getSelectedItem()));
            userManager.updateUser(context,user);
            Intent intent = new Intent(this,MainActivity.class);
            Toast.makeText(context, "Save Successful", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener((view) -> {
            Intent intent = new Intent(this,ExerciseActivity.class);
            intent.putExtra("userId",idUser);
            startActivity(intent);
            finish();
        });
    }
}
