package trinsdar.pyrotech_compat.tile;


import com.codetaylor.mc.pyrotech.modules.tech.basic.block.spi.BlockAnvilBase;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.TileAnvilGranite;
import trinsdar.pyrotech_compat.BlockInitializer;

import javax.annotation.Nonnull;

public class TileAnvilScoria extends TileAnvilGranite {
    @Override
    @Nonnull
    protected BlockAnvilBase getBlock() {
        return BlockInitializer.blockAnvilScoria;
    }
}
