package C482JLProj;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public class Inhouse extends Part{
    private int machineID;

    public Inhouse(){

    }

    public Inhouse(int partID, String partName, double price, int stock, int min, int max, int machineID ) throws Exception{
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
        setMachineID(machineID);

    }

    public void setMachineID(int iNewMachineID){machineID=iNewMachineID;}
    public int getMachineID(){return machineID;}

}
