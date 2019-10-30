package trinsdar.pyrotech_compat;

import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.codetaylor.mc.pyrotech.modules.plugin.dropt.ModulePluginDroptConfig;
import net.minecraftforge.common.config.Config;

@Config(modid = ModPyrotech.MOD_ID, name = "pyrotech/plugin.Dropt")
public class PyrotechCompatConfig {
    static {
        ModulePluginDroptConfig.ENABLED_RULES.put("netherrack", true);
    }
}
