package net.mcreator.golfit.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.golfit.block.GolfBallBlockBlock;
import net.mcreator.golfit.GolfItModElements;

import java.util.Map;

@GolfItModElements.ModElement.Tag
public class GolfClubRightClickedOnBlockProcedure extends GolfItModElements.ModElement {
	public GolfClubRightClickedOnBlockProcedure(GolfItModElements instance) {
		super(instance, 6);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GolfClubRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure GolfClubRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure GolfClubRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure GolfClubRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GolfClubRightClickedOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GolfBallBlockBlock.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			if (((entity.getHorizontalFacing()) == Direction.NORTH)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), GolfBallBlockBlock.block.getDefaultState(), 3);
			}
			if (((entity.getHorizontalFacing()) == Direction.EAST)) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), GolfBallBlockBlock.block.getDefaultState(), 3);
			}
			if (((entity.getHorizontalFacing()) == Direction.SOUTH)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), GolfBallBlockBlock.block.getDefaultState(), 3);
			}
			if (((entity.getHorizontalFacing()) == Direction.WEST)) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), GolfBallBlockBlock.block.getDefaultState(), 3);
			}
		}
	}
}
