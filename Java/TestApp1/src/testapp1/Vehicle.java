/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp1;

/**
 *
 * @author hp
 */
public abstract class Vehicle {
    protected String brand;
    private String name = "privet";
    public void sound(){
        System.out.println("VOOOOOOOOOOOOON, vooon voon");
    }
    public void driverName(){
        System.out.println("Kareeeeem");
    }
    public Vehicle(){
        System.out.println("From the Vehicle class");
        this.brand = "Ford";
    }
    
    public abstract void startEngen();
}
