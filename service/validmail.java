package service;

import java.util.Scanner;

public class validmail {
    public static boolean mailValidation(String mail){
        GenerateOTPService generateOTP = new GenerateOTPService();
        int otp = generateOTP.generateOTP();

        SendOTPService.sendOTP(mail,otp);

        Scanner sc = new Scanner(System.in);
    
        System.out.print("Enter the otp sent to your mail id : ");
        int entered_otp = sc.nextInt();

    
        if(otp == entered_otp){
            return true;
        }
        return false;
    } 
}
