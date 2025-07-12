package Maman13;

/**
 * This class provides solutions to various algorithmic problems involving arrays and sequences.
 * <p>
 * The class includes four main algorithms:
 * 1. Alternating sequence optimization - finds minimum swaps to create alternating bit pattern
 * 2. Even-sum subarray analysis - finds the longest contiguous subarray with even sum
 * 3. Path validation - checks if a valid route exists in an array using jump mechanics
 * 4. Shortest path finding - finds optimal route for a prince in a dungeon maze
 * <p>
 * All methods are designed with efficiency in mind, utilizing optimal time and space complexity
 * where possible. The solutions employ various algorithmic techniques, including greedy algorithms,
 * dynamic programming concepts, and recursive backtracking.
 *
 * @author Osher Akshikar
 * @version 2023a
 */
public class Ex13 {
    /**
     * Calculates the minimum number of swaps needed to create an alternating binary sequence.
     * <p>
     * Given a binary string, this method determines the minimum number of character swaps
     * required to transform it into an alternating pattern (either "010101..." or "101010...").
     * The algorithm counts mismatches for both possible patterns and returns the minimum.
     * <p>
     * Algorithm: Count characters at even positions that don't match each target pattern,
     * then return the minimum count needed for either pattern.
     *
     * @param s the binary string containing only '0' and '1' characters
     * @return the minimum number of swaps needed to create an alternating sequence
     * <p>
     * Time complexity: O(n) where n is the length of the string
     * Space complexity: O(1) - uses only constant extra space
     */
    public static int alternating(String s) {
        int counter10 = 0;
        int counter01 = 0;
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) == '0') { //check if char at(i is 0)
                counter10++;
            } else {
                counter01++;
            }

        }
        return Math.min(counter10, counter01); //return the numbers of swaps
    }

    /**
     * Finds the length of the longest contiguous subarray with an even sum.
     * <p>
     * This method analyzes an integer array to find the longest contiguous subarray
     * whose elements sum to an even number. The algorithm leverages the mathematical
     * property that removing an odd number from an odd sum makes it even.
     * <p>
     * Algorithm:
     * 1. Calculate the total sum of all elements
     * 2. If the total sum is even, return the entire array length
     * 3. If the total sum is odd, find the first and last odd numbers
     * 4. Return the maximum length by excluding either the first or last odd number
     *
     * @param a the integer array to analyze (must not be null)
     * @return the length of the longest contiguous subarray with even sum
     * @throws IllegalArgumentException if the array is null
     *                                  <p>
     *                                  Time complexity: O(n) where n is the length of the array
     *                                  Space complexity: O(1) - uses only constant extra space
     */
    public static int what(int[] a) {
        int sum = 0;
        int firstOddIndex = -1;
        int lastOddIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 != 0) {
                if (firstOddIndex == -1) {
                    firstOddIndex = i;
                }
                lastOddIndex = i;
            }
            sum += a[i];
        }
        if (sum % 2 == 0) {
            return a.length;
        }
        int opt0 = a.length - (firstOddIndex + 1);
        int opt1 = lastOddIndex;
        return Math.max(opt0, opt1);
    }

    /**
     * Determines if there exists a valid path from the first element to the last element of an array.
     * <p>
     * This method checks if it's possible to reach the last index of an array starting from index 0,
     * where each array element represents the number of steps you can move forward or backward.
     * The algorithm uses recursive backtracking with temporary marking to avoid infinite loops.
     * <p>
     * Path Rules:
     * - Start at index 0
     * - From any position i, you can move to position (i + a[i]) or (i - a[i])
     * - Cannot revisit positions (marked with 0 during traversal)
     * - Goal is to reach the last index (a.length - 1)
     *
     * @param a the array of positive integers representing step sizes (must not be null or empty)
     * @return true if a valid path exists from first to last element, false otherwise
     * <p>
     * Time complexity: O(2^n) in worst case due to recursive exploration
     * Space complexity: O(n) for recursion stack depth
     */
    public static boolean isWay(int[] a) {
        if (a.length == 1) return true;
        return isWay(a, 0);
    }

    /**
     * Helper method for isWay that performs the actual recursive path-finding logic.
     * <p>
     * This private method implements the core recursive algorithm for finding a valid path.
     * It uses backtracking by temporarily marking visited positions with 0 to prevent
     * infinite loops, then restoring the original value after exploring both directions.
     *
     * @param a the array being traversed (modified temporarily during recursion)
     * @param i the current index position in the array
     * @return true if a path exists from current position to the last index, false otherwise
     */
    private static boolean isWay(int[] a, int i) {
        if (i > a.length - 1 || i < 0 || a[i] == 0) return false;

        if (i == a.length - 1) return true;

        int k = a[i];
        a[i] = 0;
        boolean right = isWay(a, i + k);
        boolean left = isWay(a, i - k);
        a[i] = k;

        return right || left;
    }

    /**
     * Finds the shortest path for a prince to reach the evil entity in a dungeon maze.
     * <p>
     * This method calculates the minimum number of moves required for a prince to navigate
     * through a dungeon represented as a 2D grid and reach the evil entity (marked with -1).
     * The prince must follow specific movement rules based on the dungeon's terrain values.
     * <p>
     * Dungeon Rules:
     * - Each cell contains an integer representing the terrain difficulty
     * - The prince can move to adjacent cells (up, down, left, right)
     * - Movement is only allowed if the new cell's value is within the range [previous-2, previous+1]
     * - The goal is to reach a cell with value -1 (evil entity)
     * - -2 represents visited cells (temporary marking during traversal)
     *
     * @param drm the 2D dungeon array representing the maze (must be square and non-null)
     * @param i   the starting row position of the prince
     * @param j   the starting column position of the prince
     * @return the minimum number of moves to reach the evil entity, or -1 if no path exists
     * @throws IllegalArgumentException if the dungeon is null or starting position is invalid
     *                                  <p>
     *                                  Time complexity: O(4^(n*m)) in worst case due to recursive exploration
     *                                  Space complexity: O(n*m) for recursion stack depth
     */
    public static int prince(int[][] drm, int i, int j) {
        int result = prince(drm, i, j, drm[i][j]);
        if (result >= drm.length * drm.length + 1) return -1;
        else return result;
    }

    /**
     * Recursive helper method that implements the core pathfinding algorithm for the prince.
     * <p>
     * This method performs depth-first search with backtracking to find the shortest path
     * to the evil entity. It temporarily marks visited cells with -2 to prevent cycles,
     * then restores the original value after exploring all directions.
     *
     * @param drm the 2D dungeon array (modified temporarily during recursion)
     * @param i   the current row position
     * @param j   the current column position
     * @param pre the terrain value of the previous cell (used for movement validation)
     * @return the minimum number of moves from current position to evil entity,
     * or a large number (n*n+1) if no valid path exists
     */
    private static int prince(int[][] drm, int i, int j, int pre) {
        if (i < 0 || i > drm.length - 1 || j < 0 || j > drm[0].length - 1 || drm[i][j] == -2)
            return drm.length * drm.length + 1;
        if (drm[i][j] == -1) return 1;
        if (drm[i][j] > pre + 1 || drm[i][j] < pre - 2) return drm.length * drm.length + 1;

        int temp = drm[i][j];
        drm[i][j] = -2;
        int up = 1 + prince(drm, i - 1, j, temp);
        int down = 1 + prince(drm, i + 1, j, temp);
        int left = 1 + prince(drm, i, j - 1, temp);
        int right = 1 + prince(drm, i, j + 1, temp);
        drm[i][j] = temp;

        return Math.min(Math.min(up, down), Math.min(left, right));
    }

}