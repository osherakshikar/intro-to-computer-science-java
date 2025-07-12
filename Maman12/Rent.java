package Maman12;

/**
 * Represents a car rental record containing customer information, car details, and rental period.
 * This class manages rental transactions including pricing calculations, car upgrades, and overlap detection.
 *
 * @author Osher Akshikar
 * @version 2023a
 */
public class Rent {
    /**
     * The name of the customer renting the car
     */
    private String _name;

    /**
     * The car being rented
     */
    private Car _car;

    /**
     * The date when the rental period starts
     */
    private Date _pickDate;

    /**
     * The date when the rental period ends
     */
    private Date _returnDate;

    /**
     * Daily rental price for car type A
     */
    private final int PRICE_A = 100;

    /**
     * Daily rental price for car type B
     */
    private final int PRICE_B = 150;

    /**
     * Daily rental price for car type C
     */
    private final int PRICE_C = 180;

    /**
     * Daily rental price for car type D
     */
    private final int PRICE_D = 240;

    /**
     * Discount factor applied to weekly rentals (10% discount)
     */
    private final double DISCOUNT = 0.9;

    /**
     * Number of days in a week for pricing calculations
     */
    private final int WEEK_LENGTH = 7;

    /**
     * Constructs a new Rent object with the specified parameters.
     * If the return date is before or equal to the pick date, the return date is set to the day after pick date.
     *
     * @param name The customer's name
     * @param car  The car to be rented
     * @param pick The pickup date
     * @param ret  The return date
     */
    public Rent(String name, Car car, Date pick, Date ret) {
        _name = name;
        _car = new Car(car);
        _pickDate = new Date(pick);
        if (pick.before(ret)) {
            _returnDate = new Date(ret);
        } else {
            _returnDate = new Date(pick.tomorrow());
        }
    }

    /**
     * Copy constructor that creates a new Rent object as a copy of another Rent object.
     *
     * @param other The Rent object to copy. If null, creates an empty object.
     */
    public Rent(Rent other) {
        if (other != null) {
            _name = other._name;
            _car = new Car(other._car);
            _pickDate = new Date(other._pickDate);
            _returnDate = new Date(other._returnDate);
        }
    }

    /**
     * Returns a copy of the car being rented.
     *
     * @return A new Car object that is a copy of the rented car
     */
    public Car getCar() {
        return new Car(_car);
    }

    /**
     * Returns the customer's name.
     *
     * @return The name of the customer
     */
    public String getName() {
        return _name;
    }

    /**
     * Returns a copy of the pickup date.
     *
     * @return A new Date object representing the pickup date
     */
    public Date getPickDate() {
        return new Date(_pickDate);
    }

    /**
     * Returns a copy of the return date.
     *
     * @return A new Date object representing the return date
     */
    public Date getReturnDate() {
        return new Date(_returnDate);
    }

    /**
     * Sets the car for this rental.
     *
     * @param car The new car to set
     */
    public void setCar(Car car) {
        _car = new Car(car);
    }

    /**
     * Sets the customer's name.
     *
     * @param name The new customer name
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * Sets the pickup date if it's before the current return date.
     *
     * @param pickDate The new pickup date
     */
    public void setPickDate(Date pickDate) {
        if (pickDate.before(_returnDate)) {
            _pickDate = new Date(pickDate);
        }
    }

    /**
     * Sets the return date if it's after the current pickup date.
     *
     * @param returnDate The new return date
     */
    public void setReturnDate(Date returnDate) {
        if (returnDate.after(_pickDate)) _returnDate = new Date(returnDate);
    }

    /**
     * Checks if this rental is equal to another rental.
     * Two rentals are considered equal if they have the same customer name, car, pickup date, and return date.
     *
     * @param other The other Rent object to compare with
     * @return true if the rentals are equal, false otherwise
     */
    public boolean equals(Rent other) {
        if (other == null) return false;
        return other.getCar().equals(_car) && other.getName().equals(_name) && other.getPickDate().equals(_pickDate) && getReturnDate().equals(_returnDate);
    }

    /**
     * Calculates the number of days in the rental period.
     *
     * @return The number of days between pickup and return dates
     */
    public int howManyDays() {
        return _pickDate.difference(_returnDate);
    }

    /**
     * Calculates the total price of the rental.
     * The price is calculated based on the number of rental days. A discount is applied for full weeks.
     * The daily rate depends on the car's type.
     *
     * @return The total price for the rent
     */
    public int getPrice() {
        int weeks = howManyDays() / 7;
        int regularDays = howManyDays() % 7;
        double totalDays = (DISCOUNT * weeks * WEEK_LENGTH + regularDays);

        switch (_car.getType()) {
            case 'A':
                return (int) (PRICE_A * totalDays);
            case 'B':
                return (int) (PRICE_B * totalDays);
            case 'C':
                return (int) (PRICE_C * totalDays);
            default:
                return (int) (PRICE_D * totalDays);
        }
    }

    /**
     * Upgrades the car for the current rental to a better one.
     * If the new car is better than the current car, the car is replaced and the price difference is returned.
     *
     * @param newCar The new car to upgrade to
     * @return The difference in price after the upgrade. If the new car is not better, returns 0
     */
    public int upgrade(Car newCar) {
        if (newCar.better(_car)) {
            int priceNow = this.getPrice();
            _car = new Car(newCar);
            int priceAfter = this.getPrice();
            return (priceAfter - priceNow);
        } else return 0;
    }

    /**
     * Checks if another rental overlaps with this one and returns the merged rental if they do.
     * Two rentals overlap if they have the same customer name and car, and their rental periods intersect.
     * If there is an overlap, a new Rent object is returned representing the union of the two rental periods.
     *
     * @param other The other rental to check for overlap
     * @return A new Rent object representing the union of the rental periods if they overlap, otherwise null
     */
    public Rent overlap(Rent other) {
        // if name or car are different, return null
        if (!other.getName().equals(this._name) || !other.getCar().equals(this.getCar())) return null;

        Date thisPick = this.getPickDate();
        Date thisReturn = this.getReturnDate();
        Date otherPick = other.getPickDate();
        Date otherReturn = other.getReturnDate();

        // No overlap if one ends before the other begins
        // Note: if one ends on the same day the other begins, it is considered an overlap
        if (thisReturn.before(otherPick) || otherReturn.before(thisPick)) {
            return null;
        }

        // Overlap exists, calculate the union of the two periods
        Date unionPick = thisPick.before(otherPick) ? new Date(thisPick) : new Date(otherPick);
        Date unionReturn = thisReturn.after(otherReturn) ? new Date(thisReturn) : new Date(otherReturn);

        return new Rent(this._name, new Car(this._car), unionPick, unionReturn);
    }

    /**
     * Returns a string representation of the rental.
     *
     * @return A formatted string containing rental details including name, dates, car type, days, and price
     */
    public String toString() {
        return "Name:" + _name + " From:" + _pickDate + " To:" + _returnDate + " Type:" + _car.getType() + " Days:" + howManyDays() + " Price:" + getPrice();
    }

}
