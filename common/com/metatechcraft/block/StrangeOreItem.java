package com.metatechcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class StrangeOreItem extends ItemBlock {

	public StrangeOreItem(int par1) {
		super(par1);
		setHasSubtypes(true);
		setMaxDamage(0);
		this.maxStackSize = 64;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, StrangeOreBlock.ORE_COUNT - 1);
		return super.getUnlocalizedName() +".strange."+ StrangeOreBlock.ORE_NAMES[meta];
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return MetaBlocks.strangeOreBlock.getIcon(0, par1);
	}

	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}
}