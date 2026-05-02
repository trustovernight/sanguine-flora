package trustovernight.sanguineflora.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import trustovernight.sanguineflora.SanguineFlora;

public class SanguineFloraBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SanguineFlora.MODID);

    public static final RegistryObject<Block> MANA_BUFFER =
            BLOCKS.register("mana_buffer",
                    () -> new ManaBufferBlock(BlockBehaviour.Properties.of()
                            .strength(3.0f)
                            .requiresCorrectToolForDrops()));
}