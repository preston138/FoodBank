package foodbank;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
/**
 *
 * @author Preston Haynes
 */
public class FoodBank {
    public static FoodPantry fp = new FoodPantry();
    public String output;
    /**
     * Creates an instance of this FoodBank class
     * reads text file containing the FoodItems
     * calculates how many FoodItems to buy
     * writes results to text file
     * @param args
     * @throws IOException 
     */
    public static void main(String []args) throws IOException {
        FoodBank fb = new FoodBank();
        TextFileReader tfr = new TextFileReader();
        TextFileWriter tfw = new TextFileWriter();
        fb.fp = tfr.foodPantryFromFile();
        fb.solveTask();
        //System.out.println(fb.fp.toString());
        //System.out.println(fb.fp.toOutputString());
        tfw.writeToFile(fb.fp.toOutputString());
    }
     /**
    * Switch statement directing to proper method to solve task
    */
    private void solveTask(){
        switch((int)fp.getTaskToSolve()){
            case 1:
                solveTask1();
                break;
            case 2:
                solveTask2();
                break;
            case 3:
                solveTask3();
                break;
            case 4:
                solveTask4();
                break;
            default:
                System.out.println("Task number out of range");
        }
    }
     /**
    * Determines if one of each type of FoodItem in the Arraylist of FoodItems can be bought
    * given the parameters
    * Used when balancing the amount of FoodItems per FoodGroup
    * @param _l count so far of items bought per foodGroup
    * @param _budget budget is a factor
    * @param _weight cart weight limit is a factor
    * @param _volume cart volume limit is a factor
    * @return true if can buy one more per foodGroup
    */
    
    private boolean canBuyNextRoundForFoodGroup(int _l,boolean _budget,boolean _weight,boolean _volume){
        boolean returnBool = true;
        FoodPantry tempFp = fp.getNewCopy();
        for(int i = 0;i < tempFp.getFoodItemList().size();i++){
            FoodGroup fg = tempFp.getFoodGroupOfFoodItem(i);
            if( returnBool && tempFp.canBuyItem(i, _budget, _weight, _volume) && fg.getToBuyCount()==_l){
               tempFp.buyItem(i);
                
            }
        }
        
        for(FoodGroup _fg:tempFp.getFoodGroupList()){
            if(_fg.getToBuyCount()<_l){
                returnBool = false;
            }
            
        }
        return returnBool;
    }
     /**
    * Buys one of each type of FoodItem in the Arraylist of FoodItems
    * given the parameters
    * Used when balancing the amount of FoodItems per FoodGroup
    * @param _budget budget is a factor
    * @param _weight cart weight limit is a factor
    * @param _volume cart volume limit is a factor
    */
    private void buyNextRoundForFoodGroup(int _l,boolean _budget,boolean _weight,boolean _volume){
        for(int i = 0;i < fp.getFoodItemList().size();i++){
            FoodGroup fg = fp.getFoodGroupOfFoodItem(i);
            if(fp.canBuyItem(i, _budget, _weight, _volume) && fg.getToBuyCount()==_l){
                fp.buyItem(i);
            }
        }
    }
    /**
     * Solves the task of buying the greatest number of items
     * Method sorts the list of FoodItems by lowest price first
     * then buys as many FoodItems as possible
     * when FoodItem is out of stock the list is sorted again
     */
    private void solveTask1(){
        int fii = 0;
        fp.calcRatiosForFoodItems(-1);
        fp.sortFoodItemsByPrice();
        while(fii<fp.getFoodItemList().size()-1){
            if(fp.canBuyItem(fii ,true,false,false)){
               fp.buyItem(fii);
            }else{
                fii++;
            }
        }
    }
    /**
     * Solves the task of buying the greatest number of items
     * while not exceeding the care weight limit
     * Method sorts the list of FoodItems by the highest
     * ratio of items that can be bought factoring the price and weight
     * then buys as many FoodItems as possible
     * when FoodItem is out of stock the list is sorted again
     */
    private void solveTask2(){
        int fii = 0;
        fp.calcRatiosForFoodItems(-1);
        fp.sortFoodItemsByPriceWeight();
        while(fii<fp.getFoodItemList().size()-1){
            if(fp.canBuyItem(fii ,true,true,false)){
               fp.buyItem(fii);
            }else{
                fii++;
            }
        }
    }
    /**
     * Solves the task of buying the greatest number of items
     * while not exceeding the care weight limit
     * and maintaining a balance between FoodGroups within a 5% margin 
     * Method sorts the list of FoodItems by the highest
     * ratio of items that can be bought factoring the price and weight
     * then it tests if one item can be bought per FoodGroup
     * Finally the method buys as many FoodItems with out going over the margin
     */
    private void solveTask3(){
        int lowest = 0;//lowest item amount based on percent balance
        int balanceMax = 0;//
        int balanceMaxMore = 0;
        int fii = 0;//FoodItem index
        fp.calcRatiosForFoodItems(-1);
        fp.sortFoodItemsByPriceWeight();
        while(canBuyNextRoundForFoodGroup(lowest,true,true,false)){
            buyNextRoundForFoodGroup(lowest,true,true,false);
            lowest++;
        }
        balanceMaxMore = (int) Math.floor(lowest*fp.getFoodGroupList().size()*fp.balanceMargin*2);
        balanceMax = lowest + balanceMaxMore;
        if(balanceMaxMore > 0){
            fp.calcRatiosForFoodItems(balanceMax);
            fp.sortFoodItemsByPriceWeight();
            while(fp.canBuyItem(fii,true,true,false)){
                int fgc = fp.getFoodGroupOfFoodItem(fii).getToBuyCount();
                if(fp.getFoodItemList().get(fii).getToBuy()<balanceMax && fgc<balanceMax && fii<fp.getFoodItemList().size()-1){
                    fp.buyItem(fii);
                    fp.calcRatiosForFoodItems(balanceMax);
                    if(!fp.canBuyItem(fii,true,true,false)){
                        fp.sortFoodItemsByPriceWeight();
                    }
                }else{
                    fii++;
                }
            }
        }
    }
    
    /**
     * Solves the task of buying the greatest number of items
     * while not exceeding the care weight limit
     * and not exceeding the care volume limit
     * Method sorts the list of FoodItems by the highest
     * ratio of items that can be bought factoring the price,weight and volume
     * then buys as many FoodItems as possible
     * when FoodItem is out of stock the list is sorted again
     */
    private void solveTask4(){
        int fii = 0;
        fp.calcRatiosForFoodItems(-1);
        fp.sortFoodItemsByPriceWeightVolume();
        while(fii<fp.getFoodItemList().size()-1){
            if(fp.canBuyItem(fii ,true,true,true)){
               fp.buyItem(fii);
            }else{
                fii++;
            }
        }
    }
}
