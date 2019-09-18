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
import trinsdar.pyrotech_compat.BlockInitializer;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.stream.Stream;

public class BlockRockSedimentary extends BlockRockBase implements IBlockVariant<BlockRockSedimentary.EnumType> {
    public static final String NAME = "rock_sedimentary";
    public static final IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockRockSedimentary() {
        super(NAME, SoundType.STONE);
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
        CHALK(0, "rock_chalk"),
        SHALE(1, "rock_shale"),
        SILTSTONE(2, "rock_siltstone"),
        DOLOMITE(3, "rock_dolomite"),
        GREYWACKE(4, "rock_greywacke"),
        CHERT(5, "rock_chert");

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
            return new ItemStack(BlockInitializer.blockRockSedimentary, amount, this.meta);
        }

        public static EnumType fromMeta(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }
    }
}
