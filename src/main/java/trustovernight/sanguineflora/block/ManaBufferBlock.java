package trustovernight.sanguineflora.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import trustovernight.sanguineflora.block.entity.ManaBufferBlockEntity;
import trustovernight.sanguineflora.block.entity.SanguineFloraBlockEntities;

import javax.annotation.Nullable;

public class ManaBufferBlock extends BaseEntityBlock {
    public ManaBufferBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ManaBufferBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, SanguineFloraBlockEntities.MANA_BUFFER.get(), ManaBufferBlockEntity.ticker());
    }
}