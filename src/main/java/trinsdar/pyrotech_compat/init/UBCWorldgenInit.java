package trinsdar.pyrotech_compat.init;

import exterminatorjeff.undergroundbiomes.config.ConfigManager;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import trinsdar.pyrotech_compat.PyrotechCompat;
import trinsdar.pyrotech_compat.world.DimensionManager;

import java.io.File;
import java.util.ArrayList;

public class UBCWorldgenInit {
    private ConfigManager configManager;
    private DimensionManager dimensionManager;

    public void preInit(FMLPreInitializationEvent event){
        configManager = new ConfigManager(event);
        dimensionManager = new DimensionManager(configManager);
    }

    public void postInit(FMLPostInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(dimensionManager);
    }

    @SuppressWarnings("unused")
    public void serverLoad(FMLServerAboutToStartEvent event) {
        PyrotechCompat.logger.info("server starting via event");
        File worldSaveDirectory = null;
        String worldName = event.getServer().getFolderName();
        if (event.getServer().isSinglePlayer()) {
            File saveDirectory = event.getServer().getFile("saves");
            worldSaveDirectory = new File(saveDirectory, worldName);
        } else {
            PropertyManager settings = new PropertyManager(event.getServer().getFile("server.properties"));
            worldName = settings.getStringProperty("level-name", worldName);
            worldSaveDirectory = event.getServer().getFile(worldName);
        }
        try {
            WorldServer server = event.getServer().getWorld(0);
            File worldLocation = server.getChunkSaveLocation();
            // UndergroundBiomes.logger.info(world.toString() + " "
            // +worldLocation.getAbsolutePath());
            configManager.setWorldFile(worldLocation);
        } catch (NullPointerException e) {
            throw e;
        }
        dimensionManager.refreshManagers();
    }

    public void onServerStopped(FMLServerStoppedEvent event) {
        // for some reason onWorldLoad is running before any of the ServerStartxxx
        // events
        // so I'm having to use a clunky workaround.
        dimensionManager.clearWorldManagers();
//        for (Runnable action : serverCloseActions) {
//            action.run();
//        }
//        for (Runnable action : oneShotServerCloseActions) {
//            action.run();
//        }
//        oneShotServerCloseActions.clear();

    }

    public void runOnServerClose(Runnable action) {
        serverCloseActions.add(action);
    }

    public void runOnNextServerCloseOnly(Runnable action) {
        serverCloseActions.add(action);
    }

    private ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<Runnable>();
    private ArrayList<Runnable> serverCloseActions = new ArrayList<Runnable>();
}
