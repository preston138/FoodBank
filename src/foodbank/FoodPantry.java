package foodbank;
import java.util.ArrayList;
import java.util.Collections;
public class FoodPantry {
    private int taskToSolve;
    private double budgetStart,budget,
            cartWeightLimitStart,cartWeightLimit,
            cartVolumeLimitStart,cartVolumeLimit;
    public double balanceMargin = 0.05;
    private ArrayList<FoodItem> foodItemList = new ArrayList<FoodItem>();
    private ArrayList<FoodGroup> foodGroupList = new ArrayList<FoodGroup>();
    public void setTaskToSolve(int _tts){
        taskToSolve = _tts;
    }
    public double getTaskToSolve(){
        return taskToSolve;
    }
    public void setBudgetStart(double _budgetStart){
        budgetStart = _budgetStart;
    }
    public double getBudgetStart(){
        return budgetStart;
    }
    public void setBudget(double _budget){
        budget = _budget;
    }
    public double getBudget(){
        return budget;
    }
    public void setCartWeightLimitStart(double _cwls){
        cartWeightLimitStart = _cwls;
    }
    public double getCartWeightLimitStart(){
        return cartWeightLimitStart;
    }
    public void setCartWeightLimit(double _cwl){
        cartWeightLimit = _cwl;
    }
    public double getCartWeightLimit(){
        return cartWeightLimit;
    }
    public void setCartVolumeLimit(double _cvl){
        cartVolumeLimit = _cvl;
    }
    public double getCartVolumeLimit(){
        return cartVolumeLimit;
    }
    public void setCartVolumeLimitStart(double _cvls){
        cartVolumeLimitStart = _cvls;
    }
    public double getCartVolumeLimitStart(){
        return cartVolumeLimitStart;
    }
    public void setFoodItemList(ArrayList<FoodItem> _fil){
        foodItemList = _fil;
    }
    public ArrayList<FoodItem> getFoodItemList(){
        return foodItemList;
    }
    public void setFoodGroupList(ArrayList<FoodGroup> _fil){
        foodGroupList = _fil;
    }
    public ArrayList<FoodGroup> getFoodGroupList(){
        return foodGroupList;
    }
    /**
     * Goes through the list of FoodItems
     * and adds any new FoodGroup to
     * the list of FoodGroups
     */
    public void addFoodGroupsToList(){
        for(FoodItem fi:foodItemList){
            boolean sharesName = false;
            for(FoodGroup fg:foodGroupList){
                if(fg.getName().equals(fi.getFoodGroup())){
                    sharesName = true;
                }
            }
            if(!sharesName){
                foodGroupList.add(new FoodGroup(fi.getFoodGroup()));
            }
        }
    }
    /**
     * Gets the count of FoodItems bought 
     * that belong to that FoodItems' FoodGroup
     * @param _fii FoodItem index used in the foodItemList
     * @return 
     */
    public FoodGroup getFoodGroupOfFoodItem(int _fii){
        FoodGroup returnFG = new FoodGroup("");
        FoodItem fi = foodItemList.get(_fii);
        for(FoodGroup fg:foodGroupList){
            if(fg.getName().equals(fi.getFoodGroup())){
                returnFG = fg.getNewCopy();
            }
        }
        return returnFG;
    }
    /**
     * Adds one to the count of FoodItems bought in
     * that FoodGroup
     * @param _fi 
     */
    private void addToFoodGroupToBuyCount(FoodItem _fi){
        for(FoodGroup fg:foodGroupList){
            if(fg.getName().equals(_fi.getFoodGroup())){
                fg.addOneToBuyCount();
                
            }
        }
    }
    /**
     * Creates copy of this class
     * @return copy of this class
     */
    public FoodPantry getNewCopy(){
        FoodPantry newFp = new FoodPantry();
        newFp.budgetStart = budgetStart;
        newFp.budget =  budget;
        newFp.cartWeightLimitStart = cartWeightLimitStart;
        newFp.cartWeightLimit = cartWeightLimit;
        newFp.cartVolumeLimitStart = cartVolumeLimitStart;
        newFp.cartVolumeLimit = cartVolumeLimit;
        for(FoodItem fi:foodItemList){
            newFp.foodItemList.add(fi.getNewCopy());
        }
        for(FoodGroup fg:foodGroupList){
            newFp.foodGroupList.add(fg.getNewCopy());
        }
        return newFp;
    }
    ///
    /**
    * Determines if foodItem can be bought given the parameters
    * @param _fii FoodItem index for FoodItemList
    * @param _budget budget is a factor
    * @param _weight cart weight limit is a factor
    * @param _volume cart volume limit is a factor
    */
    public boolean canBuyItem(int _fii,boolean _budget,boolean _weight, boolean _volume){
        FoodItem _fi = foodItemList.get(_fii);
        boolean bool1 = _fi.getQuantity() > 0;
        boolean bool2 = !_budget || _fi.getPrice()<= budget;
        boolean bool3 = !_weight || _fi.getWeight()<= cartWeightLimit;
        boolean bool4 =  !_volume || _fi.getVolume()<= cartVolumeLimit;
        return bool1 && bool2 && bool3 && bool4;
    }
    /**
    * Buys one item and adjusts
    * FoodBank variables
    * @param _fi FoodItem to purchase
    */
    public void buyItem(int _fii){
        FoodItem fi = foodItemList.get(_fii);
        fi.buyOneItem();
        budget -= fi.getPrice();
        cartWeightLimit -= fi.getWeight();
        cartVolumeLimit -= fi.getVolume();
        addToFoodGroupToBuyCount(fi);
    }
    /**
     * Calculates the ratios of all FoodItems in the foodItemList
     * @param _max 
     */
    public void calcRatiosForFoodItems(int _max){
        for(FoodItem fi: foodItemList){
            fi.calcRatios(budget, cartWeightLimit, cartVolumeLimit,_max);
	 }
    }
    /**
     * Rearranges the list of FoodItems by Price- lowest first
     * quantity is factored in
     */
    public void sortFoodItemsByPrice(){
        Collections.sort(foodItemList,FoodItem.Price);
    }
    /**
     * Rearranges the list of FoodItems by Price and Weight
     * The foodItems with more available amounts are at the top of the list
     * Based on the relationship between the item price to budget
     * and the item weight to care weight limit
     * quantity is factored in
     */
    public void sortFoodItemsByPriceWeight(){
        Collections.sort(foodItemList,FoodItem.PriceWeight);
    }
    /**
     * Rearranges the list of FoodItems by Price,Weight and Volume
     * he foodItems with more available amounts are at the top of the list
     * Based on the relationship between the item price to budget
     * and the item weight to cart weight limit
     * and the item volume to cart volume limit
     * quantity is factored in
     */
    public void sortFoodItemsByPriceWeightVolume(){
        Collections.sort(foodItemList,FoodItem.PriceWeightVolume);
    }
    /**
     * Returns string containing information
     * @return string containing information
     */
    public String toString(){
        String returnString = "FOOD PANTRY: \n\n";
        for(FoodItem fi: foodItemList){
			returnString += fi.toString();
	}
        returnString += "\n";
        for(FoodGroup fg: foodGroupList){
			returnString += fg.toString();
	}
        returnString += "\navailable budget: "+budget + "\n";
        returnString += "weight remaining: "+cartWeightLimit+" kg out of "+cartWeightLimitStart + " kg\n";
        returnString += "volume remaining: "+cartVolumeLimit+" L out of "+cartVolumeLimitStart+  " L\n";
        return returnString;
    }
    /**
    * Constructs a string that lists the FoodItems to buy
    * along with their amounts in the format:
    * <item-name>, <quantity-to-buy>
    */
    public String toOutputString(){
        String returnString = "";
        for(FoodItem fi : foodItemList){
            returnString += fi.getName() + ", "+fi.getToBuy()+"\n";
        }
        return returnString;
    }
}
