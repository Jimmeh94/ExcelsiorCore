package com.excelsiormc.excelsiorcore;

import com.excelsiormc.excelsiorcore.services.chat.ChatChannelManager;
import com.excelsiormc.excelsiorcore.services.database.ServiceMongoDB;
import com.excelsiormc.excelsiorcore.services.economy.Economy;
import com.excelsiormc.excelsiorcore.services.party.PartyManager;
import com.excelsiormc.excelsiorcore.services.user.PlayerBaseManager;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "excelsiorcore",
        name = "ExcelsiorCore",
        description = "A Sponge based API with basic utilities.",
        authors = {
                "Jimmy"
        }
)
public class ExcelsiorCore {

    @Inject
    private Logger logger;

    public static ExcelsiorCore INSTANCE;

    private Economy economy;
    private PartyManager partyManager;
    private ServiceMongoDB mongoDB;
    private ChatChannelManager channelManager;
    private PlayerBaseManager playerBaseManager;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        INSTANCE = this;

        mongoDB = new ServiceMongoDB("Admin", "admin", "@ds149742.mlab.com:49742/excelsior", "excelsior");

        economy = new Economy();
        partyManager = new PartyManager();
        channelManager = new ChatChannelManager();
        playerBaseManager = new PlayerBaseManager();
    }

    public PlayerBaseManager getPlayerBaseManager() {
        return playerBaseManager;
    }

    public ChatChannelManager getChannelManager() {
        return channelManager;
    }

    public Economy getEconomy() {
        return economy;
    }

    public PartyManager getPartyManager() {
        return partyManager;
    }

    public ServiceMongoDB getMongoDB() {
        return mongoDB;
    }
}
