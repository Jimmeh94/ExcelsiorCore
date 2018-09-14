package com.excelsiormc.excelsiorcore.services.user;

import com.excelsiormc.excelsiorcore.services.chat.ChatPlayerProfile;
import com.excelsiormc.excelsiorcore.services.economy.Account;
import com.excelsiormc.excelsiorcore.services.scoreboard.Scoreboard;
import com.excelsiormc.excelsiorcore.services.scoreboard.presets.ScoreboardPreset;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

public class PlayerBase {

    private UUID owner;
    private ChatPlayerProfile chatProfile;
    private Account account;
    private Scoreboard scoreboard;

    public PlayerBase(UUID owner, ChatPlayerProfile chatProfile, ScoreboardPreset defaultPreset) {
        this.owner = owner;
        this.chatProfile = chatProfile;
        account = new Account(owner);

        this.scoreboard = new Scoreboard(this, defaultPreset);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public UUID getOwner() {
        return owner;
    }

    public Account getAccount() {
        return account;
    }

    public ChatPlayerProfile getChatProfile() {
        return chatProfile;
    }

    public boolean isOwner(UUID uuid){
        return uuid.compareTo(owner) == 0;
    }

    public Player getPlayer(){
        return Sponge.getServer().getPlayer(owner).get();
    }

    public enum ParticleModifier{
        LOW(0.25),
        MEDIUM(.50),
        NORMAL(1),
        HIGH(1.5);

        private double scale;

        ParticleModifier(double scale) {
            this.scale = scale;
        }

        public double getScale() {
            return scale;
        }
    }
}
