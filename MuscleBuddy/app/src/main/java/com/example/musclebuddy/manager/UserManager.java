package com.example.musclebuddy.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.musclebuddy.entity.User;
import com.example.musclebuddy.service.ConnexionDb;

import java.util.ArrayList;

public class UserManager {

    public ArrayList<User> getAll(Context context) {
        SQLiteDatabase db = ConnexionDb.getDb(context);
        String request = "SELECT * FROM User";
        ArrayList<User> users = null;
        Cursor cursor = db.rawQuery(request, null);
        if (cursor.isBeforeFirst()) {
            users = new ArrayList<>();
            while (cursor.moveToNext()) {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("ID")));
                user.setUserName(cursor.getString(cursor.getColumnIndexOrThrow("UserName")));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("Password")));
                user.setCurrentWeight(cursor.getFloat(cursor.getColumnIndexOrThrow("CurrentWeight")));
                user.setGoalWeight(cursor.getFloat(cursor.getColumnIndexOrThrow("GoalWeight")));
                user.setName(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
                user.setAge(cursor.getInt(cursor.getColumnIndexOrThrow("Age")));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("Email")));
                user.setGoal(cursor.getString(cursor.getColumnIndexOrThrow("Goal")));
                users.add(user);
            }
        }
        return users;
    }

    public void AddUser(Context context, User newUser) {
        ArrayList<User> users = getAll(context);

        SQLiteDatabase db = ConnexionDb.getDb(context);
        int id = users.size() + 1;
        String userName = newUser.getUserName();
        String password = newUser.getPassword();
        float currentWeight = newUser.getCurrentWeight();
        float goalWeight = newUser.getGoalWeight();
        String name = newUser.getName();
        int age = newUser.getAge();
        String email = newUser.getEmail();
        String goal = newUser.getGoal();

        String query = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, id);
        statement.bindString(2, userName);
        statement.bindString(3, password);
        statement.bindDouble(4, currentWeight);
        statement.bindDouble(5, goalWeight);
        statement.bindString(6, name);
        statement.bindLong(7, age);
        statement.bindString(8, email);
        statement.bindString(9, goal);

        statement.executeInsert();
    }

    public User getById(Context context, int id) {

        SQLiteDatabase db = ConnexionDb.getDb(context);
        String sql = "SELECT * FROM User WHERE ID = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {

            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("ID")));
            user.setUserName(cursor.getString(cursor.getColumnIndexOrThrow("UserName")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("Password")));
            user.setCurrentWeight(cursor.getFloat(cursor.getColumnIndexOrThrow("CurrentWeight")));
            user.setGoalWeight(cursor.getFloat(cursor.getColumnIndexOrThrow("GoalWeight")));
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
            user.setAge(cursor.getInt(cursor.getColumnIndexOrThrow("Age")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("Email")));
            user.setGoal(cursor.getString(cursor.getColumnIndexOrThrow("Goal")));

            cursor.close();
            return user;
        } else {
            cursor.close();
            return null;
        }
    }

    public static void updateUser(Context context, User userToUpdate) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", userToUpdate.getId());
        contentValues.put("UserName", userToUpdate.getUserName());
        contentValues.put("Password", userToUpdate.getPassword());
        contentValues.put("CurrentWeight", userToUpdate.getCurrentWeight());
        contentValues.put("GoalWeight", userToUpdate.getGoalWeight());
        contentValues.put("Name", userToUpdate.getName());
        contentValues.put("Age", userToUpdate.getAge());
        contentValues.put("Email", userToUpdate.getEmail());
        contentValues.put("Goal", userToUpdate.getGoal());

        SQLiteDatabase db = ConnexionDb.getDb(context);
        db.update("User", contentValues, "id = ?", new String[]{String.valueOf(userToUpdate.getId())});
    }

    public static void delete(Context context, int idUser){
        SQLiteDatabase db = ConnexionDb.getDb(context);
        db.delete("User","id = ?" , new String[]{String.valueOf(idUser)});
    }
}
