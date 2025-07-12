package Maman11;

import java.util.Scanner;

/**
 * This program checks if three given lengths can form a triangle and identifies the type of triangle.
 * It classifies the triangle as equilateral, isosceles, or scalene based on the lengths of its sides.
 * If the lengths do not form a valid triangle, it informs the user accordingly.
 * The program uses a method to validate the triangle condition and prints the appropriate message based on the classification.
 * The triangle condition is checked using the triangle inequality theorem.
 */
public class Triangle2 {
    private static final String INTRO_MESSAGE = "This program checks if three given lengths can form a triangle and identifies the type of triangle.";
    private static final String PURPOSE_MESSAGE = "Using this program, we can determine the type of triangle we have if we have any";
    private static final String INPUT_PROMPT = "Please enter the three lengths of the triangle's sides";
    private static final String OUTPUT_FORMAT = "The numbers: %d, %d and %d %s";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Introduction and input prompt for the user
        System.out.println(INTRO_MESSAGE);
        System.out.println(PURPOSE_MESSAGE);
        System.out.println(INPUT_PROMPT);

        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        if (isValidTriangle(a, b, c)) {
            String triangleType = determineTriangleType(a, b, c);
            System.out.printf(OUTPUT_FORMAT, a, b, c, triangleType);
        } else {
            System.out.printf(OUTPUT_FORMAT, a, b, c, "cannot represent a valid triangle.");
        }

        scan.close();
    }

    private static String determineTriangleType(int a, int b, int c) {
        if (a == b && b == c) {
            return "represent an equilateral triangle";
        } else if (a == b || b == c || a == c) {
            return "represent an isosceles triangle";
        } else if (isRightAngle(a, b, c)) {
            return "represent a right-angle triangle";
        } else return "represent a common triangle";
    }

    private static boolean isValidTriangle(int a, int b, int c) {
        return (a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a);
    }

    private static boolean isRightAngle(int a, int b, int c) {
        return Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)
                || Math.pow(a, 2) + Math.pow(c, 2) == Math.pow(b, 2)
                || Math.pow(b, 2) + Math.pow(c, 2) == Math.pow(a, 2);
    }
}