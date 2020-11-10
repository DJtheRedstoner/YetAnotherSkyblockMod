package me.djtheredstoner.yetanotherskyblockmod.commands;

import club.sk1er.mods.core.ModCore;
import me.djtheredstoner.yetanotherskyblockmod.YetAnotherSkyblockMod;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Collections;
import java.util.List;

public class YASMCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "yetanotherskyblockmod";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("yasm");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        ModCore.getInstance().getGuiHandler().open(YetAnotherSkyblockMod.instance.config.gui());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
