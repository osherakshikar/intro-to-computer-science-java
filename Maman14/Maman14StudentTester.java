package Maman14;

import Maman12.*;

/**
 * Comprehensive Tester for Maman 14 with Pass/Fail Validation
 */
public class Maman14StudentTester {
    private static int totalTests = 0;
    private static int passedTests = 0;

    // Helper method to check test results
    private static void checkTest(String testName, boolean expected, boolean actual) {
        totalTests++;
        if (expected == actual) {
            System.out.println("✓ PASS: " + testName);
            passedTests++;
        } else {
            System.out.println("✗ FAIL: " + testName + " (Expected: " + expected + ", Got: " + actual + ")");
        }
    }

    private static void checkTest(String testName, int expected, int actual) {
        totalTests++;
        if (expected == actual) {
            System.out.println("✓ PASS: " + testName);
            passedTests++;
        } else {
            System.out.println("✗ FAIL: " + testName + " (Expected: " + expected + ", Got: " + actual + ")");
        }
    }

    private static void checkTest(String testName, Object expected, Object actual) {
        totalTests++;
        boolean isEqual = (expected == null && actual == null) ||
                (expected != null && expected.equals(actual));
        if (isEqual) {
            System.out.println("✓ PASS: " + testName);
            passedTests++;
        } else {
            System.out.println("✗ FAIL: " + testName + " (Expected: " + expected + ", Got: " + actual + ")");
        }
    }

    private static void printTestSummary() {
        String separator = "==================================================";
        System.out.println("\n" + separator);
        System.out.println("TEST SUMMARY");
        System.out.println(separator);
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + String.format("%.1f", (double) passedTests / totalTests * 100) + "%");

        if (passedTests == totalTests) {
            System.out.println("🎉 ALL TESTS PASSED! 🎉");
        } else {
            System.out.println("❌ Some tests failed. Please review the output above.");
        }
        System.out.println(separator);
    }

    public static void main(String[] args) {
        System.out.println("Starting Maman 14 Test Suite...\n");

        //-----------------------RentNode Tests---------------------
        System.out.println("=== RENTNODE CLASS TESTS ===\n");

        // Test data setup
        Rent r1 = new Rent("David Cohen", new Car(1122233, 'B', "Mercedes", false), new Date(12, 3, 2004), new Date(6, 7, 2004));
        Rent r2 = new Rent("Israel Israeli", new Car(1234567, 'A', "Mazda", true), new Date(2, 8, 2017), new Date(12, 8, 2017));
        Rent r3 = new Rent("Noy Zohar", new Car(6666666, 'A', "Mazda", true), new Date(6, 6, 2066), new Date(12, 8, 2066));
        Rent r4 = new Rent("Sarah Levi", new Car(7777777, 'C', "Toyota", false), new Date(1, 1, 2023), new Date(5, 1, 2023));

        //Test Constructor(Rent r)
        System.out.println("Testing RentNode constructors:");
        RentNode rent1 = new RentNode(r1);
        checkTest("Constructor(Rent r) - next should be null", null, rent1.getNext());
        checkTest("Constructor(Rent r) - rent should match", r1.getName(), rent1.getRent().getName());

        //Test Constructor(Rent r, RentNode next)
        RentNode rent2 = new RentNode(r2, rent1);
        checkTest("Constructor(Rent r, RentNode next) - next should exist", true, rent2.getNext() != null);
        checkTest("Constructor(Rent r, RentNode next) - rent should match", r2.getName(), rent2.getRent().getName());

        //Test Copy Constructor
        RentNode rent3 = new RentNode(rent2);
        checkTest("Copy constructor - names should match", rent2.getRent().getName(), rent3.getRent().getName());
        checkTest("Copy constructor - should be different objects", true, rent2.getRent() != rent3.getRent());

        //Test setRent and getRent
        rent3.setRent(r3);
        checkTest("setRent - name should update", r3.getName(), rent3.getRent().getName());
        checkTest("setRent - should create new object", true, r3 != rent3.getRent());

        //Test setNext and getNext
        RentNode rent4 = new RentNode(r4);
        rent3.setNext(rent4);
        checkTest("setNext/getNext - should link correctly", r4.getName(), rent3.getNext().getRent().getName());

        //-----------------------Company Tests---------------------
        System.out.println("\n=== COMPANY CLASS TESTS ===\n");

        // Test empty company
        System.out.println("Testing empty company:");
        Company company1 = new Company();
        checkTest("Empty company - rent count", 0, company1.getNumOfRents());
        checkTest("Empty company - price sum", 0, company1.getSumOfPrices());
        checkTest("Empty company - days sum", 0, company1.getSumOfDays());
        checkTest("Empty company - average", 0.0, company1.averageRent());
        checkTest("Empty company - last car", null, company1.lastCarRent());
        checkTest("Empty company - longest rent", null, company1.longestRent());
        checkTest("Empty company - most common rate", 'N', company1.mostCommonRate());

        // Test addRent method
        System.out.println("\nTesting addRent method:");
        boolean added1 = company1.addRent("David Cohen", new Car(1122233, 'B', "Mercedes", false), new Date(12, 3, 2004), new Date(6, 7, 2004));
        checkTest("Add first rent", true, added1);
        checkTest("Rent count after first add", 1, company1.getNumOfRents());

        boolean added2 = company1.addRent("Israel Israeli", new Car(1234567, 'A', "Mazda", true), new Date(2, 8, 2017), new Date(12, 8, 2017));
        checkTest("Add second rent", true, added2);
        checkTest("Rent count after second add", 2, company1.getNumOfRents());

        // Test chronological ordering
        boolean added3 = company1.addRent("Early Renter", new Car(1111111, 'C', "Honda", true), new Date(1, 1, 2000), new Date(5, 1, 2000));
        checkTest("Add early rent", true, added3);
        checkTest("Rent count after early add", 3, company1.getNumOfRents());

        // Test duplicate prevention
        boolean duplicate = company1.addRent("David Cohen", new Car(1122233, 'B', "Mercedes", false), new Date(12, 3, 2004), new Date(6, 7, 2004));
        checkTest("Duplicate prevention", false, duplicate);
        checkTest("Rent count after duplicate attempt", 3, company1.getNumOfRents());

        // Test null parameter handling
        boolean nullTest = company1.addRent(null, new Car(1111111, 'A', "Test", true), new Date(1, 1, 2020), new Date(5, 1, 2020));
        checkTest("Null parameter handling", false, nullTest);

        // Test company statistics
        System.out.println("\nTesting company statistics:");
        int totalRents = company1.getNumOfRents();
        int totalPrice = company1.getSumOfPrices();
        int totalDays = company1.getSumOfDays();
        checkTest("Total rents > 0", true, totalRents > 0);
        checkTest("Total price > 0", true, totalPrice > 0);
        checkTest("Total days > 0", true, totalDays > 0);
        checkTest("Average calculation", (double) totalDays / totalRents, company1.averageRent());

        // Test lastCarRent
        Car lastCar = company1.lastCarRent();
        checkTest("Last car rent not null", true, lastCar != null);

        // Test longestRent
        Rent longestRent = company1.longestRent();
        checkTest("Longest rent not null", true, longestRent != null);

        // Test mostCommonRate
        company1.addRent("Type A User", new Car(2222222, 'A', "Car1", true), new Date(1, 1, 2021), new Date(3, 1, 2021));
        company1.addRent("Type A User2", new Car(3333333, 'A', "Car2", true), new Date(1, 2, 2021), new Date(3, 2, 2021));
        company1.addRent("Type D User", new Car(4444444, 'D', "Car3", false), new Date(1, 3, 2021), new Date(3, 3, 2021));
        char mostCommon = company1.mostCommonRate();
        checkTest("Most common rate is valid", true, mostCommon == 'A' || mostCommon == 'B' || mostCommon == 'C' || mostCommon == 'D');

        // Test removeRent
        System.out.println("\nTesting removeRent method:");
        int beforeRemoval = company1.getNumOfRents();
        boolean removed = company1.removeRent(new Date(6, 7, 2004)); // David Cohen's return date
        checkTest("Remove existing rent", true, removed);
        checkTest("Rent count after removal", beforeRemoval - 1, company1.getNumOfRents());

        // Test removal of non-existent rent
        boolean removedNonExistent = company1.removeRent(new Date(1, 1, 1999));
        checkTest("Remove non-existent rent", false, removedNonExistent);

        // Test company methods with multiple companies
        System.out.println("\nTesting company comparison methods:");
        Company company2 = new Company();
        company2.addRent("Israel Israeli", new Car(1234567, 'A', "Mazda", true), new Date(2, 8, 2017), new Date(12, 8, 2017));
        company2.addRent("New Renter", new Car(9999999, 'B', "BMW", false), new Date(1, 1, 2022), new Date(5, 1, 2022));

        // Test merge method
        int company1Before = company1.getNumOfRents();
        int company2Before = company2.getNumOfRents();
        company1.merge(company2);
        checkTest("Merge increases rent count", true, company1.getNumOfRents() > company1Before);

        // Edge case tests
        System.out.println("\n=== EDGE CASE TESTS ===\n");

        // Test company with same pickup dates but different durations
        Company company3 = new Company();
        company3.addRent("Short Rental", new Car(1111111, 'A', "Car1", true), new Date(1, 1, 2020), new Date(2, 1, 2020)); // 1 day
        company3.addRent("Long Rental", new Car(2222222, 'B', "Car2", true), new Date(1, 1, 2020), new Date(10, 1, 2020)); // 9 days
        company3.addRent("Medium Rental", new Car(3333333, 'C', "Car3", true), new Date(1, 1, 2020), new Date(5, 1, 2020)); // 4 days

        checkTest("Same pickup dates - all added", 3, company3.getNumOfRents());

        // Test that longer rentals come first when pickup dates are same
        String companyStr = company3.toString();
        int longPos = companyStr.indexOf("Long Rental");
        int shortPos = companyStr.indexOf("Short Rental");
        checkTest("Same pickup dates - longer rental first", true, longPos < shortPos);

        printTestSummary();
    }
}
