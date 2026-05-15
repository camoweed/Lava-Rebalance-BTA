package camoweed.lavarebalance.mixin;

import net.minecraft.core.block.BlockLogicWireRedstone;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static camoweed.lavarebalance.LavaRebalance.preventRedstoneTransmutation;

@Mixin(BlockLogicWireRedstone.class)
public abstract class BlockLogicWireRedstoneMixin {
	@Redirect(method = "updatePowerStrength(Lnet/minecraft/core/world/World;IIIIII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;getBlockId(III)I"))
	private int lavarebalance$noLava(World world, int x, int y, int z) {
		int id = world.getBlockId(x, y, z);
		if (preventRedstoneTransmutation) {

			if (id == Blocks.FLUID_LAVA_STILL.id()
				|| id == Blocks.FLUID_LAVA_FLOWING.id()) {
				return -1;
			}
		}
		return id;

	}
}
