package me.djtheredstoner.yetanotherskyblockmod.asm;

import me.djtheredstoner.yetanotherskyblockmod.YetAnotherSkyblockMod;
import me.djtheredstoner.yetanotherskyblockmod.config.YASMConfig;

public class ASMHooks {

    public static boolean isSkyblock(String fromMod) {
        if(YetAnotherSkyblockMod.instance != null) {
            if(!YetAnotherSkyblockMod.instance.isOnSkyblock) return false;
            YASMConfig config = YetAnotherSkyblockMod.instance.config;
            boolean shouldDisable;
            switch(fromMod) {
                case "pownsPotionHud":
                    shouldDisable = config.hidePownsPotionHud;
                    break;
                default:
                    shouldDisable = false;
            }
            return shouldDisable;
        }
        return false;
    }

}
