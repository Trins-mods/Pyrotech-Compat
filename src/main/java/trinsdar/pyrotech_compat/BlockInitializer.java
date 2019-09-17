package trinsdar.pyrotech_compat;

import com.codetaylor.mc.athenaeum.registry.Registry;
import com.codetaylor.mc.athenaeum.util.ModelRegistrationHelper;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.codetaylor.mc.pyrotech.modules.tech.basic.block.spi.BlockAnvilBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.pyrotech_compat.block.BlockAnvilScoria;
import trinsdar.pyrotech_compat.block.rocks.BlockRockIgneous;
import trinsdar.pyrotech_compat.block.rocks.BlockRockIgneousSand;
import trinsdar.pyrotech_compat.block.rocks.BlockRockMetamorphic;
import trinsdar.pyrotech_compat.block.rocks.BlockRockMetamorphicSand;
import trinsdar.pyrotech_compat.block.rocks.BlockRockSedimentary;
import trinsdar.pyrotech_compat.block.rocks.BlockRockSedimentarySand;
import trinsdar.pyrotech_compat.item.ItemRockIgneous;
import trinsdar.pyrotech_compat.item.ItemRockIgneousSand;
import trinsdar.pyrotech_compat.item.ItemRockMetamorphic;
import trinsdar.pyrotech_compat.item.ItemRockMetamorphicSand;
import trinsdar.pyrotech_compat.item.ItemRockSedimentary;
import trinsdar.pyrotech_compat.item.ItemRockSedimentarySand;
import trinsdar.pyrotech_compat.tile.TileAnvilScoria;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockInitializer {

    public static BlockInitializer instance = new BlockInitializer();
    static final Map<Item, Block> toRegister = new LinkedHashMap<>();
    public static final BlockAnvilScoria blockAnvilScoria = new BlockAnvilScoria();
    public static final BlockRockIgneous blockRockIgneous = new BlockRockIgneous();
    public static final BlockRockIgneousSand blockRockIgneousSand = new BlockRockIgneousSand();
    public static final BlockRockMetamorphic blockRockMetamorphic = new BlockRockMetamorphic();
    public static final BlockRockMetamorphicSand blockRockMetamorphicSand = new BlockRockMetamorphicSand();
    public static final BlockRockSedimentary blockRockSedimentary = new BlockRockSedimentary();
    public static final BlockRockSedimentarySand blockRockSedimentarySand = new BlockRockSedimentarySand();

    public static void onRegister() {
        instance.registerBlock(blockAnvilScoria, new BlockAnvilBase.ItemAnvil(blockAnvilScoria));
        instance.registerBlock(blockRockIgneous, new ItemRockIgneous(blockRockIgneous));
        instance.registerBlock(blockRockMetamorphic, new ItemRockMetamorphic(blockRockMetamorphic));
        instance.registerBlock(blockRockSedimentary, new ItemRockSedimentary(blockRockSedimentary));
        instance.registerBlock(blockRockIgneousSand, new ItemRockIgneousSand(blockRockIgneousSand));
        instance.registerBlock(blockRockMetamorphicSand, new ItemRockMetamorphicSand(blockRockMetamorphicSand));
        instance.registerBlock(blockRockSedimentarySand, new ItemRockSedimentarySand(blockRockSedimentarySand));
        GameRegistry.registerTileEntity(TileAnvilScoria.class, new ResourceLocation(PyrotechCompat.MODID, "tile." + TileAnvilScoria.class.getSimpleName()));
    }

    static <T extends Block, I extends Item> T registerBlock(T block, I item) {
        toRegister.put(item, block);
        return block;
    }

    public <B extends Block> B registerBlock(B block) {
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

    public <B extends Block, I extends ItemBlock> B registerBlock(B block, I itemBlock) {
        this.registerBlock(block);
        this.registerItemBlock(itemBlock, block);
        return block;
    }

    public Item registerItemBlock(Item item, Block block) {
        item.setRegistryName(block.getRegistryName());
        item.setCreativeTab(ModPyrotech.CREATIVE_TAB);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

    @SideOnly(Side.CLIENT)
    public static void onClientRegister(Registry registry) {

        registry.registerClientModelRegistrationStrategy(() -> {

            // Scoria Anvil
            ModelRegistrationHelper.registerVariantBlockItemModels(
                    blockAnvilScoria.getDefaultState(),
                    BlockAnvilBase.DAMAGE,
                    value -> value
            );
        });
    }
}
