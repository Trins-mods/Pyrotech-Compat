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
import trinsdar.pyrotech_compat.block.rocks.BlockRockIgneousSand;
import trinsdar.pyrotech_compat.entity.EntityRockIgneous;
import trinsdar.pyrotech_compat.entity.EntityRockIgneousSand;

import javax.annotation.Nonnull;

public class ItemRockIgneousSand extends ItemRockBase {
    public ItemRockIgneousSand(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    protected EntityThrowable createEntity(World world, EntityPlayer player, int meta){
        return new EntityRockIgneousSand(world, player, meta);
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return "tile." + PyrotechCompat.MODID + "." + BlockRockIgneousSand.EnumType.fromMeta(stack.getMetadata()).getName();
    }
}
