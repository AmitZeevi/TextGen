import java.util.HashMap;

/* This class provides the training stage of a random text generator.
 * The program reads text ("corpus") from a given file, and analyses
 * and records the character probabilitie in the given text. */
public class TextTrain {

	// Length of the moving window
	private static int windowLength; 
	
	// A map for managing the (window, list) mappings 
	private static HashMap<String, List> map;

	public static void main(String[] args) {
		int windowLength = Integer.parseInt(args[0]);
		String fileName = args[1];
		init(windowLength, fileName);

		// Creates the map (implemented as a global, class-level variable).
		train();
		
		// Prints a textual representation of the map (for debugging purposes only).
		System.out.println(mapString());
	}
	
	// Initializes the training process.
	
	public static void init(int length, String fileName) {
		windowLength = length;
		map = new HashMap<String, List>();
		StdIn.setInput(fileName);
	}

	/** Trains the model, creating the map. */
	public static void train() {
		String window = "";
		char c;
		// Constructs the first windowLength
		for(int i=0; i < windowLength; i++)
			window += StdIn.readChar();

		// Processes the entire text, one character at a time
		while (!StdIn.isEmpty()) {
			c = StdIn.readChar();
			
//			if the window exists 
			if(map.get(window) != null)
				map.get(window).update(c);
			
//			if the window doesn't exists 
			if(map.get(window) == null) {
				map.put(window, new List());
				map.get(window).addFirst(c);
			}
			window += c; 
			window = window.substring(1);	
		}

		// Computes and sets the p and pp fields of all the
		// CharData objects in each list in the map.
		for (List list : map.values()) {
			calculateProbabilities(list);
		}
	}

	// Computes and sets the probabilities (p and pp fields) of all the
	// characters in the given list. */
	private static void calculateProbabilities(List list) {				
		double	 sum = 0;
		double  sumpp = 0;
//		computes the total sum of characters in the list
		for(int i=0; i < list.getSize(); i++) 
			sum += list.get(i).count;
		
//		sets the p and pp
		for(int i=0; i < list.getSize(); i++) {
			list.get(i).p = (double) (list.get(i).count / sum); 
			list.get(i).pp = sumpp + list.get(i).p;
			sumpp = list.get(i).pp;
		}	
	}

	/** Generates a string representation of the map, for debugging purposes. */
	public static String mapString() {
		StringBuilder ans = new StringBuilder();
		for (String key : map.keySet()) {
			   ans.append(key + ": " + map.get(key));
	           ans.append("\n"); // next line
	        }
	        return ans.toString();

}
}
