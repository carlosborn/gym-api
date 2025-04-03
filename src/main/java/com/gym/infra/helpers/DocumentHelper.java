package com.gym.infra.helpers;

public class DocumentHelper {

    public static boolean isDocumentValid(String document){
        // Remove non-numeric characters
        document = cleanDocument(document);

        // Check if CPF has 11 digits
        if (document.length() != 11) {
            return false;
        }

        // Check if all digits are the same
        if (document.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calculate check digits
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(document.charAt(i));
        }

        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += digits[i] * (10 - i);
        }
        int remainder1 = sum1 % 11;
        int digit1 = (remainder1 < 2) ? 0 : 11 - remainder1;

        if (digit1 != digits[9]) {
            return false;
        }

        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += digits[i] * (11 - i);
        }
        int remainder2 = sum2 % 11;
        int digit2 = (remainder2 < 2) ? 0 : 11 - remainder2;

        return digit2 == digits[10];
    }

    public static String cleanDocument(String document) {
        return document.replaceAll("[.-]", "");
    }

}
