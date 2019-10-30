package trinsdar.pyrotech_compat.world;

import com.codetaylor.mc.athenaeum.util.ArrayHelper;
import com.codetaylor.mc.pyrotech.modules.worldgen.ModuleWorldGenConfig;
import exterminatorjeff.undergroundbiomes.config.ConfigManager;
import exterminatorjeff.undergroundbiomes.config.UBConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.HashMap;

public class DimensionManager {
    public HashMap<Integer, WorldGenManager> managers = new HashMap<Integer, WorldGenManager>();
    private boolean villageRegistered = false;
    private boolean oreRegistered = false;
    private ConfigManager configManager;

    public DimensionManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void refreshManagers() {
        managers = new HashMap<Integer, WorldGenManager>();
        ((UBConfig) (UBConfig.SPECIFIC)).getUBifiedDimensions().forEach(dimensionID -> {
            if (this.isAllowedDimension(dimensionID, ModuleWorldGenConfig.ROCKS.DIMENSION_WHITELIST, ModuleWorldGenConfig.ROCKS.DIMENSION_BLACKLIST)){
                WorldGenManager manager = new WorldGenManager(dimensionID);
                MinecraftForge.EVENT_BUS.register(manager);
                managers.put(dimensionID, manager);
            }
        });
    }

    private boolean isAllowedDimension(int dimensionId, int[] whitelist, int[] blacklist) {

        if (whitelist.length > 0) {
            return ArrayHelper.containsInt(whitelist, dimensionId);

        } else if (blacklist.length > 0) {
            return !ArrayHelper.containsInt(blacklist, dimensionId);
        }

        return true;
    }

    @SuppressWarnings("unused")
    public void serverLoad(MinecraftServer server) {
        if (server == null) return;
        //logger.info("server starting");
        File worldSaveDirectory = null;
        String worldName = server.getFolderName();
        if (server.isSinglePlayer()) {
            File saveDirectory = server.getFile("saves");
            worldSaveDirectory = new File(saveDirectory, worldName);
        } else {
            PropertyManager settings = new PropertyManager(server.getFile("server.properties"));
            worldName = settings.getStringProperty("level-name", worldName);
            worldSaveDirectory = server.getFile(worldName);
        }
        try {
            WorldServer worldServer = server.getWorld(0);
            File worldLocation = worldServer.getChunkSaveLocation();
            //UndergroundBiomes.logger.info(world.toString() + " " +worldLocation.getAbsolutePath());
            configManager.setWorldFile(worldLocation);
        } catch (NullPointerException e) {
            throw e;
        }
        refreshManagers();
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (managers.size() == 0) serverLoad(event.getWorld().getMinecraftServer());
        int dimension = event.getWorld().provider.getDimension();
        WorldGenManager target = managers.get(dimension);
        //if (dimension == 0) throw new RuntimeException(target.toString());
        if (target != null) target.onWorldLoad(event);
    }

    @SubscribeEvent
    public void onPopulateChunkPost(PopulateChunkEvent.Post event) {
        int dimension = event.getWorld().provider.getDimension();
        WorldGenManager target = managers.get(dimension);
        if (target != null) target.onPopulateChunkPost(event);
    }
}
