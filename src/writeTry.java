
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhumikasaivamani
 */
public class writeTry {
   
    public static void main(String args[])
    {
        
    try
    {
        File file=new File("/Users/bhumikasaivamani/NetBeansProjects/DecisionTree/src/output.txt");
         file.createNewFile();
         FileWriter writer = new FileWriter(file);
         
         String pattern1="(\\d{1,2})(th|st|nd)\\s{1}(of)\\s{1}(January|Febrauary|March|April|May|June|July|August|September|October|November|December)";
         String pattern2="(January|Febrauary|March|April|May|June|July|August|September|October|November|December)\\s{1}(\\d{1,2})(st|nd|th|\\s{1}|,)";
        
         Pattern r2 = Pattern.compile(pattern2);
         Pattern r1=Pattern.compile(pattern1);
         
         FileReader reader=new FileReader("/Users/bhumikasaivamani/NetBeansProjects/DecisionTree/src/input.txt");
         BufferedReader br=new BufferedReader(reader);
        String line=br.readLine();
        while(line!=null)
        {
        int patternFlag=0;    
        Matcher m2 = r2.matcher(line);
        
        if(m2.find())
        {
           String stringToBeReplaced=m2.group();
            System.out.println(stringToBeReplaced);
           String newLine=line.replace(stringToBeReplaced, "*********");
           line=newLine;
        }
        Matcher m1=r1.matcher(line);
        if(m1.find())
        {
            patternFlag=1;
            String stringToBeReplaced=m1.group();
            System.out.println(stringToBeReplaced);
           String newLine1=line.replace(stringToBeReplaced, "$$$$$$$$$");
           line=newLine1;
          
        }
        if(m1.find() || m2.find())
        {
           writer.write(line);
           writer.write("\n");
        }
        if(!m1.find() && !m2.find())
        {//String line1="fdgfgdfgfdgsgf";
        writer.write(line);
        writer.write("\n");
        }
        //writer.write(line1);
        line=br.readLine();
        }
        
        writer.flush();
        writer.close();
         
        
    }
    catch(Exception e)
    {
        
    }
    }
    
}
