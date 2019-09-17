package trinsdar.pyrotech_compat.block.rocks;

import com.codetaylor.mc.athenaeum.spi.IBlockVariant;
import com.codetaylor.mc.athenaeum.spi.IVariant;
import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;
import com.codetaylor.mc.pyrotech.modules.core.block.BlockRock;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.stream.Stream;

public class BlockRockSedimentarySand extends BlockRockBase implements IBlockVariant<BlockRockSedimentarySand.EnumType> {
    public static final String NAME = "rock_sedimentary_sand";
    public static final IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockRockSedimentarySand() {
        super(NAME, SoundType.STONE);
    }

    @Nonnull
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        EnumType type = state.getValue(VARIANT);
        if (type.getMeta() < 8) {
            return SoundType.SAND;
        } else {
            return SoundType.STONE;
        }
    }

    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> list) {
        BlockRock.EnumType[] var3 = BlockRock.EnumType.values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            BlockRock.EnumType type = var3[var5];
            list.add(new ItemStack(this, 1, type.getMeta()));
        }

    }

    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.fromMeta(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VARIANT)).getMeta();
    }

    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Nonnull
    public String getModelName(ItemStack itemStack) {
        return BlockRock.EnumType.fromMeta(itemStack.getMetadata()).getName();
    }

    @Nonnull
    public IProperty<EnumType> getVariant() {
        return VARIANT;
    }

    public static enum EnumType implements IVariant {
        LIMESTONE_SAND(0, "rock_limestone_sand"),
        CHALK_SAND(1, "rock_chalk_sand"),
        SHALE_SAND(2, "rock_shale_sand"),
        SILTSTONE_SAND(3, "rock_siltstone_sand"),
        LIGNITE_BLOCK_SAND(4, "rock_lignite_block_sand"),
        DOLOMITE_SAND(5, "rock_dolomite_sand"),
        GREYWACKE_SAND(6, "rock_greywacke_sand"),
        CHERT_SAND(7, "rock_chert_sand"),
        LIMESTONE_SANDSTONE(8, "rock_limestone_sandstone"),
        CHALK_SANDSTONE(9, "rock_chalk_sandstone"),
        SHALE_SANDSTONE(10, "rock_shale_sandstone"),
        SILTSTONE_SANDSTONE(11, "rock_siltstone_sandstone"),
        LIGNITE_BLOCK_SANDSTONE(12, "rock_lignite_block_sandstone"),
        DOLOMITE_SANDSTONE(13, "rock_dolomite_sandstone"),
        GREYWACKE_SANDSTONE(14, "rock_greywacke_sandstone"),
        CHERT_SANDSTONE(15, "rock_chert_sandstone");

        private static final EnumType[] META_LOOKUP = (EnumType[]) Stream.of(values()).sorted(Comparator.comparing(EnumType::getMeta)).toArray((x$0) -> {
            return new EnumType[x$0];
        });
        private final int meta;
        private final String name;

        private EnumType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public int getMeta() {
            return this.meta;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }

        public ItemStack asStack() {
            return this.asStack(1);
        }

        public ItemStack asStack(int amount) {
            return new ItemStack(ModuleCore.Blocks.ROCK, amount, this.meta);
        }

        public static EnumType fromMeta(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }
    }
}
