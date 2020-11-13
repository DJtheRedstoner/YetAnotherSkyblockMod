package me.djtheredstoner.yetanotherskyblockmod.listeners;

import club.sk1er.mods.core.util.MinecraftUtils;
import me.djtheredstoner.yetanotherskyblockmod.YetAnotherSkyblockMod;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Method;

public class YASMListener {

    private YetAnotherSkyblockMod yasm = YetAnotherSkyblockMod.instance;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(!(MinecraftUtils.isHypixel() && yasm.isOnSkyblock)) return;
        String message = event.message.getUnformattedText();
        if(message.equals("  NICE! SLAYER BOSS SLAIN!") && yasm.config.slayerOof) {
            playOofSound();
        }
    }

    private void playOofSound() {
        if(YetAnotherSkyblockMod.instance.oofModDetected) {
            try {
                Class oofMod = Class.forName("us.nickfraction.oofmod.OofMod");
                Object listener = oofMod.getField("listener").get(null);
                Class listenerClass = Class.forName("us.nickfraction.oofmod.listeners.OofModListener");
                Method oofMethod = listenerClass.getDeclaredMethod("playOofSound");
                oofMethod.setAccessible(true);
                oofMethod.invoke(listener);
            } catch (Exception ignored) {}
        }
    }

}
