package com.ile.voda;

public class Sensor {
    private String data_name;
    private String data_type;
    private int factor;
    private int size;
    private String unit;

    public Sensor(){

    }

    public Sensor(String data_name,String data_type,int factor,String unit){
        this.data_name = data_name;
        this.data_type = data_type;
        this.factor = factor;
        this.unit = unit;

        switch(unit){
            case "C":
                size = (int) (Math.random() *  3266.8);
                break;
            case "Bar":
                size = (int) (Math.random() *  65.336);
                break;
            case "litra":
                size = (int) (Math.random() *  65336);
                break;
            case "m3":
                size = (int) (Math.random() *   6533.6);
                break;
            default:
                System.out.println("You have given a invalid unit!\n");
                break;
        }
    }

    public String getDataName(){
        return data_name;
    }

    public String getDataType(){
        return data_type;
    }

    public String getUnit(){
        return unit;
    }

    public int getFactor(){
        return factor;
    }

    public int getSize(){
        return size;
    }

    public void SetRandomSize(){
        switch(unit){
            case "C":
                size = (int) (Math.random() *  3266.8);
                break;
            case "Bar":
                size = (int) (Math.random() *  65.336);
                break;
            case "litra":
                size = (int) (Math.random() *  65336);
                break;
            case "m3":
                size = (int) (Math.random() *   6533.6);
                break;
            default:
                System.out.println("You have given a invalid unit!\n");
                break;
        }
    }

    @Override
    public String toString() {
        return "Sensor [Ime_Podatka=" + data_name + ", Tip_Podatka=" + data_type + ", faktor=" + factor + ", velicina=" + size + ", jedinica=" + unit + "]";
    }

}
