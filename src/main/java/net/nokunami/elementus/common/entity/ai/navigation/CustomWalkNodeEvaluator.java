package net.nokunami.elementus.common.entity.ai.navigation;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.pathfinder.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class CustomWalkNodeEvaluator extends WalkNodeEvaluator {
    private final Object2BooleanMap<AABB> collisionCache = new Object2BooleanOpenHashMap<>();

    @Override
    public void prepare(PathNavigationRegion level, Mob mob) {
        super.prepare(level, mob);
    }

    private static boolean doesBlockHavePartialCollision(BlockPathTypes pBlockPathType) {
      return pBlockPathType == BlockPathTypes.FENCE || pBlockPathType == BlockPathTypes.DOOR_WOOD_CLOSED || pBlockPathType == BlockPathTypes.DOOR_IRON_CLOSED;
   }

   private boolean canReachWithoutCollision(Node pNode) {
      AABB aabb = this.mob.getBoundingBox();
      Vec3 vec3 = new Vec3((double)pNode.x - this.mob.getX() + aabb.getXsize() / 2.0D, (double)pNode.y - this.mob.getY() + aabb.getYsize() / 2.0D, (double)pNode.z - this.mob.getZ() + aabb.getZsize() / 2.0D);
      int i = Mth.ceil(vec3.length() / aabb.getSize());
      vec3 = vec3.scale(1.0F / (float)i);

      for(int j = 1; j <= i; ++j) {
         aabb = aabb.move(vec3);
         if (this.hasCollisions(aabb)) {
            return false;
         }
      }

      return true;
   }

   @Override
   @Nullable
   protected Node findAcceptedNode(int pX, int pY, int pZ, int verticalDeltaLimit, double nodeFloorLevel, Direction direction, BlockPathTypes pathType) {
      Node node = null;
      BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
      double d0 = getFloorLevel(mutableBlockPos.set(pX, pY, pZ));
      if (d0 - nodeFloorLevel > getMobJumpHeight()) {
         return null;
      } else {
         BlockPathTypes pathTypes = getCachedBlockType(mob, pX, pY, pZ);
         float f = mob.getPathfindingMalus(pathTypes);
         double halfWidth = mob.getBbWidth() / 2.0D;
         if (f >= 0.0F) {
            node = getNodeAndUpdateCostToMax(pX, pY, pZ, pathTypes, f);
         }

         if (doesBlockHavePartialCollision(pathType) && node != null && node.costMalus >= 0.0F && !canReachWithoutCollision(node)) {
            node = null;
         }

         if (pathTypes != BlockPathTypes.WALKABLE && (!isAmphibious() || pathTypes != BlockPathTypes.WATER)) {
            if ((node == null || node.costMalus < 0.0F) && verticalDeltaLimit > 0 && (pathTypes != BlockPathTypes.FENCE || canWalkOverFences()) && pathTypes != BlockPathTypes.UNPASSABLE_RAIL && pathTypes != BlockPathTypes.TRAPDOOR && pathTypes != BlockPathTypes.POWDER_SNOW) {
               node = findAcceptedNode(pX, pY + 1, pZ, verticalDeltaLimit - 1, nodeFloorLevel, direction, pathType);
               if (node != null && (node.type == BlockPathTypes.OPEN || node.type == BlockPathTypes.WALKABLE) && mob.getBbWidth() < 1.0F) {
                  double directionStepX = (double)(pX - direction.getStepX()) + 0.5D;
                  double directionStepZ = (double)(pZ - direction.getStepZ()) + 0.5D;
                  AABB aabb = new AABB(directionStepX - halfWidth,
                          getFloorLevel(
                                  mutableBlockPos.set(
                                          directionStepX, pY + 1,
                                          directionStepZ)) + 0.001D,
                          directionStepZ - halfWidth,
                          directionStepX + halfWidth,
                          getGolemHeight(mob) + getFloorLevel(mutableBlockPos.set(node.x, node.y, node.z)) - 0.002D, directionStepZ + halfWidth);
                  if (hasCollisions(aabb)) {
                     node = null;
                  }
               }
            }

            if (!isAmphibious() && pathTypes == BlockPathTypes.WATER && !canFloat()) {
               if (getCachedBlockType(mob, pX, pY - 1, pZ) != BlockPathTypes.WATER) {
                  return node;
               }

               while(pY > mob.level().getMinBuildHeight()) {
                  --pY;
                  pathTypes = getCachedBlockType(mob, pX, pY, pZ);
                  if (pathTypes != BlockPathTypes.WATER) {
                     return node;
                  }

                  node = getNodeAndUpdateCostToMax(pX, pY, pZ, pathTypes, mob.getPathfindingMalus(pathTypes));
               }
            }

            if (pathTypes == BlockPathTypes.OPEN) {
               int j = 0;
               int i = pY;

               while(pathTypes == BlockPathTypes.OPEN) {
                  --pY;
                  if (pY < mob.level().getMinBuildHeight()) {
                     return getBlockedNode(pX, i, pZ);
                  }

                  if (j++ >= mob.getMaxFallDistance()) {
                     return getBlockedNode(pX, pY, pZ);
                  }

                  pathTypes = getCachedBlockType(mob, pX, pY, pZ);
                  f = mob.getPathfindingMalus(pathTypes);
                  if (pathTypes != BlockPathTypes.OPEN && f >= 0.0F) {
                     node = getNodeAndUpdateCostToMax(pX, pY, pZ, pathTypes, f);
                     break;
                  }

                  if (f < 0.0F) {
                     return getBlockedNode(pX, pY, pZ);
                  }
               }
            }

            if (doesBlockHavePartialCollision(pathTypes) && node == null) {
               node = getNode(pX, pY, pZ);
               node.closed = true;
               node.type = pathTypes;
               node.costMalus = pathTypes.getMalus();
            }

            return node;
         } else {
            return node;
         }
      }
   }

   private double getMobJumpHeight() {
      return Math.max(1.125D, this.mob.getStepHeight());
   }

   private Node getNodeAndUpdateCostToMax(int pX, int pY, int pZ, BlockPathTypes pType, float pCostMalus) {
      Node node = this.getNode(pX, pY, pZ);
      node.type = pType;
      node.costMalus = Math.max(node.costMalus, pCostMalus);
      return node;
   }

   private Node getBlockedNode(int pX, int pY, int pZ) {
      Node node = this.getNode(pX, pY, pZ);
      node.type = BlockPathTypes.BLOCKED;
      node.costMalus = -1.0F;
      return node;
   }

   private boolean hasCollisions(AABB pBoundingBox) {
      return this.collisionCache.computeIfAbsent(pBoundingBox, (p_192973_) -> !this.level.noCollision(this.mob, pBoundingBox));
   }

   private double getGolemHeight(Entity entity) {
        double height = entity.getBbHeight();
        return entity.isCrouching() ? height : height - 1;
   }
}