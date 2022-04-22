package Dao.ImplDao;

import Dao.PassengerDao;
import Service.DBConnetion;
import model.Address;
import model.Pass_in_Trip;
import model.Passanger;
import model.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerDaoImpl implements PassengerDao {
    public PassengerDaoImpl() {

    }

    @Override
    public  Passanger getByID(long id) {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Passanger passanger = null;
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AiroportMS.Passanger INNER JOIN AiroportMS.Address " +
                            "ON Psg_address = ID_address   WHERE ID_psg = ?"


            );
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passanger = new Passanger(
                        resultSet.getInt("ID_psg"),
                        resultSet.getString("Psg_name"),
                        resultSet.getString("Psg_phone_num"),
                        resultSet.getInt("Psg_address")
                );
            }

        } catch (SQLException throwables) {
            System.out.println("Wrong query for Employee with id=");
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }

        return passanger;
    }


    @Override
    public Set<Passanger> getAll() {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Passanger> passangers = new HashSet<>();
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AiroportMS.Passanger INNER JOIN AiroportMS.Address " +
                            "ON Psg_address = ID_address  "
            );

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passangers.add(new Passanger(
                        resultSet.getInt("ID_psg"),
                        resultSet.getString("Psg_name"),
                        resultSet.getString("Psg_phone_num"),
                        resultSet.getInt("Psg_address")
                ));
            }

        } catch (SQLException throwable) {
            System.out.println("Wrong query for Employee with id=");
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }
        return passangers;    }

    @Override
    public Set<Passanger> get(int offset, int perPage, String sort) {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Passanger> passangers = new HashSet<>();
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Passanger ORDER BY " + sort + " LIMIT ? OFFSET ?;"
            );
            preparedStatement.setInt(1, perPage);
            preparedStatement.setInt(2, perPage * offset);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passangers.add(new Passanger(
                        resultSet.getInt("ID_psg"),
                        resultSet.getString("Psg_name"),
                        resultSet.getString("Psg_phone_num"),
                        resultSet.getInt("Psg_address")
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
        return passangers;    }

    @Override
    public Passanger savePassenger(Passanger passanger) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "INSERT INTO Passanger( Psg_name, Psg_phone_num,Psg_address) VALUES(?,?,?)"
                     )
        ) {
            preparedStatement.setString(1, passanger.getPsg_name());
            preparedStatement.setString(2, passanger.getPsg_phone_num());
            preparedStatement.setInt(3, passanger.getPsg_address());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return passanger;
    }



    @Override
    public Passanger update(int id,Passanger passanger) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE Passanger SET Psg_name =?, Psg_phone_num=?,Psg_address =? WHERE ID_psg= ?;"

                         )
            ) {
                preparedStatement.setString(1, passanger.getPsg_name());
                preparedStatement.setString(2, passanger.getPsg_phone_num());
                preparedStatement.setInt(3, passanger.getPsg_address());
                preparedStatement.setInt(4,id);

                preparedStatement.executeUpdate();

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        return passanger;    }

    @Override
    public void delite(long passangerId) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "DELETE  FROM Passanger Where ID_psg=?  "
                         )
            ) {
                preparedStatement.setLong(1, passangerId);

                preparedStatement.execute();

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void getPassangersOfTrip(long tripNumber) {
//        List<Passanger> passangerList = new ArrayList<>();
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT Psg_ID FROM Pass_in_Trip where Trip_no=?"
                     )
        ) {


            preparedStatement.setLong(1, tripNumber);
            preparedStatement.execute();



        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }



    @Override
    public void registerTrip(Pass_in_Trip pit) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "INSERT INTO Pass_in_Trip ( Trip_no,Psg_ID,Trip_Date,Place) VALUES(?,?,?,?)"
                         )
            ) {
                preparedStatement.setInt(1,pit.getTrip_no());
                preparedStatement.setInt(2, pit.getPsg_id());
                preparedStatement.setDate(3, Date.valueOf(pit.getTrip_Date()));
                preparedStatement.setString(4, pit.getPlace());

                preparedStatement.execute();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void cancelTrip(long passangerId, long tripNumber) {

    }

    public Address saveAddress(Address address) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "INSERT INTO Address(Country, City) VALUES(?,?)"
                     )
        ) {
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());


            preparedStatement.execute();



        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return address;
    }

    public int  getAddressId(Address address){
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT ID_address FROM Address WHERE Country=? and City=?"

                     )
        )

        {
            preparedStatement.setString(1,address.getCountry());
            preparedStatement.setString(2,address.getCity());
            ResultSet rs= preparedStatement.executeQuery();
            if (rs.next()) {
                address.setID_address(rs.getInt("ID_address"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address.getID_address();
    }





}
