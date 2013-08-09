package com.metatechcraft.liquid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderingMetaLiquid implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

		if (block.getRenderType() != MetaLiquids.metaLiquidModel) {
			return true;
		}

		Tessellator tessellator = Tessellator.instance;

		int l = block.colorMultiplier(world, x, y, z);
		float f = ((l >> 16) & 255) / 255.0F;
		float f1 = ((l >> 8) & 255) / 255.0F;
		float f2 = (l & 255) / 255.0F;
		boolean flag = block.shouldSideBeRendered(world, x, y + 1, z, 1);
		boolean flag1 = block.shouldSideBeRendered(world, x, y - 1, z, 0);
		boolean[] aboolean = new boolean[] { block.shouldSideBeRendered(world, x, y, z - 1, 2), block.shouldSideBeRendered(world, x, y, z + 1, 3),
				block.shouldSideBeRendered(world, x - 1, y, z, 4), block.shouldSideBeRendered(world, x + 1, y, z, 5) };

		float f3 = 0.5F;
		float f4 = 1.0F;
		float f5 = 0.8F;
		float f6 = 0.6F;
		double d0 = 0.0D;
		double d1 = 1.0D;
		Material material = block.blockMaterial;
		int i1 = world.getBlockMetadata(x, y, z);
		double d2 = renderer.getFluidHeight(x, y, z, material);
		double d3 = renderer.getFluidHeight(x, y, z + 1, material);
		double d4 = renderer.getFluidHeight(x + 1, y, z + 1, material);
		double d5 = renderer.getFluidHeight(x + 1, y, z, material);
		double d6 = 0.0010000000474974513D;
		float f7;
		float f8;

		if (renderer.renderAllFaces || flag) {
			Icon icon = renderer.getBlockIconFromSideAndMetadata(block, 1, i1);
			//float f9 = (float) BlockFluid.getFlowDirection(world, x, y, z, Material.water);

			//if (f9 > -999.0F) {
			//	icon = renderer.getBlockIconFromSideAndMetadata(block, 2, i1);
			//}

			d2 -= d6;
			d3 -= d6;
			d4 -= d6;
			d5 -= d6;
			double d7;
			double d8;
			double d9;
			double d10;
			double d11;
			double d12;
			double d13;
			double d14;

			//if (f9 < -999.0F) {
				d8 = icon.getInterpolatedU(0.0D);
				d12 = icon.getInterpolatedV(0.0D);
				d7 = d8;
				d11 = icon.getInterpolatedV(16.0D);
				d10 = icon.getInterpolatedU(16.0D);
				d14 = d11;
				d9 = d10;
				d13 = d12;
			/*} else {
				f8 = MathHelper.sin(f9) * 0.25F;
				f7 = MathHelper.cos(f9) * 0.25F;
				d8 = icon.getInterpolatedU(8.0F + ((-f7 - f8) * 16.0F));
				d12 = icon.getInterpolatedV(8.0F + ((-f7 + f8) * 16.0F));
				d7 = icon.getInterpolatedU(8.0F + ((-f7 + f8) * 16.0F));
				d11 = icon.getInterpolatedV(8.0F + ((f7 + f8) * 16.0F));
				d10 = icon.getInterpolatedU(8.0F + ((f7 + f8) * 16.0F));
				d14 = icon.getInterpolatedV(8.0F + ((f7 - f8) * 16.0F));
				d9 = icon.getInterpolatedU(8.0F + ((f7 - f8) * 16.0F));
				d13 = icon.getInterpolatedV(8.0F + ((-f7 - f8) * 16.0F));
			}*/
				
			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			f8 = 1.0F;
			tessellator.setColorOpaque_F(f4 * f8 * f, f4 * f8 * f1, f4 * f8 * f2);
			tessellator.addVertexWithUV(x + 0, y + d2, z + 0, d8, d12);
			tessellator.addVertexWithUV(x + 0, y + d3, z + 1, d7, d11);
			tessellator.addVertexWithUV(x + 1, y + d4, z + 1, d10, d14);
			tessellator.addVertexWithUV(x + 1, y + d5, z + 0, d9, d13);

			tessellator.addVertexWithUV(x + 1, y + d5, z + 0, d9, d13);
			tessellator.addVertexWithUV(x + 1, y + d4, z + 1, d10, d14);
			tessellator.addVertexWithUV(x + 0, y + d3, z + 1, d7, d11);
			tessellator.addVertexWithUV(x + 0, y + d2, z + 0, d8, d12);
		}

		if (renderer.renderAllFaces || flag1) {
			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y - 1, z));
			float f10 = 1.0F;
			tessellator.setColorOpaque_F(f3 * f10, f3 * f10, f3 * f10);
			renderer.renderFaceYNeg(block, x, y + d6, z, renderer.getBlockIconFromSide(block, 0));
		}

		for (int j1 = 0; j1 < 4; ++j1) {
			int k1 = x;
			int l1 = z;

			if (j1 == 0) {
				l1 = z - 1;
			}

			if (j1 == 1) {
				++l1;
			}

			if (j1 == 2) {
				k1 = x - 1;
			}

			if (j1 == 3) {
				++k1;
			}

			Icon icon1 = renderer.getBlockIconFromSideAndMetadata(block, 1, i1);

			if (renderer.renderAllFaces || aboolean[j1]) {
				double d15;
				double d16;
				double d17;
				double d18;
				double d19;
				double d20;

				if (j1 == 0) {
					d15 = d2;
					d17 = d5;
					d16 = x;
					d18 = x + 1;
					d19 = z + d6;
					d20 = z + d6;
				} else if (j1 == 1) {
					d15 = d4;
					d17 = d3;
					d16 = x + 1;
					d18 = x;
					d19 = z + 1 - d6;
					d20 = z + 1 - d6;
				} else if (j1 == 2) {
					d15 = d3;
					d17 = d2;
					d16 = x + d6;
					d18 = x + d6;
					d19 = z + 1;
					d20 = z;
				} else {
					d15 = d5;
					d17 = d4;
					d16 = x + 1 - d6;
					d18 = x + 1 - d6;
					d19 = z;
					d20 = z + 1;
				}

				float f11 = icon1.getInterpolatedU(0.0D);
				f8 = icon1.getInterpolatedU(8.0D);
				f7 = icon1.getInterpolatedV((1.0D - d15) * 16.0D * 0.5D);
				float f12 = icon1.getInterpolatedV((1.0D - d17) * 16.0D * 0.5D);
				float f13 = icon1.getInterpolatedV(8.0D);
				tessellator.setBrightness(block.getMixedBrightnessForBlock(world, k1, y, l1));
				float f14 = 1.0F;

				if (j1 < 2) {
					f14 *= f5;
				} else {
					f14 *= f6;
				}

				tessellator.setColorOpaque_F(f4 * f14 * f, f4 * f14 * f1, f4 * f14 * f2);
				tessellator.addVertexWithUV(d16, y + d15, d19, f11, f7);
				tessellator.addVertexWithUV(d18, y + d17, d20, f8, f12);
				tessellator.addVertexWithUV(d18, y + 0, d20, f8, f13);
				tessellator.addVertexWithUV(d16, y + 0, d19, f11, f13);

				tessellator.addVertexWithUV(d16, y + 0, d19, f11, f13);
				tessellator.addVertexWithUV(d18, y + 0, d20, f8, f13);
				tessellator.addVertexWithUV(d18, y + d17, d20, f8, f12);
				tessellator.addVertexWithUV(d16, y + d15, d19, f11, f7);
			}

			renderer.renderMinY = d0;
			renderer.renderMaxY = d1;
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return MetaLiquids.metaLiquidModel;
	}

}
