package net.fwuffypetsowo.colorstone.mixin;

import com.mojang.math.Vector3f;
import net.fwuffypetsowo.colorstone.ColorUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Random;

@Mixin(RedStoneWireBlock.class)
public class RedstoneColorsMixin {

    @Inject(at = @At("HEAD"), method = "getColorForPower", cancellable = true)
    private static void onGetColor(int i, CallbackInfoReturnable<Integer> cir) {
        Vec3 vec3 = ColorUtil.CUSTOM_COLORS[i];
        cir.setReturnValue(Mth.color((float)vec3.x(), (float)vec3.y(), (float)vec3.z()));
    }

    @Inject(at = @At("HEAD"), method = "animateTick", cancellable = true)
    public void onAnimateTick(BlockState blockState, Level level, BlockPos blockPos, Random random, CallbackInfo ci) {
        int i = blockState.getValue(RedStoneWireBlock.POWER);
        if (i == 0) {
            return;
        }
        block4: for (Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide redstoneSide = blockState.getValue(RedStoneWireBlock.PROPERTY_BY_DIRECTION.get(direction));
            switch (redstoneSide) {
                case UP: {
                    this.spawnParticlesAlongLine(level, random, blockPos, ColorUtil.CUSTOM_COLORS[i], direction, Direction.UP, -0.5f, 0.5f);
                }
                case SIDE: {
                    this.spawnParticlesAlongLine(level, random, blockPos, ColorUtil.CUSTOM_COLORS[i], Direction.DOWN, direction, 0.0f, 0.5f);
                    continue block4;
                }
            }
            this.spawnParticlesAlongLine(level, random, blockPos, ColorUtil.CUSTOM_COLORS[i], Direction.DOWN, direction, 0.0f, 0.3f);
        }
        ci.cancel();
    }

    private void spawnParticlesAlongLine(Level level, Random random, BlockPos blockPos, Vec3 vec3, Direction direction, Direction direction2, float f, float g) {
        float h = g - f;
        if (random.nextFloat() >= 0.2f * h) {
            return;
        }
        float j = f + h * random.nextFloat();
        double d = 0.5 + (double)(0.4375f * (float)direction.getStepX()) + (double)(j * (float)direction2.getStepX());
        double e = 0.5 + (double)(0.4375f * (float)direction.getStepY()) + (double)(j * (float)direction2.getStepY());
        double k = 0.5 + (double)(0.4375f * (float)direction.getStepZ()) + (double)(j * (float)direction2.getStepZ());
        level.addParticle(new DustParticleOptions(new Vector3f(vec3), 1.0f), (double)blockPos.getX() + d, (double)blockPos.getY() + e, (double)blockPos.getZ() + k, 0.0, 0.0, 0.0);
    }
}