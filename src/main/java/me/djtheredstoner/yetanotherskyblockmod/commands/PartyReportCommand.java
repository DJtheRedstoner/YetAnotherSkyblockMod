package me.djtheredstoner.yetanotherskyblockmod.commands;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

public class PartyReportCommand extends CommandBase {

    private static final String reportCommand = "/wdr %s -b PC_C IGR";

    @Override
    public String getCommandName() {
        return "partyreport";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/partyreport <player>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("preport", "pcreport");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(!(sender instanceof EntityPlayerSP)) return;
        if(args.length == 1) {
            ((EntityPlayerSP) sender).sendChatMessage(String.format(reportCommand, args[0]));
            return;
        }
        sender.addChatMessage(new ChatComponentText("§cYou must specify a player to report!"));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
