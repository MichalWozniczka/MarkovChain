import java.util.*;
import java.io.*;

public class MarkovChain
{
  public static void main(String args[]) throws IOException
  {
    Scanner scan = new Scanner(new File("input.txt"));
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("", 0);
    ArrayList<ArrayList<String>> words = new ArrayList<ArrayList<String>>();
    int mapCount = 1;
    words.add(new ArrayList<String>());
    Random rn = new Random();
    Integer nextWord = 0;
    int p = Integer.parseInt(scan.nextLine().trim());
    String output = "";
    PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
    System.setOut(out);
    
    while(scan.hasNext())
    {
      String s = scan.nextLine();
      String[] s1 = s.trim().split(" ");
      
      for(int i = 0; i <= s1.length; i++)
      {
        if(i == 0 && s1[i].equals("") == false)
        {
          words.get(map.get("")).add(s1[i]);
        }
        else if(i == s1.length && i != 0)
        {
          if(map.containsKey(s1[i-1]) != true)
          {
            words.add(new ArrayList<String>());
            map.put(s1[i-1], mapCount);
            mapCount++;
          }
          if(s1[i-1].equals("") == false)
            words.get(map.get(s1[i-1])).add("\n\n");
        }
           
        else if(i > 0 && i < s1.length)
        {
          if(map.containsKey(s1[i-1]) != true)
          {
            words.add(new ArrayList<String>());
            map.put(s1[i-1], mapCount);
            mapCount++;
          }
          words.get(map.get(s1[i-1])).add(s1[i]);
        }
      }
    }
    String printWord = "";
    int rand = 0;
    int nWord = 0;
    for(int i = 0; i < p; i++)
    {
      if(printWord.equals("\n\n"))
        nextWord = 0;
      rand = rn.nextInt(words.get(nextWord).size());
      printWord = words.get(nextWord).get(rand);
      
      if(nextWord == 0)
        output += printWord;
      else
        output += " " + printWord;
      nextWord = map.get(printWord);
    }
    out.println(output);
  }
}