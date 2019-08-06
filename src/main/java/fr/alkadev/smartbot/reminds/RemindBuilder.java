package fr.alkadev.smartbot.reminds;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class RemindBuilder {

    User user;
    TextChannel channel;
    long time;
    String message;

    public RemindBuilder(User user){
        this.user = user;

    }

    public RemindBuilder withChannel(TextChannel channel){
        this.channel = channel;
        return this;

    }

    public RemindBuilder withTime(long time){
        this.time = time;
        return this;

    }

    public RemindBuilder withMessage(String message){
        this.message = message;
        return this;

    }

    public Remind build(){
        return new Remind(this);

    }

}
