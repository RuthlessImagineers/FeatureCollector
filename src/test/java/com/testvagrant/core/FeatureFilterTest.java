package com.testvagrant.core;


import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import static com.testvagrant.utils.Commons.getTags;

public class FeatureFilterTest {

    private final String tags1 = "@abc,@def,@ghi,@xyz";
    private final String tags2 = "@abc, @def, @ghi, @xyz";

    @Test
    public void featureFilterTest() {
        List<String> tagsList1 = getTags(tags1);
        List<String> tagsList2 = getTags(tags2);
        String s = tagsList2.stream().distinct().findAny().get();
        System.out.println(s);
        tagsList2.forEach(tag -> Assert.assertTrue(!tag.startsWith(" ")));
    }
}
