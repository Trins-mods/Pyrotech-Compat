package trinsdar.pyrotech_compat.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import trinsdar.pyrotech_compat.EntityRockPC;

import javax.annotation.Nonnull;

public class ItemRockBase extends ItemBlock {

    public ItemRockBase(Block block) {

        super(block);
    }

    protected IBlockState getBlockState(ItemStack stack){
        return this.block.getDefaultState();
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return this.block.getUnlocalizedName();
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {

        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.isCreative()) {
            itemstack.shrink(1);
        }

        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            EntityRockPC entity = new EntityRockPC(world, player, itemstack.getMetadata(), getBlockState(itemstack));
            entity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.5f, 1.0f);
            world.spawnEntity(entity);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public int getMetadata(int damage) {

        return damage;
    }
}
