package me.djtheredstoner.yetanotherskyblockmod.listeners;

import club.sk1er.mods.core.util.MinecraftUtils;
import me.djtheredstoner.yetanotherskyblockmod.YetAnotherSkyblockMod;
import me.djtheredstoner.yetanotherskyblockmod.asm.interfaces.IOofModListener;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class YASMListener {

    private final YetAnotherSkyblockMod yasm = YetAnotherSkyblockMod.instance;
    public static IOofModListener oofModListener;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(!(MinecraftUtils.isHypixel() && yasm.isOnSkyblock)) return;
        String message = event.message.getUnformattedText();
        if(message.equals("  NICE! SLAYER BOSS SLAIN!") && yasm.config.slayerOof) {
            playOofSound();
        }
    }

    private void playOofSound() {
        if(YetAnotherSkyblockMod.instance.oofModDetected && oofModListener != null) {
            oofModListener.djPlayOofSound(yasm.config.slayerOofSound);
        }
    }

}
