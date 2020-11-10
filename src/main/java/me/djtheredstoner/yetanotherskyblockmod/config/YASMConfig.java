package me.djtheredstoner.yetanotherskyblockmod.config;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class YASMConfig extends Vigilant {

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Powns Potion Hud In Skyblock",
        category = "General",
        subcategory = "Hide Other Mods in Skyblock"
    )
    public boolean hidePownsPotionHud;

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Powns Armor Hud In Skyblock",
        category = "General",
        subcategory = "Hide Other Mods in Skyblock"
    )
    public boolean hidePownsArmorHud;

    public YASMConfig() {
        super(new File("./config/yetanotherskyblockmod.toml"));
        initialize();
    }

}
