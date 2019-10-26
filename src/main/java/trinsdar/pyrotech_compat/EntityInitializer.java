package trinsdar.pyrotech_compat;

import com.codetaylor.mc.athenaeum.registry.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class EntityInitializer {
    public static void onRegister(Registry registry) {

        registry.createEntityEntry(EntityRockPC.NAMEPC, EntityEntryBuilder.create()
                .entity(EntityRockPC.class)
                .tracker(80, 1, true)
        );
    }

    @SideOnly(Side.CLIENT)
    public static void onClientRegister() {

        RenderingRegistry.registerEntityRenderingHandler(EntityRockPC.class, manager -> {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new RenderSnowball<EntityRockPC>(manager, Item.getItemFromBlock(BlockInitializer.blockRockIgneous), renderItem) {

                @Nonnull
                @Override
                public ItemStack getStackToRender(EntityRockPC entity) {

                    int meta = entity.getMeta();
                    return new ItemStack(entity.getItem(), 1, meta);
                }
            };
        });
    }
}