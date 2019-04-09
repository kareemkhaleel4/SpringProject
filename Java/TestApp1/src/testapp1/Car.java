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
public class Car extends Vehicle{

    @Override
    public void driverName(){
        System.out.println("Khaleeeeeeel");
    }
    public void whatDoWeHave(){
        System.out.println("Do we have a brand? " + brand);
        //System.out.println("Do we have a name? "  + name);
        sound();

    }
    public void startEngen(){
    
    }
    public Car(){
        System.out.println("From the Car class");
    }
    public void sound(){
        System.out.println("LOLOLOLOLOLOLO");
    }
    public void sound2(){
        super.sound();
    }

}
