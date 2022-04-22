package Dao;

import model.Trip;

import java.util.Set;

public interface TripDao {
    Trip getByID(long id);
    Set<Trip> getAll();
    Set<Trip> get(int offset, int perPage,String sort);
    Trip save(Trip trip);
    Trip update(int Trip_no,Trip trip);
    void  delite(long tripId);
    Set<Trip> getTripsFrom(String city);
    Set<Trip> getTripsto(String city);

}
