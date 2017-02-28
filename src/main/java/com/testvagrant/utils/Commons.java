package com.testvagrant.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commons {

    public static List<String> getTags(String tags) {
        if(!tags.contains(",")) {
            List<String> tagsList = new ArrayList<>();
            tagsList.add(tags);
            return tagsList;
        }
        List<String> tagsList = Arrays.asList(tags.split(","));
        tagsList.replaceAll(String::trim);
        return tagsList;
    }
}
