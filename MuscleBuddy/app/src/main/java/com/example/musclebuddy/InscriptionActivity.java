package com.example.musclebuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musclebuddy.R;
import com.example.musclebuddy.entity.User;
import com.example.musclebuddy.manager.UserManager;

public class InscriptionActivity extends AppCompatActivity {

    Button buttonHome;
    EditText inputName;
    EditText inputPassword;
    EditText inputCurrentWeight;
    EditText inputGoalWeight;
    EditText inputAge;
    Button createUser;
    String selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_activity);

        buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener((view) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        inputName = findViewById(R.id.inputName);
        inputPassword = findViewById(R.id.inputPassword);
        inputCurrentWeight = findViewById(R.id.inputCurrentWeight);
        inputGoalWeight = findViewById(R.id.inputGoalWeight);
        inputAge = findViewById(R.id.inputAge);

        String[] goals = {"Gain Weight","Lose Weight","Gain Muscle","lose Muscle","Maintain Muscle"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a Goal");

        builder.setItems(goals, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                selectedOption = goals[which];
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        createUser = findViewById(R.id.createUser);
        createUser.setOnClickListener((view) -> {

            try {
                User newUser = new User();
                newUser.setId(0);
                newUser.setUserName("");
                newUser.setName(String.valueOf(inputName.getText()));
                newUser.setPassword(String.valueOf(inputPassword.getText()));
                newUser.setCurrentWeight(Float.parseFloat(String.valueOf(inputCurrentWeight.getText())));
                newUser.setGoalWeight(Float.parseFloat(String.valueOf(inputGoalWeight.getText())));
                newUser.setGoal(selectedOption);
                newUser.setAge(Integer.parseInt(String.valueOf(inputAge.getText())));
                newUser.setEmail("");

                UserManager userManager = new UserManager();
                userManager.AddUser(this, newUser);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Try Again With Valid Values", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
