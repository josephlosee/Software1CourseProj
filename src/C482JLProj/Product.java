package C482JLProj;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public class Product{
    private ArrayList<Part> parts;
    private int productID;
    private int instock, min=0, max=1;
    private double price;
    private static int nextProdID= -1;
    private String name = "No name set";

    //UML required accessor/mutator methods:
    public String getName() {        return name;    }

    public void setName(String name) {
        this.name = name;
        prodNameProp.set(name);
    }

    public int getInstock() {        return instock;    }

    public void setInstock(int instock) {
        this.instock = instock;
        prodInvProp.set(""+instock);
    }

    public int getMin() {        return min;    }

    public void setMin(int min) {        this.min = min;    }

    public int getMax() {        return max;    }

    public void setMax(int max) {        this.max = max;    }

    public double getPrice() {        return price;    }

    public void setPrice(double price) { this.price = price;    }

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

    //StringProperties for the cell factory:
    private final SimpleStringProperty prodNameProp = new SimpleStringProperty(this.getName());
    private final SimpleStringProperty prodPriceProp = new SimpleStringProperty("$"+this.getPrice());
    private final SimpleStringProperty prodIDProp = new SimpleStringProperty(""+this.getProductID());
    private final SimpleStringProperty prodInvProp = new SimpleStringProperty(""+this.getInstock());

    //StringProperty methods for cell factories
    public String getProdNameProp() {        return prodNameProp.get();    }
    public SimpleStringProperty prodNamePropProperty() {        return prodNameProp;    }
    public String getProdPriceProp() {        return prodPriceProp.get();    }
    public SimpleStringProperty prodPricePropProperty() {        return prodPriceProp;    }
    public String getProdIDProp() {        return prodIDProp.get();    }
    public SimpleStringProperty partIDPropProperty() {              return prodIDProp;    }
    public String getPartInvProp() {return prodInvProp.get();    }
    public SimpleStringProperty partInvPropProperty() {return prodInvProp;    }
}
