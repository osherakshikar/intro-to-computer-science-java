package Maman14;

import Maman12.*;

/**
 * The Company class represents a car rental company using a linked list and therefore
 * There is no limit here on the number of rentals represented in the list.
 *
 * @author Osher Akshikar
 * @version 2023a
 */
public class Company {
    private RentNode _head;

    /**
     * Constructor to initialize the _head variable to null.
     */
    public Company() {
        _head = null;
    }

    /**
     * This method adds a new Rent to the Company's list of Rentals
     * This method checks if the new RentNode should be set as the head of the list ,
     * or if it should be placed in correct chronological order
     * If the new Rent is already present in the company's list, the method returns false.
     *
     * @param name  - car name
     * @param c     - car type
     * @param start - start date of the rental
     * @param end   - end date of the rental
     * @return true if the Rent is successfully added, false if the Rent is already present in the list.
     */
    public boolean addRent(String name, Car c, Date start, Date end) {
        if (name == null || c == null || start == null || end == null) {
            return false; // Handle null parameters
        }

        Rent newRent = new Rent(name, c, start, end); //creating new object rent
        RentNode newNodeRent = new RentNode(newRent); //creating new node

        if (_head == null) { //in case of empty list
            _head = newNodeRent;
            return true;
        }

        // Check if new node should be the new head
        if (shouldComeBefore(newNodeRent, _head)) {
            newNodeRent.setNext(_head);
            _head = newNodeRent;
            return true;
        }

        // Check for duplicates and find insertion point
        RentNode current = _head;
        while (current.getNext() != null) {
            if (current.getRent().equals(newRent)) // Check for duplicate
                return false;

            if (shouldComeBefore(newNodeRent, current.getNext())) {
                // Insert between current and current.next
                newNodeRent.setNext(current.getNext());
                current.setNext(newNodeRent);
                return true;
            }
            current = current.getNext();
        }

        // Check last node for duplicate
        if (current.getRent().equals(newRent))
            return false;

        // Add at the end
        current.setNext(newNodeRent);
        return true;
    }

    private boolean shouldComeBefore(RentNode first, RentNode second) { // Method to check if first rent should come before second rent
        Date pickFirst = first.getRent().getPickDate(); // Date pickFirst gets the rent date of first Node
        Date pickSecond = second.getRent().getPickDate(); // Date pickSecond gets the rent date of second Node

        if (pickFirst.before(pickSecond))
            return true; //return true if the first rent date is before second rent
        if (pickFirst.after(pickSecond))
            return false; //return false if second rent date is before the first rent

        int daysFirst = first.getRent().howManyDays();//checks rent duration if pick dates are the same
        int daysSecond = second.getRent().howManyDays();

        return daysFirst > daysSecond; //return if the first Node should be before the second Node based on duration
    }

    /**
     * Removes a RentNode from the linked list based on the return date of the Rent object inside the node
     *
     * @param d the return date of the Rent object to be removed
     * @return true if a RentNode was removed,false otherwise.
     */
    public boolean removeRent(Date d) {
        if (_head == null) // in case of empty list
            return false;

        else {
            RentNode behind = _head;

            if (_head.getRent().getReturnDate().equals(d)) { //checks if the first Node equals the date
                _head = _head.getNext();
                behind.setNext(null);
                return true;
            } else {
                while (behind.getNext() != null) { // check the rest of Node list
                    if (behind.getNext().getRent().getReturnDate().equals(d)) {
                        RentNode cancel = behind.getNext();
                        behind.setNext(cancel.getNext());
                        cancel.setNext(null);
                        return true;
                    } else
                        behind = behind.getNext();
                }//end of while
                return false; // reached to end and didn;t found the date given
            }//end of else

        }//end of (list is not empty)
    }//end of method

    /**
     * This method is used to get the number of rentals made by the company
     *
     * @return int, the number of rentals made
     */
    public int getNumOfRents() {
        RentNode temp = _head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    /**
     * This method returns the total sum of prices of all the rents in the company.
     *
     * @return the total sum of prices in the company
     */
    public int getSumOfPrices() {
        RentNode temp = _head;
        int prices = 0;
        while (temp != null) {
            prices += temp.getRent().getPrice();
            temp = temp.getNext();
        }
        return prices;
    }

    /**
     * This method calculates the total sum of days of all the rents in the company
     *
     * @return int - the total number of days of all the rents in the company.
     */
    public int getSumOfDays() {
        RentNode temp = _head;
        int days = 0;
        while (temp != null) {
            days += temp.getRent().howManyDays();
            temp = temp.getNext();
        }
        return days;
    }

    /**
     * @return the average number of days of renting for the company
     * If there are no rents, returns 0.
     */
    public double averageRent() {
        if (_head == null)
            return 0;
        return (double) getSumOfDays() / getNumOfRents();
    }

    /**
     * This method returns the last rented car from the list of rents in the company.
     * If there are no rents in the company, the method returns 'null'.
     *
     * @return the last rented car or null if there are no rents
     */
    public Car lastCarRent() {
        if (_head == null)
            return null; // no rents
        RentNode curr = _head;
        Rent lastRent = curr.getRent();
        while (curr != null) { //end of loop
            if (curr.getRent().getReturnDate().after(lastRent.getReturnDate())) { //check if curr return date is after last date if it is last date gets curr return date
                lastRent = curr.getRent();
            }
            curr = curr.getNext();
        }
        return new Car(lastRent.getCar());

    }

    /**
     * This method is used to find the longest rent in the linked list.
     *
     * @return the longest rent in the company
     */
    public Rent longestRent() {
        if (_head == null)
            return null; // no rents
        RentNode curr = _head;
        Rent rentLongest = curr.getRent();
        int longest = curr.getRent().howManyDays();
        while (curr != null) {
            if (curr.getRent().howManyDays() > longest) {
                longest = curr.getRent().howManyDays();
                rentLongest = curr.getRent();
            }
            curr = curr.getNext();
        }//end of while loop
        return new Rent(rentLongest);
    }

    /**
     * This method returns the most common car type among all the the rents in the list.
     *
     * @return the most common car type as a character('A','B','C', or 'D') 'N' if the list is empty.
     */
    public char mostCommonRate() {
        RentNode curr = _head;
        if (curr == null) { //empty list
            return 'N';
        }
        int[] counts = new int[4];
        while (curr != null) {
            char type = curr.getRent().getCar().getType();
            switch (type) {
                case 'A':
                    counts[0]++;
                    break;
                case 'B':
                    counts[1]++;
                    break;
                case 'C':
                    counts[2]++;
                    break;
                case 'D':
                    counts[3]++;
                    break;
            }
            curr = curr.getNext();
        }
        int common = Math.max(Math.max(counts[0], counts[1]), Math.max(counts[2], counts[3])); // find out which car is the most common
        if (common == counts[3])
            return 'D';
        if (common == counts[2])
            return 'C';
        if (common == counts[1])
            return 'B';
        else
            return 'A';
    }

    /**
     * This method checks if the current company object includes all the rent objects from another company object.
     *
     * @param other The other company object to be compared with the current object.
     * @return true if the current object includes all the rent objects from the other object, false otherwise.
     */
    public boolean includes(Company other) {
        RentNode curr = _head;
        RentNode otherNode = other._head;
        while (otherNode != null && curr != null) {
            if (otherNode.getRent().equals(curr.getRent())) {
                otherNode = otherNode.getNext();
            }
            curr = curr.getNext();
        }
        return otherNode == null;
    }

    /**
     * This method merges the current company object with another company object by adding all the rents from the
     * other company to the current company.
     *
     * @param other The other company object that is being merged with the current company
     */
    public void merge(Company other) {
        RentNode otherNode = other._head;
        while (otherNode != null) {
            addRent(otherNode.getRent().getName(), otherNode.getRent().getCar(), otherNode.getRent().getPickDate(), otherNode.getRent()
                    .getReturnDate());//using addRent method written before
            otherNode = otherNode.getNext();
        }
    }

    /**
     * This method returns a string representation of the company, including the number of rents and the details of each rent.
     * If the company has no rents, it will return a message indicating this.
     *
     * @return a string representation of the company and its rents.
     */
    public String toString() {
        if (_head == null) //no rents
            return "The company has " + getNumOfRents() + " rents.";
        String s = "The company has " + getNumOfRents() + " rents:\n";
        RentNode curr = _head;
        while (curr != null) {
            s += curr.getRent().toString() + "\n";
            curr = curr.getNext();
        }
        return s;
    }
}
