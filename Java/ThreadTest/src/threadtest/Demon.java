/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadtest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
/*
public class Demon extends Thread{
    private Thread t;
    private String name ;
    private ArrayList<Integer> numArray= new ArrayList<Integer>();
    public Demon(){
        System.out.println("Creating a new thread!");
    }
    public Demon(String tName){
        this.name = tName;
        //System.out.println("Creating new thread with the naem " + name);
    }
    public Demon(String tName, ArrayList<Integer> a){
        this.name = tName;
        this.numArray = a;
    }
    @Override
    public void run() {
        //System.out.println("Running thread " + name
        synchronized(numArray){
        for(Integer i : numArray){
            System.out.println("" + name + " value is " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Demon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        System.out.println("Thread " +  name + " exiting.");

    }
    public void start(){
        if(t == null){
            t = new Thread(this, name);
            t.start();
        }
    }
    
}*/
class ThreadDemo extends Thread {
   private Thread t;
   private String threadName;
   PrintDemo  PD;

   ThreadDemo( String name,  PrintDemo pd) {
      threadName = name;
      PD = pd;
   }
   
   public void run() {
      synchronized(PD) {
         PD.printCount();
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }

   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

