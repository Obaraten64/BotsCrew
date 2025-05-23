package com.bots.crew.botscrew.config;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum Patterns {
    HEAD_OF_DEPARTMENT(Pattern.compile("Who is head of department (.+)"),
            "HEAD_OF_DEPARTMENT"),
    SHOW_STATS(Pattern.compile("Show (.+) statistics"),
            "SHOW_STATS"),
    SHOW_AVG_SALARY(Pattern.compile("Show the average salary for the department (.+)"),
            "SHOW_AVG_SALARY"),
    COUNT_EMPLOYEE(Pattern.compile("Show count of employee for (.+)"),
            "COUNT_EMPLOYEE"),
    SEARCH(Pattern.compile("Global search by (.+)"),
            "SEARCH");

    private final Pattern pattern;
    private final String command;

    Patterns(Pattern pattern, String command) {
        this.pattern = pattern;
        this.command = command;
    }

}
