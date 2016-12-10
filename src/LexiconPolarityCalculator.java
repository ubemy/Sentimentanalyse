import java.util.ArrayList;

import com.sun.javafx.scene.layout.region.Margins.Converter;

/*
 * Kalkuliert die Polaritäten mithilfe des Lexikons (Lexicon.java)
 */
public class LexiconPolarityCalculator {
	/*
	 * Polarität für ein Dokument ermitteln
	 */
	public double getPolarityForDocument(ArrayList<String> document)
	{
		double ret = 0;
		int sentenceCounter = 0;
		double temp = 0;
		double temp2 = 0;
		
		for(String s : document)
		{
			temp2 = getPolarityForSentence(s);
			if(temp2 != 0.0)
			{
				temp += temp2;
				sentenceCounter++;
			}
		}
		
		ret = temp/sentenceCounter;
		
		return ret;
	}
	
	/*
	 * Polarität für einen Satz ermitteln
	 */
	public double getPolarityForSentence(String sentence)
	{
		double ret = 0;
		int wordCounter = 0;
		double temp = 0;
		double temp2 = 0;
		
		for(String s : sentence.split(" "))
		{
			temp2 = getPolarityForWord(s);
			if(temp2 != 0.0)
			{
				temp += temp2;
				wordCounter++;
			}
		}
		
		if(temp == 0.0){ 
			ret = temp;
		}
		else {
			ret = temp / wordCounter;
		}
		
		System.out.println(sentence);
		System.out.println(ret);
		
		return ret;
	}
	
	/*
	 * Polarität für ein Wort ermitteln
	 */
	public double getPolarityForWord(String word)
	{
		double ret = 0;
		
		if(!word.isEmpty())
		{
			for(ArrayList<String> array : Lexicon.getInstance().getLexicon())
			{
				if(array.get(0).toLowerCase().equals(word.toLowerCase()))
				{
					ret = Double.parseDouble(array.get(2));
				}
			}
		}
		
		return ret;
	}
}
