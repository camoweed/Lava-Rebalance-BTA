package camoweed.lavarebalance.mixin;

import net.minecraft.core.block.BlockLogicFluid;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static camoweed.lavarebalance.LavaRebalance.preventCobbleGenerator;

@Mixin(BlockLogicFluid.class)
public abstract class FluidLavaMixin {
	@Shadow
	protected abstract void fizz(World world, int x, int y, int z);

	@Inject(method = "checkForHarden", at = @At("HEAD"), cancellable = true)
	private void checkForHarden(World world, int x, int y, int z, CallbackInfo ci) {
		if (preventCobbleGenerator) {
			ci.cancel();
			if (world.getBlockMaterial(x, y, z) != Material.lava) {
				return;
			}
			boolean touchingWater =
				world.getBlockMaterial(x, y, z - 1) == Material.water ||
					world.getBlockMaterial(x, y, z + 1) == Material.water ||
					world.getBlockMaterial(x - 1, y, z) == Material.water ||
					world.getBlockMaterial(x + 1, y, z) == Material.water ||
					world.getBlockMaterial(x, y + 1, z) == Material.water;
			if (!touchingWater) {
				return;
			}
			int data = world.getBlockMetadata(x, y, z) & 15;
			if (data == 0) {
				world.setBlockWithNotify(
					x, y, z,
					Blocks.OBSIDIAN.id()
				);
				this.fizz(world, x, y, z);
			}
		}
	}
}
