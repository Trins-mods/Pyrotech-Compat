package trinsdar.pyrotech_compat;

import com.codetaylor.mc.dropt.api.api.IDroptDropBuilder;
import com.codetaylor.mc.dropt.api.api.IDroptRuleBuilder;
import com.codetaylor.mc.dropt.api.event.DroptLoadRulesEvent;
import com.codetaylor.mc.dropt.api.reference.EnumDropStrategy;
import com.codetaylor.mc.dropt.api.reference.EnumHarvesterType;
import com.codetaylor.mc.dropt.api.reference.EnumListType;
import com.codetaylor.mc.dropt.api.reference.EnumReplaceStrategy;
import com.codetaylor.mc.pyrotech.ModPyrotechConfig;
import com.codetaylor.mc.pyrotech.modules.core.block.BlockRock;
import com.codetaylor.mc.pyrotech.modules.core.item.ItemMaterial;
import com.codetaylor.mc.pyrotech.modules.plugin.dropt.ModulePluginDropt;
import com.codetaylor.mc.pyrotech.modules.plugin.dropt.ModulePluginDroptConfig;
import com.codetaylor.mc.pyrotech.modules.tool.item.ItemCrudePickaxe;
import net.minecraft.block.BlockSand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.codetaylor.mc.dropt.api.DroptAPI.drop;
import static com.codetaylor.mc.dropt.api.DroptAPI.harvester;
import static com.codetaylor.mc.dropt.api.DroptAPI.itemString;
import static com.codetaylor.mc.dropt.api.DroptAPI.range;
import static com.codetaylor.mc.dropt.api.DroptAPI.registerRuleList;
import static com.codetaylor.mc.dropt.api.DroptAPI.rule;
import static com.codetaylor.mc.dropt.api.DroptAPI.weight;

public class PluginDropt {

    private static String item(String modId, String name) {

        return item(modId, name, 0);
    }

    private static String item(String name, int meta) {

        return item(ModulePluginDropt.MOD_ID, name, meta);
    }

    private static String item(String modId, String name, int meta) {

        return itemString(modId, name, meta);
    }

    private static boolean enabled(String key) {

        if (ModulePluginDroptConfig.ENABLED_RULES.containsKey(key)){
            return ModulePluginDroptConfig.ENABLED_RULES.get(key);
        }
        return PyrotechPluginDroptConfig.ENABLED_RULES.containsKey(key) && PyrotechPluginDroptConfig.ENABLED_RULES.get(key);
    }

    private static String ubc(String prefix, String suffix, int meta){
        return item("undergroundbiomes", prefix + "_" + suffix, meta);
    }

    private static String rock(String type, int meta){
        return item(PyrotechCompat.MODID, "rock_" + type, meta);
    }

    @SubscribeEvent
    public void on(DroptLoadRulesEvent event) {

        if (!ModPyrotechConfig.MODULES.get("plugin.dropt")) {
            return;
        }
        List<IDroptRuleBuilder> list = new ArrayList<>();



        if (Loader.isModLoaded("undergroundbiomes")){

            // -------------------------------------------------------------------------
            // - Item / Block Strings
            // -------------------------------------------------------------------------

            String gravelAndesite = item("undergroundbiomes", "igneous_gravel", 3);
            String gravelBasalt = item("undergroundbiomes", "igneous_gravel", 5);
            String gravelBlackGranite = item("undergroundbiomes", "igneous_gravel", 1);
            String gravelBlueSchist = item("undergroundbiomes", "metamorphic_gravel", 4);
            String gravelChalk = item("undergroundbiomes", "sedimentary_gravel", 1);
            String gravelChert = item("undergroundbiomes", "sedimentary_gravel", 7);
            String gravelDacite = item("undergroundbiomes", "igneous_gravel", 7);
            String gravelDolomite = item("undergroundbiomes", "sedimentary_gravel", 5);
            String gravelEclogite = item("undergroundbiomes", "metamorphic_gravel", 1);
            String gravelGabbro = item("undergroundbiomes", "igneous_gravel", 4);
            String gravelGneiss = item("undergroundbiomes", "metamorphic_gravel");
            String gravelGreenSchist = item("undergroundbiomes", "metamorphic_gravel", 5);
            String gravelGreywacke = item("undergroundbiomes", "sedimentary_gravel", 6);
            String gravelKomatiite = item("undergroundbiomes", "igneous_gravel", 6);
            String gravelLignite = item("undergroundbiomes", "sedimentary_gravel", 4);
            String gravelLimestone = item("undergroundbiomes", "sedimentary_gravel");
            String gravelMarble = item("undergroundbiomes", "metamorphic_gravel", 2);
            String gravelMigmatite = item("undergroundbiomes", "metamorphic_gravel", 7);
            String gravelQuartzite = item("undergroundbiomes", "metamorphic_gravel", 3);
            String gravelRedGranite = item("undergroundbiomes", "igneous_gravel");
            String gravelRhyolite = item("undergroundbiomes", "igneous_gravel", 2);
            String gravelShale = item("undergroundbiomes", "sedimentary_gravel", 2);
            String gravelSiltstone = item("undergroundbiomes", "sedimentary_gravel", 3);
            String gravelSoapstone = item("undergroundbiomes", "metamorphic_gravel", 6);

            String clayAndesite = item("undergroundbiomes", "igneous_clay", 3);
            String clayBasalt = item("undergroundbiomes", "igneous_clay", 5);
            String clayBlackGranite = item("undergroundbiomes", "igneous_clay", 1);
            String clayBlueSchist = item("undergroundbiomes", "metamorphic_clay", 4);
            String clayChalk = item("undergroundbiomes", "sedimentary_clay", 1);
            String clayChert = item("undergroundbiomes", "sedimentary_clay", 7);
            String clayDacite = item("undergroundbiomes", "igneous_clay", 7);
            String clayDolomite = item("undergroundbiomes", "sedimentary_clay", 5);
            String clayEclogite = item("undergroundbiomes", "metamorphic_clay", 1);
            String clayGabbro = item("undergroundbiomes", "igneous_clay", 4);
            String clayGneiss = item("undergroundbiomes", "metamorphic_clay");
            String clayGreenSchist = item("undergroundbiomes", "metamorphic_clay", 5);
            String clayGreywacke = item("undergroundbiomes", "sedimentary_clay", 6);
            String clayKomatiite = item("undergroundbiomes", "igneous_clay", 6);
            String clayLignite = item("undergroundbiomes", "sedimentary_clay", 4);
            String clayLimestone = item("undergroundbiomes", "sedimentary_clay");
            String clayMarble = item("undergroundbiomes", "metamorphic_clay", 2);
            String clayMigmatite = item("undergroundbiomes", "metamorphic_clay", 7);
            String clayQuartzite = item("undergroundbiomes", "metamorphic_clay", 3);
            String clayRedGranite = item("undergroundbiomes", "igneous_clay");
            String clayRhyolite = item("undergroundbiomes", "igneous_clay", 2);
            String clayShale = item("undergroundbiomes", "sedimentary_clay", 2);
            String claySiltstone = item("undergroundbiomes", "sedimentary_clay", 3);
            String claySoapstone = item("undergroundbiomes", "metamorphic_clay", 6);

            String flint = item("minecraft", "flint");
            String rockStone = item(BlockRock.NAME, BlockRock.EnumType.STONE.getMeta());
            String rockGranite = item(BlockRock.NAME, BlockRock.EnumType.GRANITE.getMeta());
            String rockDiorite = item(BlockRock.NAME, BlockRock.EnumType.DIORITE.getMeta());
            String rockAndesite = item(BlockRock.NAME, BlockRock.EnumType.ANDESITE.getMeta());
            String rockLimestone = item(BlockRock.NAME, BlockRock.EnumType.LIMESTONE.getMeta());
            String boneMeal = item("minecraft", "dye", 15);
            String coal = item("minecraft", "coal", 0);
            String clayBall = item("minecraft", "clay_ball", 0);

            String flintShard = item(ItemMaterial.NAME, ItemMaterial.EnumType.FLINT_SHARD.getMeta());
            String clayLump = item(ItemMaterial.NAME, ItemMaterial.EnumType.CLAY_LUMP.getMeta());

            int[] metas = {0, 1, 2, 3, 4, 5, 6, 7};
            String[] listClay = {clayAndesite, clayBasalt, clayBlackGranite, clayBlueSchist, clayChalk, clayChert, clayDacite, clayDolomite, clayEclogite, clayGabbro, clayGneiss, clayGreenSchist, clayGreywacke, clayKomatiite, clayLignite, clayLimestone, clayMarble, clayMigmatite, clayQuartzite, clayRedGranite, clayRhyolite, clayShale, claySiltstone, claySoapstone};

            String[] listIgneous = {gravelAndesite, gravelBasalt, gravelBlackGranite, gravelDacite, gravelGabbro, gravelKomatiite, gravelRedGranite, gravelRhyolite};
            String[] listMetamorphic = {gravelBlueSchist, gravelEclogite, gravelGneiss, gravelGreenSchist, gravelMarble, gravelMigmatite, gravelQuartzite, gravelSoapstone};
            String[] listSedimentary = {gravelChalk, gravelChert, gravelDolomite, gravelGreywacke, gravelLignite, gravelLimestone, gravelShale, gravelSiltstone};




            // -------------------------------------------------------------------------
            // - Sand
            // -------------------------------------------------------------------------

            if (enabled("sand")) {
                if (enabled("igneous_sand")){
                    for (int meta : metas){
                        // Not a shovel
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("igneous", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .mainHand(EnumListType.BLACKLIST, "shovel;0;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("igneous_sand", meta)}, range(1, 3))
                                })
                        );

                        // Shovel 0
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("igneous", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;1;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("igneous_sand", meta)}, range(2, 4))
                                })
                        );

                        // Shovel 1
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("igneous", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;2;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("igneous_sand", meta)}, range(3, 6)).selector(weight(4)),
                                        drop().items(new String[]{ubc("igneous", "sand", meta)})
                                })
                        );
                    }
                }

                if (enabled("metamorphic_sand")){
                    for (int meta : metas){
                        // Not a shovel
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("metamorphic", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .mainHand(EnumListType.BLACKLIST, "shovel;0;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("metamorphic_sand", meta)}, range(1, 3))
                                })
                        );

                        // Shovel 0
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("metamorphic", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;1;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("metamorphic_sand", meta)}, range(2, 4))
                                })
                        );

                        // Shovel 1
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("metamorphic", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;2;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("metamorphic_sand", meta)}, range(3, 6)).selector(weight(4)),
                                        drop().items(new String[]{ubc("metamorphic", "sand", meta)})
                                })
                        );
                    }
                }

                if (enabled("sedimentary_sand")){
                    for (int meta : metas){
                        // Not a shovel
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("sedimentary", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .mainHand(EnumListType.BLACKLIST, "shovel;0;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("sedimentary_sand", meta)}, range(1, 3))
                                })
                        );

                        // Shovel 0
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("sedimentary", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;1;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("sedimentary_sand", meta)}, range(2, 4))
                                })
                        );

                        // Shovel 1
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("sedimentary", "sand", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;2;-1")
                                )
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rock("sedimentary_sand", meta)}, range(3, 6)).selector(weight(4)),
                                        drop().items(new String[]{ubc("sedimentary", "sand", meta)})
                                })
                        );
                    }
                }
            }

            // -------------------------------------------------------------------------
            // - Gravel
            // -------------------------------------------------------------------------

            if (enabled("gravel")) {

                if (enabled("igneous_gravel")){
                    // Not a shovel
                    list.add(rule()
                            .matchBlocks(listIgneous)
                            .matchHarvester(harvester()
                                    .mainHand(EnumListType.BLACKLIST, "shovel;0;-1")
                            )
                            .addDrops(new IDroptDropBuilder[]{
                                    drop().items(new String[]{rockStone}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rockGranite}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("igneous", 0)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("igneous", 1)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("igneous", 2)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("igneous", 3)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("igneous", 4)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("igneous", 5)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("igneous", 6)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("igneous", 7)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rockAndesite}, range(1, 3)).selector(weight(10))
                            })
                    );

                    // Shovel 0
                    list.add(rule()
                            .matchBlocks(listIgneous)
                            .matchHarvester(harvester()
                                    .type(EnumHarvesterType.PLAYER)
                                    .mainHand(EnumListType.BLACKLIST, "shovel;1;-1")
                            )
                            .dropCount(range(2))
                            .dropStrategy(EnumDropStrategy.UNIQUE)
                            .addDrops(new IDroptDropBuilder[]{
                                    drop().items(new String[]{rockStone}, range(1, 2)).selector(weight(2)),
                                    drop().items(new String[]{rockGranite}, range(1, 2)).selector(weight(2)),
                                    drop().items(new String[]{rockAndesite}, range(1, 2)).selector(weight(2)),
                                    drop().items(new String[]{rock("igneous", 0)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("igneous", 1)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("igneous", 2)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("igneous", 3)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("igneous", 4)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("igneous", 5)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("igneous", 6)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("igneous", 7)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{flintShard}).selector(weight(1))
                            })
                    );

                    // Shovel 1
                    for (int meta : metas){
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("igneous", "gravel", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;2;-1")
                                )
                                .dropCount(range(2))
                                .dropStrategy(EnumDropStrategy.UNIQUE)
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rockStone}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rockGranite}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rockAndesite}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("igneous", 0)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("igneous", 1)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("igneous", 2)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("igneous", 3)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("igneous", 4)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("igneous", 5)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("igneous", 6)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("igneous", 7)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{ubc("igneous", "gravel", meta)}).selector(weight(2)),
                                        drop().items(new String[]{flintShard}).selector(weight(2)),
                                        drop().items(new String[]{flint}).selector(weight(1))
                                })
                        );
                    }
                }

                if (enabled("metamorphic_gravel")){
                    // Not a shovel
                    list.add(rule()
                            .matchBlocks(listMetamorphic)
                            .matchHarvester(harvester()
                                    .mainHand(EnumListType.BLACKLIST, "shovel;0;-1")
                            )
                            .addDrops(new IDroptDropBuilder[]{
                                    drop().items(new String[]{rockStone}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rockDiorite}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("metamorphic", 0)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("metamorphic", 1)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("metamorphic", 2)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("metamorphic", 3)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("metamorphic", 4)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("metamorphic", 5)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("metamorphic", 6)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("metamorphic", 7)}, range(1, 3)).selector(weight(10))
                            })
                    );

                    // Shovel 0
                    list.add(rule()
                            .matchBlocks(listMetamorphic)
                            .matchHarvester(harvester()
                                    .type(EnumHarvesterType.PLAYER)
                                    .mainHand(EnumListType.BLACKLIST, "shovel;1;-1")
                            )
                            .dropCount(range(2))
                            .dropStrategy(EnumDropStrategy.UNIQUE)
                            .addDrops(new IDroptDropBuilder[]{
                                    drop().items(new String[]{rockStone}, range(1, 2)).selector(weight(2)),
                                    drop().items(new String[]{rockDiorite}, range(1, 2)).selector(weight(2)),
                                    drop().items(new String[]{rock("metamorphic", 0)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("metamorphic", 1)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("metamorphic", 2)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("metamorphic", 3)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("metamorphic", 4)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("metamorphic", 5)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("metamorphic", 6)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("metamorphic", 7)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{flintShard}).selector(weight(1))
                            })
                    );

                    // Shovel 1
                    for (int meta : metas){
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("metamorphic", "gravel", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;2;-1")
                                )
                                .dropCount(range(2))
                                .dropStrategy(EnumDropStrategy.UNIQUE)
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rockStone}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rockDiorite}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("metamorphic", 0)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("metamorphic", 1)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("metamorphic", 2)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("metamorphic", 3)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("metamorphic", 4)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("metamorphic", 5)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("metamorphic", 6)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("metamorphic", 7)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{ubc("metamorphic", "gravel", meta)}).selector(weight(2)),
                                        drop().items(new String[]{flintShard}).selector(weight(2)),
                                        drop().items(new String[]{flint}).selector(weight(1))
                                })
                        );
                    }
                }

                if (enabled("sedimentary_gravel")){
                    // Not a shovel
                    list.add(rule()
                            .matchBlocks(listSedimentary)
                            .matchHarvester(harvester()
                                    .mainHand(EnumListType.BLACKLIST, "shovel;0;-1")
                            )
                            .addDrops(new IDroptDropBuilder[]{
                                    drop().items(new String[]{rockStone}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("sedimentary", 0)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("sedimentary", 1)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("sedimentary", 2)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("sedimentary", 3)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("sedimentary", 4)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rock("sedimentary", 5)}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rockLimestone}, range(1, 3)).selector(weight(10)),
                                    drop().items(new String[]{rockAndesite}, range(1, 3)).selector(weight(10))
                            })
                    );

                    // Shovel 0
                    list.add(rule()
                            .matchBlocks(listSedimentary)
                            .matchHarvester(harvester()
                                    .type(EnumHarvesterType.PLAYER)
                                    .mainHand(EnumListType.BLACKLIST, "shovel;1;-1")
                            )
                            .dropCount(range(2))
                            .dropStrategy(EnumDropStrategy.UNIQUE)
                            .addDrops(new IDroptDropBuilder[]{
                                    drop().items(new String[]{rockStone}, range(1, 2)).selector(weight(2)),
                                    drop().items(new String[]{rock("sedimentary", 0)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("sedimentary", 1)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("sedimentary", 2)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("sedimentary", 3)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("sedimentary", 4)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rock("sedimentary", 5)}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{rockLimestone}, range(1, 3)).selector(weight(2)),
                                    drop().items(new String[]{flintShard}).selector(weight(1))
                            })
                    );

                    // Shovel 1
                    for (int meta : metas){
                        list.add(rule()
                                .matchBlocks(new String[]{
                                        ubc("sedimentary", "gravel", meta)
                                })
                                .matchHarvester(harvester()
                                        .type(EnumHarvesterType.PLAYER)
                                        .mainHand(EnumListType.BLACKLIST, "shovel;2;-1")
                                )
                                .dropCount(range(2))
                                .dropStrategy(EnumDropStrategy.UNIQUE)
                                .addDrops(new IDroptDropBuilder[]{
                                        drop().items(new String[]{rockStone}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("sedimentary", 0)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("sedimentary", 1)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("sedimentary", 2)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("sedimentary", 3)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("sedimentary", 4)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rock("sedimentary", 5)}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{rockLimestone}, range(1, 3)).selector(weight(1)),
                                        drop().items(new String[]{ubc("sedimentary", "gravel", meta)}).selector(weight(2)),
                                        drop().items(new String[]{flintShard}).selector(weight(2)),
                                        drop().items(new String[]{flint}).selector(weight(1))
                                })
                        );
                    }
                }
            }

            // -------------------------------------------------------------------------
            // - Clay
            // -------------------------------------------------------------------------



            if (enabled("clay") && enabled("ubc_clay")) {

                // Not a shovel
                list.add(rule()
                        .debug()
                        .matchBlocks(listClay)
                        .matchHarvester(harvester()
                                .mainHand(EnumListType.BLACKLIST, "shovel;0;-1")
                        )
                        .addDrops(new IDroptDropBuilder[]{
                                drop().items(new String[]{clayLump}, range(1, 3))
                        })
                );

                // Shovel 0
                list.add(rule()
                        .debug()
                        .matchBlocks(listClay)
                        .matchHarvester(harvester()
                                .type(EnumHarvesterType.PLAYER)
                                .mainHand(EnumListType.BLACKLIST, "shovel;1;-1")
                        )
                        .dropCount(range(2))
                        .addDrops(new IDroptDropBuilder[]{
                                drop().items(new String[]{clayLump}, range(1, 2)).selector(weight(90)),
                                drop().items(new String[]{clayBall}, range(1, 2)).selector(weight(10))
                        })
                );

                // Shovel 1
                list.add(rule()
                        .debug()
                        .matchBlocks(listClay)
                        .matchHarvester(harvester()
                                .type(EnumHarvesterType.PLAYER)
                                .mainHand(EnumListType.BLACKLIST, "shovel;2;-1")
                        )
                        .dropStrategy(EnumDropStrategy.UNIQUE)
                        .dropCount(range(2))
                        .addDrops(new IDroptDropBuilder[]{
                                drop().items(new String[]{clayLump}, range(1, 4)).selector(weight(40)),
                                drop().items(new String[]{clayBall}, range(1, 2)).selector(weight(30)),
                                drop().items(new String[]{clayBall}, range(1, 2)).selector(weight(30))
                        })
                );
            }

            // -------------------------------------------------------------------------
            // - Sandstone
            // -------------------------------------------------------------------------

            if (enabled("sandstone")) {
                for (int meta : metas){
                    String matchBlock = ubc("igneous", "sandstone", meta);
                    String rock = rock("igneous_sand", meta + 8);
                    if (enabled("igneous_sandstone")){
                        this.addRockDrops(matchBlock, rock, list);
                    }
                    matchBlock = ubc("metamorphic", "sandstone", meta);
                    rock = rock("metamorphic_sand", meta + 8);
                    if (enabled("metamorphic_sandstone")){
                        this.addRockDrops(matchBlock, rock, list);
                    }
                    matchBlock = ubc("sedimentary", "sandstone", meta);
                    rock = rock("sedimentary_sand", meta + 8);
                    if (enabled("sedimentary_sandstone")){
                        this.addRockDrops(matchBlock, rock, list);
                    }
                }
            }

            // -------------------------------------------------------------------------
            // - Stone / Cobblestone
            // -------------------------------------------------------------------------

            if (enabled("stone")) {
                for (int meta : metas){
                    String matchBlock = ubc("igneous", "stone", meta);
                    String rock = rock("igneous", meta);
                    if (enabled("igneous_stone")){
                        this.addRockDrops(matchBlock, rock, list);
                    }
                    matchBlock = ubc("metamorphic", "stone", meta);
                    rock = rock("metamorphic", meta);
                    if (enabled("metamorphic_stone")){
                        this.addRockDrops(matchBlock, rock, list);
                    }
                    matchBlock = ubc("sedimentary", "stone", meta);
                    if (enabled("sedimentary_stone")){
                        if (meta == 4){
                            continue;
                        }
                        if (meta == 0){
                            rock = item(BlockRock.NAME, BlockRock.EnumType.LIMESTONE.getMeta());
                            this.addRockDrops(matchBlock, rock, list);
                        } else if (meta < 4){
                            rock = rock("sedimentary", meta - 1);
                            this.addRockDrops(matchBlock, rock, list);
                        } else{
                            rock = rock("sedimentary", meta - 2);
                            this.addRockDrops(matchBlock, rock, list);
                        }
                    }






                }
            }

            if (enabled("cobblestone")) {
                for (int meta : metas){
                    String matchBlock = ubc("igneous", "cobble", meta);
                    String rock = rock("igneous", meta);
                    if (enabled("igneous_cobblestone")){
                        this.addRockDrops(matchBlock, rock, list);
                    }
                    matchBlock = ubc("metamorphic", "cobble", meta);
                    rock = rock("metamorphic", meta);
                    if (enabled("metamorphic_cobblestone")){
                        this.addRockDrops(matchBlock, rock, list);
                    }
                }
            }
        }

        if (enabled("netherrack")){
            this.addRockDrops(item("minecraft", "netherrack"), item(PyrotechCompat.MODID, "rock_netherrack"), list);
        }

        ResourceLocation resourceLocation = new ResourceLocation(PyrotechCompat.MODID, "rock");
        registerRuleList(resourceLocation, 0, list);
    }

    private void addBlockReplace(String matchBlock, String replaceBlock, List<IDroptRuleBuilder> list) {

        list.add(rule()
                .matchBlocks(new String[]{
                        matchBlock
                })
                .addDrops(new IDroptDropBuilder[]{
                        drop().items(new String[]{replaceBlock})
                })
        );
    }

    private void addRockDrops(String matchBlock, String rock, List<IDroptRuleBuilder> list) {

        this.addRockDrops(matchBlock, rock, null, list);
    }

    private void addRockDrops(String matchBlock, String rock, @Nullable String replaceBlock, List<IDroptRuleBuilder> list) {

        // Non-Player
        list.add(rule()
                .matchBlocks(new String[]{
                        matchBlock
                })
                .matchHarvester(harvester()
                        .type(EnumHarvesterType.NON_PLAYER)
                )
                .addDrops(new IDroptDropBuilder[]{
                        drop().items(new String[]{rock}, range(2, 4))
                })
        );

        // Crude Pickaxe
        // Drops rocks
        list.add(rule()
                .matchBlocks(new String[]{
                        matchBlock
                })
                .matchHarvester(harvester()
                        .type(EnumHarvesterType.PLAYER)
                        .mainHand(new String[]{
                                item(ItemCrudePickaxe.NAME, OreDictionary.WILDCARD_VALUE)
                        })
                )
                .addDrops(new IDroptDropBuilder[]{
                        drop().items(new String[]{rock}, range(2, 4))
                })
        );

        // Flint / Bone / Stone Pickaxe
        // Drops rocks
        list.add(rule()
                .matchBlocks(new String[]{
                        matchBlock
                })
                .matchHarvester(harvester()
                        .type(EnumHarvesterType.PLAYER)
                        .mainHand(EnumListType.BLACKLIST, "pickaxe;2;-1")
                )
                .replaceStrategy(EnumReplaceStrategy.REPLACE_ALL_IF_SELECTED)
                .addDrops(new IDroptDropBuilder[]{
                        drop().items(new String[]{rock}, range(3, 6)).selector(weight(4)),
                        (replaceBlock == null) ? drop() : drop().items(new String[]{replaceBlock})
                })
        );
    }
}
