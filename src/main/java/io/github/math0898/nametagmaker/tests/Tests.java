package io.github.math0898.nametagmaker.tests;

import io.github.math0898.nametagmaker.TagGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the test class for testing the entire NameTag Maker plugin.
 *
 * @author Sugaku
 */
class Tests {

    /**
     * The test case for parseTag. This is a difficult system to test in game so here's a JUnit test for it. Expected
     * behavior is to take args which is a string divided by spaces and convert it into a functional and correct
     * TagGroup.
     */
    @Test
    @SuppressWarnings("All")
    void parseTag() {
        Random rand = new Random(3);

        TagGroup group1 = TagGroup.parseTag("create");
        Assertions.assertNull(group1, "Group without name is not null.");

        ArrayList<String> testNames  = new ArrayList<>();
        testNames.addAll(Arrays.asList("a", "testing1", "alk30-adf3"));
        for (String s: testNames) {
            TagGroup g = TagGroup.parseTag("create name:" + s);
            assertEquals(s, g.name);
        }

        ArrayList<String> testColors  = new ArrayList<>();
        testColors.addAll(Arrays.asList("&b", "&e", "&7"));
        for (String s: testColors) {
            TagGroup g = TagGroup.parseTag("create name:test color:" + s);
            assertEquals(s.replace("&", "").toCharArray()[0], g.color.getChar()); //Funky string vs char comparison issues
        }

        ArrayList<String> testPrefixes  = new ArrayList<>();
        testPrefixes.addAll(Arrays.asList("a", "testing1", "alk30-adf3", "a b c", "a b... "));
        for (String s: testPrefixes) {
            TagGroup g = TagGroup.parseTag("create name:test prefix:\"" + s + "\"");
            assertEquals(s, g.prefix);
        }

        ArrayList<String> testSuffix  = new ArrayList<>();
        testSuffix.addAll(Arrays.asList("a", "testing1", "alk30-adf3", "a b c", "a b... "));
        for (String s: testSuffix) {
            TagGroup g = TagGroup.parseTag("create name:test suffix:\"" + s + "\"");
            assertEquals(s, g.suffix);
        }

        ArrayList<String> testPermission  = new ArrayList<>();
        testPermission.addAll(Arrays.asList("ntm.admin", "a.d.hdt", "erwer.adfy.tyhda"));
        for (String s: testPermission) {
            TagGroup g = TagGroup.parseTag("create name:test permission:" + s);
            assertEquals(s, g.permission);
        }

        ArrayList<String> testWeight  = new ArrayList<>();
        testWeight.addAll(Arrays.asList("3", "-1", "987723"));
        for (String s: testWeight) {
            TagGroup g = TagGroup.parseTag("create name:test weight:" + s);
            assertEquals(Integer.parseInt(s), g.weight);
        }

        ArrayList<String> testVisible  = new ArrayList<>();
        testVisible.addAll(Arrays.asList("true", "TRUE", "false"));
        for (String s: testVisible) {
            TagGroup g = TagGroup.parseTag("create name:test visible:" + s);
            assertEquals(Boolean.parseBoolean(s), g.visible);
        }
    }
}