import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Lexicon {
	private ArrayList<ArrayList<String>> lexicon = new ArrayList<ArrayList<String>>();
	private String positiveLexiconFile = "positiveLexicon.txt";
	private String negativeLexiconFile = "negativeLexicon.txt";
	
	/*
	 * Konstruktor
	 */
	public Lexicon()
	{
		try {
			String line;
			
			BufferedReader br = new BufferedReader(new FileReader(positiveLexiconFile));
		    while ((line = br.readLine()) != null) {
		       lexicon.add(formatListEntry(line));
		    }
		    
		    br = new BufferedReader(new FileReader(negativeLexiconFile));
		    while ((line = br.readLine()) != null) {
		       lexicon.add(formatListEntry(line));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Lexikon ausgeben
	 */
	public void print()
	{
		for(ArrayList<String> array : lexicon)
		{
			for(String s : array)
			{
				System.out.println(s);
			}
		}
		
		System.out.println(lexicon.size());
	}
	
	/*
	 * Einzelne Zeilen der Textdatei aufsplitten
	 */
	private ArrayList<String> formatListEntry(String line)
	{
		ArrayList<String> ret = new ArrayList<String>();
		String word = line.substring(0, line.indexOf("|"));
		String wordProperties = line.substring(line.indexOf("|") + 1); 
		
		ret.add(word);
		
		for(String s : wordProperties.split("\t"))
		{
			ret.add(s);
		}
		
		return ret;
	}
}
