package filehider;

import Views.Welcome;


public class App 
{
    public static void main( String[] args )
    {
        Welcome w = new Welcome("start");
         
        do{
            w.welcomeScreen();
        }while(true);
    }
}
