public class Item {
    private String name;
    private String code;
    private double quantity;
    private double amountPrice;
    private double totalPrice;
    private double amountWeight;
    private double totalWeight;
    private boolean isItMeterSI;


    public Item(String Name,String ItemCode,  double ItemAmountPrice, double ItemAmountWeight, boolean IsItMeter) {
        this.name=Name;
        this.code=ItemCode;
        this.amountPrice=ItemAmountPrice;
        this.amountWeight=ItemAmountWeight;
        this.isItMeterSI=IsItMeter;
        quantity=0.00;
    }
    public String getItemName(){
        return this.name;
    }

    public String getItemCode(){
        return this.code;
    }

    public boolean getIsItMeterSI(){ // For checking should input be int or double
        return this.isItMeterSI;
    }
    
    public double getAmountPrice(){
        return amountPrice;
    }
    public double getTotalPrice(){
        totalPrice = quantity * amountPrice; 
        return totalPrice;
    }

    public double getTotalWeight(){
        totalWeight = quantity * amountWeight;
        return totalWeight;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setItemQuantity(double amount){
        this.quantity= amount;
    }


}
