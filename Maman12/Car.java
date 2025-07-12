package Maman12;

/**
 * This class represents a Car Object.
 * It contains attributes such as id, type, brand, and whether it is manual or automatic.
 * It provides methods to get and set these attributes, compare cars, and check if one car is better or worse than another.
 * It also includes a copy constructor and a toString method for easy representation of the car object.
 * The id must be a 7-digit number (between 1,000,000 and 9,999,999).
 * The type must be one of 'A', 'B', 'C', or 'D'.
 * The brand is a string representing the car's brand.
 * The isManual is a boolean indicating if the car is manual (true) or automatic (false).
 *
 * @author Osher Akshikar
 * @version 2023a
 */
public class Car {
    private int _id;
    private char _type;
    private String _brand;
    private boolean _isManual;

    private static final int MIN_ID = 999999;
    private static final int MAX_ID = 10000000;
    private static final int DEFAULT_ID = 9999999;

    /**
     * Constructor for objects of class Car.
     * If the id is not a 7-digit number, it will be set to 9999999.
     * If the type is not 'A', 'B', 'C', or 'D', it will be set to 'A'.
     *
     * @param id       The id of the car (must be a 7-digit number).
     * @param type     The type of the car (A, B, C, or D).
     * @param brand    The brand of the car.
     * @param isManual Whether the car is manual or not.
     */
    public Car(int id, char type, String brand, boolean isManual) {
        this._id = id;
        if (id <= MIN_ID || id >= MAX_ID) {
            this._id = DEFAULT_ID; // Default value if id is invalid
        }
        if (type == 'A' || type == 'B' || type == 'C' || type == 'D') {
            this._type = type;
        } else { // Default value if type is invalid
            this._type = 'A';
        }
        this._brand = brand;
        this._isManual = isManual;
    }

    /**
     * Copy constructor for objects of class Car.
     *
     * @param other The car to copy.
     */
    public Car(Car other) {
        if (other != null) {
            this._id = other.getId();
            this._type = other.getType();
            this._brand = other.getBrand();
            this._isManual = other.isManual();
        } else {
            this._id = DEFAULT_ID; // Default value if other is null
            this._type = 'A'; // Default type
            this._brand = "Unknown"; // Default brand
            this._isManual = false; // Default manual flag
        }
    }

    /**
     * Returns the id of the car.
     *
     * @return The id of the car.
     */
    public int getId() {
        return _id;
    }

    /**
     * Returns the type of the car.
     *
     * @return The type of the car.
     */
    public char getType() {
        return _type;
    }

    /**
     * Returns the brand of the car.
     *
     * @return The brand of the car.
     */
    public String getBrand() {
        return _brand;
    }

    /**
     * Returns whether the car is manual or not.
     *
     * @return Whether the car is manual or not.
     */
    public boolean isManual() {
        return _isManual;
    }

    /**
     * Sets the id of the car.
     * The id must be a 7-digit number (between 1,000,000 and 9,999,999).
     * If the id is invalid, it will be set to 9999999.
     *
     * @param id The id of the car.
     */
    public void setId(int id) {
        if (id > MIN_ID && id < MAX_ID) {
            this._id = id;
        } else {
            this._id = DEFAULT_ID; // Default value if id is invalid
        }
    }

    /**
     * Sets the type of the car.
     * The type must be one of 'A', 'B', 'C', or 'D'.
     * If the type is invalid, it will be set to 'A'.
     *
     * @param type The type of the car.
     */
    public void setType(char type) {
        if (type == 'A' || type == 'B' || type == 'C' || type == 'D') {
            this._type = type;
        } else {
            this._type = 'A'; // Default value if type is invalid
        }
    }

    /**
     * Sets the brand of the car.
     *
     * @param brand The brand of the car.
     */
    public void setBrand(String brand) {
        _brand = brand;
    }

    /**
     * Sets whether the car is manual or not.
     *
     * @param isManual Whether the car is manual or not.
     */
    public void setIsManual(boolean isManual) {
        _isManual = isManual;
    }

    /**
     * Returns a string representation of the car.
     *
     * @return A string representation of the car.
     */
    public String toString() {
        return "id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + (_isManual ? "manual" : "auto");
    }

    /**
     * Checks if this car is the same as another car.
     *
     * @param other The car to compare to.
     * @return True if the cars are the same, false otherwise.
     */
    public boolean equals(Car other) {
        if (other == null) {
            return false;
        }
        return this._type == other.getType() && this._brand.equals(other.getBrand()) && this._isManual == other.isManual();
    }

    /**
     * Checks if this car is better than another car.
     * A car is better if its type is higher (e.g., 'B' is better than 'A').
     * If types are equal, an automatic car is better than a manual one.
     *
     * @param other The car to compare to.
     * @return True if this car is better than the other car, false otherwise.
     */
    public boolean better(Car other) {
        if (other == null) {
            return false;
        }
        return this._type > other.getType() || (this._type == other.getType() && !this._isManual && other.isManual());
    }

    /**
     * Checks if this car is worse than another car.
     *
     * @param other The car to compare to.
     * @return True if this car is worse than the other car, false otherwise.
     */
    public boolean worse(Car other) {
        return !this.better(other) && !this.equals(other);
    }

}