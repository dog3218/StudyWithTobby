package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public abstract class UserDao{
    //private SimpleConnectionMaker simpleConnectionMaker;
    private ConnectionMaker connectionMaker;

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        User user = new User();
        user.setPassword("2");
        user.setId("2");
        user.setName("2");

        UserDao dao = new UserDao() {
            @Override
            public Connection getConnection() throws ClassNotFoundException, SQLException {
                return null;
            }

        };
        dao.add(user);
        System.out.println(user.getId()+"입력 완료");
        User user2 = dao.get(user.getId());
        System.out.println(user2.getId()+"조회 완료");
    }
    public UserDao() {
        //connectionMaker = new SimpleConnectionMaker();
        connectionMaker = new DConnectionMaker();
    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    public void add(User user) throws ClassNotFoundException, SQLException {
        //Connection conn = simpleConnectionMaker.makeNewConnection();
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        //Connection conn = simpleConnectionMaker.makeNewConnection();
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id = ?"   );
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;

    }


}

