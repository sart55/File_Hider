package DAO;

import model.User;
import database.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserDAO {
    public static boolean isExists(String email)throws SQLException{
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from users");
        boolean ret = false;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String e = rs.getString(1);
            if (e.equals(email)){
                ret = true;
            }
        }
        return ret;
    }

    public static void saveUser(User user)throws SQLException{

            Connection connection = Myconnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into Users values(default,?,?,?)");
            ps.setString(1,user.name);
            ps.setString(2, user.mail);
            ps.setString(3, user.password);
            int i = ps.executeUpdate();

            if(i == 1){
                System.out.println("Successfully registerd user...");
            }
    }

    public static String getPass(String mail) throws SQLException{
        Connection connection = Myconnection.getConnection();

        PreparedStatement ps = connection.prepareStatement("select password from Users where email = ?");
        ps.setString(1, mail);

        ResultSet rs = ps.executeQuery();

        String ret = "";
        while(rs.next()){
            ret = rs.getString("password");
        }
        return ret;
    }

    public static int getId(String file_name) throws SQLException{
        Connection connection = Myconnection.getConnection();

        PreparedStatement ps = connection.prepareStatement("select id from data where file_name = ?");
        ps.setString(1, file_name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }
}
