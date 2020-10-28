
/**
* The purpose of this program is to reads in a file “words.txt” and stores them in a LinkedList.
* Allow the user to choose to add new words to the list, remove words from the list, to display 
* the list, or to quit the program.
* @author Blake Del Rey 
* @author Jacob Sunia
*/
import java.util.*;
import java.io.*;

class Main {
	/**
	 * Main function that performs the purpose written at the top of this document.
	 *
	 * @param args No arguments used for this project
	 */
	public static void main(String[] args) {

		LinkedList<String> ll;
		ll = readFile();

		int menuChoice = 0;
		while (menuChoice != 5) {
			menu();
			menuChoice = CheckInput.getIntRange(1, 5);
			if (menuChoice == 1) {
				printForward(ll);
			} else if (menuChoice == 2) {
				printBackward(ll);
			} else if (menuChoice == 3) {
				addWord(ll);
			} else if (menuChoice == 4) {
				removeWord(ll);
			} else if (menuChoice > 5 || menuChoice < 1) {
				System.out.println("**ERROR INVALID INPUT**");
			}
		}

		System.out.println("End of Program");
	}

	/**
	 * Reads in a list of words from an external file to a linked list in
	 * alphabetical order.
	 *
	 * @preturn Alphabetically ordered linked list of words
	 */
	public static LinkedList<String> readFile() {
		final String FILE_NAME = "words.txt";
		LinkedList<String> ll = new LinkedList<String>();
		ListIterator<String> it = ll.listIterator();
		String newWord = "";

		try {
			File scannerFile = new File(FILE_NAME);
			Scanner input = new Scanner(scannerFile);
			while (input.hasNext()) {
				newWord = input.next();
				moveIter(it, newWord);
				it.add(newWord);
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("**Error File Containing Words Not Found**");
		}

		return ll;

	}

	/**
	 * Moves the iterator to the correct place to put a word into an Alphabetically
	 * organized linked list.
	 *
	 * @param it      Iterator that traverses linked list to insert words to the
	 *                correct position
	 * @param newWord Word that is to be inserted into the linked list
	 */
	public static void moveIter(ListIterator<String> it, String newWord) {
		LinkedList<String> ll = new LinkedList<String>();

		boolean isInserted = false;

		while (!isInserted) {
			if (it.hasPrevious()) {
				// toLowerCase used for case insensitivity in sorting
				if (it.previous().toLowerCase().compareTo(newWord.toLowerCase()) <= 0) {
					it.next();
					if (it.hasNext()) {
						if (it.next().toLowerCase().compareTo(newWord.toLowerCase()) >= 0) {
							it.previous();
							isInserted = true;
						}
					} else {
						isInserted = true;
					}
				}
			} else {
				if (it.hasNext()) {
					if (it.next().toLowerCase().compareTo(newWord.toLowerCase()) >= 0) {
						it.previous();
						isInserted = true;
					}
				} else {
					isInserted = true;
				}

			}
		}

	}

	/**
	 * addWord prompts the user to add a word to the linked list and parses the new
	 * word into the list for it to be sorted.
	 * 
	 * @param ll is the text file of words that is passed in from the main
	 */
	public static void addWord(LinkedList<String> ll) {
		ListIterator<String> it = ll.listIterator();

		System.out.print("Please Enter a Word to be added to the LinkedList: ");
		String word = CheckInput.getString();
		moveIter(it, word);
		it.add(word);

	}

	/**
	 * Removes all instances of a word that is entered by the user. Ignores Case.
	 * Error message printed if word to be removed is not found
	 *
	 * @param ll Linked list containing words that should be removed.
	 */
	public static void removeWord(LinkedList<String> ll) {
		ListIterator<String> it = ll.listIterator();
		int removeCounter = 0;

		System.out.print("Enter a word to be removed from the LinkedList: ");
		String key = CheckInput.getString();

		while (it.hasNext()) {
			if (it.next().toLowerCase().compareTo(key.toLowerCase()) == 0) {
				it.remove();
				removeCounter++;
			}
		}
		if (removeCounter == 0) {
			System.out.println("WORD COULD NOT BE FOUND");
		}
	}

	/**
	 * printBackward prints the inverse of the alphabetical order of the linkedList
	 * with each word on its own line
	 * 
	 * @param ll the list of words that are being passed from the text file
	 */
	public static void printBackward(LinkedList<String> ll) {

		ListIterator<String> it = ll.listIterator();

		// Initialize iterator to tail of the list
		while (it.hasNext()) {
			it.next();
		}
		while (it.hasPrevious()) {
			System.out.println(it.previous());
		}
	}

	/**
	 * Prints the linked list of alphabetized words forward with each word on own
	 * line
	 * 
	 * @param ll Linked list to be printed in a forward manner
	 */
	public static void printForward(LinkedList<String> ll) {
		ListIterator<String> it = ll.listIterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	/**
	 * Prints menu of functions user can perform on a linked list of alphabetized
	 * words.
	 */
	public static void menu() {
		System.out.println("1. Display Words\n2. Display Reversed Words\n3. Add Word\n4. Remove Word\n5. Quit");
	}

}