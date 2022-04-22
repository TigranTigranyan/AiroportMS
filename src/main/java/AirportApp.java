import Dao.CompanyDao;
import Dao.ImplDao.CompanyDaoImpl;
import Dao.ImplDao.PassengerDaoImpl;
import Dao.ImplDao.TripDaoImpl;
import com.mysql.cj.result.LocalDateTimeValueFactory;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static Service.ReadingFile.*;

public class AirportApp {

    public static void main(String[] args) {
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        PassengerDaoImpl passengerDao = new PassengerDaoImpl();
        TripDaoImpl tripDao = new TripDaoImpl();
//        readFileCompany();
//        readFileTrip();
//        readFilePassInTrip();
//        readFilePassenger();
//        readFilePassengerAddress();
//        companyDao.update( 2,new Company("Armavia", LocalDate.of(2008,02,02)));
//        System.out.println(companyDao.getByID(2));
//        System.out.println(companyDao.getAll());
//        companyDao.delete(387);
//        System.out.println(companyDao.get(0, 10, "Comp_name"));
//          companyDao.save(new Company("FlyDubai",LocalDate.of(2009,2,2)));
//        System.out.println(passengerDao.getAll());
//        System.out.println(passengerDao.get(0, 10, "Psg_name"));
//        passengerDao.update(3, new Passanger("Tigran","098765432",8359));
//        passengerDao.delite(6103);
//        System.out.println(tripDao.getTripsto("Paris"));
//        System.out.println(tripDao.getTripsFrom("Paris"));
//        System.out.println(tripDao.getAll());
//        System.out.println(tripDao.getByID(1100));
//        System.out.println(tripDao.get(0, 10, "Plane"));
//        tripDao.update(1100, new Trip(9900, 1, "Boeing", "Paris", "Moscow",
//                LocalDateTime.of(2007,1,1,13,33),
//                LocalDateTime.of(2007,1,2,15,30)));
//        tripDao.delite(8882);
//        passengerDao.registerTrip(new Pass_in_Trip(1100,6176,LocalDateTime.of(2022,6,12,14,0),"1s"));
//         passengerDao.getPassangersOfTrip(1100);

    }
}
