package assignment3_part1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import products.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;

//Assignment #3
//Question: Part 1
//Written by: Arshdeep Singh (40286514)
//
//This is the main driver class that processes trade data, applies tariffs, and generates updated reports.
//It reads product data, applies country-specific tariffs, and outputs sorted results.

public class DriverClass {
	public static void main (String[] args) {
		ArrayList<Product> productList = new ArrayList<>();
		BufferedReader bf1 = null;
		PrintWriter pw1 = null;

		readTradeDataFile(bf1, productList);
		Collections.sort(productList);
		writeToUpdateTradeData(pw1, productList);	
	}
	
	//reads the TradeData file 
	public static void readTradeDataFile (BufferedReader bf, ArrayList<Product> productList) {
		try {
			 bf = new BufferedReader (new FileReader ("TradeData.txt"));
			 
			 
		} catch (FileNotFoundException fnfex) {
			System.out.println("File not found!");
			return;
		} 
		
		
		try {
			String line;
			//reads the line
			while ((line = bf.readLine()) != null) {
				//seperates each element into a token
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				//checks if there are any invalid lines in the file
				if (tokenizer.countTokens() != 4) {
					continue;
				}
				//distingues each element
				String productName = tokenizer.nextToken();
				String country = tokenizer.nextToken();
				String category = tokenizer.nextToken();
				double price = Double.parseDouble(tokenizer.nextToken());
				
				//stores info into a an object of Product
				productList.add(new Product (productName, country, category, price));
				}
		} 
		catch (IOException ioex) {
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
		
		
		try {
			bf.close();
		} catch (IOException ioex) {
			System.out.println("Could not read file!");
			return;
		}
	}
	
	//writes and displays the info into UpdateTradeData
	public static void writeToUpdateTradeData (PrintWriter pw, ArrayList<Product> productList) {
		try {
			pw = new PrintWriter (new FileOutputStream ("UpdatedTradeData.txt"));
		} catch (FileNotFoundException fnfex) {
			System.out.println("File not found!");
		}
		
		//aplies the tariff rates
		for (Product products : productList) {
			applyTarrifs(products);
		}
		
		//displays the updated trade data
		for (Product products : productList) {
			System.out.println(products);
		}
		
		//writes the updated trade data in the file
		for (Product products : productList) {
			pw.println(products);
		} 
		
		pw.close();
	}
	
	//applies the tarrif amount
	public static void applyTarrifs (Product product) {
		String country = product.getCountry();
		double newPrice;
		switch (country) {
		//China, on all products
		case "China":
			newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.25);
			product.setIntialPrice(newPrice);
			break;
			//USA, specifically on electronics
		case "USA":
			if (product.getCategory().equals("Electronics")) {
				newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.10);
				product.setIntialPrice(newPrice);
			}
			break;
			//Japan, specifically on automobiles
		case "Japan":
			if (product.getCategory().equals("Automobile")) {
				newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.15);
				product.setIntialPrice(newPrice);
			}
			break;
			//India, specifically on agriculture
		case "India":
			if (product.getCategory().equals("Agriculture")) {
				newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.05);
				product.setIntialPrice(newPrice);
			}
			break;
			//South Korea, specifically on electronics
		case "South Korea":
			if (product.getCategory().equals("Electronics")) {
				newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.08);
				product.setIntialPrice(newPrice);
			}
			break;
			//Saudi Arabia, specifically on energy
		case "Saudi Arabia":
			if (product.getCategory().equals("Energy")) {
				newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.12);
				product.setIntialPrice(newPrice);
			}
			break;
			//Germany, specifically manufacturing
		case "Germany":
			if (product.getCategory().equals("Manufacturing")) {
				newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.06);
				product.setIntialPrice(newPrice);
			}
			break;
			//Bangladesh, specifically on textile
		case "Bangladesh":
			if (product.getCategory().equals("Textile")) {
				newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.04);
				product.setIntialPrice(newPrice);
			}
			break;
			//Brazil, specifically on agriculture
		case "Brazil":
			if (product.getCategory().equals("Agriculture")) {
				newPrice = product.getIntialPrice() + (product.getIntialPrice()*0.09);
				product.setIntialPrice(newPrice);
			}
			break;
		default:
			product.getIntialPrice();
			break;
		}
	}

}





