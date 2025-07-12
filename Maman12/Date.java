package Maman12;

/**
 * This class represents a Date with day, month, and year.
 * The date must be valid according to the Gregorian calendar rules.
 * The class provides methods for date manipulation, comparison, and calculation.
 * The valid year range is from 1000 to 9999.
 * If an invalid date is provided, it defaults to 01/01/2000.
 *
 * @author Osher Akshikar
 * @version 2023a
 */
public class Date {

    /**
     * Day of the month
     */
    private int _day;
    /**
     * Month of the year
     */
    private int _month;
    /**
     * Year (4 digits)
     */
    private int _year;

    /**
     * Constants representing months
     */
    private static final int JAN = 1, FEB = 2, MARCH = 3, APRIL = 4, MAY = 5, JUN = 6, JUL = 7, AUG = 8, SEP = 9, OCT = 10, NOV = 11, DEC = 12;

    /**
     * Boundary constants for date validation
     */
    private static final int MAX_YEAR = 9999, MIN_YEAR = 1000, MAX_DAY = 31, MIN_DAY = 1, NUM_29 = 29, NUM_30 = 30;

    /**
     * Calculates the number of days from 1/1/0001 to the given date.
     * Uses the Gregorian calendar algorithm.
     *
     * @param day   the day of the month (1-31)
     * @param month the month of the year (1-12)
     * @param year  the year (4 digits)
     * @return number of days since 1/1/0001
     */
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    /**
     * Checks if the given date is valid according to the Gregorian calendar.
     * Validates day, month, year ranges and handles special cases like leap years.
     *
     * @param day   the day to check
     * @param month the month to check
     * @param year  the year to check
     * @return true if the date is valid, false otherwise
     */
    private boolean dateCheck(int day, int month, int year) {
        if (day < MIN_DAY || day > MAX_DAY || month < JAN || month > DEC || year < MIN_YEAR || year > MAX_YEAR)
            return false;
        else if (month == FEB && day == NUM_29) {
            // Check if it's a leap year
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    return year % 400 == 0; // Leap year: divisible by 400
                }
                return true; // Leap year: divisible by 4 but not by 100
            }
            return false; // Not a leap year: not divisible by 4
        } else if (day == NUM_30) {
            return month != FEB; // February never has 30 days
        } else if (day == MAX_DAY) {
            return month == JAN || month == MARCH || month == MAY || month == JUL || month == AUG || month == OCT || month == DEC;
        }
        return true;
    }

    /**
     * Creates a new Date object. If the given date is invalid, sets it to 1/1/2000.
     *
     * @param day   the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year  the year (4 digits, 1000-9999)
     */
    public Date(int day, int month, int year) {
        if (dateCheck(day, month, year)) {
            this._day = day;
            this._month = month;
            this._year = year;
        } else {
            this._day = MIN_DAY;
            this._month = JAN;
            this._year = 2000;
        }
    }

    /**
     * Copy constructor
     *
     * @param other the date to be copied
     */
    public Date(Date other) {
        if (other != null) {
            this._day = other.getDay();
            this._month = other.getMonth();
            this._year = other.getYear();
        }
    }

    /**
     * Gets the day
     *
     * @return the day
     */
    public int getDay() {
        return this._day;
    }

    /**
     * Gets the month
     *
     * @return the month
     */
    public int getMonth() {
        return this._month;
    }

    /**
     * Gets the year
     *
     * @return the year
     */
    public int getYear() {
        return this._year;
    }

    /**
     * Sets the day. The change will only be applied if the resulting date is valid.
     *
     * @param dayToSet the day value to be set (1-31)
     */
    public void setDay(int dayToSet) {
        if (dateCheck(dayToSet, this._month, this._year)) this._day = dayToSet;
    }

    /**
     * Sets the month. The change will only be applied if the resulting date is valid.
     *
     * @param monthToSet the month value to be set (1-12)
     */
    public void setMonth(int monthToSet) {
        if (dateCheck(this._day, monthToSet, this._year)) this._month = monthToSet;
    }

    /**
     * Sets the year. The change will only be applied if the resulting date is valid.
     *
     * @param yearToSet the year value to be set (1000-9999)
     */
    public void setYear(int yearToSet) {
        if (dateCheck(this._day, this._month, yearToSet)) this._year = yearToSet;
    }

    /**
     * Checks if this date equals another date
     *
     * @param other the date to compare this date to
     * @return true if the dates are the same, false otherwise
     */
    public boolean equals(Date other) {
        if (other == null) {
            return false;
        }
        return other.getDay() == this._day && other.getMonth() == this._month && other.getYear() == this._year;
    }

    /**
     * Checks if this date comes before another date
     *
     * @param other the date to compare this date to
     * @return true if this date is before other date, false otherwise
     */
    public boolean before(Date other) {
        int firstDate = calculateDate(this._day, this._month, this._year);
        int secondDate = calculateDate(other.getDay(), other.getMonth(), other.getYear());
        return firstDate < secondDate;
    }

    /**
     * Checks if this date comes after another date
     *
     * @param other the date to compare this date to
     * @return true if this date is after other date, false otherwise
     */
    public boolean after(Date other) {
        return other.before(this);
    }

    /**
     * Calculates the difference in days between two dates
     *
     * @param other the date to calculate the difference with
     * @return the absolute number of days between the dates
     */
    public int difference(Date other) {
        int firstDate = calculateDate(this._day, this._month, this._year);
        int secondDate = calculateDate(other.getDay(), other.getMonth(), other.getYear());
        return Math.abs(firstDate - secondDate);
    }

    /**
     * Returns a String representation of this date.
     *
     * @return the date formatted as "dd/MM/yyyy" (e.g., 01/01/2000)
     */
    public String toString() {
        return String.format("%02d/%02d/%04d", _day, _month, _year);
    }

    /**
     * Calculates the date of tomorrow.
     * If the current date is the maximum possible date (31/12/9999),
     * it returns a new Date object with the same date.
     *
     * @return a new Date object representing tomorrow's date
     */
    public Date tomorrow() {
        if (this._day == MAX_DAY && this._month == DEC && this._year == MAX_YEAR)
            return new Date(this); // Return the same date as it's the max date
        else if (dateCheck(this._day + 1, this._month, this._year))
            return new Date(this._day + 1, this._month, this._year);
        else {
            if (this._month == DEC) return new Date(1, 1, this._year + 1);
            else return new Date(1, this._month + 1, this._year);
        }
    }
}