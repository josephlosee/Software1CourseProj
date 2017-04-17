package C482JLProj;

import java.util.ArrayList;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Part> getParts() {
        return parts;
    }

    private ArrayList<Part> parts = new ArrayList<>();

    public Inventory(){
        parts.add(new Inhouse());
        parts.add(new Outsourced());
        products.add(new Product());
        products.add(new Product());
    }

    public boolean removeProduct(int iIndex){
        return products.remove(iIndex)!=null;
    }

    public Product lookupProduct(int iIndex){
        Product lookup = null;
        try {
            lookup = products.get(iIndex);
        } catch (IndexOutOfBoundsException e){
            e.getCause().toString();
        }
        return lookup;
    }

    public void updateProduct(int iIndex){
        Product update = products.get(iIndex);
        if (update!= null){

        }
        //TODO STUB. Create and show stage
    }

    public ArrayList getProductList()
    {
        return products;
    }

    public void addProduct(Product newProd){
        products.add(newProd);
    }

    public void addPart(Part newPart){
            parts.add(newPart);
    }

    public void updatePart(int index, Part updatedPart){
        parts.set(index, updatedPart);
    }



}
