package trinsdar.pyrotech_compat.block;

import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.codetaylor.mc.pyrotech.modules.tech.basic.block.spi.BlockAnvilBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import trinsdar.pyrotech_compat.PyrotechCompat;
import trinsdar.pyrotech_compat.init.BlockInitializer;
import trinsdar.pyrotech_compat.tile.TileAnvilAndesite;
import trinsdar.pyrotech_compat.tile.TileAnvilDiorite;
import trinsdar.pyrotech_compat.tile.TileAnvilScoria;

public class BlockAnvilStones extends BlockAnvilBase {

    public BlockAnvilStones(String name) {
        super(Material.ROCK);
        this.setRegistryName(name);
        this.setUnlocalizedName(PyrotechCompat.MODID + "." + name);
        this.setCreativeTab(ModPyrotech.CREATIVE_TAB);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.STONE);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    protected Block getBlock() {
        return this;
    }

    @Override
    protected TileEntity createTileEntity() {
        if (this == BlockInitializer.blockAnvilAndesite){
            return new TileAnvilAndesite();
        }
        if (this == BlockInitializer.blockAnvilDiorite){
            return new TileAnvilDiorite();
        }
        return new TileAnvilScoria();
    }
}
