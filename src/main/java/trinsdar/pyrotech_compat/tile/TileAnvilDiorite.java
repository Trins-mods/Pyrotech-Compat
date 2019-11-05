package trinsdar.pyrotech_compat.tile;

import com.codetaylor.mc.pyrotech.modules.tech.basic.block.spi.BlockAnvilBase;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.TileAnvilGranite;
import trinsdar.pyrotech_compat.init.BlockInitializer;

import javax.annotation.Nonnull;

public class TileAnvilDiorite extends TileAnvilGranite {

    @Override
    @Nonnull
    protected BlockAnvilBase getBlock() {
        return BlockInitializer.blockAnvilDiorite;
    }
}
