package fr.alkadev.smartbot.reminds;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

class RemindBuilder {

    User user;
    TextChannel channel;
    long time;
    String message;

    RemindBuilder(User user){
        this.user = user;
    }

    RemindBuilder withChannel(TextChannel channel){
        this.channel = channel;
        return this;
    }

    RemindBuilder withTime(long time){
        this.time = time;
        return this;
    }

    RemindBuilder withMessage(String message){
        this.message = message;
        return this;
    }

    Remind build(){
        return new Remind(this);
    }

}
