package uk.ac.mmu.webmd.bottomup_version_10;

import java.sql.Time;

/**
 * Created by Tom on 07/06/2016.
 */
public class Person {

    private int id;
    private String password;
    private String name;
    private String gender;
    private int age;
    private double weight, height;
    private double dailyProgress;
    private double weeklyProgress;
    private Time walkingTime;


    public Person(String name, int age, String gender, String password) {
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDailyProgress() {
        return dailyProgress;
    }

    public void setDailyProgress(double dailyProgress) {
        this.dailyProgress = dailyProgress;
    }

    public double getWeeklyProgress() {
        return weeklyProgress;
    }

    public void setWeeklyProgress(double weeklyProgress) {
        this.weeklyProgress = weeklyProgress;
    }

    public Time getWalkingTime() {
        return walkingTime;
    }

    public void setWalkingTime(Time walkingTime) {
        this.walkingTime = walkingTime;

    }

}
