package me.djtheredstoner.yetanotherskyblockmod.config;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class YASMConfig extends Vigilant {

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Potion HUD",
        description = "Hides powns' potion HUD in skyblock.",
        category = "General",
        subcategory = "HUD Elements"
    )
    public boolean hidePownsPotionHud;

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Armor HUD",
        description = "Hides powns' armor HUD in skyblock.",
        category = "General",
        subcategory = "HUD Elements"
    )
    public boolean hidePownsArmorHud;

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Absorption Hearts",
        description = "Hides absorption hearts in skyblock.",
        category = "General",
        subcategory = "HUD Elements"
    )
    public boolean hideAbsorption;

    public YASMConfig() {
        super(new File("./config/yetanotherskyblockmod.toml"));
        initialize();
    }

}
