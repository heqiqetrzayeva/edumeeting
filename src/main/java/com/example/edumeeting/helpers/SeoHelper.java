package com.example.edumeeting.helpers;

public class SeoHelper {

    public  String seoHelper(String text) {

        String[] result = text.toLowerCase()
                .replace("ə","e")
                .replace("ç", "c")
                .replace("ö","o")
                .replace("ü","u")
                .split(" ");

        String test = String.join("-", result);
        return test.replaceAll("[^a-z0-9-]","");
    }
}
