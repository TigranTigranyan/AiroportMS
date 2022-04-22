package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Trip {
    private int Trip_no;
    private int ID_Comp;
    private String Plane;
    private String Town_from;
    private String Town_to;
    private LocalDateTime Time_out;
    private LocalDateTime Time_in;

    public Trip(int trip_no, int ID_Comp, String plane, String town_from, String town_to, LocalDateTime time_out, LocalDateTime time_in) {
        Trip_no = trip_no;
        this.ID_Comp = ID_Comp;
        Plane = plane;
        Town_from = town_from;
        Town_to = town_to;
        Time_out = time_out;
        Time_in = time_in;
    }
    public Trip(){
    }

    public String getPlane() {
        return Plane;
    }

    public void setPlane(String plane) {
        Plane = plane;
    }

    public int getTrip_no() {
        return Trip_no;
    }

    public void setTrip_no(int trip_no) {
        Trip_no = trip_no;
    }

    public int getID_Comp() {
        return ID_Comp;
    }

    public void setID_Comp(int ID_Comp) {
        this.ID_Comp = ID_Comp;
    }

    public String getTown_from() {
        return Town_from;
    }

    public void setTown_from(String town_from) {
        Town_from = town_from;
    }

    public String getTown_to() {
        return Town_to;
    }

    public void setTown_to(String town_to) {
        Town_to = town_to;
    }

    public LocalDate getTime_out() {
        return LocalDate.from(Time_out);
    }

    public void setTime_out(LocalDateTime time_out) {
        Time_out = time_out;
    }

    public LocalDate getTime_in() {
        return LocalDate.from(Time_in);
    }

    public void setTime_in(LocalDateTime time_in) {
        Time_in = time_in;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trip{");
        sb.append("Trip_no=").append(Trip_no);
        sb.append(", ID_Comp=").append(ID_Comp);
        sb.append(", Plane='").append(Plane).append('\'');
        sb.append(", Town_from='").append(Town_from).append('\'');
        sb.append(", Town_to='").append(Town_to).append('\'');
        sb.append(", Time_out=").append(Time_out);
        sb.append(", Time_in=").append(Time_in);
        sb.append('}');
        sb.append("\n");
        return sb.toString();
    }
}
