package trinsdar.pyrotech_compat.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.pyrotech_compat.init.BlockInitializer;
import trinsdar.pyrotech_compat.block.rocks.BlockRockIgneousSand;

import javax.annotation.Nonnull;

public class EntityRockIgneousSand extends EntityThrowable {
    public static final String  NAME = "rock_igneous_sand";

    private static final DataParameter<Integer> META = EntityDataManager.createKey(EntityRockIgneousSand.class, DataSerializers.VARINT);
    private int meta;

    public EntityRockIgneousSand(World world) {
        super(world);
    }

    public EntityRockIgneousSand(World world, int meta) {
        super(world);
        this.meta = meta;
        this.dataManager.set(META, meta);
    }

    public EntityRockIgneousSand(World world, double x, double y, double z, int meta) {
        super(world, x, y, z);
        this.meta = meta;
        this.dataManager.set(META, meta);
    }

    public EntityRockIgneousSand(World world, EntityLivingBase throwerIn, int meta) {
        super(world, throwerIn);
        this.meta = meta;
        this.dataManager.set(META, meta);
    }

    public int getMeta() {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            IBlockState blockState = BlockInitializer.blockRockIgneousSand.getDefaultState().withProperty(BlockRockIgneousSand.VARIANT, BlockRockIgneousSand.EnumType.fromMeta(meta));
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
