package assignment1.part2.Q1;

public class Computer {
    private String brand;
    private String model;
    private long SN;
    private double price;
    private static int counter;
    public Computer(String brand,String model,long SN,double price) {
        this.brand=brand;
        this.model=model;
        this.SN=SN;
        this.price=price;
        counter++;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand=brand;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public long getSN() {
        return SN;
    }
    public void setSN(long sN) {
        SN = sN;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public static int findNumberOfCreatedComputers() {
        return counter;
    }
    @Override
    public String toString() {
        return "Computer [brand=" + brand + ", model=" + model + ", SN=" + SN + ", price=" + price + "]";
    }
    public boolean equals(Computer c) {
        if(this==c)
            return true;
        else if(this.brand.equals(c.brand)&&
                this.model.equals(c.model)&&
                this.price==c.price)
            return true;
        else
            return false;
    }
}