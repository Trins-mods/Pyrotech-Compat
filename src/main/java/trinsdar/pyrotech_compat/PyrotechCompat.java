package trinsdar.pyrotech_compat;

import com.codetaylor.mc.athenaeum.registry.Registry;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PyrotechCompat.MODID, name = PyrotechCompat.MODNAME, version = PyrotechCompat.VERSION, dependencies = PyrotechCompat.DEPENDS)
public class PyrotechCompat {
    public static final String MODID = "pyrotech_compat";
    public static final String MODNAME = "Pyrotech Compat";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:pyrotech;after:primal;after:undergroundbiomes";

    public PyrotechCompat() {

    }

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        //JsonMaker.init(event);
        BlockInitializer.onRegister();
        if (event.getSide().isClient()){
            BlockInitializer.onClientRegister(new Registry(MODID, ModPyrotech.CREATIVE_TAB));
        }
    }
}
