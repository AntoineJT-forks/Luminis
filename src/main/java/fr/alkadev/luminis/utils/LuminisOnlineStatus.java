package fr.alkadev.luminis.utils;

import net.dv8tion.jda.api.OnlineStatus;

import java.util.Arrays;

public enum LuminisOnlineStatus {

    DO_NOT_DISTURB(OnlineStatus.DO_NOT_DISTURB, "Ne pas déranger"),
    IDLE(OnlineStatus.IDLE, "Inactif"),
    INVISIBLE(OnlineStatus.INVISIBLE, "Invisible"),
    OFFLINE(OnlineStatus.OFFLINE, "Déconnecté"),
    ONLINE(OnlineStatus.ONLINE, "En ligne"),
    UNKNOWN(OnlineStatus.UNKNOWN, "inconnu"),
    ;

    private final OnlineStatus jdaOnlineStatus;
    private final String french;

    LuminisOnlineStatus(OnlineStatus onelineStatus, String french) {
        this.jdaOnlineStatus = onelineStatus;
        this.french = french;
    }

    @Override
    public String toString() {
        return this.french;
    }

    public static LuminisOnlineStatus from(OnlineStatus onlineStatus) {
        return Arrays.stream(values())
                .filter(luminisOnlineStatus -> luminisOnlineStatus.jdaOnlineStatus == onlineStatus)
                .findAny()
                .orElse(LuminisOnlineStatus.UNKNOWN);
    }

}
