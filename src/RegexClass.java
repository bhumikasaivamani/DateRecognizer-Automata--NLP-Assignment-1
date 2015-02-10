
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
public class RegexClass {
    
    public static void main(String args[])
    {
        String s1= "ZVXjas March 15,";
        String s2=" 1 asdas adasdsa";
        
        String pattern1="(\\d{1,2})(th|st|nd)\\s{1}(of)\\s{1}(January|Febrauary|March|April|May|June|July|August|September|October|November|December)";
        String pattern2="(January|Febrauary|March|April|May|June|July|August|September|October|November|December)\\s{1}(\\d{1,2})";
        //Pattern object
        Pattern r = Pattern.compile(pattern2);
        Matcher m = r.matcher(s1);
        
        if (m.find( )) {
         System.out.println("Found value: " + m.group(0) );
        // System.out.println("Found value: " + m.group(1) );
         //System.out.println("Found value: " + m.group(2) );
      } else {
         System.out.println("NO MATCH");
      }
      FileReader fileReader; 
      try
      {
        fileReader=new FileReader("/Users/bhumikasaivamani/NetBeansProjects/DecisionTree/src/input.txt");
         BufferedReader br=new BufferedReader(fileReader);
         
         File file=new File("/Users/bhumikasaivamani/NetBeansProjects/DecisionTree/src/output.txt");
         file.createNewFile();
         FileWriter writer = new FileWriter(file);
         String line=br.readLine(); 
         while(line!=null)
         {
             
             Matcher m1=r.matcher(line);
             if(m1.find())
             {
                 continue;
             }
             else
             {
              writer.write(line);
             }
             line=br.readLine();
             
         }
          System.out.println("Done writing");
         writer.flush();
         writer.close();   
      }
      
      catch(Exception e)
      {
          System.out.println(e);
      }
    }
    
}
