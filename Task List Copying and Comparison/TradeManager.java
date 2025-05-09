package assignment3_part2;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

//Assignment #3
//Question: Part 2
//Written by: Arshdeep Singh (40286514)
//
//This is the main driver class that manages tariff operations and trade requests.
//It provides a menu interface which allows you to search for a tarrif and checks if 
//it is found, and displays how each method and constructor works.

public class TradeManager {
	public static void main (String [] args) {
		TariffList tl1 = new TariffList ();
		TariffList tl2 = new TariffList ();
		Scanner sc1 = new Scanner(System.in);
		BufferedReader bf1 = null;
		BufferedReader bf2 = null;
		ArrayList<TradeRequest> tradeRequestList = new ArrayList<>();
		
		displayMenu(sc1, tl1, bf1, bf2, tradeRequestList);
		
		}	

	//reads the tariff file
	public static void readTarrifFile (TariffList tl, BufferedReader bf) {
		bf= null;
		try {
			bf = new BufferedReader (new FileReader ("Tariffs.txt"));
		} catch (FileNotFoundException fnfex) {
			System.out.println("No file found!");
			return;
		}

		try {
			String line;
			//reads the line
			while ((line = bf.readLine()) != null) {
				//seperates each element into a token
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
				//checks if there are any invalid lines in the file
				if (tokenizer.countTokens() != 4) {
					continue;
				}
				//distingues each element
				String destinationCountry = tokenizer.nextToken();
				String originCountry = tokenizer.nextToken();
				String category = tokenizer.nextToken();
				double minimumTarrif = Double.parseDouble(tokenizer.nextToken());
				
				//stores info into a object Tariff
				Tariffs tariff = new Tariffs (destinationCountry, originCountry, category, minimumTarrif);
				
				//checks if the file contains any duplicates and skips them
				if (!tl.contains(originCountry, destinationCountry, category)) {
					tl.addToStart(tariff);
				}

			}
		} catch (IOException ioex) {
			System.out.println("Could not read file!");
			return;
		}
		//closes the bufferedReader
		catch (NoSuchElementException nseex) {
			try {
				bf.close();

			} catch (IOException ioex) {
				System.out.println("Could not read file!");
				return;
			}
		}		
	}
	
	//reads the TradeResquest file
	public static void readTradeRequestFile (BufferedReader bf, ArrayList<TradeRequest> trades) {
		bf = null;

		try {
			bf = new BufferedReader(new FileReader("TradeRequests.txt"));
		} catch (FileNotFoundException fnfex) {
			System.out.println("File not found!");
			return;
		}

		try {
			String line;
			//reads the line
			while ((line = bf.readLine()) != null) {
				//seperates each element into a token
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
				//checks if there are any invalid lines in the file
				if (tokenizer.countTokens() != 6) {
					continue;
				}
				//distingues each element
				String requestNumber = tokenizer.nextToken();
				String originCountry = tokenizer.nextToken();
				String destinationCountry = tokenizer.nextToken();
				String category = tokenizer.nextToken();
				double tradeValue = Double.parseDouble(tokenizer.nextToken());
				double proposedTarrif = Double.parseDouble(tokenizer.nextToken());
				//stores info into a an object of TradeRequest
				TradeRequest tr = new TradeRequest(requestNumber, originCountry, destinationCountry, category, tradeValue, proposedTarrif);
				//adds the object to the ArrayList
				trades.add(tr);
			}
		} catch (IOException ioex) {
			System.out.println("Could not read file!");
			return;
		}
		catch (NoSuchElementException nseex) {

			try {
				bf.close();

			} catch (IOException ioex) {
				System.out.println("Could not read file!");
				return;
			} 
		}
	}
	
	//displays the menu to the user
	public static void displayMenu (Scanner sc, TariffList tl1, BufferedReader bf1, BufferedReader bf2, ArrayList<TradeRequest> tradeRequestList) {
		int option;
		do {
			System.out.print("Please enter an option:\n"		
					+ "\t> 1. Enter tarrif\n"
					+ "\t> 2. Display all methods\n"
					+ "\t> 3. Quit\n"
					+ "Option: ");
			option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
				optionOne(bf1, sc, tl1);
				break;
			case 2:
				optionTwo(bf1, bf2, tl1, tradeRequestList);
				break;
			case 3:
				break;
			default:
				System.out.print("Invalid input. Please try again> ");
			}
		} while (option !=3);
	}
	
	//optionOne: Prompts the user to enter a tariff information and checks if it is found
	public static void optionOne (BufferedReader bf, Scanner sc, TariffList tl) {
		
		String option;
		readTarrifFile(tl, bf);
		do {
			System.out.print("=".repeat(8) + " Tarrif finder simulator " + "=".repeat(8) +"\n"
					+ "Please enter the following information:\n"
					+ "Destination country: ");
			String destination = sc.nextLine();
			System.out.print("Origin country: ");
			String origin = sc.nextLine();
			System.out.print("Category: ");
			String category = sc.nextLine();
		
			tl.find(origin, destination, category);
			
			System.out.print("Would you like to continue (yes/no)> ");
			option = sc.nextLine();
			
			if (option.equalsIgnoreCase("no")) {
				System.out.println("Back to main menu...");
			}
			
		} while (option.equalsIgnoreCase("yes"));
		
	}
	
	//optionTwo: displays all the constructors and methods
	public static void optionTwo (BufferedReader bf1, BufferedReader bf2, TariffList tl1, ArrayList<TradeRequest> tradeRequestList) {
		System.out.println("-".repeat(116));
		//default constructor
		System.out.println("=".repeat(46) +" 1. Default constructor " + "=".repeat(46));
		TariffList emptyList = new TariffList();
		emptyList.display();

		System.out.println("-".repeat(116));
		//copy constructor
		System.out.println("=".repeat(47) +" 2. Copy constructor " + "=".repeat(48)
				+ "\n\tI. Empty list");
		//copy constructor on a empty list
		TariffList copyOfEmptyList = new TariffList(emptyList);
		copyOfEmptyList.display();
		
		//copy constructor on a non-empty list
		System.out.println("\tII. Non-empty list");
		TariffList originalList = new TariffList();
		TariffList copiedList = new TariffList();
		originalList.addToStart(new Tariffs ("Japan", "China", "Electronics", 15));
		originalList.addToStart(new Tariffs ("India", "Canada", "Agriculture", 20));
		originalList.addToStart(new Tariffs ("Australia", "France", "Manufacturing", 5));
		//before
		System.out.println("Before copy constructor:"
				+ "\nOriginal list:");
		originalList.display();
		System.out.println("Copied list:");
		copiedList.display();
		//after method
		System.out.println("\nAfter copy constructor:\n"
				+ "Copied list:");
		copiedList = new TariffList(originalList);
		copiedList.display();
		
		System.out.println("-".repeat(116));
		
		//addToStart
		System.out.println("=".repeat(50) +" 3. addToStart " + "=".repeat(51));
		TariffList tariffList = new TariffList();
		Tariffs t1 = new Tariffs ("Japan", "USA", "Electronics", 15);
		Tariffs t2 = new Tariffs ("Germany", "USA", "Automobiles", 10);
		Tariffs t3 = new Tariffs ("India", "Brazil", "Agriculture", 20);
		Tariffs t4 = new Tariffs ("South Korea", "China", "Electronics", 45);
		tariffList.addToStart(t1);
		tariffList.addToStart(t2);
		tariffList.addToStart(t3);
		tariffList.addToStart(t4);
		System.out.println("Before addToStart:");
		tariffList.display();
		System.out.println("\nAfer addToStart:");
		Tariffs t5 = new Tariffs ("Canada", "USA", "Energy", 15);
		tariffList.addToStart(t5);
		tariffList.display();
		
		System.out.println("-".repeat(116));
		//insertAtIndex
		//add tariff at an invalid index
		System.out.println("=".repeat(49) +" 4. insertAtIndex " + "=".repeat(49) + "\n"
				+ "\tI. Add Tarrif [France, Bangladesh, Textiles, 8] at an invalid index");
		TariffList tariffList2 = new TariffList(tariffList);
		Tariffs t6 = new Tariffs("France", "Bangladesh", "Textiles", 8);
		try {
			tariffList2.insertAtIndex(t6, -1);
		} catch (NoSuchElementException nseex) {
			System.out.println(nseex.getMessage() + "\n");
		}
		//add tariff at a valid index
		System.out.println("\n\tII. Add Tarrif [France, Bangladesh, Textiles, 8] at a valid index");
		System.out.println("Before insertAtIndex:");
		tariffList2.display();
		System.out.println("After insertAtIndex:");
		try {
			tariffList2.insertAtIndex(t6, 3);
		} catch (NoSuchElementException nseex) {
			nseex.getMessage();
		}
		tariffList2.display();
		
		System.out.println("-".repeat(116));
		
		//deleteFromStart
		//deleteFromStart on a empty list
		System.out.println("=".repeat(49) +" 5. deleteFromStart " + "=".repeat(49) + "\n"
				+ "\tI. Using deleteAtStart on a empty list");
		try {
			emptyList.deleteFromStart();
		} catch (NoSuchElementException nseex) {
			System.out.println(nseex.getMessage());
		}
		
		//deleteFromStart on a non-empty list
		TariffList tariffList3 = new TariffList(tariffList);
		System.out.println("\n\tII. Using deleteAtStart on a non empty list\n"
				+ "Before deleteToStart:");
		tariffList3.display();
		System.out.println("\nAfter deleteToStart:");
		tariffList3.deleteFromStart();
		tariffList3.display();
		
		System.out.println("-".repeat(116));
		
		//deleteFromIndex
		//delete tariff at an invalid index
		System.out.println("=".repeat(48) + " 6. deleteFromIndex " + "=".repeat(48) + "\n"
				+ "\tI. Delete tarrif [Germany, USA, Automobile, 10] at a invalid index (-1)");
		TariffList tariffList4 = new TariffList(tariffList);
		try {
			tariffList4.deleteFromIndex(-1);
		} catch (NoSuchElementException nseex) {
			System.out.println(nseex.getMessage());
		}
		
		//delete tariff at a valid index
		System.out.println("\n\tII. Delete tarrif [Germany, USA, Automobile, 10] at a valid index (3)\n"
				+ "Before deleteFromIndex:");
		tariffList4.display();
		
		System.out.println("\nAfter deleteFromIndex:");
		try {
			tariffList4.deleteFromIndex(3);
		} catch (NoSuchElementException nseex) {
			System.out.println(nseex.getMessage());
		}
		tariffList4.display();
		
		System.out.println("-".repeat(116));
		
		//replaceAtIndex
		//replaces a tariff at an invalid index
		TariffList tariffList5 = new TariffList(tariffList);
		System.out.println("=".repeat(48) + " 7. replaceAtIndex " + "=".repeat(49)
				+ "\n\tI. Replace a tarrif with [France, Bangladesh, Textiles, 8] at an invalid index (-1)");
		tariffList5.replaceAtIndex(t6, -1);
		
		//replaces a tariff at a valid index
		System.out.println("\tII. Replace a tarrif with [France, Bangladesh, Textiles, 8] at a valid index (3)"
				+ "Before replaceAtIndex:");
		//beofre the method
		tariffList5.display();
		
		//after the method called
		System.out.println("\nAfter replaceAtIndex:");
		tariffList5.replaceAtIndex(t6, 3);
		tariffList5.display();
		
		System.out.println("-".repeat(116));

		//find
		System.out.println("=".repeat(53) + " 8. find " + "=".repeat(54));
		TariffList tariffList6 = new TariffList(tariffList);
		tariffList6.display();
		//if tariff is not found
		System.out.println("\n\tI. Use find when tarrif is not found");
		tariffList6.find("Canada", "USA", "Energy");
		
		//if tariff is found
		System.out.println("\n\tII. Usr find when tarrif is found");
		tariffList6.find("USA", "Japan", "Electronics");
		
		System.out.println("-".repeat(116));

		//contains
		System.out.println("=".repeat(51) +" 9. contains " + "=".repeat(52));
		TariffList tarrifList7 = new TariffList(tariffList);
		tarrifList7.display();
		//if contains is true 
		System.out.println("\n\tI. Does the list contains Tarrif: [Brazil, India, Agriculture]");
		System.out.println(tarrifList7.contains("Brazil", "India", "Agriculture"));
		
		//if contains is false
		System.out.println("\n\tII. Does the list contains Tarrif (Tarrif: [Germany, France, Energy])");
		System.out.println(tarrifList7.contains("Germany", "France", "Energy"));
		
		System.out.println("-".repeat(116));

		//equals
		System.out.println("=".repeat(52) +" 10. equals " + "=".repeat(52));
		TariffList tariffList8 = new TariffList(tariffList);
		
		//if equals is true
		System.out.println("\tI. If equals is true");
		tariffList.display();
		System.out.println("");
		tariffList8.display();
		System.out.println("Do both list equal: " + tariffList8.equals(tariffList));
		
		//if equals is false
		System.out.println("\n\tII. If equals is false");
		tariffList8.display();
		System.out.println("");
		tariffList5.display();
		System.out.println("Do both list equal: " + tariffList8.equals(tariffList5));
		
		System.out.println("-".repeat(116));
		
		//evaluateTrade
		System.out.println("=".repeat(48) +" 11. evaluateTrade " + "=".repeat(49));
		readTarrifFile(tl1, bf1);
		readTradeRequestFile(bf2, tradeRequestList);
		for (TradeRequest trades : tradeRequestList) {
			//checks if the tarrif and trade request match
			if (!tl1.contains(trades.getOriginCountry(), trades.getDestinationCountry(), trades.getCategory())) {
				System.out.println(trades.getRequestNumber() + " rejected.\n"
						+ "No match found for trade proposal.");
			}
			else {
				//finds the matching tarrif and tradeRequest 
				Tariffs tariffFound = tl1.findTarrif(trades.getOriginCountry(), trades.getDestinationCountry(), trades.getCategory());
				double minimumTariff = tariffFound.getMinimumTarrif();
				String tradeResult = tl1.evaluateTrade(trades.getProposedTariff(), minimumTariff);

				//if the trade is conditonally accepted, it calculates the surcharge fee
				if (tradeResult.contains("Conditionally Accepted.")) {
					double surcharge = trades.getTradeValue() *((Math.abs(minimumTariff-trades.getProposedTariff()))/100);
					System.out.println(trades.getRequestNumber() + tradeResult + "\n"
							+ "A surchage of $" + surcharge + " will be applied.\n");

				}
				//displays the message
				else {
					System.out.println(trades.getRequestNumber() + tradeResult);
				}
			}
		}
		System.out.println("-".repeat(116) + "\nBack to main menu...");
	}
	
}

