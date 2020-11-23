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

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Armor Protection Percent",
        description = "Hides patcher armor protection percentage in skyblock.",
        category = "General",
        subcategory = "HUD Elements"
    )
    public boolean hidePatcherArmor;

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Damage Glance",
        description = "Hides patcher damage glance in skyblock.",
        category = "General",
        subcategory = "HUD Elements"
    )
    public boolean hidePatcherDamage;

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Item Count Glance",
        description = "Hides patcher item count glance in skyblock.",
        category = "General",
        subcategory = "HUD Elements"
    )
    public boolean hidePatcherItemCount;

    @Property(
        type = PropertyType.SWITCH,
        name = "Hide Enchantment Glance",
        description = "Hides patcher enchantment glance in skyblock.",
        category = "General",
        subcategory = "HUD Elements"
    )
    public boolean hidePatcherEnch;


    @Property(
        type = PropertyType.SWITCH,
        name = "Slayer Boss Oof",
        description = "Plays Oof sound when you kill a slayer boss.\n§cRequires Oof Mod.",
        category = "General",
        subcategory = "Sound Effects"
    )
    public boolean slayerOof;

    public YASMConfig() {
        super(new File("./config/yetanotherskyblockmod.toml"));
        initialize();
    }

}
