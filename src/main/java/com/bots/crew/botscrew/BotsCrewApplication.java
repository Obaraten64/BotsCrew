package com.bots.crew.botscrew;

import com.bots.crew.botscrew.service.CommandFactory;
import com.bots.crew.botscrew.config.Patterns;
import com.bots.crew.botscrew.service.BotsCrewService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.regex.Matcher;

@SpringBootApplication
public class BotsCrewApplication {
    private BotsCrewService botsCrewService;

    public static void main(String[] args) {
        SpringApplication.run(BotsCrewApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunnerBean(BotsCrewService botsCrewService) {
        return args -> {
            this.botsCrewService = botsCrewService;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write command (or 'exit' for exit):");

            while (true) {
                String command = scanner.nextLine();

                if ("exit".equals(command)) {
                    System.out.println("Goodbye!");
                    break;
                }

                System.out.println(executeCommand(command));
            }
        };
    }

    private String executeCommand(String command) {
        for (Patterns pattern : Patterns.values()) {
            Matcher matcher = pattern.getPattern().matcher(command);

            if (matcher.matches()) {
                return CommandFactory.execute(matcher.group(1),
                        pattern.getCommand(), botsCrewService);
            }
        }

        return "Unknown command: " + command;
    }
}
