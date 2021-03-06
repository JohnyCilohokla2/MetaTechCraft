package com.metatechcraft.item;

import com.forgetutorials.lib.utilities.ItemWithInfo;
import com.metatechcraft.lib.ModInfo;
import com.metatechcraft.mod.MetaTechCraft;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StrangeDust extends ItemWithInfo {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public StrangeDust() {
		super();
		MetaTechCraft.registry.registerItem(this, "StrangeDust", "Strange Dust");
		setCreativeTab(MetaTechCraft.tabs);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID.toLowerCase() + ":" + "strangeDust");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}