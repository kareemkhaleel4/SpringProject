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
/*public class Watcher extends Thread{
    ArrayList<Demon> Demons = new ArrayList<Demon>();
    Thread t;
    public Watcher(ArrayList<Demon> demons){
        this.Demons = demons;
    }

    public void run(){
        System.out.println("Start this thread ");
        boolean isStillAlive = true;
        while(isStillAlive){
            boolean dead = true;
            for(Demon d: Demons){
                if(d.isAlive()){
                    dead = false;
                    System.out.println("Thread " + d.getName()+ "is still alive");
                }else{
                    System.out.println("Thread " + d.getName() + " is dead");
                }
            }
            if(dead == true)
                isStillAlive = false;
            try {
                Thread.sleep(11500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Watcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Exiting watcher");
    }
    
    public void start(){
        if(t == null){
            t = new Thread(this, "Watcher Tread");
            t.start();
        }
    }
}
*/

class PrintDemo {
   public void printCount() {
      try {
         for(int i = 5; i > 0; i--) {
            System.out.println("Counter   ---   "  + i );
         }
      }catch (Exception e) {
         System.out.println("Thread  interrupted.");
      }
   }
}