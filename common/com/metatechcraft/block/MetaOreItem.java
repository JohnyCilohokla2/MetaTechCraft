package com.metatechcraft.block;

import com.forgetutorials.lib.utilities.ItemBlockWithInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class MetaOreItem extends ItemBlockWithInfo {

	public MetaOreItem(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
		this.maxStackSize = 64;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, MetaOreBlock.ORE_COUNT - 1);
		return super.getUnlocalizedName() + "." + MetaOreBlock.ORE_NAMES[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return MetaBlocks.metaOreBlock.getIcon(0, par1);
	}

	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}
}
