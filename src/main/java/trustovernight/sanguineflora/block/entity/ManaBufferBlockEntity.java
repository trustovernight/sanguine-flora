package trustovernight.sanguineflora.block.entity;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block.Wandable;
import vazkii.botania.api.mana.ManaPool;
import vazkii.botania.api.mana.spark.ManaSpark;
import vazkii.botania.api.mana.spark.SparkAttachable;

import java.util.List;
import java.util.Optional;

public class ManaBufferBlockEntity extends BlockEntity implements ManaPool, SparkAttachable, Wandable {
    private static final int MAX_MANA = 100_000_000;

    private Optional<DyeColor> color = Optional.empty();
    private int mana;

    public ManaBufferBlockEntity(BlockPos pos, BlockState state) {
        super(SanguineFloraBlockEntities.MANA_BUFFER.get(), pos, state);
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> ticker() {
        return (level, pos, state, be) -> ((ManaBufferBlockEntity) be).tick(level, pos, state);
    }

    private void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) {
            clientTick(level, pos, state);
        } else {
            serverTick(level, pos, state);
        }
    }

    private void serverTick(Level level, BlockPos pos, BlockState state) {

    }

    private void clientTick(Level level, BlockPos pos, BlockState state) {

    }

    @Override
    public boolean isOutputtingPower() {
        return false;
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

    @Override
    public Optional<DyeColor> getColor() {
        return color;
    }

    @Override
    public void setColor(Optional<DyeColor> optional) { }

    @Override
    public Level getManaReceiverLevel() {
        return level;
    }

    @Override
    public BlockPos getManaReceiverPos() {
        return worldPosition;
    }

    @Override
    public int getCurrentMana() {
        return mana ;
    }

    @Override
    public boolean isFull() {
        return mana == MAX_MANA;
    }

    @Override
    public void receiveMana(int i) {
        mana = Math.min(mana + i, MAX_MANA);
        setChanged();
    }

    @Override
    public boolean canReceiveManaFromBursts() {
        return true;
    }

    @Override
    public boolean canAttachSpark(ItemStack itemStack) {
        return true;
    }

    @Override
    public int getAvailableSpaceForMana() {
        return MAX_MANA - mana;
    }

    @Override
    public ManaSpark getAttachedSpark() {
        List<Entity> sparks = level.getEntitiesOfClass(
                Entity.class,
                new AABB(worldPosition.above(),
                        worldPosition.above().offset(1, 1, 1)
                ),
                Predicates.instanceOf(ManaSpark.class)
        );

        if (sparks.size() == 1) {
            Entity e = sparks.get(0);
            return (ManaSpark) e;
        }

        return null;
    }

    @Override
    public boolean areIncomingTranfersDone() {
        return false;
    }

    @Override
    public boolean onUsedByWand(@Nullable Player player, ItemStack stack, Direction side) {
        return false;
    }
}
