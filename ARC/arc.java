import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class arc {

    public static void main (String args[]){
        system();
        
    }

    public static void system(){
        Robot robot = new Robot();
        robot.uploadingNeededData();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter items' codes");
        String itemCodes = (scanner.nextLine() + " "); 
        addItemsFromInput(itemCodes, robot);
        System.out.println("Enter items' quantity");
        String itemQuantitys = scanner.nextLine() ;
        addQuantityFromInput(itemQuantitys, robot);
        System.out.println("***************************");
        robot.printOrderForPrice();
        System.out.println("\n***************************");
        robot.printOrderForWeight();
        System.out.println("\n***************************");
        robot.printWeightLimit();
        System.out.println("***************************");
        System.out.println("Price is " + robot.getPrice() + "$ for your Robot");
    }


    public static void addItemsFromInput(String input, Robot robot){ //I try to use split method but it doesn't work in all cases such as 5: RSL, also this method is working for the cases that have double space between the Item Codes
        int i = 0;
        List<String> strl = new LinkedList<>();
        String t = "";

        while(i< input.length()){
            if(i == input.length()-1){
                    strl.add(t);
                    t = "";
                break;
            }

            if ((input.charAt(i) == ' ' && isNumeric(Character.toString(input.charAt(i+1)))) ) {
                    //t += Character.toString(s.charAt(i));
                    strl.add(t);
                    t = "";

            }else{
                t += Character.toString(input.charAt(i));
            }
            i++;
        }

        int check = 0;
        int check2 =0;
        int k =0;
        for (String e : strl){
            check2= 0;
            check=0;
            if (k ==0 ){
                check2= 0;
                check=0;
                for (char c : e.toCharArray()) {
                    if(c == ' '){
                        if(check ==1){
                            check2 = 1;
                        }
                        check=1;
                    }else{
                        check=0;
                    }
                }
            }
            if(check2==0) {
                robot.addItem(e);
            }
                k++;
        }
    }


    public static void addQuantityFromInput(String input, Robot robot){
        String[] numbers = input.split(" ");
        for(int i=0; i<numbers.length; i++){
            double number = Double.parseDouble(numbers[i]);
            if(!robot.getItem(i).getIsItMeterSI() && !isItInteger(number)){
                System.out.println("The " + robot.getItem(i).getItemName() + " is ordering by amount, however you entered a double, because of that I will assume that you are not ordering!");
                continue;
            }
            robot.addQuantity(number, i);
        }
        robot.calculatePrice();
        robot.calculateWeight();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isItInteger(double number){
        return (int) number == (double) number;
    }



}