/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilestest;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "test.doc";
        File myFile = new File(fileName);
        try {
            if(myFile.createNewFile()){
                System.out.println("File crfeated!!");
            }else{
                System.out.println("File already exists");
            }
            FileWriterK fw1= new FileWriterK(fileName, "Helooooo this is the first message", 5);
            FileWriterK fw2= new FileWriterK(fileName, "Bayyyyyyyyyyy This is the second message", 10);
            WriterDeamon wd1 = new WriterDeamon("Thread-1", "Helooooo this is the first message", 15, fileName, fw1);
            WriterDeamon wd2 = new WriterDeamon("Thread-2", "Bayyyyyyyyyyy This is the second message", 15, fileName,fw2);
            
            wd1.start();
            wd2.start();
            try {
                wd1.join();
                wd2.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
