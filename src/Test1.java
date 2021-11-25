/** Tests some of the List class methods.
 *  Should generate the output shown in the HW10 document. */
public class Test1 {
	
	public static void main(String args[]) {
		testAddFirst();
		testIndexOf();
		testGet();
		testRemove();
		testToArray();
		testIterator();
        testUpdate();

	}
	
	public static void testAddFirst() {
		System.out.println("Tests the addFirst method:");
		List q = new List();
		System.out.println("The list before adding any element: " + q);
		q.addFirst('a');
		System.out.println("The list after adding 'a' to the beginning: " + q);
		q.addFirst('b');
		System.out.println("The list after adding 'b' to the beginning: " + q);
		q.addFirst('c');
		System.out.println("The list after adding 'c' to the beginning: " + q);
		System.out.println();
	}
	
	public static void testIndexOf() {
		System.out.println("Tests the indexOf method:");
		List q = buildListabc();
		System.out.println("Base list: " + q);
		System.out.println("The index of 'a' is: " + q.indexOf('a'));
		System.out.println("The index of 'b' is: " + q.indexOf('b'));
		System.out.println("The index of 'c' is: " + q.indexOf('c'));
		System.out.println("The index of 'd' is: " + q.indexOf('d'));
		System.out.println();
	}

	public static void testGet() {
		System.out.println("Tests the get method:");
		List q = buildListabc();
		System.out.println("Base list: " + q);
		System.out.println("The element at location 0 is: "+ q.get(0));
		System.out.println("The element at location 1 is: "+ q.get(1));
		System.out.println("The element at location 2 is: "+ q.get(2));
		System.out.println();
	}

	public static void testRemove() {
		System.out.println("Tests the remove method:");
		List q = buildListabc();
		System.out.println("Base list: " + q);
		
		 q.remove('b');
		System.out.println("The list after removing 'b': "+ q);
		
		q.remove('d');
		System.out.println("The list after removing 'd': "+  q);
		
		q.remove('a');
		System.out.println("The list after removing 'a': "+  q);
		
		q.remove('c');
		System.out.println("The list after removing 'c': "+  q);
		
		q.remove('e');
		System.out.println("The list after removing 'e': "+  q);
	}

	public static void testToArray() {
		System.out.println("Tests the toArray method:");
		List q = buildListabc();
	    System.out.println("Base list: " + q);
	    CharData [] arr = q.toArray();
	    
	    for(int i=0; i < arr.length; i++)
	    	System.out.println(arr[i]);
	    
		System.out.println();	
	}
	
	public static void testIterator() {
		System.out.println("Tests the iterator method:");
		// Builds a list of some elements
		List q = new List();
		q.addFirst('a');
		q.addFirst('b');
		q.addFirst('c'); 
		q.addFirst('d');
		q.addFirst('e');
		q.addFirst('f'); 
		System.out.println("Base list: " + q);
		
		// Uses an iterator to print all the elements from 
		// position 2 to the end of the list.
		System.out.println("The elements of the list from index 2 to the end: ");
		ListIterator p = q.listIterator(2);
		while(p.hasNext()) {
			System.out.println(p.current);
			p.current = p.current.next;
		}
		
		System.out.println();
	}

	public static void testUpdate() {
		System.out.println("Tests the update(chr) method, which works as follows:");
		System.out.println("If the character chr is not in the list, adds it to the list's beginning.");
		System.out.println("If the character chr is in the list, increments its counter.");
		System.out.println();
		List q = new List();
		
		System.out.println("The list before updating any element: " + q);
				
		q.update('a');
		System.out.println("The list after updating 'a': " + q);

		q.update('b');
		System.out.println("The list after updating 'b': " + q);
		
		q.update('c');
		System.out.println("The list after updating 'c': " + q);
		
		q.update('d');
		System.out.println("The list after updating 'd': " + q);
		
		q.update('c');
		System.out.println("The list after updating 'c': " + q);
		
		q.update('b');
		System.out.println("The list after updating 'b': " + q);


		
		System.out.println();
	}

	// Builds and returns a small list. Called by some of the test methods.
	private static List buildListabc() {
		List q = new List();
		q.addFirst('a');
		q.addFirst('b');
		q.addFirst('c');
		return q;
	}
}
