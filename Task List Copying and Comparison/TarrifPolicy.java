package assignment3_part2;

//Assignment #3
//Question: Part 2
//Written by: Arshdeep Singh (40286514)
//
//This interface defines a method for evaluating trade proposals against minimum tariff.

public interface TarrifPolicy {
	
	//method
	public String evaluateTrade (double proposedTrade, double minimumTariff);

}
