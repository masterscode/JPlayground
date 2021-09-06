package com.play.jSE;

public class User  implements Comparable<User>{
    private int points;

    public User(int points){
        this.points = points;
    }
    @Override
    public int compareTo(User otherObject) {
        return Integer.compare(this.points, otherObject.points);
    }

    @Override
    public String toString() {
        return "User{" +
                "points=" + points +
                '}';
    }
}
