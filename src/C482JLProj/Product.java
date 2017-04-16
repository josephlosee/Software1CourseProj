package C482JLProj;

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

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getNextProdID() {
        nextProdID++;
        return nextProdID;
    }

    public void addPart(Part newPart){parts.add(newPart);}

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
        //eturn null;
        //TODO: STUB
    }

    public void setProductID(int iNewID){productID=iNewID;}
    public int getProductID(){return productID;}

}
