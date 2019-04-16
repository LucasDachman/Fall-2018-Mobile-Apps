package com.lucasdachman.mission;

import java.util.ArrayList;

public class MissionStore {

    // static members
    private static MissionStore instance;

    // instance members
    private ArrayList<Mission> missions;


    private  MissionStore() {
        missions = new ArrayList<Mission>();
        setUpDummyData();
    }

    public ArrayList<Mission> getMissions() {
        return missions;
    }

    public void setUpDummyData() {
        Mission m1 = new Mission("Mission 1");
        m1.addTask("Task 1, Mission 1");
        m1.addTask("Task 2, Mission 1");

        Mission m2 = new Mission("Mission 2");
        m2.addTask("Task 1, Mission 2");
        m2.addTask("Task 2, Mission 2");
        m2.addTask("Task 3, Mission 2");

        missions.add(m1);
        missions.add(m2);
        missions.add(new Mission("Mission Three"));
    }

    // Static Functions

    public static MissionStore getInstance() {
        if (instance == null) {
            instance = new MissionStore();
        }
        return  instance;
    }
}
