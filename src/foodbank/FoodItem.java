package foodbank;
import java.util.Comparator;
public class FoodItem implements Comparable<FoodItem>{
    private String name,foodGroup;
    private int quantityStart,quantity,toBuy;
    private double price,weight,volume;
    private double perQuantity,perPrice,perWeight,perVolume,
            perPriceWeight,perPriceVolume,perWeightVolume,perAll;
    /**
     * Creates copy of this class
     * @return copy of this class
     */
    public FoodItem getNewCopy(){
        FoodItem newFi = new FoodItem();
        newFi.name = name;
        newFi.foodGroup = foodGroup;
        newFi.quantityStart = quantityStart;
        newFi.quantity = quantity;
        newFi.toBuy = toBuy;
        newFi.price = price;
        newFi.weight = weight;
        newFi.volume = volume;
        newFi.perQuantity = perQuantity;
        newFi.perPrice = perPrice;
        newFi.perWeight = perWeight;
        newFi.perVolume = perVolume;
        newFi.perPriceWeight = perPriceWeight;
        newFi.perPriceVolume = perPriceVolume;
        newFi.perWeightVolume = perWeightVolume;
        newFi.perAll = perAll;
        return newFi;
    }
    public void setName(String _name){
        name = _name;
    }
    public void setFoodGroup(String _foodGroup){
        foodGroup = _foodGroup;
    }
    public void setQuantity(int _quantity){
        quantity = _quantity;
    }
     public void setQuantityStart(int _quantityStart){
        quantityStart = _quantityStart;
    }
    public void setPrice(double _price){
       price = _price;
    }
    public void setWeight(double _weight){
        weight = _weight;
    }
    public void setVolume(double _volume){
        volume = _volume;
    }
    public String getName(){
        return name;
    }
    public String getFoodGroup(){
        return foodGroup;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getPrice(){
        return price;
    }
    public double getWeight(){
        return weight;
    }
    public double getVolume(){
        return volume;
    }
    public int getToBuy(){
        return toBuy;
    }
    /**
     * Method that determines the lowest
     * out of a set of numbers
     * @param _da an array of doubles
     * @return the lowest of the doubles
     */
    private double minimum(double... _da){
        double min = 0;
        boolean first = true;
        for(double _d: _da){
            if(_d<min || first){
                first = false;
                min = _d;
            }
        }
        return min;
    }
    /**
     * Calculates the amount of foodItems that can be bought
     * to a unrounded number (for the sake of sorting the foodItemList)
     * the lowest number of applicable factors is the final value
     * including quantity 
     * @param _budget the budget.set to -1 if budget is not a factor
     * @param _weightLimit the weight limit of cart.set to -1 if weight is not a factor
     * @param _volumeLimit the volume limit of cart.set to -1 if volume is not a factor
     * @param _max a maximum that can be set (this is used for balancing FoodGroups)
     */
    public void calcRatios(double _budget,double _weightLimit,double _volumeLimit,int _max){
        if(_max == -1){
            perPrice = _budget/price;
            perWeight = _weightLimit/weight;
            perVolume = _volumeLimit/volume;
        }else{
            perPrice = minimum(_budget/price,(double)_max);
            perWeight = minimum(_weightLimit/weight,(double)_max);
            perVolume = minimum(_volumeLimit/volume,(double)_max);
        }
        perPriceWeight = minimum(perPrice,perWeight);
        perPriceVolume = minimum(perPrice,perVolume);
        perWeightVolume = minimum(perWeight,perVolume);
        perAll = minimum(perPrice,perWeight,perVolume);
    }
    /**
     * "Buys" one FoodItem
     * Adds to this FoodItems' toBuy count
     * Subtracts from this FoodItems' quantity
     */
    public void buyOneItem(){
        if(quantity > 0){
            toBuy++;
            quantity --;
        }else{
            System.out.println("Insufficient quantity of "+ name);
        }
    }
    /**
     * "Buys" an amount of this FoodItem
     * Adds to this FoodItems' toBuy count
     * Subtracts from this FoodItems' quantity
     */
    public void buyAmount(int _amount){
        if(quantity >=_amount){
            toBuy += _amount;
            quantity -= _amount;
        }else{
            System.out.println("Insufficient quantity of "+ name);
        }
    }
    /**
     * Compares this FoodItems to the other in the same ArrayList
     * With lowest price first 
     */
    public static Comparator<FoodItem> Price = new Comparator<FoodItem>() {
	public int compare(FoodItem fi1, FoodItem fi2) {
	   double foodItemPrice1 = fi1.perPrice;
	   double foodItemPrice2 = fi2.perPrice;
           if (foodItemPrice1 > foodItemPrice2) return -1;
           if (foodItemPrice1 < foodItemPrice2) return 1;
           return 0;
        }
    };
    /**
     * Compares this FoodItems to the other in the same ArrayList
     * With highest possible items that can be bought
     * factoring in Price and Weight
     */
    public static Comparator<FoodItem> PriceWeight = new Comparator<FoodItem>() {
	public int compare(FoodItem fi1, FoodItem fi2) {
	   double foodItemMax1 = fi1.perPriceWeight;
	   double foodItemMax2 = fi2.perPriceWeight;
           if (foodItemMax1 > foodItemMax2) return -1;
           if (foodItemMax1 < foodItemMax2) return 1;
           return 0;
        }
    };
    /**
     * Compares this FoodItems to the other in the same ArrayList
     * With highest possible items that can be bought
     * factoring in Price ,Weight and Volume
     */
    public static Comparator<FoodItem> PriceWeightVolume = new Comparator<FoodItem>() {
	public int compare(FoodItem fi1, FoodItem fi2) {
	   double foodItemMax1 = fi1.perAll;
	   double foodItemMax2 = fi2.perAll;
           if (foodItemMax1 > foodItemMax2) return -1;
           if (foodItemMax1 < foodItemMax2) return 1;
           return 0;
        }
    };
    /**
     * Throws exception if comparator is not declared
     * @param o
     * @return 
     */
    @Override
    public int compareTo(FoodItem o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Returns string containing information
     * @return string containing information
     */
    public String toString(){
        String returnString = "";
        returnString += name + "\n";
        returnString += "    quantity: "+quantity+ "\n";
        returnString += "    price: $ "+price+ "\n";
        returnString += "    weight: "+weight+ " kg\n";
        returnString += "    volume: "+volume+ " L\n";
        returnString += "    foodGroup: "+foodGroup+ "\n";
        returnString += "    toBuy: "+toBuy+ "\n";
        returnString += "    ratio per price: "+perPrice+ "\n";
        returnString += "    ratio per weight: "+perWeight+ "\n";
        returnString += "    ratio per volume: "+perVolume+ "\n";
        returnString += "    ratio per price+weight: "+perPriceWeight+ "\n";
        returnString += "    ratio per price+volume: "+perPriceVolume+ "\n";
        returnString += "    ratio per weight+volume: "+perWeightVolume+ "\n";
        returnString += "    ratio per price+weight+volume: "+perAll+ "\n";
        return returnString;
    }
}
