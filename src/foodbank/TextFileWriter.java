/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodbank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author prestonhaynes
 */
public class TextFileWriter {
    /**
     * Creates a text file called output.txt that contains
     * the parameter String
     * @param String to be put into output.txt file
     */
    public void writeToFile(String _string){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
             URL location = TextFileReader.class.getProtectionDomain().getCodeSource().getLocation();
            String _loc = location.getFile().substring(0, location.getFile().lastIndexOf("/")+1);
            fw = new FileWriter(_loc+"output.txt");
            bw = new BufferedWriter(fw);
            bw.write(_string);
            System.out.println("Done");
            } catch (IOException e) {
                    e.printStackTrace();
            } finally {
                    try {
                        if (bw != null)
                                bw.close();
                        if (fw != null)
                                fw.close();
                    } catch (IOException ex) {
                            ex.printStackTrace();
                    }
            }
    }
    /**
     * Gets current directory of .jar file to write output.txt;
     * @return current directory of .jar file to write output.txt;
     */
    private static String getCurrentDir(){
        URL location = TextFileReader.class.getProtectionDomain().getCodeSource().getLocation();
        String _loc = location.getFile().substring(0, location.getFile().lastIndexOf("/")+1);
        return _loc;
    }
}
