package com.bots.crew.botscrew.service;

public class CommandFactory {
    public static String execute(String variable, String command, BotsCrewService service) {
        switch (command) {
            case "HEAD_OF_DEPARTMENT" -> {return service.getHeadOfDepartment(variable);}
            case "SHOW_STATS" -> {return service.getStatistics(variable);}
            case "SHOW_AVG_SALARY" -> {return service.getAVGSalary(variable);}
            case "COUNT_EMPLOYEE" -> {return service.countEmployees(variable);}
            case "SEARCH" -> {return service.search(variable);}
        }
        return ""; //impossible to reach
    }
}
