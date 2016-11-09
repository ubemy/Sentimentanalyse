import java.util.ArrayList;

import com.sun.javafx.scene.layout.region.Margins.Converter;

/*
 * Kalkuliert die Polaritäten mithilfe des Lexikons (Lexicon.java)
 */
public class LexiconPolarityCalculator {
	/*
	 * Polarität für einen Satz ermitteln
	 */
	public int getPolarityForSentence(String sentence)
	{
		int ret = 0;
		
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
