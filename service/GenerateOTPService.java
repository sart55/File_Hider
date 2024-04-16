package service;

import java.util.Random;

public class GenerateOTPService {
    public int generateOTP(){
        Random random = new Random();
        int ret = random.nextInt(1000,10000);
        return ret;
    }
}
