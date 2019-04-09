/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilestest;

/**
 *
 * @author hp
 */
public class WriterDeamon extends Thread{
    Thread t;
    String threadName;
    String message;
    int repeats;
    String file;
    FileWriterK fw;
    WriterDeamon(String name, String msg, int r, String file, FileWriterK fw){
        this.threadName = name;
        this.message = name + " : " +msg + "\n";
        this.repeats = r;
        this.file = file;
        this.fw = fw;
    }
    
    public void run(){
        synchronized(fw){
            fw.doWrite();
        }
    }
    
    public void start(){
        System.out.println("Starting " +  threadName );
        if(t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
