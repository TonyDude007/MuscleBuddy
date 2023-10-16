package com.example.musclebuddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musclebuddy.entity.User;
import com.example.musclebuddy.manager.UserManager;
import com.example.musclebuddy.service.ConnexionDb;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    UserManager userManager = new UserManager();
    LinearLayout scrollViewList;
    Button inscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollViewList = findViewById(R.id.scrollViewList);
        inscription = findViewById(R.id.inscription);

        inscription.setOnClickListener((view) -> {
            Intent intent = new Intent(this,InscriptionActivity.class);
            startActivity(intent);
            finish();
        });

        ArrayList<User> userArrayList = userManager.getAll(this);

        for (int i = 0; i < userArrayList.size(); i++) {
            LinearLayout userLinearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.user_layout,null);

            TextView name = userLinearLayout.findViewById(R.id.name);
            TextView currentWeight = userLinearLayout.findViewById(R.id.currentWeight);
            TextView goalWeight = userLinearLayout.findViewById(R.id.goalWeight);
            TextView age = userLinearLayout.findViewById(R.id.age);

            name.setText(userArrayList.get(i).getName());
            currentWeight.setText(Float.toString(userArrayList.get(i).getCurrentWeight()));
            goalWeight.setText(Float.toString(userArrayList.get(i).getGoalWeight()));
            age.setText(Integer.toString(userArrayList.get(i).getAge()));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    150
            );

            layoutParams.setMargins(0,10,0,10);
            userLinearLayout.setLayoutParams(layoutParams);

            scrollViewList.addView(userLinearLayout);

            int finalI = i;
            userLinearLayout.setOnClickListener((view) -> {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Loading");
                builder.setMessage("Please wait...");
                AlertDialog dialog = builder.create();
                dialog.show();

                Intent intent = new Intent(this, ExerciseActivity.class);
                intent.putExtra("userId",userArrayList.get(finalI).getId());
                startActivity(intent);
                finish();
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ConnexionDb.close();
    }
}