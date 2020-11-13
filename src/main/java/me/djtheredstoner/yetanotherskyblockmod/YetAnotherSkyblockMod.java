package me.djtheredstoner.yetanotherskyblockmod;

import club.sk1er.mods.core.util.MinecraftUtils;
import com.google.common.collect.Sets;
import me.djtheredstoner.yetanotherskyblockmod.commands.YASMCommand;
import me.djtheredstoner.yetanotherskyblockmod.config.YASMConfig;
import me.djtheredstoner.yetanotherskyblockmod.listeners.YASMListener;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Set;

@Mod(modid = "yetanotherskyblockmod", name = "YetAnotherSkyblockMod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]", clientSideOnly = true)
public class YetAnotherSkyblockMod {

    @Mod.Instance
    public static YetAnotherSkyblockMod instance;

    private static final Set<String> SKYBLOCK_IN_ALL_LANGUAGES = Sets.newHashSet("SKYBLOCK","\u7A7A\u5C9B\u751F\u5B58", "\u7A7A\u5CF6\u751F\u5B58");

    public YASMConfig config = new YASMConfig();
    public boolean isOnSkyblock = false;
    public boolean oofModDetected = false;


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);

        config.preload();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new YASMListener());
        ClientCommandHandler.instance.registerCommand(new YASMCommand());

        oofModDetected = Loader.isModLoaded("refractionoof");
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        if(event.phase == TickEvent.Phase.START) {
            boolean skyblock = false;

            Minecraft mc = Minecraft.getMinecraft();

            if (mc != null && mc.theWorld != null && MinecraftUtils.isHypixel()) {
                ScoreObjective sidebarObjective = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);

                if (sidebarObjective != null) {
                    String objectiveName = EnumChatFormatting.getTextWithoutFormattingCodes(sidebarObjective.getDisplayName());
                    for(String name : SKYBLOCK_IN_ALL_LANGUAGES) {
                        if (objectiveName.startsWith(name)) {
                            skyblock = true;
                            break;
                        }
                    }
                }
            }
            if(skyblock != isOnSkyblock) {
                isOnSkyblock = skyblock;
            }
        }
    }
}
