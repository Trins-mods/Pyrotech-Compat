package trinsdar.pyrotech_compat.block.rocks;

import com.codetaylor.mc.pyrotech.ModPyrotech;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import trinsdar.pyrotech_compat.PyrotechCompat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockRockBase extends Block {
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

    public BlockRockBase(String name, SoundType soundType) {
        super(Material.GROUND);
        this.setRegistryName(name);
        this.setUnlocalizedName(PyrotechCompat.MODID +"." + name);
        this.setCreativeTab(ModPyrotech.CREATIVE_TAB);
        this.setHardness(0.1F);
        this.setSoundType(soundType);
    }

    @Override
    public boolean canPlaceBlockAt(World world, @Nonnull BlockPos pos) {
        return world.isSideSolid(pos.down(), EnumFacing.UP) && super.canPlaceBlockAt(world, pos);
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.canPlaceBlockAt(world, pos)) {
            world.destroyBlock(pos, true);
        }

    }

    @Override
    public boolean isSideSolid(IBlockState base_state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return this.isFullBlock(state);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return this.isFullBlock(state);
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return this.isFullBlock(state);
    }

    @Override
    @Nonnull
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean isPassable(IBlockAccess world, BlockPos pos) {
        return true;
    }
}
