package trinsdar.pyrotech_compat;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.apache.logging.log4j.Logger;
import trinsdar.pyrotech_compat.entity.EntityRockBase;
import trinsdar.pyrotech_compat.entity.EntityRockIgneous;
import trinsdar.pyrotech_compat.entity.EntityRockIgneousSand;
import trinsdar.pyrotech_compat.entity.EntityRockMetamorphic;
import trinsdar.pyrotech_compat.entity.EntityRockMetamorphicSand;
import trinsdar.pyrotech_compat.entity.EntityRockSedimentary;
import trinsdar.pyrotech_compat.entity.EntityRockSedimentarySand;
import trinsdar.pyrotech_compat.init.BlockInitializer;
import trinsdar.pyrotech_compat.init.EntityInitializer;
import trinsdar.pyrotech_compat.init.OredictInitializer;
import trinsdar.pyrotech_compat.init.RecipeInitializer;
import trinsdar.pyrotech_compat.init.UBCWorldgenInit;

@Mod(modid = PyrotechCompat.MODID, name = PyrotechCompat.MODNAME, version = PyrotechCompat.VERSION, dependencies = PyrotechCompat.DEPENDS)
public class PyrotechCompat {
    public static final String MODID = "pyrotech_compat";
    public static final String MODNAME = "Pyrotech Compat";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:pyrotech@[1.4,);after:primal;after:undergroundbiomes";

    public static Logger logger;

    //public static UBCWorldgenInit worldGen;

    public PyrotechCompat() {
        MinecraftForge.EVENT_BUS.register(new PluginDropt());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        JsonMaker.init(event);
        logger = event.getModLog();
        //worldGen = new UBCWorldgenInit();
        //worldGen.preInit(event);
        BlockInitializer.onRegister();
        int entityId = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockBase.NAME), EntityRockBase.class, "RockPC", entityId++, this, 80, 1, true);
        if (Loader.isModLoaded("undergroundbiomes")){
            EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockIgneous.NAME), EntityRockIgneous.class, "RockIgneous", entityId++, this, 80, 1, true);
            EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockIgneousSand.NAME), EntityRockIgneousSand.class, "RockIgneousSand", entityId++, this, 80, 1, true);
            EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockMetamorphic.NAME), EntityRockMetamorphic.class, "RockMetamorphic", entityId++, this, 80, 1, true);
            EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockMetamorphicSand.NAME), EntityRockMetamorphicSand.class, "RockMetamorphicSand", entityId++, this, 80, 1, true);
            EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockSedimentary.NAME), EntityRockSedimentary.class, "RockSedimentary", entityId++, this, 80, 1, true);
            EntityRegistry.registerModEntity(new ResourceLocation(PyrotechCompat.MODID, EntityRockSedimentarySand.NAME), EntityRockSedimentarySand.class, "RockSedimentarySand", entityId++, this, 80, 1, true);
        }
        if (event.getSide().isClient()){
            BlockInitializer.onClientRegister();
            EntityInitializer.onClientRegister();
        }
        OredictInitializer.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        //worldGen.postInit(event);
    }

    @SubscribeEvent
    public void onRegisterRecipesEvent(RegistryEvent.Register<IRecipe> event){
        if (Loader.isModLoaded("undergroundbiomes")){
            RecipeInitializer.init();
        }
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(MODID))
        {
            ConfigManager.sync(MODID, Config.Type.INSTANCE);
        }
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event){
        //worldGen.onServerStopped(event);
    }
}
