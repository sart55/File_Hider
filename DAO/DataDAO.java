package DAO;

import model.Data;
import database.Myconnection;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import Views.Userview;

import java.sql.ResultSet;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class DataDAO {

    public boolean isExists(String email,String file_name) throws SQLException{
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select file_name from data where email = ?");
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            if(file_name .equals(rs.getString("file_name"))){
                return true;
            }
        }

        return false;
    }
    
    public static List<String> getAllFiles(String email)throws SQLException{

        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select file_name from data where email = ?");
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        List<String> ret = new ArrayList<>();

        while(rs.next()){
            String file_name = rs.getString("file_name");
            ret.add(file_name);
        }

        return ret;

    }

    public static void hideFile(Data file) throws SQLException, IOException{
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into data values(default,?,?,?,?)");

        ps.setString(1, file.file_name);
        ps.setString(2, file.path);
        ps.setString(3, file.email);

        File f = new File(file.path);
        if(!f.isFile()){
            System.out.println("File doesn't exists...");
            Userview.home(file.email);   
        }

        FileReader fr = new FileReader(f);
        ps.setCharacterStream(4, fr,f.length());
        ps.executeUpdate();
        System.out.println(file.file_name+" file deleted successfully....");
        fr.close();
        f.delete();
    }

    public void unhide(String email,int id) throws SQLException, IOException {
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select path, bin_data from data where email = ?");
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        rs.next();

        String path = rs.getString(1);
        Reader r = rs.getCharacterStream("bin_data");
         File f = new File(path);
        FileWriter fw = new FileWriter(f);

        int i;
        while ((i = r.read()) > 0) {
            fw.write((char) i);
        }
        fw.close();

        ps = connection.prepareStatement("SET SQL_SAFE_UPDATES = 0");
        ps.executeUpdate();
        ps = connection.prepareStatement("delete from data where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps = connection.prepareStatement("SET SQL_SAFE_UPDATES = 1");
        ps.executeUpdate();
        System.out.println("Successfully Unhidden");
    }
}
