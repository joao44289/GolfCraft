package net.mcreator.golfit.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.golfit.item.WedgeClubItem;
import net.mcreator.golfit.item.PutterClubItem;
import net.mcreator.golfit.item.GolfClubRangedItem;
import net.mcreator.golfit.block.GolfHoleBlock;
import net.mcreator.golfit.block.GolfBallBlockBlock;
import net.mcreator.golfit.GolfItModVariables;
import net.mcreator.golfit.GolfItModElements;

import java.util.Map;
import java.util.Collections;

@GolfItModElements.ModElement.Tag
public class GolfClubSandBulletHitsBlockProcedure extends GolfItModElements.ModElement {
	public GolfClubSandBulletHitsBlockProcedure(GolfItModElements instance) {
		super(instance, 13);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GolfClubSandBulletHitsBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure GolfClubSandBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure GolfClubSandBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure GolfClubSandBulletHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GolfClubSandBulletHitsBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		GolfItModVariables.MapVariables.get(world).X = (double) (-1);
		GolfItModVariables.MapVariables.get(world).syncData(world);
		GolfItModVariables.isHole = (boolean) (false);
		for (int index0 = 0; index0 < (int) (2); index0++) {
			GolfItModVariables.MapVariables.get(world).Y = (double) (-1);
			GolfItModVariables.MapVariables.get(world).syncData(world);
			for (int index1 = 0; index1 < (int) (2); index1++) {
				GolfItModVariables.MapVariables.get(world).Z = (double) (-1);
				GolfItModVariables.MapVariables.get(world).syncData(world);
				for (int index2 = 0; index2 < (int) (2); index2++) {
					if (((world.getBlockState(new BlockPos((int) (x + (GolfItModVariables.MapVariables.get(world).X)),
							(int) (y + (GolfItModVariables.MapVariables.get(world).Y)), (int) (z + (GolfItModVariables.MapVariables.get(world).Z)))))
									.getBlock() == GolfHoleBlock.block.getDefaultState().getBlock())) {
						GolfItModVariables.isHole = (boolean) (true);
					}
					GolfItModVariables.MapVariables.get(world).Z = (double) ((GolfItModVariables.MapVariables.get(world).Z) + 1);
					GolfItModVariables.MapVariables.get(world).syncData(world);
				}
				GolfItModVariables.MapVariables.get(world).Y = (double) ((GolfItModVariables.MapVariables.get(world).Y) + 1);
				GolfItModVariables.MapVariables.get(world).syncData(world);
			}
			GolfItModVariables.MapVariables.get(world).X = (double) ((GolfItModVariables.MapVariables.get(world).X) + 1);
			GolfItModVariables.MapVariables.get(world).syncData(world);
		}
		if (((GolfItModVariables.isHole) == (true))) {
			GolfItModVariables.movement = (boolean) (false);
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).clearActivePotions();
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"title @s title {\"text\":\"Congratulations!\",\"bold\":true,\"color\":\"dark_red\"}");
				}
			}
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"scoreboard players set @s Score 0");
				}
			}
		} else {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WATER.getDefaultState().getBlock())) {
				{
					Entity _ent = entity;
					if (!_ent.world.isRemote && _ent.world.getServer() != null) {
						_ent.world.getServer().getCommandManager().handleCommand(
								_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
								"title @s title {\"text\":\"Your ball drowned!\",\"bold\":true,\"color\":\"blue\"}");
					}
				}
			} else {
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())) {
					{
						Entity _ent = entity;
						if (!_ent.world.isRemote && _ent.world.getServer() != null) {
							_ent.world.getServer().getCommandManager().handleCommand(
									_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
									"title @s title {\"text\":\"You got burned!\",\"bold\":true,\"color\":\"gold\"}");
						}
					}
					{
						Entity _ent = entity;
						if (!_ent.world.isRemote && _ent.world.getServer() != null) {
							_ent.world.getServer().getCommandManager().handleCommand(
									_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4), "scoreboard players add @s Score 1");
						}
					}
				} else {
					if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.PODZOL.getDefaultState()
							.getBlock())) {
						{
							Entity _ent = entity;
							if (!_ent.world.isRemote && _ent.world.getServer() != null) {
								_ent.world.getServer().getCommandManager().handleCommand(
										_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
										"title @s title {\"text\":\"Out of Bounds!\",\"bold\":true,\"color\":\"black\"}");
							}
						}
					} else {
						if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.GRASS_BLOCK.getDefaultState()
								.getBlock())) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GolfBallBlockBlock.block.getDefaultState(), 3);
							{
								Entity _ent = entity;
								_ent.setPositionAndUpdate(x, (y + 1), z);
								if (_ent instanceof ServerPlayerEntity) {
									((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, (y + 1), z, _ent.rotationYaw, _ent.rotationPitch,
											Collections.emptySet());
								}
							}
							GolfItModVariables.movement = (boolean) (true);
							entity.getPersistentData().putDouble("blockX", x);
							entity.getPersistentData().putDouble("blockY", y);
							entity.getPersistentData().putDouble("blockZ", z);
							if (((GolfItModVariables.isIron) == (true))) {
								if (entity instanceof LivingEntity) {
									ItemStack _setstack = new ItemStack(GolfClubRangedItem.block, (int) (1));
									_setstack.setCount((int) 1);
									((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
									if (entity instanceof ServerPlayerEntity)
										((ServerPlayerEntity) entity).inventory.markDirty();
								}
								GolfItModVariables.isIron = (boolean) (false);
							} else {
								if (((GolfItModVariables.isWedge) == (true))) {
									if (entity instanceof LivingEntity) {
										ItemStack _setstack = new ItemStack(WedgeClubItem.block, (int) (1));
										_setstack.setCount((int) 1);
										((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
										if (entity instanceof ServerPlayerEntity)
											((ServerPlayerEntity) entity).inventory.markDirty();
									}
									GolfItModVariables.isWedge = (boolean) (false);
								} else {
									if (((GolfItModVariables.isPutter) == (true))) {
										if (entity instanceof LivingEntity) {
											ItemStack _setstack = new ItemStack(PutterClubItem.block, (int) (1));
											_setstack.setCount((int) 1);
											((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
											if (entity instanceof ServerPlayerEntity)
												((ServerPlayerEntity) entity).inventory.markDirty();
										}
										GolfItModVariables.isPutter = (boolean) (false);
									}
								}
							}
						} else {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GolfBallBlockBlock.block.getDefaultState(), 3);
							{
								Entity _ent = entity;
								_ent.setPositionAndUpdate(x, (y + 1), z);
								if (_ent instanceof ServerPlayerEntity) {
									((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, (y + 1), z, _ent.rotationYaw, _ent.rotationPitch,
											Collections.emptySet());
								}
							}
							entity.getPersistentData().putDouble("blockX", x);
							entity.getPersistentData().putDouble("blockY", y);
							entity.getPersistentData().putDouble("blockZ", z);
							GolfItModVariables.movement = (boolean) (true);
						}
					}
				}
			}
		}
	}
}
