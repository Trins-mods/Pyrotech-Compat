package trinsdar.pyrotech_compat.world;

import exterminatorjeff.undergroundbiomes.api.UBBiome;
import exterminatorjeff.undergroundbiomes.api.UndergroundBiomeSet;
import exterminatorjeff.undergroundbiomes.api.common.UBLogger;
import exterminatorjeff.undergroundbiomes.api.event.UBForceReProcessEvent;
import exterminatorjeff.undergroundbiomes.config.UBConfig;
import exterminatorjeff.undergroundbiomes.world.TraditionalStoneReplacer;
import exterminatorjeff.undergroundbiomes.world.UBBiomesSet;
import exterminatorjeff.undergroundbiomes.world.UBStoneReplacer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.logging.Level;

public class WorldGenManager {
    private final UBLogger LOGGER;

    private final int dimensionID;
    private final UndergroundBiomeSet biomesSet;
    private UBStoneReplacer stoneReplacer;

    private boolean worldLoaded = false;
    private World world;
    private int seed;

    public WorldGenManager(int dimensionID) {
        LOGGER = new UBLogger(WorldGenManager.class + " " + dimensionID, Level.INFO);
        LOGGER.debug("Dimension " + dimensionID + " will be UBified");

        this.dimensionID = dimensionID;
        biomesSet = new UBBiomesSet(UBConfig.SPECIFIC);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        world = event.getWorld();

        // TODO World specific config
        world.getSaveHandler().getWorldDirectory();

        if (world.provider.getDimension() == dimensionID && !worldLoaded) {
            LOGGER.debug("Dimension " + dimensionID + " loaded");

            worldLoaded = true;

            seed = (int) world.getSeed();
            if (UBConfig.SPECIFIC.dimensionSpecificSeeds())
                seed += dimensionID;
            this.stoneReplacer = new TraditionalStoneReplacer(seed, UBConfig.SPECIFIC.biomeSize(), biomesSet);
        }
    }


    private UBBiome blockBiomeValue(int xPos, int zPos) {
        return stoneReplacer.UBBiomeAt(xPos, zPos);
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPopulateChunkPost(PopulateChunkEvent.Post event) {
        if (event.getWorld().provider.getDimension() == dimensionID && worldLoaded) {
            Chunk chunk = event.getWorld().getChunkFromChunkCoords(event.getChunkX(), event.getChunkZ());
            this.stoneReplacer.replaceStoneInChunk(chunk);
            stoneReplacer.redoOres(event.getWorld());
        }
    }
}
