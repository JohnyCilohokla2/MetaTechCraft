package com.metatechcraft.core.proxy;

import net.minecraftforge.client.MinecraftForgeClient;

import com.metatechcraft.block.MetaBlocks;
import com.metatechcraft.mod.MetaTechCraft;
import com.metatechcraft.multientity.InfernosMultiBlock;
import com.metatechcraft.multientity.InfernosMultiEntity;
import com.metatechcraft.multientity.InfernosMultiRenderer;
import com.metatechcraft.tileentity.InfuserTopTileEntity;
import com.metatechcraft.tileentity.renderers.InfuserRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void initizeRendering() {
		MetaTechCraft.infuserRendererId = RenderingRegistry.getNextAvailableRenderId();
		InfuserRenderer infuserRenderer = new InfuserRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(InfuserTopTileEntity.class, infuserRenderer);
		MinecraftForgeClient.registerItemRenderer(MetaBlocks.infuserTopBlock.blockID, infuserRenderer);
		
		MetaTechCraft.infernosRendererId = RenderingRegistry.getNextAvailableRenderId();
		InfernosMultiRenderer infernosMultiRenderer = new InfernosMultiRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(InfernosMultiEntity.class, infernosMultiRenderer);

	}

}
