package com.lucasdachman.mission;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;


public class MissionStore implements ValueEventListener {
    final String TAG = "MissionStore";

    // static members
    private static MissionStore instance;

    // instance members
    private ArrayList<Mission> missions = new ArrayList<Mission>();
    private MissionDataChangeListener missionDataChangeListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference missionsListRef = database.getReference("missions");

    private MissionStore() {
        Log.i(TAG, "New MissionStore created");
        missionsListRef.orderByChild("order").addValueEventListener(this);
    }

    public void addMission(Mission mission) {
        Log.i(TAG, "Adding new mission: " + mission.getName());

        // find db position where to put task
        DatabaseReference newRef = missionsListRef.push();

        // put the mission at the beginning by setting the order
        Orderable.setOrder(mission, getMissions());

        // set the key to the reference to this mission in the db
        mission.setKey(newRef.getKey());

        // set the value in the db
        newRef.setValue(mission);
    }

    public ArrayList<Mission> getMissions() {
        return missions;
    }

    public Mission getMissionAt(int index) {
        return missions.get(index);
    }

    public void addTask(Mission mission, Task task) {
        Log.i(TAG, "Adding new task: " + task.getName());

        // find db position where to put task
        String missionKey = mission.getKey();
        DatabaseReference missionRef = missionsListRef.child(missionKey);
        DatabaseReference newRef = missionRef.child("tasks").push();

        // put the task at the beginning by setting the order
        Orderable.setOrder(task, mission.getTasksAsList());

        // set the key to the reference to this task in the db
        task.setKey(newRef.getKey());

        // set the value in the db
        newRef.setValue(task);
    }

    public void deleteTask(Mission mission, Task task) {
        Log.i(TAG, "Deleting Task: " + task.getName());
        String missionKey = mission.getKey();
        String taskKey = task.getKey();
        missionsListRef.child(missionKey).child("tasks").child(taskKey).removeValue();
    }

    public void updateTask(Mission mission, Task task) {
        Log.i(TAG, "Updating Task: " + task.getName());
        String missionKey = mission.getKey();
        String taskKey = task.getKey();
        missionsListRef.child(missionKey).child("tasks").child(taskKey).setValue(task);
    }

    public void setMissionDataChangeListener(MissionDataChangeListener missionDataChangeListener) {
        this.missionDataChangeListener = missionDataChangeListener;
    }

    // invoke interface method onChange
    private void onDataChanged() {
        if (missionDataChangeListener != null) {
            missionDataChangeListener.onDataChange();
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.i(TAG, "onDataChange Firebase");
        missions.clear();
        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
            missions.add(childSnapshot.getValue(Mission.class));
        }
        onDataChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.e(TAG, "Firebase connection cancelled: " + databaseError);
    }

    /*** Firebase functions ***/


    // Static Functions
    public static MissionStore getInstance() {
        if (instance == null) {
            instance = new MissionStore();
        }
        return instance;
    }
}
