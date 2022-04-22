package Dao.ImplDao;

import Dao.TripDao;
import Service.DBConnetion;
import model.Company;
import model.Trip;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class TripDaoImpl implements TripDao {
    @Override
    public Trip getByID(long id) {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Trip trip = null;
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Trip WHERE trip_no = ?"
            );

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                trip = new Trip(
                        resultSet.getInt("Trip_no"),
                        resultSet.getInt("ID_comp"),
                        resultSet.getString("Plane"),
                        resultSet.getString("Town_from"),
                        resultSet.getString("Town_to"),
                        Instant.ofEpochMilli(resultSet.getDate("Time_out").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime(),
                        Instant.ofEpochMilli(resultSet.getDate("Time_in").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                );
            }

        } catch (SQLException throwable) {
            System.out.println("Wrong query for Employee with id=" + id);
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }
        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Trip> trips = new HashSet<>();
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AiroportMS.Trip "
            );


            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trips.add(new Trip(
                        resultSet.getInt("Trip_no"),
                        resultSet.getInt("ID_comp"),
                        resultSet.getString("Plane"),
                        resultSet.getString("Town_from"),
                        resultSet.getString("Town_to"),
                        Instant.ofEpochMilli(resultSet.getDate("Time_out").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime(),
                        Instant.ofEpochMilli(resultSet.getDate("Time_in").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                ));
            }

        } catch (SQLException throwable) {
            System.out.println("Wrong query ");
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }
        return trips;
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Trip> trips = new HashSet<>();
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AiroportMS.Trip ORDER BY " + sort + " LIMIT ? OFFSET ?;"
            );
            preparedStatement.setInt(1, perPage);
            preparedStatement.setInt(2, perPage * offset);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trips.add(new Trip(
                        resultSet.getInt("Trip_no"),
                        resultSet.getInt("ID_comp"),
                        resultSet.getString("Plane"),
                        resultSet.getString("Town_from"),
                        resultSet.getString("Town_to"),
                        Instant.ofEpochMilli(resultSet.getDate("Time_out").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime(),
                        Instant.ofEpochMilli(resultSet.getDate("Time_in").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                ));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }

        return trips;
    }

    @Override
    public  Trip save(Trip trip) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "INSERT INTO Trip (Trip_no, ID_Comp,Plane,Town_from,Town_to,Time_out,Time_in) VALUES(?,?,?,?,?,?,?)"
                         )
            ) {
                preparedStatement.setInt(1, trip.getTrip_no());
                preparedStatement.setInt(2, trip.getID_Comp());
                preparedStatement.setString(3, trip.getPlane());
                preparedStatement.setString(4, trip.getTown_from());
                preparedStatement.setString(5, trip.getTown_to());
                preparedStatement.setDate(6, Date.valueOf(trip.getTime_out()));
                preparedStatement.setDate(7, Date.valueOf(trip.getTime_in()));

                preparedStatement.execute();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return trip;
    }

    @Override
    public Trip update(int Trip_no, Trip trip) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE AiroportMS.Trip SET  ID_Comp=?,Plane=?,Town_from=?," +
                                         "Town_to=?,Time_out=?, Time_in=? WHERE Trip_no= ?;"

                         )
            ) {
                preparedStatement.setInt(1, trip.getID_Comp());
                preparedStatement.setString(2, trip.getPlane());
                preparedStatement.setString(3, trip.getTown_from());
                preparedStatement.setString(4, trip.getTown_to());
                preparedStatement.setDate(5, Date.valueOf(trip.getTime_out()));
                preparedStatement.setDate(6, Date.valueOf(trip.getTime_in()));
                preparedStatement.setInt(7, Trip_no);


                preparedStatement.executeUpdate();

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        return trip;
    }

    @Override
    public void delite(long tripId) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "DELETE  FROM AiroportMS.Trip Where Trip_no=?  "
                         )
            ) {
                preparedStatement.setLong(1, tripId);

                preparedStatement.execute();

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Set<Trip> getTripsFrom(String city) {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Trip> trips = new HashSet<>();
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AiroportMS.Trip where Town_from=?"
            );


            preparedStatement.setString(1,city);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trips.add(new Trip(
                        resultSet.getInt("Trip_no"),
                        resultSet.getInt("ID_comp"),
                        resultSet.getString("Plane"),
                        resultSet.getString("Town_from"),
                        resultSet.getString("Town_to"),
                        Instant.ofEpochMilli(resultSet.getDate("Time_out").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime(),
                        Instant.ofEpochMilli(resultSet.getDate("Time_in").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                ));
            }

        } catch (SQLException throwable) {
            System.out.println("Wrong query ");
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }
      return trips;
    }

    @Override
    public Set<Trip> getTripsto(String city) {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Trip> trips = new HashSet<>();
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AiroportMS.Trip where Town_to=?"
            );


            preparedStatement.setString(1,city);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trips.add(new Trip(
                        resultSet.getInt("Trip_no"),
                        resultSet.getInt("ID_comp"),
                        resultSet.getString("Plane"),
                        resultSet.getString("Town_from"),
                        resultSet.getString("Town_to"),
                        Instant.ofEpochMilli(resultSet.getDate("Time_out").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime(),
                        Instant.ofEpochMilli(resultSet.getDate("Time_in").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                ));
            }

        } catch (SQLException throwable) {
            System.out.println("Wrong query ");
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }
        return trips;    }
}
