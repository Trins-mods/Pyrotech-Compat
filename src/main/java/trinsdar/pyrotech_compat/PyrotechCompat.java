package trinsdar.pyrotech_compat;

import com.codetaylor.mc.athenaeum.module.ModuleBase;
import com.codetaylor.mc.athenaeum.module.ModuleManager;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static trinsdar.pyrotech_compat.ModulePyrotechCompat.MOD_ID;

@Mod(modid = PyrotechCompat.MODID, name = PyrotechCompat.MODNAME, version = PyrotechCompat.VERSION, dependencies = PyrotechCompat.DEPENDS)
public class PyrotechCompat {
    public static final String MODID = "pyrotech_compat";
    public static final String MODNAME = "Pyrotech Compat";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:pyrotech;after:primal;after:undergroundbiomes";

    public PyrotechCompat() {

    }

    @Mod.EventHandler
    public static void onConstruction(FMLConstructionEvent event){
        Field moduleManager = null;
        Field registeredModules = null;
        try {
            moduleManager = ModPyrotech.class.getDeclaredField("moduleManager");
        } catch (NoSuchFieldException e) {
            FMLLog.log.info(e);
        } catch (SecurityException e) {
            FMLLog.log.info(e);
        }
        if (moduleManager != null) {
            moduleManager.setAccessible(true);
        }
        ModuleManager copy = null;
        try {
            if (moduleManager != null) {
                copy = (ModuleManager) moduleManager.get(moduleManager);
            }
        } catch (IllegalArgumentException e) {
            FMLLog.log.info(e);
        } catch (IllegalAccessException e) {
            FMLLog.log.info(e);
        }
        try {
            registeredModules = ModPyrotech.class.getDeclaredField("registeredModules");
        } catch (NoSuchFieldException e) {
            FMLLog.log.info(e);
        } catch (SecurityException e) {
            FMLLog.log.info(e);
        }
        if (registeredModules != null) {
            registeredModules.setAccessible(true);
        }
        Set<Class<? extends ModuleBase>> copy2 = null;
        try {
            if (registeredModules != null) {
                copy2 = (Set<Class<? extends ModuleBase>>) registeredModules.get(registeredModules);
            }
        } catch (IllegalArgumentException e) {
            FMLLog.log.info(e);
        } catch (IllegalAccessException e) {
            FMLLog.log.info(e);
        }
        copy.registerModules(ModulePyrotechCompat.class);
        copy2.add(ModulePyrotechCompat.class);
    }
}
