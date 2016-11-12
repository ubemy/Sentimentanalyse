import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
					BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
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
			
		}
	}
	
	public void printTestData()
	{
		for(ArrayList<String> array : testData)
		{
			System.out.println(array.get(0) + "|" + array.get(1) + "|" + array.get(2));
		}
	}
}
