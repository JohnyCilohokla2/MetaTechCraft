package com.metatechcraft.liquid;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialMetaLiquid extends Material {
	public MaterialMetaLiquid(MapColor par1MapColor) {
		super(par1MapColor);
		setNoPushMobility();
		setReplaceable();
	}

	/**
	 * Returns if blocks of these materials are liquids.
	 */
	@Override
	public boolean isLiquid() {
		return true;
	}

	/**
	 * Returns if this material is considered solid or not
	 */
	@Override
	public boolean blocksMovement() {
		return true;
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}