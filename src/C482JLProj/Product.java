package C482JLProj;

import com.sun.javafx.binding.StringFormatter;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public class Product{
    private ArrayList<Part> parts = new ArrayList<>();
    private int productID = -2; //flag value
    private int instock=0, min=0, max=1;
    private double price;
    private static int nextProdID= -1;
    private String name ="Placeholder";

    public Product(){

    }

    //Used for testing
    public Product(String name){
        try {
            this.setName(name);
        }catch(Exception e){

        }
    }

    //UML required accessor/mutator methods:
    public String getName() {        return name;    }

    public void setName(String name) throws Exception {
        if (name== null || name.trim().equals("")){
            throw new Exception("Name cannot be blank");
        }
        else{
            this.name = name;
            prodNameProp.set(name);
        }
    }

    public int getInstock() {        return instock;    }

    public void setInstock(int iNewStock) throws Exception {
        if (iNewStock < min){
            throw new Exception("Inventory cannot be below the minimum amount.");
        }
        else if (iNewStock > max){
            throw new Exception("Inventory cannot exceed the maximum amount.");
        }
        else{
            this.instock=iNewStock;
            prodInvProp.set(""+iNewStock);
        }
    }

    public int getMin() {        return min;    }

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

    public int getMax() {        return max;    }


    public void setMax(int iNewMax) throws Exception{
        if (iNewMax < this.min){
            throw new Exception("Maximum ("+iNewMax+") cannot be less than the minimum("+this.min+ ").");
        }
        else{
            max = iNewMax;
        }
    }

    //DONE
    public double getPrice() {        return price;    }

    //DONE
    public void setPrice(double dNewPrice) throws Exception{
        double partsPriceSum = 0;
        for (Part p:parts){
            partsPriceSum+=p.getPrice();
        }
        if (partsPriceSum > dNewPrice){
            String excMessage = "Product price cannot be less than the sum of parts $"+ String.format("%.2f", partsPriceSum);
            throw new Exception(excMessage);
        }
        else{
            this.price=dNewPrice;
            this.prodPriceProp.set("$"+String.format("%.2f", dNewPrice));
        }
    }

    public static int getNextProdID() {
        nextProdID++;
        return nextProdID;
    }

    public void addPart(Part newPart){parts.add(newPart);}

    /**
     * Removes the part at the indicated index
     * @param iPartIndex
     * @return if the part was removed successfully
     */
    public boolean removePart(int iPartIndex)
    {
        Part removed = null;
        try {
            removed = parts.remove(iPartIndex);
        }catch (IndexOutOfBoundsException e){
            System.out.println("removePart index "+iPartIndex+ "out of bounds, returned null");
        }
        return removed != null;
    }

    /**
     * Returns a part at the indicated index
     * @param iLookup
     * @return reference to the part if found
     */
    public Part lookupPart(int iLookup){
        Part retPart = null;
        try {
            retPart = parts.get(iLookup);

        }catch (IndexOutOfBoundsException e){
            System.out.println("lookupPart index "+iLookup+ "out of bounds, returned null");
        }
        return retPart;
    }

    public void updatePart(int iPartIndex){
        Part updatePart = lookupPart(iPartIndex);

        //TODO: STUB // create then showAndWait modify part stage for selected index
    }

    public ArrayList<Part> getPartsList(){
        return this.parts;
    }

    public void setProductID(int iNewID){
        productID=iNewID;
        prodIDProp.set(""+iNewID);
    }

    public int getProductID(){return productID;}

    @Override public boolean equals(Object o){
        boolean result = false;
        if (o instanceof Product){
            result = this.getProductID()==((Product)o).getProductID();
        }
        return result;
    }

    @Override public int hashCode(){
        return (int) (name.hashCode()*productID+price*(100));
    }

    //StringProperties for the cell factory:
    private final SimpleStringProperty prodNameProp = new SimpleStringProperty(this.getName());
    private final SimpleStringProperty prodPriceProp = new SimpleStringProperty("$"+String.format("%.2f", this.getPrice()));
    private final SimpleStringProperty prodIDProp = new SimpleStringProperty(""+this.getProductID());
    private final SimpleStringProperty prodInvProp = new SimpleStringProperty(""+this.getInstock());

    //StringProperty methods for cell factories
    public String getProdNameProp() {        return prodNameProp.get();    }
    public SimpleStringProperty prodNamePropProperty() {        return prodNameProp;    }
    public String getProdPriceProp() {        return prodPriceProp.get();    }
    public SimpleStringProperty prodPricePropProperty() {        return prodPriceProp;    }
    public String getProdIDProp() {        return prodIDProp.get();    }
    public SimpleStringProperty partIDPropProperty() {              return prodIDProp;    }
    public String getProdInvProp() {return prodInvProp.get();    }
    public SimpleStringProperty prodInvPropProperty() {return prodInvProp;    }
}
