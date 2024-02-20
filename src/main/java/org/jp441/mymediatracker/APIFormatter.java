package org.jp441.mymediatracker;

public class APIFormatter {
    public static String formatNameQuery(String name){
        return name.replaceAll("\\s", "+");
    }
}
