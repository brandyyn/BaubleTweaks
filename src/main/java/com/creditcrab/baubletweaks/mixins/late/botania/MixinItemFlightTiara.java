package com.creditcrab.baubletweaks.mixins.late.botania;

import baubles.api.BaublesApi;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import com.creditcrab.baubletweaks.mixinplugin.BaubleTweaksLateMixins;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemFlightTiara;

import java.util.List;

import static vazkii.botania.common.item.equipment.bauble.ItemFlightTiara.playerStr;
import static vazkii.botania.common.item.equipment.bauble.ItemFlightTiara.playersWithFlight;

@SuppressWarnings("unchecked")
@Mixin(value = ItemFlightTiara.class, remap = false)
public abstract class MixinItemFlightTiara extends ItemBauble implements IBaubleExpanded, IManaUsingItem, IBaubleRender, ICraftAchievement {
    @Shadow
    private static ResourceLocation textureHud;

    @Shadow
    public abstract int getCost(ItemStack stack, int timeLeft);

    public MixinItemFlightTiara(String name) {
        super(name);
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{"cosmetic"};
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean unused) {
        list.add(StatCollector.translateToLocal("botania.wings" + stack.getItemDamage()));
        BaubleItemHelper.addSlotInformation(list,this.getBaubleTypes(stack));
    }

    /**
     *
     * @author Nick
     * @reason update to extended bauble api
     */
    @Overwrite
    public boolean shouldPlayerHaveFlight(EntityPlayer player){
        return false;
    }

    /**
     * @author Nick
     * @reason rewrite to extended bauble API
     */
    @Overwrite
    @SubscribeEvent
    public void updatePlayerFlyStatus(LivingEvent.LivingUpdateEvent event) {
        // NOP
    }

    /**
     *
     * @author Nick
     * @reason update to extended bauble api
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    public static void renderHUD(ScaledResolution resolution, EntityPlayer player, ItemStack stack) {
        // NOP
    }
}
