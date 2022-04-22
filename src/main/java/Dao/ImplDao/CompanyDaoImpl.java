package Dao.ImplDao;

import Dao.CompanyDao;
import Service.DBConnetion;
import model.Company;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class CompanyDaoImpl implements CompanyDao {
    @Override
    public Company getByID(long id) {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Company company = null;
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Company WHERE ID_Comp = ?"
            );

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("ID_Comp"),
                        resultSet.getString("Comp_name"),
                        Instant.ofEpochMilli(resultSet.getDate("Founding_date").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
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
        return company;
    }

    @Override
    public Set<Company> getAll() {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Company> company = new HashSet<>();
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Company  "
            );

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                company.add(new Company(
                        resultSet.getInt("ID_Comp"),
                        resultSet.getString("Comp_name"),
                        Instant.ofEpochMilli(resultSet.getDate("Founding_date").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
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
        return company;
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        Connection connection =
                DBConnetion.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Set<Company> company = new HashSet<>();
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Company ORDER BY " + sort + " LIMIT ? OFFSET ?;"
            );
            preparedStatement.setInt(1, perPage);
            preparedStatement.setInt(2, perPage * offset);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                company.add(new Company(
                        resultSet.getInt("ID_Comp"),
                        resultSet.getString("Comp_name"),
                        Instant.ofEpochMilli(resultSet.getDate("Founding_date").getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                ));
            }

        } catch (SQLException throwable) {
//            System.out.println("Wrong query for Employee with id=" );
            throwable.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }
        return company;
    }

    @Override
    public Company save(Company company) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "INSERT INTO Company (Comp_name, Founding_date) VALUES(?,?)"
                         )
            ) {
                preparedStatement.setString(1, company.getComp_name());
                preparedStatement.setDate(2, Date.valueOf(String.valueOf(company.getFounding_date())));

                preparedStatement.execute();

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return company;
    }

    @Override
    public Company update(int id, Company company) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE Company SET Comp_name =?, Founding_date=? WHERE ID_Comp= ?;"

                         )
            ) {
                preparedStatement.setString(1, company.getComp_name());
                preparedStatement.setDate(2, Date.valueOf(String.valueOf(company.getFounding_date())));
                preparedStatement.setInt(3, id);

                preparedStatement.executeUpdate();

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        return company;
    }

    @Override
    public void delete(long companyId) {
        try (Connection connection = DBConnetion
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "DELETE  FROM Company Where ID_Comp=?  "
                         )
            ) {
                preparedStatement.setLong(1, companyId);

                preparedStatement.execute();

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
