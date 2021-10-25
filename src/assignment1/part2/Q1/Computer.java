package assignment1.part2.Q1;

// -----------------------------------------------------
// Assignment 1
// © Sijie Min, Zexin Peng
// Written by: Sijie Min 40152234, Zexin Peng 40166520
// -----------------------------------------------------

public class Computer {
    private String brand;
    private String model;
    private long SN;
    private double price;
    private static int counter;
    
    public Computer() {
    	this.brand="unknow";
    	this.model="unknow";
    	this.SN=0;
    	this.price=9999;
    	counter++;
    }
    
    public Computer(String brand,String model,long SN,double price) {
        this.brand=brand;
        this.model=model;
        this.SN=SN;
        this.price=price;
        counter++;
    }
    
    public Computer(Computer other) {
    	this.brand=other.brand;
    	this.model=other.model;
    	this.SN=other.SN;
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
    public static void setNumberOfCreatedComputers(int num) {
        counter=num;
    }
    @Override
    public String toString() {
        return "Computer [brand=" + brand + ", model=" + model + ", SN=" + SN + ", price=" + price + "]";
    }
    public boolean equals(Computer c) {
        if (c == null) {
            return false;
        }
        if(this==c)
            return true;
        if(this.brand.equals(c.brand)&&
                this.model.equals(c.model)&&
                this.price==c.price) {
            return true;
        }
        return false;
    }
}