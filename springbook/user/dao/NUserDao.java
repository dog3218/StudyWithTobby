package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:ORCL", "test", "test");
        return conn;
    }

    @Override
    public void add(User user) throws ClassNotFoundException, SQLException {
        super.add(user);
    }

    @Override
    public User get(String id) throws ClassNotFoundException, SQLException {
        return super.get(id);
    }
}


