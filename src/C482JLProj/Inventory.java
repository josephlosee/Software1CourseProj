package C482JLProj;

import java.util.ArrayList;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public class Inventory {
    private ArrayList<Product> products;

    public boolean removeProduct(int iIndex){return products.remove(iIndex)!=null;}
    public Product lookupProduct(int iIndex){return products.get(iIndex);}
    public void updateProduct(int iIndex){
        //TODO STUB0
    }

}
