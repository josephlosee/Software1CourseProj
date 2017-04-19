package C482JLProj;

import javafx.beans.property.SimpleStringProperty;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public abstract class Part {
    private String name = "Placeholder";
    private int partID, instock=1, min=0, max=4;
    private double price =0;
    private static int nextPartID= -1;

    public Part(){
        name="GenericPart";
        //this.setPartID(0);
        this.setPrice(1.6649);
    }

    //StringProperties for the cell factory:
    private final SimpleStringProperty partNameProp = new SimpleStringProperty(this.getName());
    private final SimpleStringProperty partPriceProp = new SimpleStringProperty("$"+String.format("%.2f", this.getPrice()));
    private final SimpleStringProperty partIDProp = new SimpleStringProperty(""+this.getPartID());
    private final SimpleStringProperty partInvProp = new SimpleStringProperty(""+this.getInstock());

    public SimpleStringProperty partNamePropProperty() {
        return partNameProp;
    }
    public SimpleStringProperty partPricePropProperty() {
        return partPriceProp;
    }
    public SimpleStringProperty partIDPropProperty() {
        return partIDProp;
    }
    public SimpleStringProperty partInvPropProperty() {
        return partInvProp;
    }



    public static int getNextPartID()
    {
        nextPartID++;
        return nextPartID;
    }

    public String getName() {return name;}
    public void setName(String sNewName) throws Exception{
        if (sNewName.trim().isEmpty()){
            throw new Exception("Part name cannot be blank.");
        }
        name = sNewName;
        partNameProp.set(sNewName);
    }

    public double getPrice(){return price;}
    public void setPrice(double dNewPrice){
        price = dNewPrice;
        partPriceProp.set("$"+String.format("%1.2f", dNewPrice));
    }

    public int getInstock(){return instock;}

    public void setInstock(int iNewStock) throws Exception {
            if (iNewStock < min){
                throw new Exception("Inventory cannot be below the minimum amount.");
            }
            else if (iNewStock > max){
                throw new Exception("Inventory cannot exceed the maximum amount.");
            }
            else{
                this.instock=iNewStock;
                partInvProp.set(""+iNewStock);
            }
    }

    public int getPartID(){return partID;}
    public void setPartID(int iNewID){partID=iNewID; partIDProp.set(""+iNewID);}

    /**
     *
     * @return
     */
    public int getMin()    {        return min;    }
    /**
     *
     * @param iNewMin
     */
    public void setMin(int iNewMin) throws Exception   {
        if (iNewMin < this.max){
            this.min = iNewMin;
        }
        else {
            throw new Exception("Minimum ("+iNewMin+") cannot be greater than the maximum("+this.max+ ").");
        }

    }

    /**
     *
     * @return
     */
    public int getMax()    {        return max;    }

    /**
     *
     * @param iNewMax
     */
    public void setMax(int iNewMax) throws Exception{
        if (iNewMax < this.min){
            throw new Exception("Maximum ("+iNewMax+") cannot be less than the minimum("+this.min+ ").");
        }
        else{
            max = iNewMax;
        }
    }

    @Override public boolean equals(Object o){
        boolean result = false;
        if (o instanceof Part){
            result = this.getPartID()==((Part) o).getPartID();
        }

        return result;
    }

    @Override public int hashCode(){
        return (int) (name.hashCode()*partID+price*100);
    }
}
