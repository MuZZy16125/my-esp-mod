package com.example.esp;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.InputEvent;
import org.lwjgl.glfw.GLFW;

@Mod("espmod")
public class EspMod {
    private boolean enabled = false;

    public EspMod() {
        // Регистрируем мод в системе событий Forge
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        // Если нажата клавиша M (GLFW_KEY_M)
        if (event.getKey() == GLFW.GLFW_KEY_M && event.getAction() == GLFW.GLFW_PRESS) {
            enabled = !enabled;
        }
    }

    @SubscribeEvent
    public void onRender(net.minecraftforge.client.event.RenderWorldLastEvent event) {
        if (enabled) {
            // Включаем свечение для всех игроков, кроме себя
            for (PlayerEntity p : Minecraft.getInstance().level.players()) {
                if (p != Minecraft.getInstance().player) {
                    p.setGlowing(true);
                }
            }
        } else {
            // Выключаем свечение, если ESP выключен
            for (PlayerEntity p : Minecraft.getInstance().level.players()) {
                if (p != Minecraft.getInstance().player) {
                    p.setGlowing(false);
                }
            }
        }
    }
}
