package com.fwloopins.spectatorcrosshair.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final MinecraftClient client;

    @Inject(method = "shouldRenderBlockOutline", at = @At("HEAD"), cancellable = true)
    private void onShouldRenderBlockOutline(CallbackInfoReturnable<Boolean> cir) {
        if (client.player != null && client.world != null && client.interactionManager != null) {
            if (client.interactionManager.getCurrentGameMode() == GameMode.SPECTATOR && !client.options.hudHidden) {
                 if (client.world.getBlockState(BlockPos.ofFloored(client.player.getEyePos())).isAir()) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
