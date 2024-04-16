package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Myconnection{

    public static Connection connection = null;

    public static Connection getConnection(){
      try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/File_Hider","root","******");
      }
      catch(ClassNotFoundException | SQLException e){
        e.printStackTrace();
      }

      return connection;
      
    }

    public void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Connection closed");
        }
    }
}