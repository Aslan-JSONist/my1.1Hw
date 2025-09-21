package model;

public class Human {
    private String name;
    private String surname;
    private int age;
    private boolean works;

    public String getName() {
        return this.name;
    }
    public String getSurname() {
        return this.surname;
    }
    public int getAge() {
        return this.age;
    }
    public boolean getWorks() {
        return this.works;
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
    public void setWorks(boolean works) {
        this.works = works;
    }

}