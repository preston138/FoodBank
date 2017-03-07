package foodbank;
import foodbank.FoodBank.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
public class TextFileReader {
    /**
    * Extracts food bank information from Scanner
    * from input.txt file
    * and returns FoodPantry
    */
    public static FoodPantry foodPantryFromFile() throws IOException{
        Scanner s = null;
        FoodPantry rfp = new FoodPantry();
        try {
            s = new Scanner(new BufferedReader(new FileReader(getCurrentDir()+"input.txt")));
            if(s.hasNextLine()){
                rfp.setTaskToSolve(Integer.parseInt(getNextFromScanner(s)));
                double bs = Float.parseFloat(getNextFromScanner(s));
                rfp.setBudgetStart(bs);
                rfp.setBudget(bs);
                double cwls = Float.parseFloat(getNextFromScanner(s));
                rfp.setCartWeightLimitStart(cwls);
                rfp.setCartWeightLimit(cwls);
                double cvls = Float.parseFloat(getNextFromScanner(s));
                rfp.setCartVolumeLimitStart(cvls);
                rfp.setCartVolumeLimit(cvls);
            }
            while (s.hasNextLine()) {
                FoodItem newFI = new FoodItem();
                newFI.setName(getNextFromScanner(s));
                int _q = Integer.parseInt(getNextFromScanner(s));
                newFI.setQuantityStart(_q);
                newFI.setQuantity(_q);
                newFI.setPrice(Float.parseFloat(getNextFromScanner(s)));
                newFI.setWeight(Float.parseFloat(getNextFromScanner(s)));
                newFI.setVolume(Float.parseFloat(getNextFromScanner(s)));
                newFI.setFoodGroup(getNextFromScanner(s));
                rfp.getFoodItemList().add(newFI);
            }
            rfp.addFoodGroupsToList();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return rfp;
    }
    /**
    * Reads next item from input.txt file
    * and subtracts spaces,commas and semicolons
    * @param _s Scanner for input.txt
    */
    private static String getNextFromScanner(Scanner _s){
        return _s.next().replaceAll("\\s*,\\s*","").replaceAll("\\s*;\\s*","");
    }
    /**
     * Gets current directory of .jar file to read input.txt;
     * @return 
     */
    private static String getCurrentDir(){
        URL location = TextFileReader.class.getProtectionDomain().getCodeSource().getLocation();
        String _returnLocation = location.getFile().substring(0, location.getFile().lastIndexOf("/")+1);
        return _returnLocation;
    }
}
