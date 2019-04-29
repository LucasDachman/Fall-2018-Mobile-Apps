package com.lucasdachman.mission;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MissionStore implements ChildEventListener {
    final String TAG = "MissionStore";

    // static members
    private static MissionStore instance;

    // instance members
    private HashMap<String, Mission> missions;
    private MissionDataChangeListener missionDataChangeListener;

    private MissionStore() {
        Log.i(TAG, "New MissionStore created");
        missions = new HashMap<String, Mission>();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("missions");
        myRef.orderByChild("name").addChildEventListener(this);
    }


    public Mission[] getMissions() {
        return missions.values().toArray(new Mission[missions.size()]);
    }

    public Mission getMissionAt(int index) {
        return (Mission) missions.values().toArray()[index];
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

    /*** Firebase functions ***/

    /*** Child Event Listener ***/

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.i(TAG, "Child Added: " + dataSnapshot.getKey());
        Mission mission = dataSnapshot.getValue(Mission.class);
        missions.put(dataSnapshot.getKey(), mission);
        onDataChanged();
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.i(TAG, "Child Changed: " + dataSnapshot.getKey());
        Mission mission = dataSnapshot.getValue(Mission.class);
        missions.put(dataSnapshot.getKey(), mission);
        onDataChanged();
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        Log.i(TAG, "Child Removed: " + dataSnapshot.getKey());
        missions.remove(dataSnapshot.getKey());
        onDataChanged();
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.i(TAG, "Child Moved: " + dataSnapshot.getKey());
        onDataChanged();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.e(TAG, "Event Listener for Missions failed: " + databaseError.toString());
    }

    // Static Functions

    public static MissionStore getInstance() {
        if (instance == null) {
            instance = new MissionStore();
        }
        return instance;
    }
}
