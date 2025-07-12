package Maman14;


import Maman12.Rent;

/**
 * The RentNode class will represent one rental in the company
 *
 * @author Osher Akshikar
 * @version 2023a
 */
public class RentNode {
    private Rent _rent;
    private RentNode _next;

    /**
     * Creates a new RentNode object
     *
     * @param r rent object
     */
    public RentNode(Rent r) {
        _rent = r;
        _next = null;
    }

    /**
     * The constructor creates a new RentNode object with a rent value and a link to the next RentNode
     *
     * @param r    First rent value
     * @param next Next RentNode to be linked
     */
    public RentNode(Rent r, RentNode next) {
        _rent = new Rent(r);
        _next = next;
    }

    /**
     * Copy constructor for RentNode class
     *
     * @param other The RentNode object to be copied.
     */
    public RentNode(RentNode other) {
        _rent = new Rent(other._rent);
        _next = other._next;

    }

    /**
     * Returns a new Rent object that is a copy of the Rent object stored in this RentNode
     *
     * @return A new Rent object
     */
    public Rent getRent() {
        return new Rent(_rent);
    }

    /**
     * Returns the next RentNode in the list
     *
     * @return RentNode the next in the list
     */
    public RentNode getNext() {
        return _next;
    }

    /**
     * Sets the Rent object for this RentNode
     *
     * @param r The Rent object to set
     */
    public void setRent(Rent r) {
        _rent = new Rent(r);
    }

    /**
     * Sets the next RentNode in the linked list.
     *
     * @param next the RentNode to set as the next node in the list
     */
    public void setNext(RentNode next) {
        _next = next;
    }
}