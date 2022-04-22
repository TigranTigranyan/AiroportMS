package Dao;

import model.Pass_in_Trip;
import model.Passanger;
import model.Trip;

import java.util.List;
import java.util.Set;

public interface PassengerDao {
    Passanger getByID(long id);
    Set<Passanger> getAll();
    Set<Passanger> get(int offset, int perPage,String sort);
    Passanger savePassenger(Passanger passanger);
    Passanger update(int id, Passanger passanger);
    void  delite(long passangerId);
    void getPassangersOfTrip(long tripNumber);
    void registerTrip(Pass_in_Trip pit);
    void cancelTrip(long passangerId, long tripNumber);

}
