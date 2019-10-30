package trinsdar.pyrotech_compat.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import trinsdar.pyrotech_compat.init.BlockInitializer;

public class OredictInitializer {
    public static void init(){
        if (Loader.isModLoaded("undergroundbiomes")){
            OreDictionary.registerOre("rock", new ItemStack(BlockInitializer.itemRockIgneous, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("rock", new ItemStack(BlockInitializer.itemRockMetamorphic, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("rock", new ItemStack(BlockInitializer.itemRockSedimentary, 1, OreDictionary.WILDCARD_VALUE));
            for (int i = 8; i < 16; i++){
                OreDictionary.registerOre("rock", new ItemStack(BlockInitializer.itemRockIgneousSand, 1, i));
                OreDictionary.registerOre("rock", new ItemStack(BlockInitializer.itemRockMetamorphicSand, 1, i));
                OreDictionary.registerOre("rock", new ItemStack(BlockInitializer.itemRockSedimentarySand, 1, i));
            }
        }
        OreDictionary.registerOre("rock", new ItemStack(BlockInitializer.itemRockNetherrack));
    }
}
