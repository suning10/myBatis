package org.LearnMyBatis.pojo;

public class User {

    int id;

    String name;
    int age;

    String curPosition;

    public User(int id, String name, int age, String curPosition) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.curPosition = curPosition;
    }

    public User( String name, int age, String curPosition) {
        this.name = name;
        this.age = age;
        this.curPosition = curPosition;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", curPosition='" + curPosition + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCurPosition(String curPosition) {
        this.curPosition = curPosition;
    }
}
