package com.uno.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class PopulatingDeck {
	 
private static Map<Long,String> map;
private static Map<String,Integer> mapDeck;
private static ArrayList <String> finalDeck;
private static boolean cardShow= false;
private static String checkCardKey;
private static boolean MapContainsValueFlag= false;
private static String cardValueRecieved;


    public PopulatingDeck(){
        map=new HashMap<Long,String>();
        finalDeck=new ArrayList<String>();
        mapDeck=new HashMap<String,Integer>();
        map.put(new Long(0), "blue");
        map.put(new Long(1), "green");
        map.put(new Long(2), "yellow");
        map.put(new Long(3), "red");
        generateDeck();
    }
     
   
    public static void main(String[] args) {
        System.out.println("hiiiiiiiii");
        PopulatingDeck generatingDeck=new PopulatingDeck();
      for(int i=0;i<finalDeck.size();i++){
          System.out.println(finalDeck.get(i)+"  \n");
      } 
    }
     
    private static String  randomColorGeneration(){
        long temp=Math.round(Math.random()*3);
            if(map.containsKey(temp)){
                return map.get(temp);
            }
            else{
                return null;
            }
}
    public static void generateDeck(){
        for(int i=0;finalDeck.size()<108;i++){
            cardShow=cardDisplayCheck();
            if (cardShow)
                finalDeck.add(checkCardKey);
        }            
          }
     
    public static String randCardNumber() {
         String returnString; 
        Random rand = new Random();
         int randomNum = rand.nextInt(15);
        if(randomNum>=10 && randomNum<15){
            switch(randomNum){
            case 10: return "+2";
            case 11: return "Skip";
            case 12: return "Reverse";
            case 13: return "WildCard-Farbe";
            case 14: return "WildCard- +4"; 
            }
        }
            else{
            returnString=String.valueOf(randomNum);
        return returnString;
        }
        return null;
        }
     
    public static boolean cardDisplayCheck(){
        String localCardNumberDisplay=randCardNumber();
        if(!localCardNumberDisplay.startsWith("W")){
         checkCardKey=localCardNumberDisplay+" - " +randomColorGeneration();
        }
        else{
             checkCardKey=localCardNumberDisplay;
        }
        int cardCounter=0;
        if(!MapContainsValueFlag||!mapDeck.containsKey(checkCardKey)){
            mapDeck.put(checkCardKey, ++cardCounter);
            MapContainsValueFlag=true;
            return true;
    }
        if(checkCardKey.startsWith("0") && mapDeck.containsKey(checkCardKey)){
                return false;
             
        }
        if(checkCardKey.startsWith("W") && mapDeck.containsKey(checkCardKey)&& mapDeck.get(checkCardKey)<4){
            mapDeck.put(checkCardKey, mapDeck.get(checkCardKey)+1);
            return true;
         
    }
         
        if(mapDeck.containsKey(checkCardKey)){
               if(mapDeck.get(checkCardKey)<2){
                   mapDeck.put(checkCardKey, mapDeck.get(checkCardKey)+1);
                   return true;
               }
        }
        return false;
    }       
}
 
             
            }
        }
            else{
            returnString=String.valueOf(randomNum);
        return returnString;
        }
        return null;
        }
     
    public static boolean cardDisplayCheck(){
        String localCardNumberDisplay=randCardNumber();
        if(!localCardNumberDisplay.startsWith("W")){
         checkCardKey=localCardNumberDisplay+" - " +randomColorGeneration();
        }
        else{
             checkCardKey=localCardNumberDisplay;
        }
        int cardCounter=0;
        if(!MapContainsValueFlag||!mapDeck.containsKey(checkCardKey)){
            mapDeck.put(checkCardKey, ++cardCounter);
            MapContainsValueFlag=true;
            return true;
    }
        if(checkCardKey.startsWith("0") && mapDeck.containsKey(checkCardKey)){
                return false;
             
        }
        if(checkCardKey.startsWith("W") && mapDeck.containsKey(checkCardKey)&& mapDeck.get(checkCardKey)<4){
            mapDeck.put(checkCardKey, mapDeck.get(checkCardKey)+1);
            return true;        
    }
         
        if(mapDeck.containsKey(checkCardKey)){
               if(mapDeck.get(checkCardKey)<2){
                   mapDeck.put(checkCardKey, mapDeck.get(checkCardKey)+1);
                   return true;
               }
        }
        return false;
    }   
}

