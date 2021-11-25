
import java.util.HashMap;
import java.util.Random;

/* A random text generator. The program "trains" itself by reading and analysing
 * character probabilitie in a given text, and then generates random text that is
 * "similar" to the given text. */
public class TextGen {

	// Length of the moving window
	private static int windowLength; 
	
	// A map for managing the (window, list) mappings 
	private static HashMap<String, List> map;

	// Random number generator, used by the getRandomChar method. 
	private static Random randomGenerator;

	public static void main(String[] args) {
		int windowLength = Integer.parseInt(args[0]);
		String initialText = args[1];
		int generatedTextLength = Integer.parseInt(args[2]);
		boolean randomGeneration = args[3].equals("random");
		String fileName = args[4];
		init(windowLength, randomGeneration, fileName);

		// Creates the map (implemented as a global, class-level variable).
		train();
		
		// Uses the map for generating random text, and prints the text.
		String generatedText = generate(initialText, generatedTextLength);
		System.out.println(generatedText);
	}
	
	// Initializes the training and text generation processes
	private static void init(int length, boolean randomMode, String fileName) {
		windowLength = length;
		map = new HashMap<String, List>();
		StdIn.setInput(fileName);
		if (randomMode) {
			// Creates a random number generator with a random seed:
			// Each program run will produce a different random text.
		    randomGenerator = new Random();    
		} else {
			// Creates a random number generator with a fixed seed:
			// Each program run will produce the same random text.
			// Designed to support consistent testing and debugging.
            randomGenerator = new Random(20);
		}
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
		}	}

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
		}	}

	/** Generates a string representation of the map, for debugging purposes. */
	public static String mapString() {
		StringBuilder ans = new StringBuilder();
		for (String key : map.keySet()) {
			   ans.append(key + ": " + map.get(key));
	           ans.append("\n"); // next line
	        }
	        return ans.toString();

	}
	/**
	 * Generates random text, based on the map crated by the train() method. 
	 * @param initialText - the beginning of the generated text 
	 * @param textLength - the size of generated text
	 * @return the generated text
	 */
	public static String generate(String initialText, int textLength) {
		if(initialText.length() < windowLength) 
			return initialText;

		String window = initialText.substring(initialText.length() - windowLength);
		StringBuilder str = new StringBuilder(window);

		for (int i = 0; i < textLength; i++) {
			char ranChr = getRandomChar(map.get(window));
			str.append(ranChr);
			window = str.substring(str.length() - windowLength);
			
			if (map.get(window) == null) 
				break;
		}

		return str.toString();		

	}

	// Returns a random character from a given list of CharData objects,
    // using Monte Carlo.
	private static char getRandomChar(List list) {
		double r = randomGenerator.nextDouble();
		ListIterator pointer = list.listIterator(0);
		
		while(pointer.hasNext()) {
			if(pointer.current.cd.pp >= r)
				return pointer.current.cd.chr;
			pointer.current = pointer.current.next;
		}
		return ' ';		
	}
}
