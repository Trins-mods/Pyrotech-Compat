package trinsdar.pyrotech_compat.init;

import com.codetaylor.mc.pyrotech.Reference;
import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;
import com.codetaylor.mc.pyrotech.modules.core.block.BlockRock;
import com.codetaylor.mc.pyrotech.modules.core.item.ItemMaterial;
import com.codetaylor.mc.pyrotech.modules.tech.basic.ModuleTechBasic;
import com.codetaylor.mc.pyrotech.modules.tech.basic.init.recipe.AnvilIroncladRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.AnvilRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.CompactingBinRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.KilnPitRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.machine.ModuleTechMachine;
import com.codetaylor.mc.pyrotech.modules.tech.machine.init.recipe.BrickKilnRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.machine.init.recipe.MechanicalCompactingBinRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.machine.init.recipe.StoneKilnRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.machine.recipe.BrickKilnRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.machine.recipe.StoneKilnRecipe;
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
        initKilnPit(ModuleTechBasic.Registries.KILN_PIT_RECIPE);
        initAnvil(ModuleTechBasic.Registries.ANVIL_RECIPE);
        StoneKilnRecipesAdd.registerInheritedRecipes(ModuleTechBasic.Registries.KILN_PIT_RECIPE, ModuleTechMachine.Registries.STONE_KILN_RECIPES);
        BrickKilnRecipesAdd.registerInheritedRecipes(ModuleTechMachine.Registries.STONE_KILN_RECIPES, ModuleTechMachine.Registries.BRICK_KILN_RECIPES);
        AnvilIroncladRecipesAdd.registerInheritedRecipes(ModuleTechBasic.Registries.ANVIL_RECIPE);
        MechanicalCompactingBinRecipesAdd.registerInheritedRecipes(ModuleTechBasic.Registries.COMPACTING_BIN_RECIPE, ModuleTechMachine.Registries.MECHANICAL_COMPACTING_BIN_RECIPES);
    }

    public static void initAnvil(IForgeRegistryModifiable<AnvilRecipe> registry){
        for(int i = 0; i < 8; i++){
            registry.register(new AnvilRecipe(
                    getUBC("igneous", "cobble", i),
                    Ingredient.fromStacks(getUBC("igneous", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.HAMMER,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_stone_to_cobbled"));

            registry.register(new AnvilRecipe(
                    new ItemStack(BlockInitializer.itemRockIgneous, 8, i),
                    Ingredient.fromStacks(getUBC("igneous", "cobble", i)),
                    8,
                    AnvilRecipe.EnumType.HAMMER,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID,  BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_cobblestone_to_rocks"));

            registry.register(new AnvilRecipe(
                    getUBC("igneous", "brick_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("igneous", "brick", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_brick_slab"));

            registry.register(new AnvilRecipe(
                    getUBC("igneous", "stone_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("igneous", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_stone_slab"));

            registry.register(new AnvilRecipe(
                    getUBC("igneous", "cobble_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("igneous", "cobble", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_cobble_slab"));

            registry.register(new AnvilRecipe(
                    ItemMaterial.EnumType.BRICK_STONE.asStack(2),
                    Ingredient.fromStacks(
                            getUBC("igneous", "brick_halfslab", 2, i)
                    ),
                    4,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, "brick_stone_from_" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_brick_slab"));

            registry.register(new AnvilRecipe(
                    getUBC("metamorphic", "cobble", i),
                    Ingredient.fromStacks(getUBC("metamorphic", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.HAMMER,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_stone_to_cobbled"));

            registry.register(new AnvilRecipe(
                    new ItemStack(BlockInitializer.itemRockMetamorphic, 8, i),
                    Ingredient.fromStacks(getUBC("metamorphic", "cobble", i)),
                    8,
                    AnvilRecipe.EnumType.HAMMER,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID,  BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_cobblestone_to_rocks"));

            registry.register(new AnvilRecipe(
                    getUBC("metamorphic", "brick_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("metamorphic", "brick", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_brick_slab"));

            registry.register(new AnvilRecipe(
                    getUBC("metamorphic", "stone_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("metamorphic", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_stone_slab"));

            registry.register(new AnvilRecipe(
                    getUBC("metamorphic", "cobble_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("metamorphic", "cobble", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_cobble_slab"));

            registry.register(new AnvilRecipe(
                    ItemMaterial.EnumType.BRICK_STONE.asStack(2),
                    Ingredient.fromStacks(
                            getUBC("metamorphic", "brick_halfslab", i)
                    ),
                    4,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, "brick_stone_from_" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_brick_slab"));

            registry.register(new AnvilRecipe(
                    getUBC("sedimentary", "stone_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("sedimentary", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE
            ).setRegistryName(PyrotechCompat.MODID, getSedimentaryName(i) + "_stone_slab"));

            if (i == 4){
                continue;
            }
            if (i == 0){
                registry.register(new AnvilRecipe(
                        new ItemStack(ModuleCore.Blocks.ROCK, 8, BlockRock.EnumType.LIMESTONE.getMeta()),
                        Ingredient.fromStacks(getUBC("sedimentary", "stone", i)),
                        8,
                        AnvilRecipe.EnumType.HAMMER,
                        AnvilRecipe.EnumTier.GRANITE
                ).setRegistryName(PyrotechCompat.MODID,  getSedimentaryName(i) + "_stone_to_rocks"));
            } else if (i < 4){
                registry.register(new AnvilRecipe(
                        new ItemStack(BlockInitializer.itemRockSedimentary, 8, i - 1),
                        Ingredient.fromStacks(getUBC("sedimentary", "stone", i)),
                        8,
                        AnvilRecipe.EnumType.HAMMER,
                        AnvilRecipe.EnumTier.GRANITE
                ).setRegistryName(PyrotechCompat.MODID,  getSedimentaryName(i) + "_stone_to_rocks"));
            } else {
                registry.register(new AnvilRecipe(
                        new ItemStack(BlockInitializer.itemRockSedimentary, 8, i - 2),
                        Ingredient.fromStacks(getUBC("sedimentary", "stone", i)),
                        8,
                        AnvilRecipe.EnumType.HAMMER,
                        AnvilRecipe.EnumTier.GRANITE
                ).setRegistryName(PyrotechCompat.MODID,  getSedimentaryName(i) + "_stone_to_rocks"));
            }
        }


    }

    public static String getSedimentaryName(int meta){
        if (meta == 0){
            return "limestone";
        } else if (meta < 4){
            return BlockRockSedimentary.EnumType.fromMeta(meta - 1).getName().replace("rock_", "");
        } else if (meta == 4){
            return "lignite";
        } else {
            return BlockRockSedimentary.EnumType.fromMeta(meta - 2).getName().replace("rock_", "");
        }
    }

    public static void initKilnPit(IForgeRegistryModifiable<KilnPitRecipe> registry){
        for (int i = 0; i < 8; i++){
            // Stone Slab
            registry.register(new KilnPitRecipe(
                    getUBC("igneous", "stone_halfslab", i),
                    Ingredient.fromStacks(getUBC("igneous", "cobble_halfslab", i)),
                    Reference.PitKiln.DEFAULT_BURN_TIME_TICKS,
                    Reference.PitKiln.DEFAULT_FAILURE_CHANCE,
                    new ItemStack[]{
                            ItemMaterial.EnumType.PIT_ASH.asStack(),
                            new ItemStack(BlockInitializer.blockRockIgneous, 5, i)
                    }
            ).setRegistryName(PyrotechCompat.MODID, "stone_slab" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock", "")));

            registry.register(new KilnPitRecipe(
                    getUBC("igneous", "stone", i),
                    Ingredient.fromStacks(getUBC("igneous", "cobble", i)),
                    Reference.PitKiln.DEFAULT_BURN_TIME_TICKS,
                    Reference.PitKiln.DEFAULT_FAILURE_CHANCE,
                    new ItemStack[]{
                            ItemMaterial.EnumType.PIT_ASH.asStack(),
                            new ItemStack(BlockInitializer.blockRockIgneous, 5, i)
                    }
            ).setRegistryName(PyrotechCompat.MODID, "stone" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock", "")));

            registry.register(new KilnPitRecipe(
                    getUBC("metamorphic", "stone_halfslab", i),
                    Ingredient.fromStacks(getUBC("metamorphic", "cobble_halfslab", i)),
                    Reference.PitKiln.DEFAULT_BURN_TIME_TICKS,
                    Reference.PitKiln.DEFAULT_FAILURE_CHANCE,
                    new ItemStack[]{
                            ItemMaterial.EnumType.PIT_ASH.asStack(),
                            new ItemStack(BlockInitializer.blockRockMetamorphic, 5, i)
                    }
            ).setRegistryName(PyrotechCompat.MODID, "stone_slab" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock", "")));

            registry.register(new KilnPitRecipe(
                    getUBC("metamorphic", "stone", i),
                    Ingredient.fromStacks(getUBC("metamorphic", "cobble", i)),
                    Reference.PitKiln.DEFAULT_BURN_TIME_TICKS,
                    Reference.PitKiln.DEFAULT_FAILURE_CHANCE,
                    new ItemStack[]{
                            ItemMaterial.EnumType.PIT_ASH.asStack(),
                            new ItemStack(BlockInitializer.blockRockMetamorphic, 5, i)
                    }
            ).setRegistryName(PyrotechCompat.MODID, "stone" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock", "")));
        }
    }

    public static void initCompactor(IForgeRegistryModifiable<CompactingBinRecipe> registry){
        for (int i = 0; i < 8; i++){
            registry.register(new CompactingBinRecipe(
                    getUBCGravel("igneous", i),
                    Ingredient.fromStacks(
                            new ItemStack(BlockInitializer.itemRockIgneous, 1, i)
                    ),
                    8
            ).setRegistryName(PyrotechCompat.MODID, "igneous_gravel_" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "")));
            registry.register(new CompactingBinRecipe(
                    getUBCGravel("metamorphic", i),
                    Ingredient.fromStacks(
                            new ItemStack(BlockInitializer.itemRockMetamorphic, 1, i)
                    ),
                    8
            ).setRegistryName(PyrotechCompat.MODID, "metamorphic_gravel_" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "")));
            if (i == 0){
                registry.register(new CompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(ModuleCore.Blocks.ROCK, 1, BlockRock.EnumType.LIMESTONE.getMeta())
                        ),
                        8
                ).setRegistryName(PyrotechCompat.MODID, "sedimentary_gravel_limestone"));
            } else if (i < 4){
                registry.register(new CompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(BlockInitializer.itemRockSedimentary, 1, i - 1)
                        ),
                        8
                ).setRegistryName(PyrotechCompat.MODID, "sedimentary_gravel_" + BlockRockSedimentary.EnumType.fromMeta(i - 1).getName().replace("rock_", "")));
            } else if (i == 4){
                continue;
            } else {
                registry.register(new CompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(BlockInitializer.itemRockSedimentary, 1, i - 2)
                        ),
                        8
                ).setRegistryName(PyrotechCompat.MODID, "sedimentary_gravel_" + BlockRockSedimentary.EnumType.fromMeta(i - 2).getName().replace("rock_", "")));
            }
        }
    }

    public static ItemStack getUBCGravel(String prefix, int meta){
        return getUBC(prefix, "gravel", meta);
    }

    public static ItemStack getUBC(String prefix, String suffix, int meta){
        return getUBC(prefix, suffix, 1, meta);
    }

    public static ItemStack getUBC(String prefix, String suffix, int amount, int meta){
        return GameRegistry.makeItemStack("undergroundbiomes:" + prefix + "_" + suffix, meta, amount, null);
    }
}
