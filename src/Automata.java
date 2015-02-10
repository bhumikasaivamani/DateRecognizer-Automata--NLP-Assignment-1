
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhumikasaivamani
 */
public class Automata {
    
    StateTransmission transmission;
    public Automata()
    {
        transmission=new StateTransmission();
    }
    
    /**
     * Reads input file and returns array list of words
     * @return 
     */
    public ArrayList<String> ExtractData(String inputFile)
    {
        ArrayList<String> words=new ArrayList<>();
        try
        {
            FileReader reader=new FileReader(inputFile);
            BufferedReader br=new BufferedReader(reader);
            String line=br.readLine();
            while(line!=null)
            {
                //tokenize with space
                StringTokenizer token=new StringTokenizer(line," ");

                while(token.hasMoreTokens())
                {
                    words.add(token.nextToken());
                }
                line=br.readLine();
            }
        }
        catch(Exception e)
        {
            
        }
        return words;
    }
    
    public String PrintClean(String wrd)
    {
        wrd=wrd.replace(',', ' ');
        wrd=wrd.replace('.', ' ');
        wrd=wrd.replace(';',' ');
        return wrd;
    }
    public void AssignStates(ArrayList<String> words)
    {
        ArrayList<State> s1List=new ArrayList<>();
        ArrayList<State> s1s2List=new ArrayList<>();
        ArrayList<State> s1s2s3List=new ArrayList<>();
        ArrayList<State> s1s2s3s9List=new ArrayList<>();
        ArrayList<State> s4List=new ArrayList<>();
        ArrayList<State> s4s5List=new ArrayList<>();
        ArrayList<State> s4s5s8List=new ArrayList<>();
        ArrayList<State> s6List=new ArrayList<>();
        ArrayList<State> s6s7List=new ArrayList<>();
        ArrayList<State>s4s5s10List=new ArrayList<>();
        
        /****Checks Date Format 11th of November */
        
        //Checking transition from State 0 to State 1
        for(int i=0;i<words.size();i++)
        {
            Matcher m1=transmission.p1.matcher(words.get(i).toLowerCase());
            if(m1.find())
            {
               State s=new State("S1");
               s.wordInState=words.get(i);
               s.stateIndex=i;
               s1List.add(s);
            }
            else
            {
                State s=new State("S0");
                s.wordInState=words.get(i);
            }
        }
        //checking transition from State 1 to State 2
        for(int i=0;i<s1List.size();i++)
        {
            Matcher m2=transmission.p2.matcher(words.get(s1List.get(i).stateIndex+1).toLowerCase());
            if(m2.find())
            {
                State s=new State("S2");
                s.stateIndex=s1List.get(i).stateIndex+1;
                s.wordInState=words.get(s.stateIndex);
                s1List.get(i).nextState=s;
                s1s2List.add(s1List.get(i));
            }
        }
        
        //checking transition from State 2 to State 3
        for(int i=0;i<s1s2List.size();i++)
        {
            Matcher m3=transmission.p3.matcher(words.get(s1s2List.get(i).nextState.stateIndex+1).toLowerCase());
            if(m3.find())
            {
                State s=new State("S3");
                s.stateIndex=s1s2List.get(i).nextState.stateIndex+1;
                s.wordInState=words.get(s.stateIndex);
                s1s2List.get(i).nextState.nextState=s;
                s1s2s3List.add(s1s2List.get(i));
            }
        }
     /**End of State eg)11th of Novemeber,Where State 3 is the final State**/
     
     /** Check for presence of Year-to reach final State State 5**/   
        //checking transition from State 3 to State State 5
        Iterator it1 = s1s2s3List.iterator();
        while(it1.hasNext()) {
            State st = (State)it1.next();
            int index=st.nextState.nextState.stateIndex+1;
            Matcher m9=transmission.p7.matcher(words.get(index).toLowerCase());
            if(m9.find())
            {
                State s=new State("S9");
                s.stateIndex=index;
                s.wordInState=words.get(s.stateIndex);
                st.nextState.nextState.nextState=s;
                s1s2s3s9List.add(st);
                it1.remove();
            }
        }
        
       /****Checks Date Format November 21st */
       //Checking Transition from State 0 to State 4 
        for(int i=0;i<words.size();i++)
        {
           Matcher m4=transmission.p3.matcher(words.get(i).toLowerCase());
           if(m4.find())
           {
               State s=new State("S4");
               s.wordInState=words.get(i);
               s.stateIndex=i;
               s4List.add(s);
           }
        }

        //checking transition from State 4 to State 5
        for(int i=0;i<s4List.size();i++)
        {
            Matcher m5=transmission.p4.matcher(words.get(s4List.get(i).stateIndex+1).toLowerCase());
            if(m5.find())
            {
                State s=new State("S5");
                s.stateIndex=s4List.get(i).stateIndex+1;
                s.wordInState=words.get(s.stateIndex);
                s4List.get(i).nextState=s;
                s4s5List.add(s4List.get(i));
            }
        }
       
        Iterator it = s4s5List.iterator();
        while(it.hasNext()) {
            State st = (State)it.next();
            int index=st.nextState.stateIndex+1;
            Matcher m8=transmission.p7.matcher(words.get(index).toLowerCase());
            if(m8.find())
            {
                State s=new State("S8");
                s.stateIndex=index;
                s.wordInState=words.get(s.stateIndex);
                st.nextState.nextState=s;
                s4s5s8List.add(st);
                it.remove();
            }
        }
        /** End of Pattern eg) 21st November Reaching FInal State State 3*/
        /**Check for the presence of Year - to reach final State State 5**/
        Iterator it2 = s4s5List.iterator();
        while(it2.hasNext()) {
            State st = (State)it2.next();
            int index=st.nextState.stateIndex+1;
            Matcher m9=transmission.p2.matcher(words.get(index).toLowerCase());
            if(m9.find())
            {
                State s=new State("S10");
                s.stateIndex=index;
                s.wordInState=words.get(s.stateIndex);
                st.nextState.nextState=s;
                Matcher m10=transmission.p7.matcher(words.get(index+1).toLowerCase());
                if(m10.find())
                {
                State snew=new State("S11");
                snew.stateIndex=index+1;
                snew.wordInState=words.get(snew.stateIndex);
                st.nextState.nextState.nextState=snew;
                s4s5s10List.add(st);
                it2.remove();
                }
            }
        }
   
      /** Check for the Pattern like Class of holidays*/
        
        //Transition from State 0 to State 3**/
        for(int i=0;i<words.size();i++)
        {
           Matcher m6=transmission.p5.matcher(words.get(i).toLowerCase());
           if(m6.find())
           {
               State s=new State("S6");
               s.wordInState=words.get(i);
               s.stateIndex=i;
               s6List.add(s);
           } 
        }
        //Transition from state 6 to state 7
         for(int i=0;i<s6List.size();i++)
        {
            Matcher m5=transmission.p6.matcher(words.get(s6List.get(i).stateIndex+1).toLowerCase());
            if(m5.find())
            {
                State s=new State("S7");
                s.stateIndex=s6List.get(i).stateIndex+1;
                s.wordInState=words.get(s.stateIndex);
                s6List.get(i).nextState=s;
                s6s7List.add(s6List.get(i));
            }
        }
         
    /**Printing All States**/
         
        for(int i=0;i<s1s2s3List.size();i++)
        {
            if(i==0)
            {
                 System.out.println("\n\n\tDates of Format : Example 10th of November");
            }
            System.out.print(s1s2s3List.get(i).wordInState+" ");
            System.out.print(s1s2s3List.get(i).nextState.wordInState+" ");
            String output=s1s2s3List.get(i).nextState.nextState.wordInState.replace(',',' ');
            System.out.println(output);
        }
     
        for(int i=0;i<s1s2s3s9List.size();i++)
        {
           if(i==0)
           {
                System.out.println("\n\n\tDates of Format : Example 21st of August, 2015");
           }
           System.out.print(s1s2s3s9List.get(i).wordInState+" ");
           System.out.print(s1s2s3s9List.get(i).nextState.wordInState+" ");
           System.out.print(s1s2s3s9List.get(i).nextState.nextState.wordInState+" ");
           String wrd=s1s2s3s9List.get(i).nextState.nextState.nextState.wordInState;
           System.out.println(PrintClean(wrd)); 
        }  
        
       
        for(int i=0;i<s4s5List.size();i++)
        {
            if(i==0)
            {
                System.out.println("\n\n\tDates of Format : Example January 30th");
            }
            System.out.print(s4s5List.get(i).wordInState+" ");
            String wrd=s4s5List.get(i).nextState.wordInState;
            System.out.println(PrintClean(wrd)+" ");
        }
        
      
        
        for(int i=0;i<s4s5s8List.size();i++)
        {
            if(i==0)
            {
                System.out.println("\n\n\tDates of Format : Example February 9,2015");
            }
            System.out.print(s4s5s8List.get(i).wordInState+" ");
            System.out.print(s4s5s8List.get(i).nextState.wordInState+" ");
            String wrd=s4s5s8List.get(i).nextState.nextState.wordInState;
            System.out.println(PrintClean(wrd)+" ");
            
        }
        
        
        for(int i=0;i<s4s5s10List.size();i++)
        {
            if(i==0)
            {
                System.out.println("\n\n\tDates of Format : Example March 20th of 2015 or '12");
            }
            String wrd=s4s5s10List.get(i).nextState.nextState.nextState.wordInState;
            System.out.println(s4s5s10List.get(i).wordInState+"\t"+s4s5s10List.get(i).nextState.wordInState+" "+s4s5s10List.get(i).nextState.nextState.wordInState+" "+PrintClean(wrd));
        }
        
        for(int i=0;i<s6s7List.size();i++)
        {
            if(i==0)
            {
                System.out.println("\n\n\tHolidays Class");
            }
            System.out.print(s6s7List.get(i).wordInState+" ");
            System.out.println(PrintClean(s6s7List.get(i).nextState.wordInState)+" ");
        } 
        
    }
    public static void main(String args[])
    {
        Automata mainClass=new Automata();
        if(args[0]==null)
        {
            System.out.println("Please give file Name as argument");
        }
        else
        {
        String inputFileName=args[0];//"/Users/bhumikasaivamani/NetBeansProjects/DecisionTree/src/input.txt"
        ArrayList<String> words=mainClass.ExtractData(inputFileName);
        mainClass.AssignStates(words);
        }
    }
    
}
