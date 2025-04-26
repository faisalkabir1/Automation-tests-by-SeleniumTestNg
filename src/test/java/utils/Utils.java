package utils;

public class Utils {
    public static int generateRandomNumber(int min, int max){
        double randomNumber= Math.random()*(max-min)+min;
        return (int)randomNumber;
    }

    public static void main(String[] args) {
        int id= generateRandomNumber(1000,9999);
        System.out.println(id);
    }
}
