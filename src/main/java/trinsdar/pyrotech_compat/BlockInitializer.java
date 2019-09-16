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
import trinsdar.pyrotech_compat.tile.TileAnvilScoria;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockInitializer {

    public static BlockInitializer instance = new BlockInitializer();
    static final Map<Item, Block> toRegister = new LinkedHashMap<>();
    public static BlockAnvilScoria blockAnvilScoria;

    public static void onRegister() {
        blockAnvilScoria = new BlockAnvilScoria();
        instance.registerBlock(blockAnvilScoria, new BlockAnvilBase.ItemAnvil(blockAnvilScoria));

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
