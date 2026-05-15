package camoweed.lavarebalance.mixin;

import net.minecraft.core.block.BlockLogicNetherrackIgneous;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static camoweed.lavarebalance.LavaRebalance.preventIgneousTransmutation;

@Mixin(BlockLogicNetherrackIgneous.class)
public abstract class BlockLogicNetherrackIgneousMixin {
		@Inject(method = "updateTick", at = @At("HEAD"), cancellable = true)
		private void updateTick (World world, int x, int y, int z, Random rand, CallbackInfo ci){
			if(preventIgneousTransmutation) {
				ci.cancel();
			}
	}
}
