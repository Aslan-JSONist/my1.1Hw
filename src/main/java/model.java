package model;

public class Human {
    private String name;
    private String surname;
    private int age;
    private boolean working;

    public String getName() {
        return this.name;
    }
    public String getSurname() {
        return this.surname;
    }
    public int getAge() {
        return this.age;
    }
    public boolean getWorking() {
        return this.working;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setWorks(boolean working) {
        this.working = working;
    }

}