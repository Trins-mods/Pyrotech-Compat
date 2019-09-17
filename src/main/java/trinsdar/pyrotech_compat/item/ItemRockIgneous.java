package trinsdar.pyrotech_compat.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import trinsdar.pyrotech_compat.BlockInitializer;
import trinsdar.pyrotech_compat.PyrotechCompat;
import trinsdar.pyrotech_compat.block.rocks.BlockRockIgneous;

import javax.annotation.Nonnull;

public class ItemRockIgneous extends ItemRockBase {
    public ItemRockIgneous(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    protected IBlockState getBlockState(ItemStack stack) {
        return  BlockInitializer.blockRockIgneous.getDefaultState().withProperty(BlockRockIgneous.VARIANT, BlockRockIgneous.EnumType.fromMeta(stack.getMetadata()));
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return "tile." + PyrotechCompat.MODID + "." + BlockRockIgneous.EnumType.fromMeta(stack.getMetadata()).getName();
    }
}
