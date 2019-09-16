package trinsdar.pyrotech_compat;

import com.codetaylor.mc.athenaeum.module.ModuleBase;
import com.codetaylor.mc.athenaeum.network.IPacketRegistry;
import com.codetaylor.mc.athenaeum.network.IPacketService;
import com.codetaylor.mc.athenaeum.network.tile.ITileDataService;
import com.codetaylor.mc.athenaeum.registry.Registry;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trinsdar.pyrotech_compat.block.BlockAnvilScoria;

public class ModulePyrotechCompat extends ModuleBase {
    public static final String MODULE_ID = "module.pyrotech_compat";
    public static final String MOD_ID = PyrotechCompat.MODID;
    public static final CreativeTabs CREATIVE_TAB = ModPyrotech.CREATIVE_TAB;

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID + "." + ModulePyrotechCompat.class.getSimpleName());

    public static IPacketService PACKET_SERVICE;
    public static ITileDataService TILE_DATA_SERVICE;

    protected ModulePyrotechCompat() {
        super(0, MOD_ID);
        this.setRegistry(new Registry(MOD_ID, CREATIVE_TAB));
        this.enableAutoRegistry();

        PACKET_SERVICE = this.enableNetwork();
        TILE_DATA_SERVICE = this.enableNetworkTileDataService(PACKET_SERVICE);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onPreInitializationEvent(FMLPreInitializationEvent event) {

        super.onPreInitializationEvent(event);
    }

    @Override
    public void onRegisterRecipesEvent(RegistryEvent.Register<IRecipe> event) {

        super.onRegisterRecipesEvent(event);
        //AnvilGraniteRecipesAdd.apply(ModuleTechBasic.Registries.ANVIL_RECIPE);
    }

    @Override
    public void onRegister(Registry registry) {

        BlockInitializer.onRegister(registry);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onClientRegister(Registry registry) {

        BlockInitializer.onClientRegister(registry);
    }

    @Override
    public void onPostInitializationEvent(FMLPostInitializationEvent event) {

        super.onPostInitializationEvent(event);
    }

    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks {

        @GameRegistry.ObjectHolder(BlockAnvilScoria.NAME)
        public static final BlockAnvilScoria ANVIL_SCORIA;

        static {
            ANVIL_SCORIA = null;
        }
    }
}
