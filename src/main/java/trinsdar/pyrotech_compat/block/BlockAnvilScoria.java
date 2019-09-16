package trinsdar.pyrotech_compat.block;

import com.codetaylor.mc.pyrotech.modules.tech.basic.block.spi.BlockAnvilBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import trinsdar.pyrotech_compat.ModulePyrotechCompat;
import trinsdar.pyrotech_compat.tile.TileAnvilScoria;

public class BlockAnvilScoria extends BlockAnvilBase {
    public static final String NAME = "anvil_scoria";

    public BlockAnvilScoria() {
        super(Material.ROCK);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.STONE);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    protected Block getBlock() {
        return ModulePyrotechCompat.Blocks.ANVIL_SCORIA;
    }

    @Override
    protected TileEntity createTileEntity() {
        return new TileAnvilScoria();
    }
}
