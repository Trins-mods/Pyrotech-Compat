package trinsdar.pyrotech_compat.init;

import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;
import com.codetaylor.mc.pyrotech.modules.core.block.BlockRock;
import com.codetaylor.mc.pyrotech.modules.tech.basic.ModuleTechBasic;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.CompactingBinRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import trinsdar.pyrotech_compat.PyrotechCompat;
import trinsdar.pyrotech_compat.block.rocks.BlockRockIgneous;
import trinsdar.pyrotech_compat.block.rocks.BlockRockMetamorphic;
import trinsdar.pyrotech_compat.block.rocks.BlockRockSedimentary;

public class RecipeInitializer {
    public static void init(){
        initCompactor(ModuleTechBasic.Registries.COMPACTING_BIN_RECIPE);
    }

    public static void initCompactor(IForgeRegistryModifiable<CompactingBinRecipe> registry){
        for (int i = 0; i < 8; i++){
            registry.register(new CompactingBinRecipe(
                    getUBCGravel("igneous", i),
                    Ingredient.fromStacks(
                            new ItemStack(BlockInitializer.itemRockIgneous, 1, i)
                    ),
                    8
            ).setRegistryName(PyrotechCompat.MODID, "igneous_gravel:" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "")));
            registry.register(new CompactingBinRecipe(
                    getUBCGravel("metamorphic", i),
                    Ingredient.fromStacks(
                            new ItemStack(BlockInitializer.itemRockMetamorphic, 1, i)
                    ),
                    8
            ).setRegistryName(PyrotechCompat.MODID, "metamorphic_gravel:" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "")));
            if (i == 0){
                registry.register(new CompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(ModuleCore.Blocks.ROCK, 1, BlockRock.EnumType.LIMESTONE.getMeta())
                        ),
                        8
                ).setRegistryName(PyrotechCompat.MODID, "sedimentary_gravel:limestone"));
            } else if (i < 4){
                registry.register(new CompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(BlockInitializer.itemRockSedimentary, 1, i - 1)
                        ),
                        8
                ).setRegistryName(PyrotechCompat.MODID, "sedimentary_gravel:" + BlockRockSedimentary.EnumType.fromMeta(i - 1).getName().replace("rock_", "")));
            } else if (i == 4){
                continue;
            } else {
                registry.register(new CompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(BlockInitializer.itemRockSedimentary, 1, i - 2)
                        ),
                        8
                ).setRegistryName(PyrotechCompat.MODID, "sedimentary_gravel:" + BlockRockSedimentary.EnumType.fromMeta(i - 2).getName().replace("rock_", "")));
            }
        }
    }

    public static ItemStack getUBCGravel(String type, int meta){
        return GameRegistry.makeItemStack("undergroundbiomes:" + type + "_gravel", meta, 1, null);
    }
}
