package com.eventures.base;

public class Utils {
    public static String generateString(int n){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(characters.length() * Math.random());
            sb.append(characters.charAt(index));
        }
        return sb.toString().toLowerCase();
    }
}
