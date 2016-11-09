import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Lexikon als Singleton implementiert
 */
public class Lexicon {
	private ArrayList<ArrayList<String>> lexicon = new ArrayList<ArrayList<String>>();
	private static String positiveLexiconFile = "positiveLexicon.txt";
	private static String negativeLexiconFile = "negativeLexicon.txt";
	private static String positivePolarity = "P";
	private static String negativePolarity = "N";
	private static Lexicon instance;
	
	/*
	 * Konstruktor:
	 * Lexikon Textdateien einlesen und speichern
	 */
	private Lexicon()
	{
		try {
			String line;
			
			BufferedReader br = new BufferedReader(new FileReader(positiveLexiconFile));
		    while ((line = br.readLine()) != null) {
		       lexicon.addAll(formatListEntry(line, positivePolarity));
		    }
		    
		    br = new BufferedReader(new FileReader(negativeLexiconFile));
		    while ((line = br.readLine()) != null) {
		       lexicon.addAll(formatListEntry(line, negativePolarity));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Singleton
	 */
	public static Lexicon getInstance()
	{
		if(Lexicon.instance == null)
		{
			Lexicon.instance = new Lexicon();
		}
		return Lexicon.instance;
	}
	
	/*
	 * Lexikon als Objekt zurückgeben
	 */
	public ArrayList<ArrayList<String>> getLexicon()
	{
		return lexicon;
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
	private ArrayList<ArrayList<String>> formatListEntry(String line, String polarity)
	{
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		ArrayList<String> listEntry = new ArrayList<String>();
		String word = line.substring(0, line.indexOf("|"));
		String wordProperties = line.substring(line.indexOf("|") + 1); 
		String[] wordPropertiesArray = wordProperties.split("\t");
		String posTag = wordPropertiesArray[0];
		String polarityWeigth = wordPropertiesArray[1];
		String synonyms = null;
		
		listEntry.add(word);
		listEntry.add(posTag);
		listEntry.add(polarityWeigth);
		listEntry.add(polarity);
		ret.add(listEntry);
		
		/*Synonyme der Wörter werden als selbstständige Einträge gespeichert*/
		if(wordPropertiesArray.length > 2)
		{
			synonyms = wordPropertiesArray[2];
			for(String s : synonyms.split(","))
			{
				listEntry = new ArrayList<String>();
				listEntry.add(s);
				listEntry.add(posTag);
				listEntry.add(polarityWeigth);
				listEntry.add(polarity);
				ret.add(listEntry);
			}
		}
		
		return ret;
	}
}
