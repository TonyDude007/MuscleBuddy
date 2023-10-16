package com.example.musclebuddy.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MuscleBuddyDbHelper extends SQLiteOpenHelper {
    public MuscleBuddyDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (\n" +
                "ID INTEGER NOT NULL DEFAULT 1 UNIQUE PRIMARY KEY AUTOINCREMENT," +
                "UserName TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "CurrentWeight REAL," +
                "GoalWeight REAL," +
                "Name TEXT," +
                "Age INTEGER," +
                "Email TEXT," +
                "Goal TEXT NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS Exercises (\n" +
                "ID INTEGER NOT NULL DEFAULT 1 UNIQUE PRIMARY KEY," +
                "Name TEXT NOT NULL," +
                "Description TEXT," +
                "Muscle TEXT," +
                "ImgName TEXT," +
                "Fav Text" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS weights (\n" +
                "ID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT," +
                "UserId INTEGER NOT NULL," +
                "ExerciseId INTEGER NOT NULL," +
                "LastWeight INTEGER NOT NULL," +
                "NbReps INTEGER NOT NULL," +
                "Date TEXT NOT NULL," +
                "FOREIGN KEY(UserId) REFERENCES User(ID)" +
                ");");

        db.execSQL("INSERT INTO User VALUES (1,'tony','1',140.0,160.0,'Antoine',22,'Tonydude007@gmail.com','GainWeight');");
        db.execSQL("INSERT INTO User VALUES (2,'bob','1',200.0,400.0,'Alfred',45,'','GainWeight');");
        db.execSQL("INSERT INTO User VALUES (3,'titi','1',0.0,12.0,'Alphonse',76,'','GainWeight');");
        db.execSQL("INSERT INTO User VALUES (4,'tata','1',75.0,85.0,'Aspen',21,'','GainWeight');");
        db.execSQL("INSERT INTO User VALUES (5,'toto','1',32.0,67.0,'Astrid',4,'','GainWeight');");

        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 1, 150, 10, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 2, 50, 20, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 3, 200, 8, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 4, 100, 12, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 5, 0, 15, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 1, 200, 8, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 2, 60, 15, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 3, 250, 6, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 4, 120, 10, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 5, 10, 12, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 1, 100, 12, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 2, 40, 25, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 3, 150, 10, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 4, 80, 15, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 5, 5, 20, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 1, 120, 10, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 2, 30, 30, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 3, 100, 12, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 4, 60, 20, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 5, 0, 25, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 1, 150, 10, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 2, 50, 20, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 3, 200, 8, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 4, 100, 12, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 5, 0, 15, '2023-06-01');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 1, 160, 10, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 2, 55, 20, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 3, 210, 8, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 4, 110, 12, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (1, 5, 0, 18, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 1, 210, 8, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 2, 65, 15, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 3, 260, 6, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 4, 130, 10, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (2, 5, 15, 13, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 1, 110, 12, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 2, 45, 25, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 3, 160, 10, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 4, 90, 15, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (3, 5, 5, 22, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 1, 130, 10, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 2, 35, 30, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 3, 160, 8, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 4, 70, 20, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (4, 5, 0, 28, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 1, 160, 12, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 2, 55, 22, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 3, 210, 10, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 4, 110, 14, '2023-06-05');");
        db.execSQL("INSERT INTO weights (UserId, ExerciseId, LastWeight, NbReps, Date) VALUES (5, 5, 0, 20, '2023-06-05');");

        db.execSQL("INSERT INTO Exercises VALUES (1,'Push-ups','A classic bodyweight exercise that targets the chest, triceps, and shoulders. Start in a high plank position and lower your body until your chest touches the floor, then push back up.','Chest','push-ups.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (2,'Sit-ups','An abdominal exercise that strengthens the core. Lie on your back with knees bent, hands behind your head, and lift your upper body towards your knees while engaging your abdominal muscles.','Abdominals','sit-ups.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (3,'Squats','A lower body exercise that targets the quadriceps, hamstrings, and glutes. Stand with feet shoulder-width apart, lower your hips back and down as if sitting in a chair, and return to a standing position.','Quadriceps','squats.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (4,'Lunges','A lower body exercise that targets the quadriceps, hamstrings, and glutes. Take a big step forward with one leg, lower your body until both knees are bent at a 90-degree angle, and then return to the starting position. Repeat with the other leg.','Quadriceps','lunges.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (5,'Burpees','A full-body exercise that combines a squat, plank, and jump. Start in a standing position, squat down, kick your legs back to a high plank, perform a push-up, jump your feet back in, and jump up explosively.','Full Body','burpees.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (6,'Plank','A core-strengthening exercise that also engages the shoulders and glutes. Start in a push-up position and hold your body straight as a plank, with elbows directly beneath your shoulders and forearms on the ground.','Abdominals','plank.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (7,'Mountain climbers','A dynamic exercise that targets the core, shoulders, and legs. Begin in a high plank position, alternate bringing your knees in towards your chest, as if running in place, while keeping your upper body stable.','Abdominals','mountain-climbers.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (8,'Jumping jacks','A cardio exercise that engages the whole body. Start with feet together and arms at your sides, then jump while simultaneously spreading your legs and raising your arms overhead. Jump again to return to the starting position.','Full Body','jumping-jacks.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (9,'High knees','A cardiovascular exercise that targets the lower body and improves coordination. Stand tall, lift one knee towards your chest, quickly switch to the other knee, and continue alternating rapidly while maintaining an upright posture.','Legs','high-knees.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (10,'Bicycle crunches','An abdominal exercise that engages the rectus abdominis and obliques. Lie on your back, bring your knees towards your chest, and perform a cycling motion with your legs while reaching opposite elbows to opposite knees.','Abdominals','bicycle-crunches.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (11,'Russian twists','An exercise that targets the obliques and improves rotational strength. Sit on the floor, lean back slightly, lift your feet off the ground, and twist your torso from side to side while holding a weight or touching the ground with your hands.','Obliques','russian-twists.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (12,'Tricep dips','An upper body exercise that targets the triceps. Sit on the edge of a chair or bench with hands gripping the front edge, slide your butt off the seat, and lower your body by bending your elbows. Push back up to the starting position.','Triceps','tricep-dips.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (13,'Bicep curls','An exercise that targets the biceps. Stand with a dumbbell in each hand, palms facing forward, and curl the weights towards your shoulders by bending your elbows. Lower the weights back down with control.','Biceps','bicep-curls.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (14,'Shoulder press','An exercise that targets the shoulders. Stand with dumbbells at shoulder level, palms facing forward, and press the weights overhead until your arms are fully extended. Lower the weights back down to shoulder level.','Shoulders','shoulder-press.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (15,'Lateral raises','An exercise that targets the shoulders and improves shoulder width. Stand with dumbbells at your sides, palms facing inward, and raise the weights out to the sides until they are parallel to the ground. Lower the weights back down with control.','Shoulders','lateral-raises.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (16,'Deadlifts','A compound exercise that targets the lower back, glutes, hamstrings, and quadriceps. Stand with feet hip-width apart, bend at the hips to lower your torso while keeping your back straight, and then lift the barbell by extending your hips and knees.','Lower Back','deadlifts.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (17,'Bench press','A compound exercise that targets the chest, triceps, and shoulders. Lie on a flat bench, grip the barbell with hands slightly wider than shoulder-width apart, and lower it to your chest. Press the barbell back up until your arms are fully extended.','Chest','bench-press.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (18,'Squat jumps','A plyometric exercise that targets the quadriceps, hamstrings, and glutes. Start in a squat position, then explosively jump as high as you can. Land softly in a squat position and immediately jump again.','Quadriceps','squat-jumps.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (19,'Dumbbell lunges','A lower body exercise that targets the quadriceps, hamstrings, and glutes. Hold a dumbbell in each hand, step forward with one leg and lower your body until both knees are bent at a 90-degree angle. Push back up and repeat with the other leg.','Quadriceps','dumbbell-lunges.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (20,'Box jumps','A plyometric exercise that targets the lower body and improves explosive power. Stand facing a sturdy box or platform, jump onto the box using both feet, and then step or jump back down.','Legs','box-jumps.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (21,'Plank jacks','A dynamic exercise that targets the core, shoulders, and legs. Begin in a forearm plank position, then jump your feet out wide like a jumping jack, and jump them back together.','Abdominals','plank-jacks.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (22,'Mountain climbers with sliders','A dynamic exercise that targets the core, shoulders, and legs. Place sliders or towels under your feet in a high plank position, then alternate pulling your knees in towards your chest, as if running in place.','Abdominals','mountain-climbers-sliders.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (23,'Jump rope','A cardio exercise that engages the whole body. Hold the handles of a jump rope and jump over the rope as it passes under your feet, keeping a steady rhythm.','Full Body','jump-rope.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (24,'Jump squats','A plyometric exercise that targets the quadriceps, hamstrings, and glutes. Start in a squat position, then explosively jump as high as you can. Land softly in a squat position and immediately jump again.','Quadriceps','jump-squats.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (25,'Flutter kicks','An exercise that targets the abdominal muscles. Lie on your back with legs extended, lift your heels off the ground, and kick your legs up and down in a scissor-like motion.','Abdominals','flutter-kicks.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (26,'Russian twists with medicine ball','An exercise that targets the obliques and improves rotational strength. Sit on the floor with knees bent and feet lifted off the ground, hold a medicine ball or weight, and twist your torso from side to side, touching the ball to the ground on each side.','Obliques','russian-twists-medicine-ball.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (27,'Seated cable rows','An exercise that targets the back muscles. Sit at a cable row machine with your feet resting against the foot pads, grab the handle, and pull it towards your midsection while keeping your back straight. Return to the starting position.','Back','seated-cable-rows.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (28,'Lat pulldowns','An exercise that targets the latissimus dorsi muscles. Sit at a lat pulldown machine, grip the bar with hands wider than shoulder-width apart, and pull the bar down towards your chest while keeping your back straight. Return to the starting position.','Back','lat-pulldowns.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (29,'Dumbbell bench press','A compound exercise that targets the chest, triceps, and shoulders. Lie on a flat bench with a dumbbell in each hand, palms facing forward, and lower the dumbbells to your chest. Press the dumbbells back up until your arms are fully extended.','Chest','dumbbell-bench-press.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (30,'Tricep kickbacks','An exercise that targets the triceps. Stand with one foot in front of the other, hinge at the hips to lean forward, and extend the arm straight back, squeezing the tricep at the top of the movement. Repeat with the other arm.','Triceps','tricep-kickbacks.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (31,'Arnold press','An exercise that targets the shoulders. Sit on a bench or chair with dumbbells held at shoulder level, palms facing your body. Press the dumbbells overhead while rotating your palms to face forward. Lower the dumbbells back down to shoulder level and rotate your palms back to the starting position.','Shoulders','arnold-press.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (32,'Front raises','An exercise that targets the front of the shoulders. Stand with dumbbells in front of your thighs, palms facing your body, and raise the dumbbells in front of you until they are at shoulder level. Lower the dumbbells back down with control.','Shoulders','front-raises.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (33,'Romanian deadlifts','An exercise that targets the hamstrings and glutes. Stand with feet hip-width apart, holding a barbell or dumbbells in front of your thighs, hinge at the hips to lower the weights towards the floor while keeping your back straight, and then lift back up to the starting position.','Hamstrings','romanian-deadlifts.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (34,'Hamstring curls','An exercise that targets the hamstrings. Lie face down on a leg curl machine, hook your ankles under the padded lever, and curl your legs up towards your glutes. Slowly lower the weight back down.','Hamstrings','hamstring-curls.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (35,'Calf raises','An exercise that targets the calf muscles. Stand with feet hip-width apart, raise your heels off the ground as high as you can, and then lower them back down.','Calves','calf-raises.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (36,'Sit-ups','An exercise that targets the abdominal muscles. Lie on your back with knees bent, feet flat on the floor, and hands behind your head. Contract your abs to lift your upper body off the ground, then lower back down with control.','Abdominals','sit-ups.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (37,'Reverse crunches','An exercise that targets the lower abs. Lie on your back with legs extended, lift your legs off the ground, and then curl your knees towards your chest. Slowly lower your legs back down.','Abdominals','reverse-crunches.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (38,'Oblique crunches','An exercise that targets the obliques. Lie on your back with knees bent, feet flat on the floor, and hands behind your head. Lift your shoulder blades off the ground and twist your torso to one side, bringing your elbow towards the opposite knee. Repeat on the other side.','Obliques','oblique-crunches.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (39,'Incline bench press','A compound exercise that targets the upper chest, triceps, and shoulders. Lie on an incline bench, grip the barbell with hands slightly wider than shoulder-width apart, and lower it to your upper chest. Press the barbell back up until your arms are fully extended.','Chest','incline-bench-press.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (40,'Dips','An exercise that targets the chest, triceps, and shoulders. Hold onto parallel bars, with your arms straight and feet off the ground, lower your body until your elbows are at a 90-degree angle, then push back up to the starting position.','Chest','dips.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (41,'Medicine ball slams','A dynamic exercise that targets the whole body. Stand with feet shoulder-width apart, hold a medicine ball overhead, and forcefully slam it into the ground. Catch the ball on the rebound and repeat.','Full Body','medicine-ball-slams.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (42,'Battle ropes','A cardio and strength exercise that targets the upper body and core. Hold onto the ends of a thick rope and alternate raising and lowering each arm rapidly, creating waves in the rope.','Upper Body','battle-ropes.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (43,'Burpees','A full-body exercise that combines a squat, plank, and jump. Start in a standing position, squat down, place your hands on the floor, jump your feet back into a plank position, jump your feet back to your hands, and jump up explosively.','Full Body','burpees.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (44,'Jumping lunges','A plyometric exercise that targets the legs and glutes. Start in a lunge position, then jump up and switch legs in mid-air, landing in a lunge with the opposite leg forward. Continue alternating.','Legs','jumping-lunges.jpg','false');");
        db.execSQL("INSERT INTO Exercises VALUES (45,'Bicycle crunches','An exercise that targets the abdominal muscles. Lie on your back with knees bent, hands behind your head, and bring your left elbow to your right knee while straightening your left leg. Alternate sides in a pedaling motion.','Abdominals','bicycle-crunches.jpg','false');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
