package trinsdar.pyrotech_compat.init;

import com.codetaylor.mc.pyrotech.ModPyrotech;
import com.codetaylor.mc.pyrotech.Reference;
import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;
import com.codetaylor.mc.pyrotech.modules.core.block.BlockRock;
import com.codetaylor.mc.pyrotech.modules.core.item.ItemMaterial;
import com.codetaylor.mc.pyrotech.modules.tech.basic.ModuleTechBasic;
import com.codetaylor.mc.pyrotech.modules.tech.basic.ModuleTechBasicConfig;
import com.codetaylor.mc.pyrotech.modules.tech.basic.init.recipe.AnvilIroncladRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.AnvilRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.CompactingBinRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.basic.recipe.KilnPitRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.machine.ModuleTechMachine;
import com.codetaylor.mc.pyrotech.modules.tech.machine.ModuleTechMachineConfig;
import com.codetaylor.mc.pyrotech.modules.tech.machine.init.recipe.BrickKilnRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.machine.init.recipe.MechanicalCompactingBinRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.machine.init.recipe.StoneKilnRecipesAdd;
import com.codetaylor.mc.pyrotech.modules.tech.machine.recipe.BrickKilnRecipe;
import com.codetaylor.mc.pyrotech.modules.tech.machine.recipe.MechanicalCompactingBinRecipe;
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

import java.util.ArrayList;
import java.util.List;

public class RecipeInitializer {
    static List<AnvilRecipe> anvilRecipes = new ArrayList<>();
    static List<KilnPitRecipe> kilnPitRecipes = new ArrayList<>();
    static List<StoneKilnRecipe> stoneKilnRecipes = new ArrayList<>();
    static List<CompactingBinRecipe> compactingBinRecipes = new ArrayList<>();

    public static void init(){
        initCompactor(ModuleTechBasic.Registries.COMPACTING_BIN_RECIPE);
        initKilnPit(ModuleTechBasic.Registries.KILN_PIT_RECIPE);
        initAnvil(ModuleTechBasic.Registries.ANVIL_RECIPE);
        registerInheritedRecipes();
    }

    public static void initAnvil(IForgeRegistryModifiable<AnvilRecipe> registry){
        for(int i = 0; i < 8; i++){
            registry.register(createAnvilRecipe(
                    getUBC("igneous", "cobble", i),
                    Ingredient.fromStacks(getUBC("igneous", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.HAMMER,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_stone_to_cobbled"
            ));

            registry.register(createAnvilRecipe(
                    new ItemStack(BlockInitializer.itemRockIgneous, 8, i),
                    Ingredient.fromStacks(getUBC("igneous", "cobble", i)),
                    8,
                    AnvilRecipe.EnumType.HAMMER,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_cobblestone_to_rocks"
            ));

            registry.register(createAnvilRecipe(
                    getUBC("igneous", "brick_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("igneous", "brick", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_brick_slab"
            ));

            registry.register(createAnvilRecipe(
                    getUBC("igneous", "stone_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("igneous", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_stone_slab"
            ));

            registry.register(createAnvilRecipe(
                    getUBC("igneous", "cobble_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("igneous", "cobble", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_cobble_slab"
            ));

            registry.register(createAnvilRecipe(
                    ItemMaterial.EnumType.BRICK_STONE.asStack(2),
                    Ingredient.fromStacks(
                            getUBC("igneous", "brick_halfslab", 2, i)
                    ),
                    4,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    "brick_stone_from_" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "") + "_brick_slab"
            ));

            registry.register(createAnvilRecipe(
                    getUBC("metamorphic", "cobble", i),
                    Ingredient.fromStacks(getUBC("metamorphic", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.HAMMER,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_stone_to_cobbled"
            ));

            registry.register(createAnvilRecipe(
                    new ItemStack(BlockInitializer.itemRockMetamorphic, 8, i),
                    Ingredient.fromStacks(getUBC("metamorphic", "cobble", i)),
                    8,
                    AnvilRecipe.EnumType.HAMMER,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_cobblestone_to_rocks"
            ));

            registry.register(createAnvilRecipe(
                    getUBC("metamorphic", "brick_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("metamorphic", "brick", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_brick_slab"
            ));

            registry.register(createAnvilRecipe(
                    getUBC("metamorphic", "stone_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("metamorphic", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_stone_slab"
            ));

            registry.register(createAnvilRecipe(
                    getUBC("metamorphic", "cobble_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("metamorphic", "cobble", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_cobble_slab"
            ));

            registry.register(createAnvilRecipe(
                    ItemMaterial.EnumType.BRICK_STONE.asStack(2),
                    Ingredient.fromStacks(
                            getUBC("metamorphic", "brick_halfslab", i)
                    ),
                    4,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    "brick_stone_from_" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "") + "_brick_slab"
            ));

            registry.register(createAnvilRecipe(
                    getUBC("sedimentary", "stone_halfslab", 2, i),
                    Ingredient.fromStacks(getUBC("sedimentary", "stone", i)),
                    8,
                    AnvilRecipe.EnumType.PICKAXE,
                    AnvilRecipe.EnumTier.GRANITE,
                    getSedimentaryName(i) + "_stone_slab"
            ));

            if (i == 4){
                continue;
            }
            if (i == 0){
                registry.register(createAnvilRecipe(
                        new ItemStack(ModuleCore.Blocks.ROCK, 8, BlockRock.EnumType.LIMESTONE.getMeta()),
                        Ingredient.fromStacks(getUBC("sedimentary", "stone", i)),
                        8,
                        AnvilRecipe.EnumType.HAMMER,
                        AnvilRecipe.EnumTier.GRANITE,
                        getSedimentaryName(i) + "_stone_to_rocks"
                ));
            } else if (i < 4){
                registry.register(createAnvilRecipe(
                        new ItemStack(BlockInitializer.itemRockSedimentary, 8, i - 1),
                        Ingredient.fromStacks(getUBC("sedimentary", "stone", i)),
                        8,
                        AnvilRecipe.EnumType.HAMMER,
                        AnvilRecipe.EnumTier.GRANITE,
                        getSedimentaryName(i) + "_stone_to_rocks"
                ));
            } else {
                registry.register(createAnvilRecipe(
                        new ItemStack(BlockInitializer.itemRockSedimentary, 8, i - 2),
                        Ingredient.fromStacks(getUBC("sedimentary", "stone", i)),
                        8,
                        AnvilRecipe.EnumType.HAMMER,
                        AnvilRecipe.EnumTier.GRANITE,
                        getSedimentaryName(i) + "_stone_to_rocks"
                ));
            }
        }


    }


    public static void initKilnPit(IForgeRegistryModifiable<KilnPitRecipe> registry){
        for (int i = 0; i < 8; i++){
            // Stone Slab
            registry.register(createKilnPitRecipe(
                    getUBC("igneous", "stone_halfslab", i),
                    Ingredient.fromStacks(getUBC("igneous", "cobble_halfslab", i)),
                    new ItemStack[]{
                            ItemMaterial.EnumType.PIT_ASH.asStack(),
                            new ItemStack(BlockInitializer.blockRockIgneous, 5, i)
                    },
                    "stone_slab" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock", "")
            ));

            registry.register(createKilnPitRecipe(
                    getUBC("igneous", "stone", i),
                    Ingredient.fromStacks(getUBC("igneous", "cobble", i)),
                    new ItemStack[]{
                            ItemMaterial.EnumType.PIT_ASH.asStack(),
                            new ItemStack(BlockInitializer.blockRockIgneous, 5, i)
                    },
                    "stone" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock", "")
            ));

            registry.register(createKilnPitRecipe(
                    getUBC("metamorphic", "stone_halfslab", i),
                    Ingredient.fromStacks(getUBC("metamorphic", "cobble_halfslab", i)),
                    new ItemStack[]{
                            ItemMaterial.EnumType.PIT_ASH.asStack(),
                            new ItemStack(BlockInitializer.blockRockMetamorphic, 5, i)
                    },
                    "stone_slab" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock", "")
            ));

            registry.register(createKilnPitRecipe(
                    getUBC("metamorphic", "stone", i),
                    Ingredient.fromStacks(getUBC("metamorphic", "cobble", i)),
                    new ItemStack[]{
                            ItemMaterial.EnumType.PIT_ASH.asStack(),
                            new ItemStack(BlockInitializer.blockRockMetamorphic, 5, i)
                    },
                    "stone" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock", "")
            ));
        }
    }

    public static void initCompactor(IForgeRegistryModifiable<CompactingBinRecipe> registry){
        for (int i = 0; i < 8; i++){
            registry.register(createCompactingBinRecipe(
                    getUBCGravel("igneous", i),
                    Ingredient.fromStacks(
                            new ItemStack(BlockInitializer.itemRockIgneous, 1, i)
                    ),
                    8,
                    "igneous_gravel_" + BlockRockIgneous.EnumType.fromMeta(i).getName().replace("rock_", "")
            ));
            registry.register(createCompactingBinRecipe(
                    getUBCGravel("metamorphic", i),
                    Ingredient.fromStacks(
                            new ItemStack(BlockInitializer.itemRockMetamorphic, 1, i)
                    ),
                    8,
                    "metamorphic_gravel_" + BlockRockMetamorphic.EnumType.fromMeta(i).getName().replace("rock_", "")
            ));
            if (i == 0){
                registry.register(createCompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(ModuleCore.Blocks.ROCK, 1, BlockRock.EnumType.LIMESTONE.getMeta())
                        ),
                        8,
                        "sedimentary_gravel_limestone"
                ));
            } else if (i < 4){
                registry.register(createCompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(BlockInitializer.itemRockSedimentary, 1, i - 1)
                        ),
                        8,
                        "sedimentary_gravel_" + BlockRockSedimentary.EnumType.fromMeta(i - 1).getName().replace("rock_", "")
                ).setRegistryName(PyrotechCompat.MODID, "sedimentary_gravel_" + BlockRockSedimentary.EnumType.fromMeta(i - 1).getName().replace("rock_", "")));
            } else if (i == 4){
                continue;
            } else {
                registry.register(createCompactingBinRecipe(
                        getUBCGravel("sedimentary", i),
                        Ingredient.fromStacks(
                                new ItemStack(BlockInitializer.itemRockSedimentary, 1, i - 2)
                        ),
                        8,
                        "sedimentary_gravel_" + BlockRockSedimentary.EnumType.fromMeta(i - 2).getName().replace("rock_", "")
                ));
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

    private static AnvilRecipe createAnvilRecipe(ItemStack output, Ingredient input, int hits, AnvilRecipe.EnumType type, AnvilRecipe.EnumTier tier, String id){
        AnvilRecipe recipe = new AnvilRecipe(output, input, hits, type, tier);
        recipe.setRegistryName(PyrotechCompat.MODID, id);
        anvilRecipes.add(recipe);
        return recipe;
    }

    private static KilnPitRecipe createKilnPitRecipe(ItemStack output, Ingredient input, ItemStack[] failureItems, String id){
        KilnPitRecipe recipe = new KilnPitRecipe(output, input, Reference.PitKiln.DEFAULT_BURN_TIME_TICKS, Reference.PitKiln.DEFAULT_FAILURE_CHANCE, failureItems);
        recipe.setRegistryName(PyrotechCompat.MODID, id);
        kilnPitRecipes.add(recipe);
        return recipe;
    }

    private static StoneKilnRecipe createStoneKilnRecipe(ItemStack output, Ingredient input, int burnTime, float failureChance, ItemStack[] falureItems, String id){
        StoneKilnRecipe recipe = new StoneKilnRecipe(output, input, burnTime, failureChance, falureItems);
        recipe.setRegistryName(PyrotechCompat.MODID, id);
        stoneKilnRecipes.add(recipe);
        return recipe;
    }

    private static CompactingBinRecipe createCompactingBinRecipe(ItemStack output, Ingredient input, int amount, String id){
        CompactingBinRecipe recipe = new CompactingBinRecipe(output, input, amount);
        recipe.setRegistryName(PyrotechCompat.MODID, id);
        compactingBinRecipes.add(recipe);
        return recipe;
    }

    private static void registerInheritedRecipes(){
        if (ModuleTechBasicConfig.IRONCLAD_ANVIL.INHERIT_GRANITE_ANVIL_RECIPES){
            for (AnvilRecipe recipe : anvilRecipes){
                ModuleTechBasic.Registries.ANVIL_RECIPE.register(new AnvilRecipe(recipe.getOutput(), recipe.getInput(), Math.max(1, (int)((double)recipe.getHits() * Math.max(0.0D, ModuleTechBasicConfig.IRONCLAD_ANVIL.INHERITED_GRANITE_ANVIL_RECIPE_HIT_MODIFIER))), recipe.getType(), AnvilRecipe.EnumTier.IRONCLAD).setRegistryName(PyrotechCompat.MODID, "granite_anvil/" + recipe.getRegistryName().getResourcePath()));
            }
        }
        if (ModPyrotech.INSTANCE.isModuleEnabled(ModuleTechBasic.class) && ModuleTechMachineConfig.MECHANICAL_COMPACTING_BIN.INHERIT_COMPACTING_BIN_RECIPES){
            for (CompactingBinRecipe recipe : compactingBinRecipes){
                ModuleTechMachine.Registries.MECHANICAL_COMPACTING_BIN_RECIPES.register(new MechanicalCompactingBinRecipe(recipe.getOutput(), recipe.getInput(), recipe.getAmount(), recipe.getRequiredToolUses()).setRegistryName(PyrotechCompat.MODID, "compacting_bin/" + recipe.getRegistryName().getResourcePath()));
            }
        }
        if (ModPyrotech.INSTANCE.isModuleEnabled(ModuleTechBasic.class) && ModuleTechMachineConfig.STONE_KILN.INHERIT_PIT_KILN_RECIPES){
            for (KilnPitRecipe recipe : kilnPitRecipes){
                int timeTicks = (int)((double)recipe.getTimeTicks() * ModuleTechMachineConfig.STONE_KILN.INHERITED_PIT_KILN_RECIPE_DURATION_MODIFIER);
                float failureChance = (float)((double)recipe.getFailureChance() * ModuleTechMachineConfig.STONE_KILN.INHERITED_PIT_KILN_RECIPE_FAILURE_CHANCE_MODIFIER);
                ModuleTechMachine.Registries.STONE_KILN_RECIPES.register(createStoneKilnRecipe(recipe.getOutput(), recipe.getInput(), timeTicks, failureChance, recipe.getFailureItems(), "pit_kiln/" + recipe.getRegistryName().getResourcePath()));
            }
        }
        if (ModuleTechMachineConfig.BRICK_KILN.INHERIT_STONE_TIER_RECIPES){
            for (StoneKilnRecipe recipe : stoneKilnRecipes){
                int timeTicks = (int)((double)recipe.getTimeTicks() * ModuleTechMachineConfig.BRICK_KILN.INHERITED_STONE_TIER_RECIPE_DURATION_MODIFIER);
                float failureChance = (float)((double)recipe.getFailureChance() * ModuleTechMachineConfig.BRICK_KILN.INHERITED_STONE_TIER_RECIPE_FAILURE_CHANCE_MODIFIER);
                ModuleTechMachine.Registries.BRICK_KILN_RECIPES.register(new BrickKilnRecipe(recipe.getOutput(), recipe.getInput(), timeTicks, failureChance, recipe.getFailureItems()).setRegistryName(PyrotechCompat.MODID,"stone_kiln/" + recipe.getRegistryName().getResourcePath()));
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
