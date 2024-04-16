package Views;

import DAO.DataDAO;
import DAO.UserDAO;
import model.Data;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Userview {
    
    public static void home(String email){ 
        System.out.println("---------------------------------------\n             HomePage\n---------------------------------------");
        System.out.println("Press 1 to show all hidden files      |");
        System.out.println("Press 2 to hide file                  |");
        System.out.println("Press 3 to unhide file                |");
        System.out.println("Press 4 to logout                     |");
        System.out.println("Press 5 to Exit Application           |");
        System.out.println("---------------------------------------");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){
            case 1 : getAllFiles(email);
            case 2 : hide_File(email);
            case 3 : unhide_File(email);
            case 4 : logout();
            case 5 : System.out.println("Thank You..."); System.exit(0);
            
        }
    }

    private static void getAllFiles(String email){
        List<String> all_files = new ArrayList<>();
        try {
            all_files = DataDAO.getAllFiles(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(all_files.size() > 0){
            int i = 1;
            System.out.println("Your files are as follows :");
            for(String file : all_files){
                System.out.println(i+++" "+file);
            }
        }
        else{
            System.out.println("Zero files are hidden..");
        }
        Userview.home(email);

    }

    public static void hide_File(String email){
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the file path : ");
        String path = sc.nextLine();
        System.out.print("Enter the file name : ");
        String name = sc.nextLine();

        try {
            Data data = new Data(name,path,email);
            DataDAO.hideFile(data);
        } catch (SQLException  | IOException e) {
            e.printStackTrace();
        }
        Userview.home(email);
    }

    public static void unhide_File(String email){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name : ");
        String file_name = sc.nextLine();

        try {
            if(!new DataDAO().isExists(email, file_name)){
                System.out.println("File doesn't exists");
            }
            else{
                System.out.print("Enter password : ");
                String pass = sc.nextLine();
                if(pass.equals(UserDAO.getPass(email))){
                    new DataDAO().unhide(email,UserDAO.getId(file_name));
                }
                else{
                    System.out.println("Wrong Password, Can't hide file ");
                    Userview.home(email);
                }
                
           }
        } catch (SQLException | IOException e ) {
            e.printStackTrace();
        }

        Userview.home(email);
    }

    private static void logout(){
        Welcome.welcomeScreen();
    }
}
