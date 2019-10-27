package trinsdar.pyrotech_compat;

import com.codetaylor.mc.athenaeum.network.NetworkEntityIdSupplier;
import com.codetaylor.mc.athenaeum.registry.Registry;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = PyrotechCompat.MODID, name = PyrotechCompat.MODNAME, version = PyrotechCompat.VERSION, dependencies = PyrotechCompat.DEPENDS)
public class PyrotechCompat {
    public static final String MODID = "pyrotech_compat";
    public static final String MODNAME = "Pyrotech Compat";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:pyrotech;after:primal;after:undergroundbiomes";

    public static Logger logger;

    public PyrotechCompat() {

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //JsonMaker.init(event);
        logger = event.getModLog();
        BlockInitializer.onRegister();
        int entityId = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID,EntityRockPC.NAMEPC), EntityRockPC.class, "RockPC", entityId++, this, 80, 1, true);
        if (event.getSide().isClient()){
            BlockInitializer.onClientRegister();
            EntityInitializer.onClientRegister();
        }
    }
}
