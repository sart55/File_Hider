package Views;

import DAO.UserDAO;
import service.Passcheck;
import service.validmail;

import java.sql.SQLException;
import java.util.Scanner;
import model.User;

public class Welcome {

    public Welcome(String start){
        System.out.println("--------------------------------\n Welcome to File Hider Project");
    }
    public static void welcomeScreen(){

        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("       Login Page              |");
        System.out.println("--------------------------------");
        System.out.println("Enter 1 to Login               |");
        System.out.println("Enter 2 to Sign Up             |");
        System.out.println("Enter 3 to Exit                |");
        System.out.println("--------------------------------");

        int choice = sc.nextInt();

        switch(choice){
            case 1 : login();
            break;

            case 2 : signUp();
            break;
            
            case 3 : System.out.println("Thank You...");
            System.exit(0);
        }
        sc.close();
    }

    public static void login(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your mail : ");
        String mail = sc.nextLine();

        try{
            if(!UserDAO.isExists(mail)){
                System.out.println("Mail is not registered..");
                Welcome.welcomeScreen();
            }
            else{
                System.out.print("Enter your password : ");
                String password = sc.nextLine();
    
                if(password.equals(UserDAO.getPass(mail))){
                    System.out.println("Successfully login....");
                    Userview.home(mail);
                }
                else{
                    System.out.println("Wrong Password");
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        Welcome.welcomeScreen();
        sc.close();
    }

    public static void signUp(){
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter your mail : ");
            String mail = sc.nextLine();

            if(UserDAO.isExists(mail) == true){
                System.out.println("mail already registered...");
            }
            else if(!validmail.mailValidation(mail)){
                System.out.println("Wrong OTP, Please enter Email Id again..");
                signUp();
            }
            else{
                System.out.print("Enter your name : ");
                String name = sc.nextLine();
           
                String pass = Passcheck.passcheck();

                while(pass == null){
                    System.out.println("Password doesn't matches");
                    pass = Passcheck.passcheck();
                }
                    User user = new User(name,mail,pass);

                    UserDAO.saveUser(user);                   
                    Userview.home(mail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
        Welcome.welcomeScreen();
        
    }

}
