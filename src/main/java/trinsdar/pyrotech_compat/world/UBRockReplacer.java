package trinsdar.pyrotech_compat.world;

import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;
import exterminatorjeff.undergroundbiomes.api.API;
import exterminatorjeff.undergroundbiomes.api.UBBiome;
import exterminatorjeff.undergroundbiomes.api.UBStrataColumn;
import exterminatorjeff.undergroundbiomes.api.UBStrataColumnProvider;
import exterminatorjeff.undergroundbiomes.api.UndergroundBiomeSet;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneStyle;
import exterminatorjeff.undergroundbiomes.api.enums.UBStoneType;
import exterminatorjeff.undergroundbiomes.common.block.UBStone;
import exterminatorjeff.undergroundbiomes.intermod.OresRegistry;
import exterminatorjeff.undergroundbiomes.intermod.StonesRegistry;
import exterminatorjeff.undergroundbiomes.world.noise.NoiseGenerator;
import exterminatorjeff.undergroundbiomes.world.noise.SimplexNoiseGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.fml.common.Loader;
import trinsdar.pyrotech_compat.block.rocks.BlockRockBase;

public class UBRockReplacer implements UBStrataColumnProvider {
    private GenLayer undergroundBiomeIndexLayer;
    final UBBiome[] biomeList;
    final NoiseGenerator noiseGenerator;

    public UBRockReplacer(long seed, int size, UndergroundBiomeSet biomeSet){
        biomeList = biomeSet.allowedBiomes();
        noiseGenerator = new SimplexNoiseGenerator(seed);
    }

    @SuppressWarnings("deprecation")
    public void replaceStoneInChunk(Chunk chunk)  {
        boolean quarkpresent = Loader.isModLoaded("quark");
        int[] biomeValues = getBiomeValues(chunk);
        int xPos = chunk.getPos().x * 16;
        int zPos = chunk.getPos().z * 16;
        // TimeTracker.manager.start("overall");

        // For each storage array
        for (ExtendedBlockStorage storage : chunk.getBlockStorageArray()) {
            if (storage != null && !storage.isEmpty()) {
                int yPos = storage.getYLocation();
                if (yPos < chunk.getWorld().getSeaLevel())
                    return;
                //
                for (int x = 0; x < 16; ++x) {
                    for (int z = 0; z < 16; ++z) {
                        // Get the underground biome for the position
                        UBBiome currentBiome = biomeList[biomeValues[x * 16 + z]];
                        if (currentBiome == null)
                            throw new RuntimeException("" + biomeValues[x * 16 + z]);
                        //
                        // Perlin noise for strata layers height variation
                        int variation = (int) (noiseGenerator.noise((xPos + x) / 55.533, (zPos + z) / 55.533, 3, 1, 0.5) * 10 - 5);
                        for (int y = 0; y < 16; ++y) {
                            IBlockState currentBlockState = storage.get(x, y, z);
                            Block currentBlock = currentBlockState.getBlock();
                            BlockPos currentBlockPos = new BlockPos(x, y, z);
                            /*
                             * Skip air, water and UBStone
                             */
                            if (Block.isEqualTo(Blocks.AIR, currentBlock))
                                continue;
                            if (Block.isEqualTo(Blocks.WATER, currentBlock))
                                continue;
                            // TODO Test without
                            if (currentBlock instanceof BlockRockBase)
                                continue;
                            /*
                             * Stone
                             */
                            if (currentBlock == Blocks.STONE) {
                                // Replace with UBified version
                                storage.set(x, y, z, currentBiome.getStrataBlockAtLayer(yPos + y + variation));
                            } else if (currentBlock == ModuleCore.Blocks.ROCK && API.SETTINGS.replaceCobblestone()) {
                                // Replace with UBified version
                                IBlockState strata = currentBiome.getStrataBlockAtLayer(yPos + y + variation);
                                if (strata.getBlock() instanceof UBStone) {
                                    UBStone block = (UBStone) strata.getBlock();
                                    storage.set(x, y, z,
                                            (StonesRegistry.INSTANCE.stoneFor(block.getStoneType(), UBStoneStyle.COBBLE).getBlock())
                                                    .getStateFromMeta(block.getMetaFromState(strata)));
                                }
                                continue;
                            }
                        }
                    }
                }
            }
        }
        // TimeTracker.manager.stop("overall");
    }

    public int[] getBiomeValues(Chunk chunk){
        int[] var7 = this.undergroundBiomeIndexLayer.getInts(chunk.getPos().x * 16, chunk.getPos().z * 16, 16, 16);
        return var7;
    }

    @Override
    public UBStrataColumn strataColumn(int i, int i1) {
        return null;
    }
}
