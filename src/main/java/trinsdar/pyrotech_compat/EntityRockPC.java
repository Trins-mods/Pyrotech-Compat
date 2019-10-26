package trinsdar.pyrotech_compat;

import com.codetaylor.mc.pyrotech.modules.core.entity.EntityRock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRockPC extends EntityRock {
    private IBlockState blockState;
    public static final String  NAMEPC = "rock_pc";

    public EntityRockPC(World world) {
        super(world);
    }

    public EntityRockPC(World world, int meta, IBlockState blockState) {
        super(world, meta);
        this.blockState = blockState;
    }

    public EntityRockPC(World world, double x, double y, double z, int meta, IBlockState blockState) {
        super(world, x, y, z, meta);
        this.blockState = blockState;
    }

    public EntityRockPC(World world, EntityLivingBase throwerIn, int meta, IBlockState blockState) {
        super(world, throwerIn, meta);
        this.blockState = blockState;
    }

    public Item getItem(){
        return Item.getItemFromBlock(this.blockState.getBlock());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for(int i = 0; i < 8; ++i) {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, Block.getStateId(blockState));
            }
        }
    }
}
