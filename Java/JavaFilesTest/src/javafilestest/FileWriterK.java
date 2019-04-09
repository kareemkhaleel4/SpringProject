/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilestest;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hp
 */
public class FileWriterK {
    String myFile;
    String message;
    int repeats;
    public FileWriterK(String f, String m, int r){
        this.myFile = f;
        this.message = m;
        this.repeats = r;

    }
    
    public void doWrite(){
//        System.out.println("Start writing the following message to the file:\n" + message);
        try {
            FileWriter fw = new FileWriter(myFile, true);
            //message += "\n";
            for(int i = 0; i< repeats; i++){
                System.out.println(i + message);
            }
            //fw.write(message);
           // fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileWriterK.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
