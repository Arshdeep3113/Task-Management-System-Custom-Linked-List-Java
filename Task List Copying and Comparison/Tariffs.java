package assignment3_part2;

//Assignment #3
//Question: Part 2
//Written by: Arshdeep Singh (40286514)
//
//This class represents individual tariff entries with destination country, origin country, 
//product category and rate information. It implements cloning and comparison operations 
//for tariff objects.

public class Tariffs implements Cloneable {
	//attributes
	private String destinationCountry;
	private String originCountry;
	private String productCategory;
	private double minimumTariff;
	
	/**
	 * @param destinationCountry
	 * @param originCountry
	 * @param productCategory
	 * @param minimumTariff
	 */
	public Tariffs(String destinationCountry, String originCountry, String productCategory, double minimumTariff) {
		this.destinationCountry = destinationCountry;
		this.originCountry = originCountry;
		this.productCategory = productCategory;
		this.minimumTariff = minimumTariff;
	}
	
	//copy constructor
	public Tariffs (Tariffs otherTarrif) {
		this(otherTarrif.destinationCountry, otherTarrif.originCountry, otherTarrif.productCategory, otherTarrif.minimumTariff);
	}
	
	//setters and getters
	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public double getMinimumTarrif() {
		return minimumTariff;
	}

	public void setMinimumTarrif(double minimumTariff) {
		this.minimumTariff = minimumTariff;
	}
	
	//toString
	@Override
	public String toString() {
		return "[destinationCountry: " + destinationCountry + ", originCountry: " + originCountry
				+ ", productCategory: " + productCategory + ", minimumTarrif: " + minimumTariff + "%]";
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (this.getClass() != obj.getClass())
			return false;
		
		Tariffs other = (Tariffs) obj;
		final double EPISLON = 0.00001;
		double difference = Math.abs(this.minimumTariff - other.minimumTariff);
		
		return this.destinationCountry.equals(other.destinationCountry)
				&& this.destinationCountry.equals(other.originCountry)
				&& this.productCategory.equals(other.productCategory)
				&& difference < EPISLON;
	}
	
	//clone method
	@Override
	public Object clone () throws CloneNotSupportedException {
		Tariffs tarrif = (Tariffs) super.clone();
		
		tarrif.destinationCountry = this.destinationCountry;
		tarrif.originCountry = this.originCountry;
		tarrif.productCategory = this.productCategory;
		tarrif.minimumTariff = this.minimumTariff;
		
		return tarrif;
	}
}
