package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pass_in_Trip {
    private int ID_pit;
    private int Trip_no;
    private int Psg_id;
    private LocalDateTime Trip_Date;
    private String Place;

    public Pass_in_Trip( int trip_no, int psg_id, LocalDateTime trip_Date, String place) {
        Trip_no = trip_no;
        Psg_id = psg_id;
        Trip_Date = trip_Date;
        Place = place;
    }

    public int getID_pit() {
        return ID_pit;
    }

    public void setID_pit(int ID_pit) {
        this.ID_pit = ID_pit;
    }

    public int getTrip_no() {
        return Trip_no;
    }

    public void setTrip_no(int trip_no) {
        Trip_no = trip_no;
    }

    public int getPsg_id() {
        return Psg_id;
    }

    public void setPsg_id(int psg_id) {
        Psg_id = psg_id;
    }

    public LocalDate getTrip_Date() {
        return LocalDate.from(Trip_Date);
    }

    public void setTrip_Date(LocalDateTime trip_Date) {
        Trip_Date = trip_Date;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    @Override
    public String toString() {
        return "Pass_in_Trip{" +
                "ID_pit=" + ID_pit +
                ", Trip_no=" + Trip_no +
                ", Psg_id=" + Psg_id +
                ", Trip_Date=" + Trip_Date +
                ", Place='" + Place + '\'' +
                '}';
    }
}
