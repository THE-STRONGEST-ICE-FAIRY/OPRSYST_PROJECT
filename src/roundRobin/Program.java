package roundRobin;

import java.util.Scanner;

public class Program implements Cloneable{
    private String name;
    private int timeIn;
    private int duration;

    Program(String name, int timeIn, int duration) {
        this.name = name;
        this.timeIn = timeIn;
        this.duration = duration;
    }

    Program(Scanner sc) {
        edit(sc);
    }

    public void edit(Scanner sc) {
        System.out.print("Program Name: ");
        name = sc.nextLine();
        System.out.print("Program Time In: ");
        timeIn = Integer.parseInt(sc.nextLine());
        System.out.print(".Program Duration: ");
        duration = Integer.parseInt(sc.nextLine());
    }

    public String getName(int limit) {
        if (name.length() > limit) return name.substring(0, Math.min(limit - 3, name.length())) + "...";
        else return name + " ".repeat(limit - name.length());
    }

    public String getName() {
        return name;
    }

    public String getTimeIn(int limit) {
        return Integer.toString(timeIn).substring(0, Math.min(limit, Integer.toString(timeIn).length())) + " ".repeat(14 - Integer.toString(timeIn).length());
    }

    public String getDuration(int limit) {
        return Integer.toString(duration).substring(0, Math.min(limit, Integer.toString(duration).length())) + " ".repeat(14 - Integer.toString(duration).length());
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTimeIn() {
        return timeIn;
    }

    @Override
    public Program clone() {
        try {
            return (Program) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
