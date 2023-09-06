package com.fwloopins.spectatorcrosshair.client.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "shouldRenderSpectatorCrosshair", at = @At("RETURN"), cancellable = true)
    private void modifyShouldRenderSpectatorCrosshair(HitResult hitResult, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
