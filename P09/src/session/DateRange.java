package session;

import java.io.PrintStream;
import java.util.Scanner;
import people.Tutor;

public class DateRange {
    private String date;
    private String startTime;
    private String endTime;

    public DateRange(String date, String startTime, String endTime) {
        if(date == null || date.isEmpty() || startTime == null || endTime == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public DateRange(String date, String startTime, long duration) {
        if(date == null || date.isEmpty() || startTime == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.date = date;
        this.startTime = startTime;
        this.endTime = calcEndTime(startTime, duration);
    }
    
    public DateRange(Scanner in) {
        this.date = in.nextLine();
        this.startTime = in.nextLine();
        this.endTime = in.nextLine();
    }
    
    public void save(PrintStream out) {
        out.println(date);
        out.println(startTime);
        out.println(endTime);
    }

    private String calcEndTime(String startTime, long duration) {
        String[] parts = startTime.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        long totalTime = hours * 60 + minutes + duration;
        long endHours = totalTime / 60;
        long endMinutes = totalTime % 60;
        String endHour = Long.toString(endHours);
        String endMinute = Long.toString(endMinutes);
        return endHour + ":" + endMinute;
    }

    public long duration() {
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");
        int startMinutes = Integer.parseInt(startParts[0]) * 60 + Integer.parseInt(startParts[1]);
        int endMinutes = Integer.parseInt(endParts[0]) * 60 + Integer.parseInt(endParts[1]);
        return endMinutes - startMinutes;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(date + startTime + "-" + endTime + "(" + duration() + ")");
        return sb.toString();
    }
    
    public String getDate() {
        return date;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
}