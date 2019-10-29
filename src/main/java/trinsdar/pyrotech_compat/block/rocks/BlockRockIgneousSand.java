package trinsdar.pyrotech_compat.block.rocks;

import com.codetaylor.mc.athenaeum.spi.IBlockVariant;
import com.codetaylor.mc.athenaeum.spi.IVariant;
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
import trinsdar.pyrotech_compat.init.BlockInitializer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.stream.Stream;

public class BlockRockIgneousSand extends BlockRockBase implements IBlockVariant<BlockRockIgneousSand.EnumType> {
    public static final String NAME = "rock_igneous_sand";
    public static final IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockRockIgneousSand() {
        super(NAME, SoundType.STONE);
    }

    @Override
    @Nonnull
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        EnumType type = state.getValue(VARIANT);
        if (type.getMeta() < 8) {
            return SoundType.SAND;
        } else {
            return SoundType.STONE;
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> list) {
        EnumType[] var3 = EnumType.values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            EnumType type = var3[var5];
            list.add(new ItemStack(this, 1, type.getMeta()));
        }
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.fromMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VARIANT)).getMeta();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Override
    @Nonnull
    public String getModelName(ItemStack itemStack) {
        return EnumType.fromMeta(itemStack.getMetadata()).getName();
    }

    @Override
    @Nonnull
    public IProperty<EnumType> getVariant() {
        return VARIANT;
    }

    public static enum EnumType implements IVariant {
        RED_GRANITE_SAND(0, "rock_red_granite_sand"),
        BLACK_GRANITE_SAND(1, "rock_black_granite_sand"),
        RHYOLITE_SAND(2, "rock_rhyolite_sand"),
        ANDESITE_SAND(3, "rock_andesite_sand"),
        GABBRO_SAND(4, "rock_gabbro_sand"),
        BASALT_SAND(5, "rock_basalt_sand"),
        KOMATIITE_SAND(6, "rock_komatiite_sand"),
        DACITE_SAND(7, "rock_dacite_sand"),
        RED_GRANITE_SANDSTONE(8, "rock_red_granite_sandstone"),
        BLACK_GRANITE_SANDSTONE(9, "rock_black_granite_sandstone"),
        RHYOLITE_SANDSTONE(10, "rock_rhyolite_sandstone"),
        ANDESITE_SANDSTONE(11, "rock_andesite_sandstone"),
        GABBRO_SANDSTONE(12, "rock_gabbro_sandstone"),
        BASALT_SANDSTONE(13, "rock_basalt_sandstone"),
        KOMATIITE_SANDSTONE(14, "rock_komatiite_sandstone"),
        DACITE_SANDSTONE(15, "rock_dacite_sandstone");

        private static final EnumType[] META_LOOKUP = (EnumType[]) Stream.of(values()).sorted(Comparator.comparing(EnumType::getMeta)).toArray((x$0) -> {
            return new EnumType[x$0];
        });
        private final int meta;
        private final String name;

        private EnumType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        @Override
        public int getMeta() {
            return this.meta;
        }

        @Override
        @Nonnull
        public String getName() {
            return this.name;
        }

        public ItemStack asStack() {
            return this.asStack(1);
        }

        public ItemStack asStack(int amount) {
            return new ItemStack(BlockInitializer.blockRockIgneousSand, amount, this.meta);
        }

        public static EnumType fromMeta(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }
    }
}
