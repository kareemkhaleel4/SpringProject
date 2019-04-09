/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadtest;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hp
 */
/*public class MainClass {

    /**
     * @param args the command line arguments
    /
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Random r = new Random();
        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        ArrayList<Demon> threadArray = new ArrayList<Demon>();
        int low = 10, high = 100;
       
        int numOfArrays = 10;//r.nextInt(15);
        for(int i = 0; i < numOfArrays; i++){
            ArrayList<Integer> aTemp = new ArrayList<Integer>();
            int arraySize = r.nextInt(10);
            for(int j = 0; j < arraySize; j++){
                int num = r.nextInt(high-low) + low;
                aTemp.add(num);
            }
            System.out.println("Array " + i + " Value is " + aTemp);
            a.add(aTemp);
            Demon obj = new Demon("Thread-" + i, aTemp);
            threadArray.add(obj);
        }
        Watcher w = new Watcher(threadArray);
//        w.start();
        for(Demon d : threadArray){
            d.start();
            d.join();
        }

        System.out.println("1111111111111111111111");

        for(Demon d : threadArray){
            if(d.isAlive()){
                System.out.println("Thread " + d.getName() + " is still alive");  
            }
        }
        
        System.out.println("22222222222222222");
    }
    
}*/
public class MainClass {

   public static void main(String args[]) {
      PrintDemo PD = new PrintDemo();

      ThreadDemo T1 = new ThreadDemo( "Thread - 1 ", PD );
      ThreadDemo T2 = new ThreadDemo( "Thread - 2 ", PD );

      T1.start();
      T2.start();

      // wait for threads to end
      try {
         T1.join();
         T2.join();
      }catch( Exception e) {
         System.out.println("Interrupted");
      }
   }
}
