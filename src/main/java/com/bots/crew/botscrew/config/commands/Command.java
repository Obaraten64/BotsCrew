package com.bots.crew.botscrew.config.commands;

import com.bots.crew.botscrew.service.BotsCrewService;

public interface Command {
    String execute(String variable, BotsCrewService service);
}
