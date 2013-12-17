package com.supinfo.supcrowdfunder.util;

/**
 * Created with IntelliJ IDEA.
 * User: Fireaxe
 * Date: 17/12/13
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class TextHelper {
    public static String truncateAfterWords(int n, String s) {
        if (s == null)
            return null;
        if (n <= 0)
            return "";
        else if (n >= s.length())
            return s;
        else if (s.indexOf(" ", n) != -1)
            return (s.substring(0, s.indexOf(" ", n)) + "...");
        return (s.substring(0, n) + "...");
    }
}
