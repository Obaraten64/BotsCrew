package com.bots.crew.botscrew.config.commands;

import com.bots.crew.botscrew.service.BotsCrewService;

public class HeadCommand implements Command {
    @Override
    public String execute(String variable, BotsCrewService service) {
        return service.getHeadOfDepartment(variable);
    }
}
