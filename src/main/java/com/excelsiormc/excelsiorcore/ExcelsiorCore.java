package com.excelsiormc.excelsiorcore;

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

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        INSTANCE = this;
    }
}
