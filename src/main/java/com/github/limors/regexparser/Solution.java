package com.github.limors.regexparser;


import javafx.util.Pair;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

class Solution {

    private static HashMap<Character, Function<String, Integer>> SPECIAL_CHAR_MAP = new HashMap<>();

    static {
        SPECIAL_CHAR_MAP.put('+', s -> 1);
        SPECIAL_CHAR_MAP.put('*', String::length);
    }

    boolean isMatch(String pattern, String str) {
        return isMatchRecursive(pattern, str);
    }

    private boolean isMatchRecursive(String pattern, String str) {
        return Optional.ofNullable(str)
                .filter(s -> !isStringEmpty(s))
                .map(s -> handelNoneEmptyString(pattern, s))
                .orElseGet(() -> handelEmptyString(pattern));
    }

    private boolean handelNoneEmptyString(String pattern, String str) {
        if (isStringEmpty(pattern)) {
            return false;
        } else if (SPECIAL_CHAR_MAP.containsKey(pattern.charAt(0))) {
            return isMatchSpecialChar(pattern, str, SPECIAL_CHAR_MAP.get(pattern.charAt(0)).apply(str));
        } else {
            return handelChars(pattern, str);
        }
    }

    private boolean handelEmptyString(String pattern) {
        if (isStringEmpty(pattern)) {
            return true;
        } else if (SPECIAL_CHAR_MAP.containsKey(pattern.charAt(0))) {
            return isMatchRecursive(pattern.substring(1), "");
        } else {
            return false;
        }
    }

    private boolean handelChars(String pattern, String str) {
        return Optional.of(pattern.charAt(0))
                .map(c -> new Pair<>(c, str.charAt(0)))
                .filter(pair -> pair.getKey() == pair.getValue())
                .map(pair -> isMatchRecursive(pattern.substring(1), str.substring(1)))
                .orElse(false);
    }


    private boolean isMatchSpecialChar(String pattern, String str, int slidingWindow) {
        if (pattern.length() == 1) {
            return Optional.ofNullable(str).filter(string -> string.length() <= slidingWindow).isPresent();
        }
        return IntStream.rangeClosed(0, slidingWindow)
                .anyMatch(stringIndex -> isMatchRecursive(pattern.substring(1), str.substring(stringIndex)));
    }

    private boolean isStringEmpty(String str) {
        return Optional.ofNullable(str).filter(string -> string.length() == 0).isPresent();
    }

}
