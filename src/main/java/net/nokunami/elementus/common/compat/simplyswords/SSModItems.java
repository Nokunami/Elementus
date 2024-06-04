package net.nokunami.elementus.common.compat.simplyswords;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.config.ConfigDefaultValue;
import net.nokunami.elementus.common.config.EConfig;
import net.nokunami.elementus.common.registry.ModTiers;
import net.sweenus.simplyswords.config.Config;
import net.sweenus.simplyswords.config.ConfigDefaultValues;

import static net.nokunami.elementus.Elementus.MODID;

public class SSModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    static float steel_modifier =  Config.getFloat("steel_damageModifier", "WeaponAttributes", ConfigDefaultValues.steel_damageModifier);
    static float diarkrite_modifier = EConfig.getFloat("diarkrite_damageModifier", "WeaponAttributes", (float) ConfigDefaultValue.simply_sword_diarkrite_damageModifier);
    static float anthektite_modifier = EConfig.getFloat("anthektite_damageModifier", "WeaponAttributes", (float) ConfigDefaultValue.simply_sword_anthektite_damageModifier);
    static float diarkrite_atkSpeedAdd = EConfig.getFloat("darkrite_atkSpeedAdd", "WeaponAttributes", (float) ConfigDefaultValue.simply_sword_darkrite_atkSpeedAdd);
    static float diarkrite_atkSpeedNeg = EConfig.getFloat("darkrite_atkSpeedNeg", "WeaponAttributes", (float) ConfigDefaultValue.simply_sword_darkrite_atkSpeedNeg);
    static float anthektite_atkSpeedAdd = EConfig.getFloat("anthektite_atkSpeedAdd", "WeaponAttributes", (float) ConfigDefaultValue.simply_sword_anthektite_atkSpeedAdd);
    static float anthektite_atkSpeedNeg = EConfig.getFloat("anthektite_atkSpeedNeg", "WeaponAttributes", (float) ConfigDefaultValue.simply_sword_anthektite_atkSpeedNeg);

    static float longsword_positive_modifier = Config.getFloat("longsword_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.longsword_positiveDamageModifier);
    static float twinblade_positive_modifier = Config.getFloat("twinblade_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.twinblade_positiveDamageModifier);
    static float rapier_positive_modifier = Config.getFloat("rapier_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.rapier_positiveDamageModifier);
    static float katana_positive_modifier = Config.getFloat("katana_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.katana_positiveDamageModifier);
    static float sai_positive_modifier = Config.getFloat("sai_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.sai_positiveDamageModifier);
    static float spear_positive_modifier = Config.getFloat("spear_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.spear_positiveDamageModifier);
    static float glaive_positive_modifier = Config.getFloat("glaive_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.glaive_positiveDamageModifier);
    static float warglaive_positive_modifier = Config.getFloat("warglaive_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.warglaive_positiveDamageModifier);
    static float cutlass_positive_modifier = Config.getFloat("cutlass_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.cutlass_positiveDamageModifier);
    static float claymore_positive_modifier = Config.getFloat("claymore_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.claymore_positiveDamageModifier);
    static float greataxe_positive_modifier = Config.getFloat("greataxe_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.greataxe_positiveDamageModifier);
    static float greathammer_positive_modifier = Config.getFloat("greathammer_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.greathammer_positiveDamageModifier);
    static float chakram_positive_modifier = Config.getFloat("chakram_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.chakram_positiveDamageModifier);
    static float scythe_positive_modifier = Config.getFloat("scythe_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.scythe_positiveDamageModifier);
    static float halberd_positive_modifier = Config.getFloat("halberd_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValues.halberd_positiveDamageModifier);

    static float longsword_negative_modifier = Config.getFloat("longsword_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.longsword_negativeDamageModifier);
    static float twinblade_negative_modifier = Config.getFloat("twinblade_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.twinblade_negativeDamageModifier);
    static float rapier_negative_modifier = Config.getFloat("rapier_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.rapier_negativeDamageModifier);
    static float sai_negative_modifier = Config.getFloat("sai_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.sai_negativeDamageModifier);
    static float spear_negative_modifier = Config.getFloat("spear_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.spear_negativeDamageModifier);
    static float katana_negative_modifier = Config.getFloat("katana_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.katana_negativeDamageModifier);
    static float glaive_negative_modifier = Config.getFloat("glaive_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.glaive_negativeDamageModifier);
    static float warglaive_negative_modifier = Config.getFloat("warglaive_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.warglaive_negativeDamageModifier);
    static float cutlass_negative_modifier = Config.getFloat("cutlass_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.cutlass_negativeDamageModifier);
    static float claymore_negative_modifier = Config.getFloat("claymore_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.claymore_negativeDamageModifier);
    static float greataxe_negative_modifier = Config.getFloat("greataxe_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.greataxe_negativeDamageModifier);
    static float greathammer_negative_modifier = Config.getFloat("greathammer_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.greathammer_negativeDamageModifier);
    static float chakram_negative_modifier = Config.getFloat("chakram_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.chakram_negativeDamageModifier);
    static float scythe_negative_modifier = Config.getFloat("scythe_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.scythe_negativeDamageModifier);
    static float halberd_negative_modifier = Config.getFloat("halberd_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValues.halberd_negativeDamageModifier);

    static float longsword_attackspeed = Config.getFloat("longsword_attackSpeed", "WeaponAttributes", ConfigDefaultValues.longsword_attackSpeed);
    static float twinblade_attackspeed = Config.getFloat("twinblade_attackSpeed", "WeaponAttributes", ConfigDefaultValues.twinblade_attackSpeed);
    static float rapier_attackspeed = Config.getFloat("rapier_attackSpeed", "WeaponAttributes", ConfigDefaultValues.rapier_attackSpeed);
    static float sai_attackspeed = Config.getFloat("sai_attackSpeed", "WeaponAttributes", ConfigDefaultValues.sai_attackSpeed);
    static float spear_attackspeed = Config.getFloat("spear_attackSpeed", "WeaponAttributes", ConfigDefaultValues.spear_attackSpeed);
    static float katana_attackspeed = Config.getFloat("katana_attackSpeed", "WeaponAttributes", ConfigDefaultValues.katana_attackSpeed);
    static float glaive_attackspeed = Config.getFloat("glaive_attackSpeed", "WeaponAttributes", ConfigDefaultValues.glaive_attackSpeed);
    static float warglaive_attackspeed = Config.getFloat("warglaive_attackSpeed", "WeaponAttributes", ConfigDefaultValues.warglaive_attackSpeed);
    static float cutlass_attackspeed = Config.getFloat("cutlass_attackSpeed", "WeaponAttributes", ConfigDefaultValues.cutlass_attackSpeed);
    static float claymore_attackspeed = Config.getFloat("claymore_attackSpeed", "WeaponAttributes", ConfigDefaultValues.claymore_attackSpeed);
    static float greataxe_attackspeed = Config.getFloat("greataxe_attackSpeed", "WeaponAttributes", ConfigDefaultValues.greataxe_attackSpeed);
    static float greathammer_attackspeed = Config.getFloat("greathammer_attackSpeed", "WeaponAttributes", ConfigDefaultValues.greathammer_attackSpeed);
    static float chakram_attackspeed = Config.getFloat("chakram_attackSpeed", "WeaponAttributes", ConfigDefaultValues.chakram_attackSpeed);
    static float scythe_attackspeed = Config.getFloat("scythe_attackSpeed", "WeaponAttributes", ConfigDefaultValues.scythe_attackSpeed);
    static float halberd_attackspeed = Config.getFloat("halberd_attackSpeed", "WeaponAttributes", ConfigDefaultValues.halberd_attackSpeed);

    public static final RegistryObject<Item> STEEL_CHARKRAM = ITEMS.register("steel_chakram",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + chakram_positive_modifier - chakram_negative_modifier), chakram_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CLAYMORE = ITEMS.register("steel_claymore",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + claymore_positive_modifier - claymore_negative_modifier), claymore_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CUTLASS = ITEMS.register("steel_cutlass",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + cutlass_positive_modifier - cutlass_negative_modifier), cutlass_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_GLAIVE = ITEMS.register("steel_glaive",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + glaive_positive_modifier - glaive_negative_modifier), glaive_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_GREATAXE = ITEMS.register("steel_greataxe",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + greataxe_positive_modifier - greataxe_negative_modifier), greataxe_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_GREATHAMMER = ITEMS.register("steel_greathammer",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + greathammer_positive_modifier - greathammer_negative_modifier), greathammer_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HALBERD = ITEMS.register("steel_halberd",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + halberd_positive_modifier - halberd_negative_modifier), halberd_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_KATANA = ITEMS.register("steel_katana",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + katana_positive_modifier - katana_negative_modifier), katana_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_LONGSWORD = ITEMS.register("steel_longsword",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + longsword_positive_modifier - longsword_negative_modifier), longsword_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_RAPIER = ITEMS.register("steel_rapier",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + rapier_positive_modifier - rapier_negative_modifier), rapier_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SAI = ITEMS.register("steel_sai",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + sai_positive_modifier - sai_negative_modifier), sai_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SCYTHE = ITEMS.register("steel_scythe",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + scythe_positive_modifier - scythe_negative_modifier), scythe_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SPEAR = ITEMS.register("steel_spear",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + spear_positive_modifier - spear_negative_modifier), spear_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_TWINBLADE = ITEMS.register("steel_twinblade",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + twinblade_positive_modifier - twinblade_negative_modifier), twinblade_attackspeed,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_WARGLAIVE = ITEMS.register("steel_warglaive",
            () -> new SimplySwordItem(ModTiers.STEEL,
                    (int) (steel_modifier + warglaive_positive_modifier - warglaive_negative_modifier), warglaive_attackspeed,
                    new Item.Properties()));


    public static final RegistryObject<Item> DIARKRITE_CHARKRAM = ITEMS.register("diarkrite_chakram",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + chakram_positive_modifier - chakram_negative_modifier),
                    chakram_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_CLAYMORE = ITEMS.register("diarkrite_claymore",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + claymore_positive_modifier - claymore_negative_modifier),
                    claymore_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_CUTLASS = ITEMS.register("diarkrite_cutlass",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + cutlass_positive_modifier - cutlass_negative_modifier),
                    cutlass_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_GLAIVE = ITEMS.register("diarkrite_glaive",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + glaive_positive_modifier - glaive_negative_modifier),
                    glaive_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_GREATAXE = ITEMS.register("diarkrite_greataxe",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + greataxe_positive_modifier - greataxe_negative_modifier),
                    greataxe_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_GREATHAMMER = ITEMS.register("diarkrite_greathammer",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + greathammer_positive_modifier - greathammer_negative_modifier),
                    greathammer_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_HALBERD = ITEMS.register("diarkrite_halberd",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + halberd_positive_modifier - halberd_negative_modifier)
                    , halberd_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_KATANA = ITEMS.register("diarkrite_katana",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + katana_positive_modifier - katana_negative_modifier),
                    katana_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_LONGSWORD = ITEMS.register("diarkrite_longsword",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + longsword_positive_modifier - longsword_negative_modifier),
                    longsword_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_RAPIER = ITEMS.register("diarkrite_rapier",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + rapier_positive_modifier - rapier_negative_modifier),
                    rapier_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_SAI = ITEMS.register("diarkrite_sai",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + sai_positive_modifier - sai_negative_modifier),
                    sai_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_SCYTHE = ITEMS.register("diarkrite_scythe",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + scythe_positive_modifier - scythe_negative_modifier),
                    scythe_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_SPEAR = ITEMS.register("diarkrite_spear",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + spear_positive_modifier - spear_negative_modifier),
                    spear_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_TWINBLADE = ITEMS.register("diarkrite_twinblade",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + twinblade_positive_modifier - twinblade_negative_modifier),
                    twinblade_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_WARGLAIVE = ITEMS.register("diarkrite_warglaive",
            () -> new SimplySwordItem(ModTiers.DIARKRITE,
                    (int) (diarkrite_modifier + warglaive_positive_modifier - warglaive_negative_modifier),
                    warglaive_attackspeed + diarkrite_atkSpeedAdd - diarkrite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> ANTHEKTITE_CHARKRAM = ITEMS.register("anthektite_chakram",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + chakram_positive_modifier - chakram_negative_modifier),
                    chakram_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_CLAYMORE = ITEMS.register("anthektite_claymore",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + claymore_positive_modifier - claymore_negative_modifier),
                    claymore_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_CUTLASS = ITEMS.register("anthektite_cutlass",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + cutlass_positive_modifier - cutlass_negative_modifier),
                    cutlass_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_GLAIVE = ITEMS.register("anthektite_glaive",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + glaive_positive_modifier - glaive_negative_modifier),
                    glaive_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_GREATAXE = ITEMS.register("anthektite_greataxe",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + greataxe_positive_modifier - greataxe_negative_modifier),
                    greataxe_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_GREATHAMMER = ITEMS.register("anthektite_greathammer",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + greathammer_positive_modifier - greathammer_negative_modifier),
                    greathammer_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_HALBERD = ITEMS.register("anthektite_halberd",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + halberd_positive_modifier - halberd_negative_modifier),
                    halberd_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_KATANA = ITEMS.register("anthektite_katana",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + katana_positive_modifier - katana_negative_modifier),
                    katana_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_LONGSWORD = ITEMS.register("anthektite_longsword",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + longsword_positive_modifier - longsword_negative_modifier),
                    longsword_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_RAPIER = ITEMS.register("anthektite_rapier",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + rapier_positive_modifier - rapier_negative_modifier),
                    rapier_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_SAI = ITEMS.register("anthektite_sai",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + sai_positive_modifier - sai_negative_modifier),
                    sai_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_SCYTHE = ITEMS.register("anthektite_scythe",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + scythe_positive_modifier - scythe_negative_modifier),
                    scythe_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_SPEAR = ITEMS.register("anthektite_spear",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + spear_positive_modifier - spear_negative_modifier),
                    spear_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_TWINBLADE = ITEMS.register("anthektite_twinblade",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + twinblade_positive_modifier - twinblade_negative_modifier),
                    twinblade_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_WARGLAIVE = ITEMS.register("anthektite_warglaive",
            () -> new SimplySwordItem(ModTiers.ANTHEKTITE,
                    (int) (anthektite_modifier + warglaive_positive_modifier - warglaive_negative_modifier),
                    warglaive_attackspeed + anthektite_atkSpeedAdd - anthektite_atkSpeedNeg,
                    new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
