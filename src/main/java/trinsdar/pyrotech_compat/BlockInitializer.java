package trinsdar.pyrotech_compat;

import com.codetaylor.mc.athenaeum.registry.Registry;
import com.codetaylor.mc.athenaeum.util.ModelRegistrationHelper;
import com.codetaylor.mc.pyrotech.library.util.RegistryHelper;
import com.codetaylor.mc.pyrotech.modules.tech.basic.block.spi.BlockAnvilBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.pyrotech_compat.block.BlockAnvilScoria;
import trinsdar.pyrotech_compat.tile.TileAnvilScoria;

public class BlockInitializer {

    public static void onRegister(Registry registry) {
        BlockAnvilScoria blockAnvilScoria = new BlockAnvilScoria();
        registry.registerBlock(blockAnvilScoria, new BlockAnvilBase.ItemAnvil(blockAnvilScoria), BlockAnvilScoria.NAME);

        RegistryHelper.registerTileEntities(
                registry,
                TileAnvilScoria.class
        );
    }

    @SideOnly(Side.CLIENT)
    public static void onClientRegister(Registry registry) {

        registry.registerClientModelRegistrationStrategy(() -> {

            // Scoria Anvil
            ModelRegistrationHelper.registerVariantBlockItemModels(
                    ModulePyrotechCompat.Blocks.ANVIL_SCORIA.getDefaultState(),
                    BlockAnvilBase.DAMAGE,
                    value -> value
            );
        });
    }
}
