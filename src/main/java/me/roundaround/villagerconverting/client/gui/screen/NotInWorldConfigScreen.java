package me.roundaround.villagerconverting.client.gui.screen;

import me.roundaround.roundalib.client.gui.layout.screen.ThreeSectionLayoutWidget;
import me.roundaround.roundalib.client.gui.widget.drawable.LabelWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.List;

public class NotInWorldConfigScreen extends Screen {
  private final Screen parent;
  private final ThreeSectionLayoutWidget layout = new ThreeSectionLayoutWidget(this);

  public NotInWorldConfigScreen(Screen parent) {
    super(Component.translatable("villagerconverting.config.title"));
    this.parent = parent;
  }

  @Override
  protected void init() {
    this.layout.addHeader(this.font, this.title);

    this.layout.addBody(LabelWidget.builder(
        this.font, List.of(
            Component.translatable("villagerconverting.config.notInWorld.1"),
            Component.translatable("villagerconverting.config.notInWorld.2")
        )
    ).alignTextCenterX().alignTextCenterY().hideBackground().showShadow().lineSpacing(2).build());

    this.layout.addFooter(Button.builder(CommonComponents.GUI_BACK, (b) -> this.onClose())
        .width(Button.BIG_WIDTH)
        .build());

    this.layout.visitWidgets(this::addRenderableWidget);
    this.repositionElements();
  }

  @Override
  protected void repositionElements() {
    this.layout.arrangeElements();
  }

  @Override
  public void onClose() {
    this.minecraft.setScreen(this.parent);
  }
}
