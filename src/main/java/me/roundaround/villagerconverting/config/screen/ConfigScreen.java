package me.roundaround.villagerconverting.config.screen;

import dev.lambdaurora.spruceui.screen.SpruceScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

public class ConfigScreen extends SpruceScreen {
  private final Screen parent;

  protected ConfigScreen(Screen parent) {
    super(new TranslatableText("config.title"));
    this.parent = parent;
  }
  
}
