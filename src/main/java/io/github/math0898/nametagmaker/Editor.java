package io.github.math0898.nametagmaker;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: This class needs to be better defined.
 *
 * @author Sugaku
 */
public class Editor {

    /**
     * Returns a list of command line editor options which are not present in the given string. Will return an empty
     * list if current does not have an even number of quotation marks.
     *
     * @param current The current string with editor options.
     * @return A list of optional editor options not yet included.
     */
    public static List<String> addMissingOptions (String current) {
        List<String> list = new ArrayList<>();
        if ((current.length() - current.replace("\"", "").length()) % 2 != 0) return list;
        if (!current.contains("color:")) list.add("color:");
        if (!current.contains("permission:")) list.add("permission:");
        if (!current.contains("prefix:\"")) list.add("prefix:\"");
        if (!current.contains("suffix:\"")) list.add("suffix:\"");
        if (!current.contains("visible:")) list.add("visible:");
        if (!current.contains("weight:")) list.add("weight:");
        if (!current.contains("name:")) list.add("name:");
        return list;
    }
}
