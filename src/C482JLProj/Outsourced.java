package C482JLProj;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public class Outsourced extends Part {
    private String companyName;

    public Outsourced(){

    }

    public Outsourced(int partID, String partName, double price, int stock, int min, int max, String companyName ) throws Exception{
        if (min > max){
            throw new Exception("Min cannot be greater than max.");
        }
        if (max < min){
            throw new Exception("Max cannot be less than min");
        }
        setMin(min);
        setMax(max);
        setPartID(partID);
        setPrice(price);
        setName(partName);

        try {
            setInstock(stock);
        } catch (Exception e){
            throw e;
        }
        setCompanyName(companyName);

    }
    public void setCompanyName(String newName) {companyName=newName;}
    public String getCompanyName(){return companyName;}
}
