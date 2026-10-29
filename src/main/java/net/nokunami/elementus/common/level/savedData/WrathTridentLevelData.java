package net.nokunami.elementus.common.level.savedData;

//public class WrathTridentLevelData extends SavedData {
//    final ServerLevel world;
//    private final Map<UUID, OwnedTridents> tridents = new HashMap<>();
//
//    @Override
//    public CompoundTag save(CompoundTag tag) {
////        return null;
//        if (!this.tridents.isEmpty()) {
//            NbtList ownersNbt = new NbtList();
//            this.tridents.forEach((ownerUuid, tridents) -> {
//                if (!tridents.isEmpty()) {
//                    NbtCompound ownerNbt = new NbtCompound();
//                    ownerNbt.putUuid("owner_uuid", ownerUuid);
//                    tridents.toTag(ownerNbt);
//                    ownersNbt.add(ownerNbt);
//                }
//            });
//            tag.put("trident_owners", ownersNbt);
//        }
//        return tag;
//    }
//}