package me.djtheredstoner.yetanotherskyblockmod.commands;

import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.api.commands.DisplayName;
import gg.essential.universal.UMinecraft;
import gg.essential.universal.wrappers.UPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartyReportCommand extends Command {

    private static final String reportCommand = "/wdr %s -b PC_C IGR";

    public PartyReportCommand() {
        super("partyreport");
    }

    @Nullable
    @Override
    public Set<Alias> getCommandAliases() {
        Set<Alias> set = new HashSet<>();
        set.add(new Alias("preport"));
        set.add(new Alias("pcreport"));
        return set;
    }

    @DefaultHandler
    public void handle(@DisplayName("player") String player) {
        if (UPlayer.getPlayer() != null) {
            UPlayer.getPlayer().sendChatMessage(String.format(reportCommand, player));
        }
    }

}
