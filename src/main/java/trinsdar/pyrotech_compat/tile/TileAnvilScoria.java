package trinsdar.pyrotech_compat.tile;


import com.codetaylor.mc.pyrotech.modules.tech.basic.block.spi.BlockAnvilBase;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.TileAnvilGranite;
import com.codetaylor.mc.pyrotech.modules.tech.basic.tile.spi.TileAnvilBase;
import trinsdar.pyrotech_compat.ModulePyrotechCompat;

import javax.annotation.Nonnull;

public class TileAnvilScoria extends TileAnvilGranite {
    @Override
    @Nonnull
    protected BlockAnvilBase getBlock() {
        return ModulePyrotechCompat.Blocks.ANVIL_SCORIA;
    }
}
