package trustovernight.sanguineflora.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import trustovernight.sanguineflora.SanguineFlora;
import trustovernight.sanguineflora.block.SanguineFloraBlocks;

public class SanguineFloraBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SanguineFlora.MODID);

    public static final RegistryObject<BlockEntityType<ManaBufferBlockEntity>> MANA_BUFFER =
            BLOCK_ENTITIES.register("mana_buffer",
                () -> BlockEntityType.Builder.of(
                        ManaBufferBlockEntity::new,
                        SanguineFloraBlocks.MANA_BUFFER.get()
            ).build(null));
}