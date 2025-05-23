package com.bots.crew.botscrew.config;

import com.bots.crew.botscrew.config.commands.*;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum Patterns {
    HEAD_OF_DEPARTMENT(Pattern.compile("Who is head of department (.+)"),
            new HeadCommand()),
    SHOW_STATS(Pattern.compile("Show (.+) statistics"),
            new StatsCommand()),
    SHOW_AVG_SALARY(Pattern.compile("Show the average salary for the department (.+)"),
            new SalaryCommand()),
    COUNT_EMPLOYEE(Pattern.compile("Show count of employee for (.+)"),
            new CountCommand()),
    SEARCH(Pattern.compile("Global search by (.+)"),
            new SearchCommand());

    private final Pattern pattern;
    private final Command command;

    Patterns(Pattern pattern, Command command) {
        this.pattern = pattern;
        this.command = command;
    }

}
