package C482JLProj;

import java.util.ArrayList;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public class Product extends Part{
    private ArrayList<Part> parts;
    private int productID;

    public void addPart(Part newPart){parts.add(newPart);}
    public boolean removePart(int iPartIndex/*Maybe? TODO: ensure this is correct*/)
    {
        Part test = parts.remove(iPartIndex);
        return test != null;
    }

    public Part lookupPart(int iLookup){
        return null;
        //TODO: STUB
    }

    public Part updatePart(int iPartIndex){
        return null;
        //TODO: STUB
    }

    public void setProductID(int iNewID){productID=iNewID;}
    public int getProductID(){return productID;}

}
