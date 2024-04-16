package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String prefix = "[";
        String suffix = "]";
        String pipe = "|";

        StringJoiner joiner = new StringJoiner(pipe, prefix, suffix);
        delimiters.forEach(joiner::add);
        String pattern = joiner.toString();

        String newDelimiter = " ";
        String sourceWithOneDelimiter = source.replaceAll(pattern, newDelimiter);
        StringTokenizer tokenizer = new StringTokenizer(sourceWithOneDelimiter,
                newDelimiter);

        List<String> result = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }

        return result;
    }
}
