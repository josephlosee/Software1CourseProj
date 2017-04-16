package C482JLProj;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public abstract class Part {
    private String name;
    private int partID, instock, min=0, max=1;
    private double price;
    private static int nextPartID= -1;

    public static int getNextPartID()
    {
        nextPartID++;
        return nextPartID;
    }

    public String getName() {return name;}
    public void setName(String sNewName) {name = sNewName;}

    public double getPrice(){return price;}
    public void setPrice(double dNewPrice){price = dNewPrice;}

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
            }
    }

    public int getPartID(){return partID;}
    public void setPartID(int iNewID){partID=iNewID;}

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
        if (iNewMin > this.max){
            this.min = iNewMin;
        }
        else {
            throw new Exception("Minimum cannot be great than the maximum.");
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
            throw new Exception("Maximum cannot exceed minimum");
        }
        else{
            max = iNewMax;
        }

    }
}
