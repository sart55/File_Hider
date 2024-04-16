package service;

import java.util.Scanner;

public class Passcheck{

    public static String passcheck(){

        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter password : ");
        String pass = sc.nextLine();
        System.out.print("Enter Password again : ");
        String confirmpass = sc.nextLine();
        
        if(pass.equals(confirmpass)){
            return pass;
        }

        return null;
    }
}
