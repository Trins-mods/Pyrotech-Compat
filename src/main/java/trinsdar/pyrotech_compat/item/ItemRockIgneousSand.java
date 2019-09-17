package trinsdar.pyrotech_compat.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import trinsdar.pyrotech_compat.BlockInitializer;
import trinsdar.pyrotech_compat.PyrotechCompat;
import trinsdar.pyrotech_compat.block.rocks.BlockRockIgneousSand;

import javax.annotation.Nonnull;

public class ItemRockIgneousSand extends ItemRockBase {
    public ItemRockIgneousSand(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    protected IBlockState getBlockState(ItemStack stack) {
        return  BlockInitializer.blockRockIgneousSand.getDefaultState().withProperty(BlockRockIgneousSand.VARIANT, BlockRockIgneousSand.EnumType.fromMeta(stack.getMetadata()));
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return "tile." + PyrotechCompat.MODID + "." + BlockRockIgneousSand.EnumType.fromMeta(stack.getMetadata()).getName();
    }
}
