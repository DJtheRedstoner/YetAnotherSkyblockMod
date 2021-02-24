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
                case "pownsArmorHud":
                    shouldDisable = config.hidePownsArmorHud;
                    break;
                case "guiIngameAbsorption":
                    shouldDisable = config.hideAbsorption;
                    break;
                case "patcherArmor":
                    shouldDisable = config.hidePatcherArmor;
                    break;
                case "patcherDamageGlance":
                    shouldDisable = config.hidePatcherDamage;
                    break;
                case "patcherItemCountGlance":
                    shouldDisable = config.hidePatcherItemCount;
                    break;
                case "patcherEnchGlance":
                    shouldDisable = config.hidePatcherEnch;
                    break;
                case "keystrokes":
                    shouldDisable = config.hideKeystrokes;
                    break;
                default:
                    shouldDisable = false;
            }
            return shouldDisable;
        }
        return false;
    }

}
