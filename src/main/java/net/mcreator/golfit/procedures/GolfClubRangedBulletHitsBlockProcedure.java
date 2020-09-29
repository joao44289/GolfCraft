package net.mcreator.golfit.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.golfit.block.GolfHoleBlock;
import net.mcreator.golfit.block.GolfBallBlockBlock;
import net.mcreator.golfit.GolfItModElements;

import java.util.Map;
import java.util.Collections;

@GolfItModElements.ModElement.Tag
public class GolfClubRangedBulletHitsBlockProcedure extends GolfItModElements.ModElement {
	public GolfClubRangedBulletHitsBlockProcedure(GolfItModElements instance) {
		super(instance, 9);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GolfClubRangedBulletHitsBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure GolfClubRangedBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure GolfClubRangedBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure GolfClubRangedBulletHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GolfClubRangedBulletHitsBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == GolfHoleBlock.block.getDefaultState().getBlock())) {
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"/title @a actionbar {\"text\":\"Congratulations!\", \"bold\":true, \"color\":\"red\"}");
			}
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GolfBallBlockBlock.block.getDefaultState(), 3);
		((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag().putDouble("blockX", x);
		((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag().putDouble("blockY", y);
		((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag().putDouble("blockZ", z);
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate((x - 3), y, z);
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation((x - 3), y, z, _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
		}
	}
}
