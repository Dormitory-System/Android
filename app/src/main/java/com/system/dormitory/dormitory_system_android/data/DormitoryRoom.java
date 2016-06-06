package com.system.dormitory.dormitory_system_android.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by secret on 6/3/16.
 */
public class DormitoryRoom implements Serializable {
    private int room;
    private int count;
    private ArrayList<String> student;

    public DormitoryRoom() {
        this.student = new ArrayList<String>();
    }

    public DormitoryRoom(int room) {
        this();
        this.room = room;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<String> getStudent() {
        return student;
    }

    public boolean addStudent(String name) {
        if (student.size() < 4) {
            student.add(name);
            return true;
        }
        else
            return false;
    }
}