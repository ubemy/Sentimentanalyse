import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class TestdatenEingabe {
	private ArrayList<ArrayList<String>> testData = new ArrayList<ArrayList<String>>();
	ArrayList<String> listEntry;
	String testDataDirPositive = "Testdaten/positive";
	String testDataDirNegative = "Testdaten/negative";
	String testDataDirNeutral = "Testdaten/neutral";
	private static String positivePolarity = "P";
	private static String negativePolarity = "N";
	private static String neutralPolarity = "O";
	
	public void getTestData()
	{
		readFiles(testDataDirPositive, positivePolarity);
		readFiles(testDataDirNegative, negativePolarity);
		readFiles(testDataDirNeutral, neutralPolarity);
	}
	
	private void readFiles(String directory, String polarity)
	{
		File f = new File(directory);
		File[] fileArray = f.listFiles();
		String line;
		
		try
		{
			if(fileArray != null)
			{
				for(File file : fileArray)
				{
					//BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8"));
				    while ((line = br.readLine()) != null) {
				    	listEntry = new ArrayList<String>();
				    	listEntry.add(polarity);
				    	listEntry.add(file.getName());
				    	listEntry.add(line);
				    	testData.add(listEntry);
				    }
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void printTestData()
	{
		for(ArrayList<String> array : testData)
		{
			System.out.println(array.get(0) + "|" + array.get(1) + "|" + array.get(2));
		}
	}
	
	/*
	 * Berechnet die Häufigkeit des Vorkommens eines gegebenen Wortes,
	 * gruppiert nach der Polarität.
	 * 
	 * Verwendet von Maik zum Testen. Hat ansonsten keine sinnvolle Funktionalität.
	 */
	public void calcTermPolarityCounts(String word)
	{
		int positiveCounter = 0;
		int negativeCounter = 0;
		int neutralCounter = 0;
		
		for(ArrayList<String> array : testData)
		{
			if(array.get(2).toLowerCase().contains(word.toLowerCase()))
			{
				if(array.get(0).equals(positivePolarity)) positiveCounter++;
				else if(array.get(0).equals(negativePolarity)) negativeCounter++;
				else if(array.get(0).equals(neutralPolarity)) neutralCounter++;
			}
		}
		
		System.out.println(positiveCounter);
		System.out.println(negativeCounter);
		System.out.println(neutralCounter);
	}
}
