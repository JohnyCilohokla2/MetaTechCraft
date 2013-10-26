package com.metatechcraft.multientity.entites;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import com.forgetutorials.lib.network.MultiEntitySystem;
import com.forgetutorials.lib.network.PacketMultiTileEntity;
import com.forgetutorials.lib.renderers.GLDisplayList;
import com.forgetutorials.lib.utilities.ItemStackUtilities;
import com.forgetutorials.multientity.InfernosMultiEntity;
import com.forgetutorials.multientity.base.InfernosProxyEntityBase;
import com.metatechcraft.models.MetaTechCraftModels;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class StrangeFrameTileEntity extends InfernosProxyEntityBase {

	public final static String TYPE_NAME = "metatech.StrangeFrameTileEntity";

	@Override
	public String getTypeName() {
		return StrangeFrameTileEntity.TYPE_NAME;
	}

	@Override
	public boolean hasInventory() {
		return false;
	}

	@Override
	public boolean hasLiquids() {
		return false;
	}

	@Override
	public ItemStack getSilkTouchItemStack() {
		ItemStack stack = new ItemStack(MultiEntitySystem.infernosMultiBlock, 1, 3);
		ItemStackUtilities.addStringTag(stack, "MES", getTypeName());
		return stack;
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(int fortune) {
		ArrayList<ItemStack> droppedItems = new ArrayList<ItemStack>();
		droppedItems.add(getSilkTouchItemStack());
		return droppedItems;
	}

	public StrangeFrameTileEntity(InfernosMultiEntity entity) {
		super(entity);
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
	}

	@Override
	public void addToDescriptionPacket(PacketMultiTileEntity packet) {
	}

	GLDisplayList frameBoxList[] = new GLDisplayList[7];

	@Override
	public void renderTileEntityAt(double x, double y, double z) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslated(x, y, z);
		int facing = this.entity.getFacingInt();
		if (this.frameBoxList[facing] == null) {
			this.frameBoxList[facing] = new GLDisplayList();
		}
		if (!this.frameBoxList[facing].isGenerated()) {
			this.frameBoxList[facing].generate();
			this.frameBoxList[facing].bind();
			MetaTechCraftModels.squareFrame.render(MetaTechCraftModels.boxFrameTexture, facing == 6 ? null : EnumFacing.getFront(facing));
			this.frameBoxList[facing].unbind();
		}
		this.frameBoxList[facing].render();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	public void renderStaticBlockAt(RenderBlocks renderer, int x, int y, int z) {
		// nothing for now
	}

	@Override
	public void tick() {

	}

	@Override
	public void renderItem(ItemRenderType type) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		// GL11.glTranslated(x, y, z);
		int facing = 6;
		if (this.frameBoxList[facing] == null) {
			this.frameBoxList[facing] = new GLDisplayList();
		}
		if (!this.frameBoxList[facing].isGenerated()) {
			this.frameBoxList[facing].generate();
			this.frameBoxList[facing].bind();
			MetaTechCraftModels.squareFrame.render();
			this.frameBoxList[facing].unbind();
		}
		this.frameBoxList[facing].render();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

	}

}