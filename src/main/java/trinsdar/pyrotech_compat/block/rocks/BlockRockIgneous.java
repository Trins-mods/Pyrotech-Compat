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
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.stream.Stream;

public class BlockRockIgneous extends BlockRockBase implements IBlockVariant<BlockRockIgneous.EnumType> {
    public static final String NAME = "rock_igneous";
    public static final IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockRockIgneous() {
        super(NAME, SoundType.STONE);
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
        RED_GRANITE(0, "rock_red_granite"),
        BLACK_GRANITE(1, "rock_black_granite"),
        RHYOLITE(2, "rock_rhyolite"),
        ANDESITE(3, "rock_andesite"),
        GABBRO(4, "rock_gabbro"),
        BASALT(5, "rock_basalt"),
        KOMATITE(6, "rock_komatite"),
        DACITE(7, "rock_dacite");

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
