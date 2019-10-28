package trinsdar.pyrotech_compat;

import com.codetaylor.mc.dropt.api.api.IDroptDropBuilder;
import com.codetaylor.mc.dropt.api.api.IDroptRuleBuilder;
import com.codetaylor.mc.dropt.api.event.DroptLoadRulesEvent;
import com.codetaylor.mc.dropt.api.reference.EnumDropStrategy;
import com.codetaylor.mc.dropt.api.reference.EnumHarvesterType;
import com.codetaylor.mc.dropt.api.reference.EnumListType;
import com.codetaylor.mc.dropt.api.reference.EnumReplaceStrategy;
import com.codetaylor.mc.pyrotech.ModPyrotechConfig;
import com.codetaylor.mc.pyrotech.modules.core.item.ItemMaterial;
import com.codetaylor.mc.pyrotech.modules.plugin.dropt.ModulePluginDropt;
import com.codetaylor.mc.pyrotech.modules.plugin.dropt.ModulePluginDroptConfig;
import com.codetaylor.mc.pyrotech.modules.tool.item.ItemCrudePickaxe;
import net.minecraft.block.BlockSand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
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

        return ModulePluginDroptConfig.ENABLED_RULES.containsKey(key)
                && ModulePluginDroptConfig.ENABLED_RULES.get(key);
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

        // -------------------------------------------------------------------------
        // - Item / Block Strings
        // -------------------------------------------------------------------------

        String sandAndesite = item("undergroundbiomes", "igneous_sand", 3);
        String sandBasalt = item("undergroundbiomes", "igneous_sand", 5);
        String sandBlackGranite = item("undergroundbiomes", "igneous_sand", 1);
        String sandBlueSchist = item("undergroundbiomes", "metamorphic_sand", 4);
        String sandChalk = item("undergroundbiomes", "sedimentary_sand", 1);
        String sandChert = item("undergroundbiomes", "sedimentary_sand", 7);
        String sandDacite = item("undergroundbiomes", "igneous_sand", 7);
        String sandDolomite = item("undergroundbiomes", "sedimentary_sand", 5);
        String sandEclogite = item("undergroundbiomes", "metamorphic_sand", 1);
        String sandGabbro = item("undergroundbiomes", "igneous_sand", 4);
        String sandGneiss = item("undergroundbiomes", "metamorphic_sand");
        String sandGreenSchist = item("undergroundbiomes", "metamorphic_sand", 5);
        String sandGreywacke = item("undergroundbiomes", "sedimentary_sand", 6);
        String sandKomatiite = item("undergroundbiomes", "igneous_sand", 6);
        String sandLignite = item("undergroundbiomes", "sedimentary_sand", 4);
        String sandLimestone = item("undergroundbiomes", "sedimentary_sand");
        String sandMarble = item("undergroundbiomes", "metamorphic_sand", 2);
        String sandMigmatite = item("undergroundbiomes", "metamorphic_sand", 7);
        String sandQuartzite = item("undergroundbiomes", "metamorphic_sand", 3);
        String sandRedGranite = item("undergroundbiomes", "igneous_sand");
        String sandRhyolite = item("undergroundbiomes", "igneous_sand", 2);
        String sandShale = item("undergroundbiomes", "sedimentary_sand", 2);
        String sandSiltstone = item("undergroundbiomes", "sedimentary_sand", 3);
        String sandSoapstone = item("undergroundbiomes", "metamorphic_sand", 6);

        String sandstoneAndesite = item("undergroundbiomes", "igneous_sandstone", 3);
        String sandstoneBasalt = item("undergroundbiomes", "igneous_sandstone", 5);
        String sandstoneBlackGranite = item("undergroundbiomes", "igneous_sandstone", 1);
        String sandstoneBlueSchist = item("undergroundbiomes", "metamorphic_sandstone", 4);
        String sandstoneChalk = item("undergroundbiomes", "sedimentary_sandstone", 1);
        String sandstoneChert = item("undergroundbiomes", "sedimentary_sandstone", 7);
        String sandstoneDacite = item("undergroundbiomes", "igneous_sandstone", 7);
        String sandstoneDolomite = item("undergroundbiomes", "sedimentary_sandstone", 5);
        String sandstoneEclogite = item("undergroundbiomes", "metamorphic_sandstone", 1);
        String sandstoneGabbro = item("undergroundbiomes", "igneous_sandstone", 4);
        String sandstoneGneiss = item("undergroundbiomes", "metamorphic_sandstone");
        String sandstoneGreenSchist = item("undergroundbiomes", "metamorphic_sandstone", 5);
        String sandstoneGreywacke = item("undergroundbiomes", "sedimentary_sandstone", 6);
        String sandstoneKomatiite = item("undergroundbiomes", "igneous_sandstone", 6);
        String sandstoneLignite = item("undergroundbiomes", "sedimentary_sandstone", 4);
        String sandstoneLimestone = item("undergroundbiomes", "sedimentary_sandstone");
        String sandstoneMarble = item("undergroundbiomes", "metamorphic_sandstone", 2);
        String sandstoneMigmatite = item("undergroundbiomes", "metamorphic_sandstone", 7);
        String sandstoneQuartzite = item("undergroundbiomes", "metamorphic_sandstone", 3);
        String sandstoneRedGranite = item("undergroundbiomes", "igneous_sandstone");
        String sandstoneRhyolite = item("undergroundbiomes", "igneous_sandstone", 2);
        String sandstoneShale = item("undergroundbiomes", "sedimentary_sandstone", 2);
        String sandstoneSiltstone = item("undergroundbiomes", "sedimentary_sandstone", 3);
        String sandstoneSoapstone = item("undergroundbiomes", "metamorphic_sandstone", 6);

        String stoneAndesite = item("undergroundbiomes", "igneous_stone", 3);
        String stoneBasalt = item("undergroundbiomes", "igneous_stone", 5);
        String stoneBlackGranite = item("undergroundbiomes", "igneous_stone", 1);
        String stoneBlueSchist = item("undergroundbiomes", "metamorphic_stone", 4);
        String stoneChalk = item("undergroundbiomes", "sedimentary_stone", 1);
        String stoneChert = item("undergroundbiomes", "sedimentary_stone", 7);
        String stoneDacite = item("undergroundbiomes", "igneous_stone", 7);
        String stoneDolomite = item("undergroundbiomes", "sedimentary_stone", 5);
        String stoneEclogite = item("undergroundbiomes", "metamorphic_stone", 1);
        String stoneGabbro = item("undergroundbiomes", "igneous_stone", 4);
        String stoneGneiss = item("undergroundbiomes", "metamorphic_stone");
        String stoneGreenSchist = item("undergroundbiomes", "metamorphic_stone", 5);
        String stoneGreywacke = item("undergroundbiomes", "sedimentary_stone", 6);
        String stoneKomatiite = item("undergroundbiomes", "igneous_stone", 6);
        String stoneLignite = item("undergroundbiomes", "sedimentary_stone", 4);
        String stoneLimestone = item("undergroundbiomes", "sedimentary_stone");
        String stoneMarble = item("undergroundbiomes", "metamorphic_stone", 2);
        String stoneMigmatite = item("undergroundbiomes", "metamorphic_stone", 7);
        String stoneQuartzite = item("undergroundbiomes", "metamorphic_stone", 3);
        String stoneRedGranite = item("undergroundbiomes", "igneous_stone");
        String stoneRhyolite = item("undergroundbiomes", "igneous_stone", 2);
        String stoneShale = item("undergroundbiomes", "sedimentary_stone", 2);
        String stoneSiltstone = item("undergroundbiomes", "sedimentary_stone", 3);
        String stoneSoapstone = item("undergroundbiomes", "metamorphic_stone", 6);

        String cobblestoneAndesite = item("undergroundbiomes", "igneous_cobblestone", 3);
        String cobblestoneBasalt = item("undergroundbiomes", "igneous_cobblestone", 5);
        String cobblestoneBlackGranite = item("undergroundbiomes", "igneous_cobblestone", 1);
        String cobblestoneBlueSchist = item("undergroundbiomes", "metamorphic_cobblestone", 4);
        String cobblestoneChalk = item("undergroundbiomes", "sedimentary_cobblestone", 1);
        String cobblestoneChert = item("undergroundbiomes", "sedimentary_cobblestone", 7);
        String cobblestoneDacite = item("undergroundbiomes", "igneous_cobblestone", 7);
        String cobblestoneDolomite = item("undergroundbiomes", "sedimentary_cobblestone", 5);
        String cobblestoneEclogite = item("undergroundbiomes", "metamorphic_cobblestone", 1);
        String cobblestoneGabbro = item("undergroundbiomes", "igneous_cobblestone", 4);
        String cobblestoneGneiss = item("undergroundbiomes", "metamorphic_cobblestone");
        String cobblestoneGreenSchist = item("undergroundbiomes", "metamorphic_cobblestone", 5);
        String cobblestoneGreywacke = item("undergroundbiomes", "sedimentary_cobblestone", 6);
        String cobblestoneKomatiite = item("undergroundbiomes", "igneous_cobblestone", 6);
        String cobblestoneLignite = item("undergroundbiomes", "sedimentary_cobblestone", 4);
        String cobblestoneLimestone = item("undergroundbiomes", "sedimentary_cobblestone");
        String cobblestoneMarble = item("undergroundbiomes", "metamorphic_cobblestone", 2);
        String cobblestoneMigmatite = item("undergroundbiomes", "metamorphic_cobblestone", 7);
        String cobblestoneQuartzite = item("undergroundbiomes", "metamorphic_cobblestone", 3);
        String cobblestoneRedGranite = item("undergroundbiomes", "igneous_cobblestone");
        String cobblestoneRhyolite = item("undergroundbiomes", "igneous_cobblestone", 2);
        String cobblestoneShale = item("undergroundbiomes", "sedimentary_cobblestone", 2);
        String cobblestoneSiltstone = item("undergroundbiomes", "sedimentary_cobblestone", 3);
        String cobblestoneSoapstone = item("undergroundbiomes", "metamorphic_cobblestone", 6);

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
        String boneMeal = item("minecraft", "dye", 15);
        String coal = item("minecraft", "coal", 0);
        String clayBall = item("minecraft", "clay_ball", 0);

        String flintShard = item(ItemMaterial.NAME, ItemMaterial.EnumType.FLINT_SHARD.getMeta());
        String clayLump = item(ItemMaterial.NAME, ItemMaterial.EnumType.CLAY_LUMP.getMeta());

        int[] metas = {0, 1, 2, 3, 4, 5, 6, 7};
        String[] listClay = {clayAndesite, clayBasalt, clayBlackGranite, clayBlueSchist, clayChalk, clayChert, clayDacite, clayDolomite, clayEclogite, clayGabbro, clayGneiss, clayGreenSchist, clayGreywacke, clayKomatiite, clayLignite, clayLimestone, clayMarble, clayMigmatite, clayQuartzite, clayRedGranite, clayRhyolite, clayShale, claySiltstone, claySoapstone};

        List<IDroptRuleBuilder> list = new ArrayList<>();

        // -------------------------------------------------------------------------
        // - Sand
        // -------------------------------------------------------------------------

        if (enabled("sand")) {

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

        // -------------------------------------------------------------------------
        // - Gravel
        // -------------------------------------------------------------------------

        /*if (enabled("gravel")) {

            // Not a shovel
            list.add(rule()
                    .matchBlocks(new String[]{
                            gravel
                    })
                    .matchHarvester(harvester()
                            .mainHand(EnumListType.BLACKLIST, "shovel;0;-1")
                    )
                    .addDrops(new IDroptDropBuilder[]{
                            drop().items(new String[]{rockStone}, range(1, 3)).selector(weight(10)),
                            drop().items(new String[]{rockGranite}, range(1, 3)).selector(weight(10)),
                            drop().items(new String[]{rockDiorite}, range(1, 3)).selector(weight(10)),
                            drop().items(new String[]{rockAndesite}, range(1, 3)).selector(weight(10))
                    })
            );

            // Shovel 0
            list.add(rule()
                    .matchBlocks(new String[]{
                            gravel
                    })
                    .matchHarvester(harvester()
                            .type(EnumHarvesterType.PLAYER)
                            .mainHand(EnumListType.BLACKLIST, "shovel;1;-1")
                    )
                    .dropCount(range(2))
                    .dropStrategy(EnumDropStrategy.UNIQUE)
                    .addDrops(new IDroptDropBuilder[]{
                            drop().items(new String[]{rockStone}, range(1, 2)).selector(weight(2)),
                            drop().items(new String[]{rockGranite}, range(1, 2)).selector(weight(2)),
                            drop().items(new String[]{rockDiorite}, range(1, 2)).selector(weight(2)),
                            drop().items(new String[]{rockAndesite}, range(1, 2)).selector(weight(2)),
                            drop().items(new String[]{flintShard}).selector(weight(1))
                    })
            );

            // Shovel 1
            list.add(rule()
                    .matchBlocks(new String[]{
                            gravel
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
                            drop().items(new String[]{rockDiorite}, range(1, 3)).selector(weight(1)),
                            drop().items(new String[]{rockAndesite}, range(1, 3)).selector(weight(1)),
                            drop().items(new String[]{gravel}).selector(weight(2)),
                            drop().items(new String[]{flintShard}).selector(weight(2)),
                            drop().items(new String[]{flint}).selector(weight(1))
                    })
            );
        }*/

        // -------------------------------------------------------------------------
        // - Clay
        // -------------------------------------------------------------------------

        if (enabled("clay")) {

            // Not a shovel
            list.add(rule()
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

        /*if (enabled("sandstone")) {
            String matchBlock = item("minecraft", "sandstone", OreDictionary.WILDCARD_VALUE);
            String rock = item(BlockRock.NAME, BlockRock.EnumType.SANDSTONE.getMeta());
            this.addRockDrops(matchBlock, rock, list);
        }

        // -------------------------------------------------------------------------
        // - Limestone
        // -------------------------------------------------------------------------

        if (enabled("limestone")) {
            String matchBlock = item("pyrotech", "limestone");
            String rock = item(BlockRock.NAME, BlockRock.EnumType.LIMESTONE.getMeta());
            this.addRockDrops(matchBlock, rock, list);
        }

        if (enabled("limestone_cobbled")) {
            String rock = item(BlockRock.NAME, BlockRock.EnumType.LIMESTONE.getMeta());
            this.addRockDrops(cobbledLimestone, rock, cobbledLimestone, list);
            this.addBlockReplace(cobbledLimestone, cobbledLimestone, list);
        }

        // -------------------------------------------------------------------------
        // - Stone / Cobblestone
        // -------------------------------------------------------------------------

        if (enabled("stone")) {
            String matchBlock = item("minecraft", "stone", BlockStone.EnumType.STONE.getMetadata());
            String rock = item(BlockRock.NAME, BlockRock.EnumType.STONE.getMeta());
            this.addRockDrops(matchBlock, rock, list);
        }

        if (enabled("cobblestone")) {
            String matchBlock = item("minecraft", "cobblestone");
            String rock = item(BlockRock.NAME, BlockRock.EnumType.STONE.getMeta());
            this.addRockDrops(matchBlock, rock, list);
        }*/

        ResourceLocation resourceLocation = new ResourceLocation(ModulePluginDropt.MOD_ID, "dropt");
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
