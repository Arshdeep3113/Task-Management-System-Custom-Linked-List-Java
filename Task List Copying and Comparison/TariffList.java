package assignment3_part2;

import java.util.NoSuchElementException;
//Assignment #3
//Question: Part 2
//Written by: Arshdeep Singh (40286514)
//This class implements a linked list of tariffs with various operations like add, delete,
//find, contains and display. It maintains tariff policies between countries and 
//evaluates trade proposals against minimum tariffs.

public class TariffList implements TarrifPolicy {
	//inner clas
	private class TariffNode implements Cloneable {
		//inner class attributes
		private Tariffs tariff;
		private TariffNode link;
		
		//default constructor
		public TariffNode() {
			this.tariff = null;
			this.link = null;
		}
		
		//parametrized constructor
		public TariffNode(Tariffs tarrif, TariffNode link) {
			this.tariff = tarrif;
			this.link = link;
		}

		//copy constructor
		public TariffNode (TariffNode other) {
			if (other == null) {
				this.tariff = null;
				this.link = null;
			}
			else {
				this.tariff = (other.tariff != null) ? new Tariffs(other.tariff) : null;
				this.link = (other.link != null) ? new TariffNode (other.link) : null;
			}
			
		}
		
		//getters and setters
		public Tariffs getTariff() {
			return tariff;
		}
		
		public void setTariff(Tariffs tariff) {
			this.tariff = tariff;
		}
		
		//privacy leak as they expose the internal structure of the list
		public TariffNode getLink() {
			return link;
		}

		//privacy leak as they expose the internal structure of the list
		public void setLink(TariffNode link) {
			this.link = link;
		}
		
		//equals
		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			
			if (getClass() != obj.getClass())
				return false;
			
			TariffNode other = (TariffNode) obj;
			return (this.tariff.equals(other.tariff));
		}
		
		//clone method
		@Override
		public Object clone () throws CloneNotSupportedException {
			TariffNode otherNode = (TariffNode) super.clone();
			
			otherNode.tariff = new Tariffs (this.tariff);
			if (this.link != null) {
				otherNode.link = (TariffNode) this.link.clone();
			}
			else {
				otherNode.link = null;
			}
			
			return otherNode;
		}
	}
	
	//outer class attributes
	private TariffNode head;
	private int size = 0;
	
	//default constructor
	public TariffList () {
		this.head = null;
		this.size = 0;
	}
	
	//copy constructor
	public TariffList (TariffList other) {
		if (other.head == null) {
			this.head = null;
			this.size = 0;
		}
		else {
			this.head = new TariffNode (other.head);
			this.size = other.size;
		}
		
	}
	
	//getters and setters
	//getHead() and setHead() exposes the entire linked list structure to external classes
	public TariffNode getHead() {
		return head;
	}

	public void setHead(TariffNode head) {
		this.head = head;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	//addToStart: adds the tariffs at the head position
	public void addToStart (Tariffs newTarrif) {
		head = new TariffNode (newTarrif, head);
		size++;
	}

	//insertAtIndex: adds the tarrif at the index (between 0 and size-1)
	public void insertAtIndex (Tariffs newTarrif, int index)  throws NoSuchElementException {
		
		if (index < 0 || index >= size) {
			throw new NoSuchElementException("Invalid index entered.");
		}

		// Insert at the start
		if (index == 0) {
			addToStart(newTarrif);
		}
		
		//if index is in the middle or tail
		else {
			TariffNode newNode;
			TariffNode position = head;
			
			for (int i = 0; i < index - 1; i++) {
				position = position.link;
			}
			
			newNode = new TariffNode (newTarrif, position.link);
			position.link = newNode;
			size++;
			}

		
	}
	
	//deleteFromIndex: deletes the tariff at the index (between 0 and size-1)
	public Tariffs deleteFromIndex (int index) throws NoSuchElementException {
		if (index < 0 || index >= size) {
			throw new NoSuchElementException ("Please enter a index within the size limit");
		}
		//works
		else if (index == 0) {
			return deleteFromStart();
		}
		
		else {
			TariffNode position = head;
			for (int i = 0; i < index - 1; i++) {
				position = position.link;
			}
			Tariffs data = position.link.tariff;
			position.link = position.link.link;
			size--;
			return data;
		}
	}
	
	//deleteFromStart: delete the tariff at the head
	public Tariffs deleteFromStart () throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException ("There is no element in the list.");
		}
		
		else {
			Tariffs data = head.tariff;
			head = head.link;
			size--;
			return data;
		}
	}
	
	//replaceAtIndex: replaces the old tariff at a index with a new one
	public void replaceAtIndex (Tariffs newTarrif, int index) {
		if (index < 0 || index >= size) {
			System.out.println("Invalid index!");
			return;
		}
		
		else {
			TariffNode position = head;
			for (int i = 0; i < index; i++) {
				position = position.link;
			}
			position.tariff = newTarrif;
		}
	}
	
	//find: finds the tariff and returns the number of iterations if found
	public TariffNode find (String origin, String destination, String category) {
		int counter = 0;
		TariffNode position = head;
		while (position != null) {
			counter++;
			if (position.tariff.getOriginCountry().equals(origin) &&
				position.tariff.getDestinationCountry().equals(destination) &&
				position.tariff.getProductCategory().equals(category)) {
				System.out.println("Tarrif: " + "[" + origin + ", " + destination + ", " + category + "] was found.\n"
						+ "Number of iterations before Tarrif was found: " + counter + " iterations.");
				return new TariffNode (position);
			}
			position = position.link;
			
			
		}
		System.out.println("No match found for tarrif " + origin + ", " + destination + ", " + category);
		return null;
	}
	
	//contains: returns true or false if the tariff is found
	public boolean contains (String origin, String destination, String category) {
		TariffNode position = head;
		while (position != null) {
			if (position.tariff.getOriginCountry().equals(origin) &&
				position.tariff.getDestinationCountry().equals(destination) &&
				position.tariff.getProductCategory().equals(category)) {
				return true;
			}
			position = position.link;
		}
		return false;
	}
	
	//equals: equals (overload) checks if two lists are equivalent
	public boolean equals (TariffList otherList) {
		TariffNode positionOfInitialList = this.head;
		TariffNode positionOfOtherList = otherList.head;
		
		if (this.size != otherList.size) {
			return false;
		}
		
		while (positionOfInitialList != null && positionOfOtherList != null) {
			if (positionOfInitialList.tariff.equals(positionOfOtherList.tariff)) {
				positionOfInitialList = positionOfInitialList.link;
				positionOfOtherList = positionOfOtherList.link;
			}
			else {
				return false;
			}
		}
		return true;
	}

	@Override
	//evaluateTrade: evaluates proposed trades and displays a message
	public String evaluateTrade(double proposedTarrif, double minimumTarrif) {
		String evaluationResult = null;
		//trade is accepted: if the proposed tariff is equal or greater than minimum tariff
		if (proposedTarrif >= minimumTarrif) {
			evaluationResult = " Accepted.\n"
					+ "Proposed tariff meets or exceeds the minimum requirement.\n";
		}
		//trade is conditionally accepted: if the proposed tariff is within the acceptable range of 20%
		else if (proposedTarrif >= (minimumTarrif*0.8)) {
			evaluationResult = " Conditionally Accepted.\n"
					+ "Proposed tariff " + proposedTarrif + " is within 20% of the required minimum tariff " + minimumTarrif + "%.";
		}
		//tade is rejected: if the proposed trade is lower than the acceptable range
		else {
			evaluationResult = " Rejected.\n"
					+ "Proposed tariff " + proposedTarrif + "% is more than 20% below the required minimum tariff " + minimumTarrif + "%.\n";
		}
		
		return evaluationResult;
	}
	
	//display: displays all tariff in the list
	public void display () {
		if (head == null) {
			System.out.println("No elements!");
		}
		else {
			int pos = 0;
			System.out.println("Your list has " + size + " element(s): ");
			TariffNode position = head;
			while (position != null) {
				if (position.tariff == null) {
					continue;
				}
				System.out.println(pos + ". " + position.tariff); //display
				position = position.link; //moves to the next one
				pos++;
			}
		}
		
	}
	
	//findTariff: finds the tariff and returns the tariff at that tariffNode
	public Tariffs findTarrif (String origin, String destination, String category) {
		TariffNode position = head;
		while (position != null) {
			if (position.tariff.getOriginCountry().equals(origin) &&
				position.tariff.getDestinationCountry().equals(destination) &&
				position.tariff.getProductCategory().equals(category)) {
				
				return new Tariffs (position.tariff);
			}
			position = position.link;
		
		}
		System.out.println("No match found for tarrif " + origin + ", " + destination + ", " + category);
		return null;
		
	}
	
}
