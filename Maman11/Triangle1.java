package Maman11;

import java.util.Scanner;

/**
 * This program calculates the area and the perimeter of a triangle given its three sides.
 * It checks if the sides can form a valid triangle before performing the calculations.
 * If the sides do not form a valid triangle, it informs the user accordingly.
 * The area is calculated using Heron's formula, and the perimeter is simply the sum of the sides.
 */
public class Triangle1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("This program calculates the area and the perimeter of a given triangle. ");
        System.out.println("Please enter the three lengths of the triangle's sides");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        double halfPerimeter = (double) (a + b + c) / 2;
        if (a + b > c && a + c > b && b + c > a) {
            double area = Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
            System.out.println("The lengths of the triangle's sides are: " + a + ", " + b + ", " + c);
            System.out.println("The perimeter of the triangle is: " + (a + b + c));
            System.out.println("The area of the triangle is: " + area);
        } else {
            System.out.println("The lengths you entered do not form a valid triangle.");
        }
        scan.close();
    }
}