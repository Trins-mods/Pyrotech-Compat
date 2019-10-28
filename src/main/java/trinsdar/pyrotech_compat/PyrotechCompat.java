package trinsdar.pyrotech_compat;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.apache.logging.log4j.Logger;
import trinsdar.pyrotech_compat.entity.EntityRockBase;
import trinsdar.pyrotech_compat.entity.EntityRockIgneous;
import trinsdar.pyrotech_compat.entity.EntityRockIgneousSand;
import trinsdar.pyrotech_compat.entity.EntityRockMetamorphic;
import trinsdar.pyrotech_compat.entity.EntityRockMetamorphicSand;
import trinsdar.pyrotech_compat.entity.EntityRockSedimentary;
import trinsdar.pyrotech_compat.entity.EntityRockSedimentarySand;

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
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockBase.NAME), EntityRockBase.class, "RockPC", entityId++, this, 80, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockIgneous.NAME), EntityRockIgneous.class, "RockIgneous", entityId++, this, 80, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockIgneousSand.NAME), EntityRockIgneousSand.class, "RockIgneousSand", entityId++, this, 80, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockMetamorphic.NAME), EntityRockMetamorphic.class, "RockMetamorphic", entityId++, this, 80, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockMetamorphicSand.NAME), EntityRockMetamorphicSand.class, "RockMetamorphicSand", entityId++, this, 80, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockSedimentary.NAME), EntityRockSedimentary.class, "RockSedimentary", entityId++, this, 80, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockSedimentarySand.NAME), EntityRockSedimentarySand.class, "RockSedimentarySand", entityId++, this, 80, 1, true);
        if (event.getSide().isClient()){
            BlockInitializer.onClientRegister();
            EntityInitializer.onClientRegister();
        }
    }
}
