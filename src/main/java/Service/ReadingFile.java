package Service;

import Dao.ImplDao.*;
import Dao.ImplDao.CompanyDaoImpl;
import model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReadingFile {

    public static void readFileCompany() {
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/tigranyantigran/IdeaProjects/AiroportMS/src/main/java/companies.txt")))) {

            String[] strArr;
            String str;
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Company company = new Company(strArr[0], LocalDate.parse(strArr[1], formatter));
                companyDao.save(company);

            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }


    public static void readFilePassInTrip() {
        PassengerDaoImpl pit =new PassengerDaoImpl();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS" );
        LocalDateTime dateTime = LocalDateTime.parse("1909-01-01 04:00:00.000",formatter);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/tigranyantigran/IdeaProjects/AiroportMS/src/main/java/pass_in_trip.txt")))) {

            String[] strArr;
            String str;
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Pass_in_Trip pass_in_trip = new Pass_in_Trip(Integer.parseInt(strArr[0])
                        , Integer.parseInt(strArr[1]), (LocalDateTime.parse(strArr[2], formatter)), strArr[3]);
                pit.registerTrip(pass_in_trip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFileTrip() {
        TripDaoImpl tripDao = new TripDaoImpl();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse("1909-01-01 04:00:00.000", formatter);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/tigranyantigran/IdeaProjects/AiroportMS/src/main/java/trip.txt")))) {

            String[] strArr;
            String str;
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Trip trip = new Trip(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]), strArr[2], strArr[3], strArr[4],
                        LocalDateTime.parse(strArr[5], formatter), LocalDateTime.parse(strArr[6], formatter));
                tripDao.save(trip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//        public static void readFilePassenger()  {
//            PassengerDaoImpl passangerDao =new PassengerDaoImpl();
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(
//                    new FileInputStream("/Users/tigranyantigran/IdeaProjects/AiroportMS/src/main/java/passengers.txt")))) {
//
//                String[] strArr;
//                String str;
//                while ((str = br.readLine()) != null) {
//                    strArr = str.split(",");
//                    Passanger passanger = new Passanger( strArr[0], strArr[1]);
//                    passangerDao.savePassenger(passanger);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    public static void readFilePassengerAddress(){
        PassengerDaoImpl passangerDao =new PassengerDaoImpl();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/tigranyantigran/IdeaProjects/AiroportMS/src/main/java/passengers.txt")))) {

            String[] strArr;
            String str;
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Address address = new Address( strArr[2], strArr[3]);
                passangerDao.saveAddress(address);
                Passanger passanger =new Passanger(strArr[0],strArr[1], passangerDao.getAddressId(address));
                passangerDao.savePassenger(passanger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
