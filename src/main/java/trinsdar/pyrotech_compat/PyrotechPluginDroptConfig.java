package trinsdar.pyrotech_compat;

import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.codetaylor.mc.pyrotech.modules.plugin.dropt.ModulePluginDroptConfig;
import net.minecraftforge.common.config.Config;

@Config(modid = ModPyrotech.MOD_ID, name = "pyrotech/plugin.Dropt")
public class PyrotechPluginDroptConfig {
    static {
        ModulePluginDroptConfig.ENABLED_RULES.put("netherrack", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("igneous_cobblestone", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("igneous_stone", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("igneous_gravel", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("igneous_sand", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("igneous_sandstone", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("metamorphic_cobblestone", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("metamorphic_stone", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("metamorphic_gravel", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("metamorphic_sand", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("metamorphic_sandstone", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("sedimentary_stone", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("sedimentary_gravel", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("sedimentary_sand", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("sedimentary_sandstone", true);
        ModulePluginDroptConfig.ENABLED_RULES.put("ubc_clay", true);
    }
}
