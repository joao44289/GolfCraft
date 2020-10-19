package net.mcreator.golfit.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.golfit.GolfItModElements;

import java.util.Map;

@GolfItModElements.ModElement.Tag
public class GolfClubRangedCanUseRangedItemProcedure extends GolfItModElements.ModElement {
	public GolfClubRangedCanUseRangedItemProcedure(GolfItModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GolfClubRangedCanUseRangedItem!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GolfClubRangedCanUseRangedItem!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		world.setBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("blockX")),
				(int) (entity.getPersistentData().getDouble("blockY")), (int) (entity.getPersistentData().getDouble("blockZ"))),
				Blocks.AIR.getDefaultState(), 3);
	}
}
