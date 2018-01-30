package com.example.demo.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IssueIdFilter {
    public static List<String> getValidIssueIds(final String comment)
    {
        return filterOnlyIssueIds(comment);
    }

    private static ArrayList<String> filterOnlyIssueIds(final String comment)
    {
        Set<String> issueSet = new HashSet<>();
        String regEx = "\\[([^\\s]*)-([0-9]*)\\]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(comment);

        while (matcher.find())
        {
            String group = matcher.group();
            List<String> issueList = removeBrackets(group);
            issueSet.addAll(issueList);
        }
        return new ArrayList<>(issueSet);
    }

    private static List<String> removeBrackets(String value)
    {
        List<String> issueList = new ArrayList<>();
        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(value);
        while (m.find())
        {
            issueList.add(m.group(1));
        }
        return issueList;
    }
}
