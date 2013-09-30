package com.metatechcraft.block;

import java.util.ArrayList;
import java.util.List;

import com.forgetutorials.lib.registry.MetaMaterial;
import com.metatechcraft.item.MetaItems;
import com.metatechcraft.lib.ModInfo;
import com.metatechcraft.mod.MetaTechCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class MetaOreBlock extends Block {

	public static final String[] ORE_NAMES = new String[] { "Empty", "White", "Black", "Red", "Green", "Blue" };
	public static final int ORE_COUNT = MetaOreBlock.ORE_NAMES.length;
	private static final int ORE_SIZE = MetaOreBlock.ORE_COUNT - 1;
	private Icon[] icons;

	protected MetaOreBlock(int par1) {
		// make sure the material used can be broken by hand!
		super(par1, MetaMaterial.metaMaterial);
		MetaTechCraft.registry.registerBlock(this, "MetaOreBlock", "MetaOre Block");
		setCreativeTab(MetaTechCraft.tabs);
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		return meta == 0 ? 0.3f : 2;
	}

	/**
	 * Called when the player destroys a block with an item that can harvest it.
	 * (i, j, k) are the coordinates of the block and l is the block's
	 * subtype/damage.
	 */
	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
		par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
		par2EntityPlayer.addExhaustion(0.025F);

		if (this.canSilkHarvest(par1World, par2EntityPlayer, par3, par4, par5, par6)
				&& (par2EntityPlayer.getCurrentEquippedItem().getItem() == MetaItems.strangeChisel)) {
			ItemStack itemstack = createStackedBlock(par6);

			if (itemstack != null) {
				dropBlockAsItem_do(par1World, par3, par4, par5, itemstack);
			}
		} else {
			int i1 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
			dropBlockAsItem(par1World, par3, par4, par5, par6, i1);
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> droppedItems = new ArrayList<ItemStack>();
		droppedItems.add(new ItemStack(this, 1, 0));
		if (metadata != 0) {
			droppedItems.add(new ItemStack(MetaItems.metaChunk, 2, metadata - 1));
		}
		return droppedItems;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.icons = new Icon[MetaOreBlock.ORE_NAMES.length];
		for (int i = 0; i < MetaOreBlock.ORE_NAMES.length; ++i) {
			this.icons[i] = iconRegister.registerIcon(ModInfo.MOD_ID.toLowerCase() + ":" + "ore/meta" + MetaOreBlock.ORE_NAMES[i]);
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
	}

	// {"DOWN", "UP", "NORTH", "SOUTH", "WEST", "EAST"};
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		int meta = MathHelper.clamp_int(par2, 0, MetaOreBlock.ORE_SIZE);
		return this.icons[meta];
	}

	public String getUnlocalizedName(int i) {
		return super.getUnlocalizedName() + "." + MetaOreBlock.ORE_NAMES[i].toLowerCase();
	}

	@Override
	public String getUnlocalizedName() {
		return super.getUnlocalizedName();
	}

	public static String getDisplayName(ItemStack itemStack) {
		int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, MetaOreBlock.ORE_SIZE);
		switch (meta) {
		case 0:
			return EnumChatFormatting.WHITE + "Meta Stone";
		case 1:
			return EnumChatFormatting.AQUA + "Meta Ore";
		case 2:
			return EnumChatFormatting.DARK_GRAY + "Meta Ore";
		case 3:
			return EnumChatFormatting.RED + "Meta Ore";
		case 4:
			return EnumChatFormatting.GREEN + "Meta Ore";
		case 5:
			return EnumChatFormatting.BLUE + "Meta Ore";
		default:
			return EnumChatFormatting.WHITE + "Meta Ore(undefined?)";
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		for (int meta = 0; meta < (MetaOreBlock.ORE_SIZE + 1); meta++) {
			list.add(new ItemStack(id, 1, meta));
		}
	}
}
