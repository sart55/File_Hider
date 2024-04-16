package model;

import java.sql.SQLException;

public class Data {
    public int id;
    public String file_name;
    public String path;
    public String email;
    
    public Data(String file_name,String path,String email) throws SQLException{
        this.file_name = file_name;
        this.path = path;
        this.email = email;
    }
}
