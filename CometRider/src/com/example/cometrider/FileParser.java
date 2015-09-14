package com.example.cometrider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author devang304
 */
public class FileParser
{
    
    static ArrayList<String> McC = new ArrayList<String>();
    static ArrayList<String> UTD = new ArrayList<String>();
    static ArrayList<String> PGT = new ArrayList<String>();
    
    public static void main(String[] args) throws IOException {
        try 
        {
            File file = new File("times.txt");
            if(!file.exists())
            {
                System.out.println("File does not exist");
                System.exit(0);
            }
                    
            Scanner scanner = new Scanner(file);
            StringTokenizer st;
            String current;    
            String temp;
            current = scanner.nextLine();
            System.out.println(current);
            int i = 0;
            while(scanner.hasNext())
            {
                current = scanner.nextLine();
                if(!current.trim().isEmpty() && !current.startsWith("DICKERSON"))
                {
                    st = new StringTokenizer(current);
                
                    while(st.hasMoreTokens())
                    {
                        
                        temp = st.nextToken();
                        if(i%3 == 0)
                        {
                            McC.add(temp);
                        }
                        else if(i%3 == 1)
                        {
                            UTD.add(temp);
                        }
                        else if(i%3 == 2)
                        {
                            PGT.add(temp);
                        }
                        i++;
                        System.out.print(temp + " \t\t\t\t");
                    }
                    

                    System.out.println("");
                }
                
            }
            
            saveUrl("downloaded.txt", "https://dl.dropboxusercontent.com/s/04s8domhisuuran/times.txt?dl=1&amp;token_hash=AAGabXfXDb3sHtUxP-8agEd-XB3f1yR6Gi3W0psoYnaq6Q");
            scanner.close();
        } 

        catch (FileNotFoundException ex) 
        
        {
            System.err.println("File not found");
        }
    }
    
    public static void saveUrl(final String filename, final String urlString)
        throws MalformedURLException, IOException {
    BufferedInputStream in = null;
    FileOutputStream fout = null;
    try {
        in = new BufferedInputStream(new URL(urlString).openStream());
        fout = new FileOutputStream(filename);

        final byte data[] = new byte[1024];
        int count;
        while ((count = in.read(data, 0, 1024)) != -1) {
            fout.write(data, 0, count);
        }
    } finally {
        if (in != null) {
            in.close();
        }
        if (fout != null) {
            fout.close();
        }
    }
}
   
}
