package trinsdar.pyrotech_compat;

import com.codetaylor.mc.pyrotech.modules.core.entity.EntityRock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class EntityRockPC extends EntityThrowable {
    private IBlockState blockState;
    public static final String  NAMEPC = "rock_pc";

    private static final DataParameter<Integer> META = EntityDataManager.createKey(EntityRockPC.class, DataSerializers.VARINT);
    private int meta;

    public EntityRockPC(World world) {
        super(world);
    }

    public EntityRockPC(World world, int meta, IBlockState blockState) {
        super(world);
        this.meta = meta;
        this.dataManager.set(META, meta);
        this.blockState = blockState;
    }

    public EntityRockPC(World world, double x, double y, double z, int meta, IBlockState blockState) {
        super(world, x, y, z);
        this.meta = meta;
        this.dataManager.set(META, meta);
        this.blockState = blockState;
    }

    public EntityRockPC(World world, EntityLivingBase throwerIn, int meta, IBlockState blockState) {
        super(world, throwerIn);
        this.meta = meta;
        this.dataManager.set(META, meta);
        this.blockState = blockState;
    }

    public Item getItem(){
        return Item.getItemFromBlock(this.blockState.getBlock());
    }

    public int getMeta() {
        return meta;
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

    @Override
    protected void onImpact(@Nonnull RayTraceResult result) {

        if (result.entityHit != null) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 1);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }
    }

    @Override
    protected void entityInit() {

        super.entityInit();
        this.dataManager.register(META, this.meta);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {

        if (this.world.isRemote) {

            if (META.equals(key)) {
                this.meta = this.dataManager.get(META);
            }
        }

        super.notifyDataManagerChange(key);
    }
}
