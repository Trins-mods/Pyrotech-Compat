package trinsdar.pyrotech_compat.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import trinsdar.pyrotech_compat.BlockInitializer;
import trinsdar.pyrotech_compat.PyrotechCompat;
import trinsdar.pyrotech_compat.block.rocks.BlockRockMetamorphicSand;

import javax.annotation.Nonnull;

public class ItemRockMetamorphicSand extends ItemRockBase {
    public ItemRockMetamorphicSand(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    protected IBlockState getBlockState(ItemStack stack) {
        return  BlockInitializer.blockRockMetamorphicSand.getDefaultState().withProperty(BlockRockMetamorphicSand.VARIANT, BlockRockMetamorphicSand.EnumType.fromMeta(stack.getMetadata()));
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return "tile." + PyrotechCompat.MODID + "." + BlockRockMetamorphicSand.EnumType.fromMeta(stack.getMetadata()).getName();
    }
}
