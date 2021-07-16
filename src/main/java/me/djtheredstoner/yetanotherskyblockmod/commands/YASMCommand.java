package me.djtheredstoner.yetanotherskyblockmod.commands;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import me.djtheredstoner.yetanotherskyblockmod.YetAnotherSkyblockMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class YASMCommand extends Command {
    public YASMCommand() {
        super("yetanotherskyblockmod");
    }

    @Nullable
    @Override
    public Set<Alias> getCommandAliases() {
        return Collections.singleton(new Alias("yasm"));
    }

    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(YetAnotherSkyblockMod.instance.config.gui());
    }
}
