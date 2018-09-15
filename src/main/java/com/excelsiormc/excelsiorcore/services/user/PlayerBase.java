package com.excelsiormc.excelsiorcore.services.user;

import com.excelsiormc.excelsiorcore.services.chat.ChatPlayerProfile;
import com.excelsiormc.excelsiorcore.services.economy.Account;
import com.excelsiormc.excelsiorcore.services.inventory.Hotbar;
import com.excelsiormc.excelsiorcore.services.scoreboard.Scoreboard;
import com.excelsiormc.excelsiorcore.services.scoreboard.presets.ScoreboardPreset;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

public class PlayerBase {

    private UUID owner;
    private ChatPlayerProfile chatProfile;
    private Account account;
    private ParticleModifier particleModifier = ParticleModifier.NORMAL;
    private Hotbar currentHotbar;

    public PlayerBase(UUID owner, ChatPlayerProfile chatProfile) {
        this.owner = owner;
        this.chatProfile = chatProfile;
        account = new Account(owner);
    }

    public void setCurrentHotbar(Hotbar currentHotbar) {
        this.currentHotbar = currentHotbar;
    }

    public Hotbar getCurrentHotbar() {
        return currentHotbar;
    }

    public void setParticleModifier(ParticleModifier particleModifier) {
        this.particleModifier = particleModifier;
    }

    public ParticleModifier getParticleModifier() {
        return particleModifier;
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
