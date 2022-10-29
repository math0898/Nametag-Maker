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
    public static List<String> addMissingOptions (String[] current) {
        String check = "";
        for (String u: current) check += u;
        return addMissingOptions(check);
    }

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
        String[] options = new String[]{ "color:", "permission:", "prefix\"", "suffix:\"", "visible:", "weight:", "name:" };
        for (String s : options) if (!current.contains(s)) list.add(s);
        return list;
    }
}
