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

        switch (unit) {
            case "C" -> size = (int) (Math.random() * 3266.8);
            case "Bar" -> size = (int) (Math.random() * 65.336);
            case "litra" -> size = (int) (Math.random() * 65336);
            case "m3" -> size = (int) (Math.random() * 6533.6);
            default -> System.out.println("You have given a invalid unit!\n");
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

    public void setDataName(String DataName) { this.data_name = DataName; }

    public void setDataType(String DataType) { this.data_type = DataType; }

    public void setUnit(String Unit) { this.unit = Unit; }

    public void setFactor(int Factor) { this.factor = Factor; }

    public void setSize(int Size) { this.size = Size; }

    public void SetRandomSize(){
        switch (unit) {
            case "C" -> size = (int) (Math.random() * 3266.8);
            case "Bar" -> size = (int) (Math.random() * 65.336);
            case "litra" -> size = (int) (Math.random() * 65336);
            case "m3" -> size = (int) (Math.random() * 6533.6);
            default -> System.out.println("You have given a invalid unit!\n");
        }
    }

    @Override
    public String toString() {
        return "Sensor [data_name = " + data_name + ", data_type = " + data_type + ", factor = " + factor + ", size = " + size + ", unit = " + unit + "]";
    }

}
