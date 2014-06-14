import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Comparator;



public class WordFrequencyTest 
{
	public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
                return (m2.getValue()).compareTo(m1.getValue());
            }
        });

        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
	public static void main(String[] args) throws IOException
	{
		File file = new File("Input.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line;
	    
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
	    int numberOfInstances = 0;
		
		while ((line = bufferedReader.readLine()) != null)
		{
		    int wordCount = 0;
		    boolean isThereAWord = false;
			int endOfLine = line.length() - 1;

		    for (int i = 0; i < line.length(); i++) {
		        if (Character.isLetter(line.charAt(i)) && i != endOfLine) {
		        	isThereAWord = true;
		        } else if (!Character.isLetter(line.charAt(i)) && isThereAWord) {
		            wordCount++;
		            isThereAWord = false;
		        } else if (Character.isLetter(line.charAt(i)) && i == endOfLine) {
		            wordCount++;
		        }
		    }
			
		    String[] words = line.split(" ");
		    
		    for (int j = 0; j <= wordCount - 1; j++) {
				boolean exists = hm.containsKey(words[j]);
				
				if (exists == true) {
					int currentValue = hm.get(words[j]);
					int updatedValue = currentValue + numberOfInstances;
					hm.put(words[j], updatedValue);
				} else {
					numberOfInstances = 1;
					hm.put(words[j], numberOfInstances);
				}
		    }
		    System.out.println("Total word count: " + wordCount);
		    System.out.println();
		}
		
		Map<String, Integer> rtn = sortByValue(hm);
		
		System.out.println("Sorted by Top Ten (10) Most Frequent Words:");
		System.out.println();
//      Set<String> keys = rtn.keySet();
//    	for(String key : keys) {
//        	int frequency = rtn.get(key);
//        	String stringFinal = "Key: " + key + "\t Frequency: " + frequency;
//        	System.out.println(stringFinal);
//    	}

		int lineNumber = 0;
		Set<String> keys = rtn.keySet();
		for(String key : keys) {
			int frequency = rtn.get(key);
			String stringFinal = "Key: " + key + "\t Frequency: " + frequency;
			System.out.println(stringFinal);
			lineNumber++;
			if (lineNumber>9) {
				break;
			}
		}
		
		fileReader.close();
	}
}

