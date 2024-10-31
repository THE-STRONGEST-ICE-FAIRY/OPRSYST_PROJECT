package gui.utilities;

public class CheckStringForInt {
    public static void main(String[] args) {
        String input = "123"; // Example string

        if (canConvertToInt(input)) {
            System.out.println("The string can be converted to an integer.");
        } else {
            System.out.println("The string cannot be converted to an integer.");
        }
    }

    public static boolean canConvertToInt(String str) {
        str = str.trim(); // Remove any leading/trailing whitespace
        try {
            Integer.parseInt(str);
            return true; // Conversion is successful
        } catch (NumberFormatException e) {
            return false; // Conversion failed
        }
    }
}
