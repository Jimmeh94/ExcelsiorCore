package com.excelsiormc.excelsiorcore.event;

import com.excelsiormc.excelsiorcore.ExcelsiorCore;
import com.excelsiormc.excelsiorcore.services.chat.ChatColorTemplate;
import com.excelsiormc.excelsiorcore.services.chat.ChatPlayerProfile;
import com.excelsiormc.excelsiorcore.services.text.Message;
import com.excelsiormc.excelsiorcore.services.text.Messager;
import com.excelsiormc.excelsiorcore.services.user.PlayerBase;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;

public class ChatEvents {

    @Listener
    public void onChat(MessageChannelEvent.Chat event, @First Player player){
        event.setCancelled(true);
        PlayerBase profile = ExcelsiorCore.INSTANCE.getPlayerBaseManager().getPlayerBase(player.getUniqueId()).get();
        profile.getChatProfile().getChatChannel().displayMessage(build(event.getRawMessage().toPlain(), profile.getChatProfile()));

        //Now we're going to check for receiver channels listening to this player
        for(PlayerBase user: ExcelsiorCore.INSTANCE.getPlayerBaseManager().getObjects()){

            if(user.getChatProfile().getReceiverChannel().has(player.getUniqueId())){
                Messager.sendMessage(Message.builder().addReceiver(user.getPlayer()).addMessage(build(event.getRawMessage().toPlain(), profile.getChatProfile())).build());
            }
        }
    }

    private Text build(String message, ChatPlayerProfile profile){
        ChatColorTemplate color = profile.getColors();
        return Text.builder().append(Text.of(color.getPrefix(), profile.getTitle().getDisplay()))
                .append(Text.of(color.getName(), profile.getPlayer().getName() + ": "))
                .append(Text.of(color.getMessage(), message)).build();
    }

}
