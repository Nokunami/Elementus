package net.nokunami.elementus.common.entity.projectile;

//public class AnthektiteSlasht extends AbstractHurtingProjectile {
//    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(AnthektiteSlasht.class, EntityDataSerializers.OPTIONAL_UUID);
//    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(AnthektiteSlasht.class, EntityDataSerializers.INT);
//    public static final Map<Integer, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
//        map.put(0, modLoc("textures/entity/projectiles/scythe/scythe_0.png"));
//        map.put(1, modLoc("textures/entity/projectiles/scythe/scythe_1.png"));
//        map.put(2, modLoc("textures/entity/projectiles/scythe/scythe_2.png"));
//        map.put(3, modLoc("textures/entity/projectiles/scythe/scythe_3.png"));
//        map.put(4, modLoc("textures/entity/projectiles/scythe/scythe_4.png"));
//        map.put(5, modLoc("textures/entity/projectiles/scythe/scythe_5.png"));
//        map.put(6, modLoc("textures/entity/projectiles/scythe/scythe_6.png"));
//        map.put(7, modLoc("textures/entity/projectiles/scythe/scythe_7.png"));
//    });
//    private ItemStack weapon = new ItemStack(ModItems.ElementusItems.ANTHEKTITE_CHARGE_BLADE.get());
//    private float damage;
//    private int lifespan;
//    private int totallife;
//
//    public AnthektiteSlasht(EntityType<? extends AbstractHurtingProjectile> p_i50173_1_, Level p_i50173_2_) {
//        super(p_i50173_1_, p_i50173_2_);
//        this.damage = 7.5F;
//        this.lifespan = 0;
//        this.totallife = 60;
//    }
//
//    public AnthektiteSlasht(ItemStack itemStack, Level world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
//        super(ModEntityType.ANTHEKTITE_SLASH.get(), x, y, z, xSpeed, ySpeed, zSpeed, world);
//        this.weapon = itemStack;
//    }
//
//    public AnthektiteSlasht(Level world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
//        super(ModEntityType.ANTHEKTITE_SLASH.get(), x, y, z, xSpeed, ySpeed, zSpeed, world);
//    }
//
//    public ResourceLocation getResourceLocation() {
//        return TEXTURE_BY_TYPE.getOrDefault(this.getAnimation(), TEXTURE_BY_TYPE.get(0));
//    }
//
//    public int getAnimation() {
//        return this.entityData.get(DATA_TYPE_ID);
//    }
//
//    public void setAnimation(int pType) {
//        this.entityData.set(DATA_TYPE_ID, pType);
//    }
//
//    public float getDamage() {
//        return this.damage;
//    }
//
//    public void setDamage(float damage) {
//        this.damage = damage;
//    }
//
//    public int getTotallife() {
//        return totallife;
//    }
//
//    public void setTotallife(int totallife) {
//        this.totallife = totallife;
//    }
//
//    public int getLifespan() {
//        return lifespan;
//    }
//
//    public void setLifespan(int lifespan) {
//        this.lifespan = lifespan;
//    }
//
//    protected void defineSynchedData() {
//        this.entityData.define(OWNER_UNIQUE_ID, Optional.empty());
//        this.entityData.define(DATA_TYPE_ID, 0);
//    }
//
//    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
//        super.readAdditionalSaveData(compound);
//        UUID uuid;
//        if (compound.hasUUID("Owner")) {
//            uuid = compound.getUUID("Owner");
//        } else {
//            String s = compound.getString("Owner");
//            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
//        }
//
//        if (uuid != null) {
//            try {
//                this.setOwnerId(uuid);
//            } catch (Throwable ignored) {
//            }
//        }
//        this.setAnimation(compound.getInt("Animation"));
//
//        if (compound.contains("Damage")) {
//            this.setLifespan(compound.getInt("Damage"));
//        }
//        if (compound.contains("Lifespan")) {
//            this.setLifespan(compound.getInt("Lifespan"));
//        }
//        if (compound.contains("TotalLife")) {
//            this.setTotallife(compound.getInt("TotalLife"));
//        }
//
//    }
//
//    public void addAdditionalSaveData(CompoundTag compound) {
//        super.addAdditionalSaveData(compound);
//        if (this.getOwnerId() != null) {
//            compound.putUUID("Owner", this.getOwnerId());
//        }
//        compound.putInt("Animation", this.getAnimation());
//        compound.putFloat("Damage", this.getDamage());
//        compound.putInt("Lifespan", this.getLifespan());
//        compound.putInt("TotalLife", this.getTotallife());
//    }
//
//    public LivingEntity getTrueOwner() {
//        try {
//            UUID uuid = this.getOwnerId();
//            return uuid == null ? null : /*EntityFinder.getLivingEntityByUuiD(uuid)*/ this.level().getPlayerByUUID(uuid);
//        } catch (IllegalArgumentException illegalargumentexception) {
//            return null;
//        }
//    }
//
//    @Nullable
//    public UUID getOwnerId() {
//        return this.entityData.get(OWNER_UNIQUE_ID).orElse(null);
//    }
//
//    public void setOwnerId(@Nullable UUID p_184754_1_) {
//        this.entityData.set(OWNER_UNIQUE_ID, Optional.ofNullable(p_184754_1_));
//    }
//
//    public void tick() {
//        super.tick();
//        if (this.lifespan < getTotallife()){
//            ++this.lifespan;
//        } else {
//            this.discard();
//        }
//        if (this.getAnimation() < 7) {
//            this.setAnimation(this.getAnimation() + 1);
//        } else {
//            this.setAnimation(0);
//        }
//        List<Entity> targets = new ArrayList<>();
//        for (Entity entity : this.level().getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(0.5F))) {
//            if (this.getTrueOwner() != null) {
//                if (entity != this.getTrueOwner() && !MobUtil.areAllies(entity, this.getTrueOwner()) && entity != this.getTrueOwner().getVehicle()) {
//                    targets.add(entity);
//                }
//            } else {
//                targets.add(entity);
//            }
//        }
//        if (ItemConfig.AnthektiteSlashBreaks.get()) {
//            AABB aabb = this.getBoundingBox().inflate(0.2D);
//
//            for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
//                BlockState blockstate = this.level().getBlockState(blockpos);
//                if (blockstate.is(BlockTags.MINEABLE_WITH_HOE) || BlockFinder.isScytheBreak(blockstate)) {
//                    ItemStack itemStack = this.weapon;
//                    if (this.weapon == null || this.weapon.isEmpty()){
//                        itemStack = new ItemStack(ModItems.DEATH_SCYTHE.get());
//                    }
//                    BlockFinder.breakBlock(this.level(), blockpos, itemStack, this);
//                }
//            }
//        }
//        if (!targets.isEmpty()){
//            for (Entity entity: targets){
//                if (MobUtil.validEntity(entity)) {
//                    float f = this.getDamage();
//                    if (this.getTrueOwner() != null) {
//                        if (entity instanceof LivingEntity) {
//                            f += EnchantmentHelper.getDamageBonus(this.weapon, ((LivingEntity) entity).getMobType());
//                        }
//                        if (this.getTrueOwner() instanceof Player player) {
//                            boolean attack = entity.hurt(entity.damageSources().playerAttack(player), f);
//                            if (entity instanceof EnderDragon enderDragonEntity){
//                                attack = enderDragonEntity.hurt(entity.damageSources().playerAttack(player), f);
//                            }
//                        } else {
//                            entity.hurt(entity.damageSources().mobAttack(this.getTrueOwner()), f);
//                        }
//                    } else {
//                        entity.hurt(entity.damageSources().generic(), f);
//                    }
//                }
//            }
//        }
//    }
//
//    protected void onHitBlock(BlockHitResult p_230299_1_) {
//        super.onHitBlock(p_230299_1_);
//        this.discard();
//    }
//
//    public boolean isOnFire() {
//        return false;
//    }
//
//    public boolean isPickable() {
//        return false;
//    }
//
//    public boolean hurt(DamageSource pSource, float pAmount) {
//        return false;
//    }
//
//    protected ParticleOptions getTrailParticle() {
//        return ParticleTypes.CRIT;
//    }
//
//    @Override
//    public Packet<ClientGamePacketListener> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
//}
