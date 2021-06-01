
import java.util.ArrayList;
import static java.lang.Math.round;

public class Robot {
    private final ArrayList<Item> itemsForChecking = new ArrayList<Item>();
    private final ArrayList<Item> itemsInRobot = new ArrayList<Item>();
    private double rTotalPrice;
    private double rTotalWeight;
    private static ArrayList<Item> lengthQuantities;
    private static ArrayList<Item> amountQuantities;

    public Robot(){
        rTotalPrice=0.0;
        rTotalWeight=0.0;
    }

    public void uploadingNeededData(){
        itemsForChecking.add(new Item("Encoder", "4.1", 20, 0.01, false));
        itemsForChecking.add(new Item("Magnetic Encoder", "4.2M", 50, 0.03, false));
        itemsForChecking.add(new Item("Polikarbon Plaka", "3.1", 200, 0.5, true));
        itemsForChecking.add(new Item("Alimünyum 2024 Plaka", "3.2-2", 30, 3.5, true));
        itemsForChecking.add(new Item("Alimünyum 4145 Plaka", "3.2-4", 50, 4.5, true));
        itemsForChecking.add(new Item("Omniwheel", "2.Omni", 30, 0.4, false));
        itemsForChecking.add(new Item("6 in. Wheel", "2.\"6in\"", 20, 0.3, false));
        itemsForChecking.add(new Item("4 in. Wheel", "2.\"4in\"", 15, 0.2, false));
        itemsForChecking.add(new Item("NavX", "1/NavX", 200, 0.1, false));
        itemsForChecking.add(new Item("RoboRIO", "1/RIO", 250, 1, false));
        itemsForChecking.add(new Item("Modem", "1/Modem", 50, 0.2, false));
        itemsForChecking.add(new Item("Circuit Breaker", "5: CB", 20, 1.5, false));
        itemsForChecking.add(new Item("Robot Signal Light", "5: RSL", 80, 0.5, false));
        itemsForChecking.add(new Item("16 AWG Fire", "6.'16'", 2.2, 0.25, true));
        itemsForChecking.add(new Item("18 AWG Fire", "6.'18'", 0.75, 0.35, true));
    }

    public void addItem(String ItemCode){ // I try to us .equals method but even string are equal their values are not equal(I learned that after 1 hour search:)
        for(Item cItem : itemsForChecking){ // cItem stands for Current Item
            char[] first  = cItem.getItemCode().toCharArray();
            char[] second = ItemCode.toCharArray();
            int counter = 0;
            int minLength = Math.min(first.length, second.length);
            for(int i = 0; i < minLength; i++)
            {
                if (first[i] != second[i])
                {
                    counter++;
                }
            }
            if(counter == 0){
                //System.out.println("true");
                itemsInRobot.add(cItem);
                //System.out.println(cItem.getItemName());
            }
        }
        
    }

    public void addQuantity(double amount, int index){
        itemsInRobot.get(index).setItemQuantity(amount);
    }

    public double getPrice(){
        return rTotalPrice;
    }
    public void calculatePrice(){
        for(Item cItem : itemsInRobot){
            rTotalPrice+=cItem.getTotalPrice();
        }
    }

    public double getWeight(){
        return rTotalWeight;
    }
    public void calculateWeight(){
        for(Item cItem : itemsInRobot){
            rTotalWeight+= cItem.getTotalWeight();
        }
    }
    public Item getItem(int index){
        return itemsInRobot.get(index);
    }

    public void printOrderForWeight(){ // Selection Sort Algorithm
        for (int i = 0; i < itemsInRobot.size() - 1; i++){
            int index = i;  
            for (int j = i + 1; j < itemsInRobot.size(); j++){
                if (itemsInRobot.get(j).getTotalWeight() < itemsInRobot.get(index).getTotalWeight()){
                    index = j;//searching for lowest index  
                }  
            }  
            Item smallerWeight = itemsInRobot.get(index);
            itemsInRobot.set(index, itemsInRobot.get(i));
            itemsInRobot.set(i, smallerWeight);
        } 
        int maxArrayValue= 4;
        if(itemsInRobot.size()-1<4){
            maxArrayValue= itemsInRobot.size()-1;
        }
        System.out.println("Smaller Weight to Heavy Weight");
        for(int cIndex=0; cIndex<= maxArrayValue; cIndex++){
            System.out.print(itemsInRobot.get(cIndex).getItemCode()+ " (" + (Math.round(itemsInRobot.get(cIndex).getTotalWeight() * 100.0)/100.0)+  "kg) - ");
        }
    }  

    public void printOrderForPrice(){ // Selection Sort Algorithm
        for (int i = 0; i < itemsInRobot.size() - 1; i++){
            int index = i;  
            for (int j = i + 1; j < itemsInRobot.size(); j++){
                if (itemsInRobot.get(j).getTotalPrice() > itemsInRobot.get(index).getTotalPrice()) {
                    index = j;//searching for highest index  
                }  
            }  
            Item cheapestItem = itemsInRobot.get(index);
            itemsInRobot.set(index, itemsInRobot.get(i));
            itemsInRobot.set(i, cheapestItem);
        }
        System.out.println("Most Expensive to Cheapest");
        for(Item cItem : itemsInRobot){
            System.out.print(cItem.getItemName()+ " (" +(Math.round(cItem.getTotalPrice()*100.0)/100.0)+  "$) - ");
        }
    }
    public ArrayList<Item> orderForWeight(ArrayList<Item> arrayList){ // Selection Sort Algorithm
        ArrayList<Item> a = new ArrayList<Item>(arrayList);
        for (int i = 0; i < a.size() - 1; i++){
            int index = i;
            for (int j = i + 1; j < a.size(); j++){
                if (a.get(j).getTotalWeight() > a.get(index).getTotalWeight()) {
                    index = j;//searching for highest index
                }
            }
            Item x = itemsInRobot.get(index);
            itemsInRobot.set(index, itemsInRobot.get(i));
            itemsInRobot.set(i, x);
        }
        return a;
    }
    
    public void printWeightLimit() {
        if(getWeight() > 52.5){
            System.out.println("Unfortunately, your robot's weight is over than it should be by " + (Math.round((getWeight() - 52.5)*100.0)/100.0) );
        } else {
            System.out.println("Fortunately, your robot's weight("+ (Math.round(getWeight()*100.0)/100.0) +"kg) is good for the competition");
        }
    }

    
}