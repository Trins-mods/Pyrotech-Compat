package trinsdar.pyrotech_compat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.pyrotech_compat.entity.EntityRockBase;
import trinsdar.pyrotech_compat.entity.EntityRockIgneous;
import trinsdar.pyrotech_compat.entity.EntityRockIgneousSand;
import trinsdar.pyrotech_compat.entity.EntityRockMetamorphic;
import trinsdar.pyrotech_compat.entity.EntityRockMetamorphicSand;
import trinsdar.pyrotech_compat.entity.EntityRockSedimentary;
import trinsdar.pyrotech_compat.entity.EntityRockSedimentarySand;

import javax.annotation.Nonnull;

public class EntityInitializer {

    @SideOnly(Side.CLIENT)
    public static void onClientRegister() {

        RenderingRegistry.registerEntityRenderingHandler(EntityRockBase.class, manager -> {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityRockBase>(manager, Item.getItemFromBlock(BlockInitializer.blockRockNetherrack), renderItem);
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityRockIgneous.class, manager -> {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityRockIgneous>(manager, Item.getItemFromBlock(BlockInitializer.blockRockIgneous), renderItem) {

                @Nonnull
                @Override
                public ItemStack getStackToRender(EntityRockIgneous entity) {

                    int meta = entity.getMeta();
                    return new ItemStack(BlockInitializer.itemRockIgneous, 1, meta);
                }
            };
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityRockIgneousSand.class, manager -> {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityRockIgneousSand>(manager, Item.getItemFromBlock(BlockInitializer.blockRockIgneousSand), renderItem) {

                @Nonnull
                @Override
                public ItemStack getStackToRender(EntityRockIgneousSand entity) {

                    int meta = entity.getMeta();
                    return new ItemStack(BlockInitializer.itemRockIgneousSand, 1, meta);
                }
            };
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityRockMetamorphic.class, manager -> {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityRockMetamorphic>(manager, Item.getItemFromBlock(BlockInitializer.blockRockMetamorphic), renderItem) {

                @Nonnull
                @Override
                public ItemStack getStackToRender(EntityRockMetamorphic entity) {

                    int meta = entity.getMeta();
                    return new ItemStack(BlockInitializer.itemRockMetamorphic, 1, meta);
                }
            };
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityRockMetamorphicSand.class, manager -> {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityRockMetamorphicSand>(manager, Item.getItemFromBlock(BlockInitializer.blockRockMetamorphicSand), renderItem) {

                @Nonnull
                @Override
                public ItemStack getStackToRender(EntityRockMetamorphicSand entity) {

                    int meta = entity.getMeta();
                    return new ItemStack(BlockInitializer.itemRockMetamorphicSand, 1, meta);
                }
            };
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityRockSedimentary.class, manager -> {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityRockSedimentary>(manager, Item.getItemFromBlock(BlockInitializer.blockRockSedimentary), renderItem) {

                @Nonnull
                @Override
                public ItemStack getStackToRender(EntityRockSedimentary entity) {

                    int meta = entity.getMeta();
                    return new ItemStack(BlockInitializer.itemRockSedimentary, 1, meta);
                }
            };
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityRockSedimentarySand.class, manager -> {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityRockSedimentarySand>(manager, Item.getItemFromBlock(BlockInitializer.blockRockSedimentarySand), renderItem) {

                @Nonnull
                @Override
                public ItemStack getStackToRender(EntityRockSedimentarySand entity) {

                    int meta = entity.getMeta();
                    return new ItemStack(BlockInitializer.itemRockSedimentarySand, 1, meta);
                }
            };
        });
    }
}
