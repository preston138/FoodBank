package foodbank;
public class FoodGroup {
    private String name;
    private int toBuyCount = 0;
    public FoodGroup(String _name){
        name = _name;
    }
    /**
     * Creates copy of this class
     * @return copy of this class
     */
    public FoodGroup getNewCopy(){
        FoodGroup newFg = new FoodGroup(name);
        newFg.toBuyCount = toBuyCount;
        return newFg;
    }
    public void setName(String _name){
        name = _name;
    }
    public String getName(){
        return name;
    }
    public void setToBuyCount(int _count){
        toBuyCount = _count;
    }
    public int getToBuyCount(){
        return toBuyCount;
    }
    /**
     * Adds one to toBuyCount
     */
    public void addOneToBuyCount(){
        toBuyCount++;
    }
    /**
     * Returns string containing name and toBuyCount
     * @return string containing name and toBuyCount
     */
    public String toString(){
        String returnString = name+": "+toBuyCount+ "\n";
        return returnString;
    }
}

