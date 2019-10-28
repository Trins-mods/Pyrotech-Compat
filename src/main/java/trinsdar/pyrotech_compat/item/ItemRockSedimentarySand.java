package trinsdar.pyrotech_compat.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import trinsdar.pyrotech_compat.BlockInitializer;
import trinsdar.pyrotech_compat.PyrotechCompat;
import trinsdar.pyrotech_compat.block.rocks.BlockRockSedimentarySand;
import trinsdar.pyrotech_compat.entity.EntityRockIgneous;
import trinsdar.pyrotech_compat.entity.EntityRockSedimentarySand;

import javax.annotation.Nonnull;

public class ItemRockSedimentarySand extends ItemRockBase {
    public ItemRockSedimentarySand(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    protected EntityThrowable createEntity(World world, EntityPlayer player, int meta){
        return new EntityRockSedimentarySand(world, player, meta);
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return "tile." + PyrotechCompat.MODID + "." + BlockRockSedimentarySand.EnumType.fromMeta(stack.getMetadata()).getName();
    }
}
