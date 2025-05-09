package assignment3_part2;

//Assignment #3
//Question: Part 2
//Written by: Arshdeep Singh (40286514)
//
//This class represents trade requests with proposed tariffs between countries for 
//specific products.

public class TradeRequest {
	//attributes
	private String requestNumber;
	private String originCountry;
	private String destinationCountry;
	private String category;
	private double tradeValue;
	private double proposedTariff;
	
	//parametrized constructor
	public TradeRequest(String requestNumber, String originCountry, String destinationCountry, String category,
			double tradeValue, double proposedTariff) {
		this.requestNumber = requestNumber;
		this.originCountry = originCountry;
		this.destinationCountry = destinationCountry;
		this.category = category;
		this.tradeValue = tradeValue;
		this.proposedTariff = proposedTariff;
	}
	
	//setters and getters
	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(double tradeValue) {
		this.tradeValue = tradeValue;
	}

	public double getProposedTariff() {
		return proposedTariff;
	}

	public void setProposedTariff(double proposedTariff) {
		this.proposedTariff = proposedTariff;
	}
	
	//toString
	@Override
	public String toString() {
		return "[requestNumber: " + requestNumber + ", originCountry: " + originCountry
				+ ", destinationCountry: " + destinationCountry + ", category: " + category + ", tradeValue: " + tradeValue
				+ ", proposedTarrif: " + proposedTariff + "]";
	}
	
	

}
