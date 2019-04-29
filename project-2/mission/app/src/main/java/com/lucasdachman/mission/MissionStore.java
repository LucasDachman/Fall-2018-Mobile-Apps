package com.lucasdachman.mission;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MissionStore implements ValueEventListener {
    final String TAG = "MissionStore";

    // static members
    private static MissionStore instance;

    // instance members
    private ArrayList<Mission> missions = new ArrayList<Mission>();
    private MissionDataChangeListener missionDataChangeListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("missions");

    private MissionStore() {
        Log.i(TAG, "New MissionStore created");
        myRef.orderByChild("order").addValueEventListener(this);
    }

    public void addDummy() {
        this.addMission(new Mission("my mission " + new Date().toString()));
    }

    public void addMission(Mission mission) {
        DatabaseReference newRef = myRef.push();
        mission.setKey(newRef.getKey());
        // order at beginning of list
        if (missions.size() > 0) {
            mission.setOrder(getMissionAt(0).getOrder() - 1);
        } else {
            mission.setOrder(0);
        }
        newRef.setValue(mission);
    }

    public ArrayList<Mission> getMissions() {
        return missions;
    }

    public Mission getMissionAt(int index) {
        return missions.get(index);
    }

    public void addTask(Mission mission, Task task) {
        String missionKey = mission.getKey();
        if (mission.getTasksAsList().size() > 0) {
            task.setOrder(mission.getTasksAsList().get(0).getOrder() - 1);
        } else {
            task.setOrder(0);
        }
        DatabaseReference taskListRef = database.getReferenceFromUrl(missionKey).child("tasks");
        DatabaseReference newRef = taskListRef.push();
        task.setKey(newRef.getKey());
        newRef.setValue(task);
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
        missions.clear();
        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
            missions.add(childSnapshot.getValue(Mission.class));
        }
        missionDataChangeListener.onDataChange();
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
