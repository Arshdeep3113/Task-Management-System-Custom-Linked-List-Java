package products;

//Assignment #3
//Question: Part 1
//Written by: Arshdeep Singh (40286514)
//
//This class represents product information including name, origin country, category and price.
//It implements Comparable for sorting products alphabetically by name.

public class Product implements Comparable {
	//attributes
	private String productName;
	private String country;
	private String category;
	private double intialPrice;
	
	//default constructors
	public Product () {
		this.productName = "";
		this.country = "";
		this.category = "";
		this.intialPrice = 0;
	}
	
	/**
	 * @param productName
	 * @param country
	 * @param category
	 * @param intialPrice
	 */
	public Product (String productName, String country, String category, double intialPrice) {
		this.productName = productName;
		this.country = country;
		this.category = category;
		this.intialPrice = intialPrice;
	}
	
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the intialPrice
	 */
	public double getIntialPrice() {
		return intialPrice;
	}

	/**
	 * @param intialPrice the intialPrice to set
	 */
	public void setIntialPrice(double intialPrice) {
		this.intialPrice = intialPrice;
	}
	
	//toString
	@Override
	public String toString() {
		return productName + ", " + country + ", " + category + ", " + intialPrice;
	}
	
	//comparesTo
	@Override 
	public int compareTo (Object otherObject) {
		Product otherProduct = (Product) otherObject;
		int productNameComparison = this.productName.compareTo(otherProduct.getProductName()); 
		
		//sorts from alphabetical order
		if (productNameComparison < 0) {
			return -1;
		}
		else if (productNameComparison > 0) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	

}
