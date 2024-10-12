package net.nokunami.elementus.common.compat.simplyswords;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.config.simplyswords.ConfigDefaultValue;
import net.nokunami.elementus.common.config.simplyswords.SimplySwordsConfigHelper;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;

import java.util.HashMap;
import java.util.Locale;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.config.simplyswords.ConfigDefaultValue.*;

public class SSModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    protected static float steelSpeed = ModItems.steelSpeed;
    protected static float diarkriteSpeed = ModItems.diarkriteSpeed;
    protected static float anthektiteSpeed = ModItems.anthektiteSpeed;

    static double steel_modifier =  SimplySwordsConfigHelper.getDouble("steel_damageModifier", "WeaponAttributes", ConfigDefaultValue.SS_STEEL);
    static double diarkrite_modifier = SimplySwordsConfigHelper.getDouble("diarkrite_damageModifier", "WeaponAttributes", ConfigDefaultValue.SS_DIARKRITE);
    static double anthektite_modifier = SimplySwordsConfigHelper.getDouble("anthektite_damageModifier", "WeaponAttributes", ConfigDefaultValue.SS_ANTHEKTITE);
//    static double diarkrite_atkSpeedAdd = SimplySwordsConfigHelper.getDouble("darkrite_atkSpeedAdd", "WeaponAttributes", ConfigDefaultValue.SS_DIARKRITE_ATK_SPD_ADD);
//    static double diarkrite_atkSpeedNeg = SimplySwordsConfigHelper.getDouble("darkrite_atkSpeedNeg", "WeaponAttributes", ConfigDefaultValue.SS_DIARKRITE_ATK_SPD_NEG);
//    static double anthektite_atkSpeedAdd = SimplySwordsConfigHelper.getDouble("anthektite_atkSpeedAdd", "WeaponAttributes", ConfigDefaultValue.SS_ANTHEKTITE_ATK_SPD_ADD);
//    static double anthektite_atkSpeedNeg = SimplySwordsConfigHelper.getDouble("anthektite_atkSpeedNeg", "WeaponAttributes", ConfigDefaultValue.SS_ANTHEKTITE_ATK_SPD_NEG);


    static float longsword_positive_modifier = SimplySwordsConfigHelper.getFloat("longsword_positiveDamageModifier", "WeaponAttributes", longsword_positiveDamageModifier);
    static float twinblade_positive_modifier = SimplySwordsConfigHelper.getFloat("twinblade_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.twinblade_positiveDamageModifier);
    static float rapier_positive_modifier = SimplySwordsConfigHelper.getFloat("rapier_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.rapier_positiveDamageModifier);
    static float katana_positive_modifier = SimplySwordsConfigHelper.getFloat("katana_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.katana_positiveDamageModifier);
    static float sai_positive_modifier = SimplySwordsConfigHelper.getFloat("sai_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.sai_positiveDamageModifier);
    static float spear_positive_modifier = SimplySwordsConfigHelper.getFloat("spear_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.spear_positiveDamageModifier);
    static float glaive_positive_modifier = SimplySwordsConfigHelper.getFloat("glaive_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.glaive_positiveDamageModifier);
    static float warglaive_positive_modifier = SimplySwordsConfigHelper.getFloat("warglaive_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.warglaive_positiveDamageModifier);
    static float cutlass_positive_modifier = SimplySwordsConfigHelper.getFloat("cutlass_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.cutlass_positiveDamageModifier);
    static float claymore_positive_modifier = SimplySwordsConfigHelper.getFloat("claymore_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.claymore_positiveDamageModifier);
    static float greataxe_positive_modifier = SimplySwordsConfigHelper.getFloat("greataxe_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.greataxe_positiveDamageModifier);
    static float greathammer_positive_modifier = SimplySwordsConfigHelper.getFloat("greathammer_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.greathammer_positiveDamageModifier);
    static float chakram_positive_modifier = SimplySwordsConfigHelper.getFloat("chakram_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.chakram_positiveDamageModifier);
    static float scythe_positive_modifier = SimplySwordsConfigHelper.getFloat("scythe_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.scythe_positiveDamageModifier);
    static float halberd_positive_modifier = SimplySwordsConfigHelper.getFloat("halberd_positiveDamageModifier", "WeaponAttributes", ConfigDefaultValue.halberd_positiveDamageModifier);

    static float longsword_negative_modifier = SimplySwordsConfigHelper.getFloat("longsword_negativeDamageModifier", "WeaponAttributes", longsword_negativeDamageModifier);
    static float twinblade_negative_modifier = SimplySwordsConfigHelper.getFloat("twinblade_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.twinblade_negativeDamageModifier);
    static float rapier_negative_modifier = SimplySwordsConfigHelper.getFloat("rapier_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.rapier_negativeDamageModifier);
    static float sai_negative_modifier = SimplySwordsConfigHelper.getFloat("sai_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.sai_negativeDamageModifier);
    static float spear_negative_modifier = SimplySwordsConfigHelper.getFloat("spear_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.spear_negativeDamageModifier);
    static float katana_negative_modifier = SimplySwordsConfigHelper.getFloat("katana_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.katana_negativeDamageModifier);
    static float glaive_negative_modifier = SimplySwordsConfigHelper.getFloat("glaive_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.glaive_negativeDamageModifier);
    static float warglaive_negative_modifier = SimplySwordsConfigHelper.getFloat("warglaive_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.warglaive_negativeDamageModifier);
    static float cutlass_negative_modifier = SimplySwordsConfigHelper.getFloat("cutlass_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.cutlass_negativeDamageModifier);
    static float claymore_negative_modifier = SimplySwordsConfigHelper.getFloat("claymore_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.claymore_negativeDamageModifier);
    static float greataxe_negative_modifier = SimplySwordsConfigHelper.getFloat("greataxe_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.greataxe_negativeDamageModifier);
    static float greathammer_negative_modifier = SimplySwordsConfigHelper.getFloat("greathammer_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.greathammer_negativeDamageModifier);
    static float chakram_negative_modifier = SimplySwordsConfigHelper.getFloat("chakram_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.chakram_negativeDamageModifier);
    static float scythe_negative_modifier = SimplySwordsConfigHelper.getFloat("scythe_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.scythe_negativeDamageModifier);
    static float halberd_negative_modifier = SimplySwordsConfigHelper.getFloat("halberd_negativeDamageModifier", "WeaponAttributes", ConfigDefaultValue.halberd_negativeDamageModifier);

    static float longsword_attackspeed = SimplySwordsConfigHelper.getFloat("longsword_attackSpeed", "WeaponAttributes", ConfigDefaultValue.longsword_attackSpeed);
    static float twinblade_attackspeed = SimplySwordsConfigHelper.getFloat("twinblade_attackSpeed", "WeaponAttributes", ConfigDefaultValue.twinblade_attackSpeed);
    static float rapier_attackspeed = SimplySwordsConfigHelper.getFloat("rapier_attackSpeed", "WeaponAttributes", ConfigDefaultValue.rapier_attackSpeed);
    static float sai_attackspeed = SimplySwordsConfigHelper.getFloat("sai_attackSpeed", "WeaponAttributes", ConfigDefaultValue.sai_attackSpeed);
    static float spear_attackspeed = SimplySwordsConfigHelper.getFloat("spear_attackSpeed", "WeaponAttributes", ConfigDefaultValue.spear_attackSpeed);
    static float katana_attackspeed = SimplySwordsConfigHelper.getFloat("katana_attackSpeed", "WeaponAttributes", ConfigDefaultValue.katana_attackSpeed);
    static float glaive_attackspeed = SimplySwordsConfigHelper.getFloat("glaive_attackSpeed", "WeaponAttributes", ConfigDefaultValue.glaive_attackSpeed);
    static float warglaive_attackspeed = SimplySwordsConfigHelper.getFloat("warglaive_attackSpeed", "WeaponAttributes", ConfigDefaultValue.warglaive_attackSpeed);
    static float cutlass_attackspeed = SimplySwordsConfigHelper.getFloat("cutlass_attackSpeed", "WeaponAttributes", ConfigDefaultValue.cutlass_attackSpeed);
    static float claymore_attackspeed = SimplySwordsConfigHelper.getFloat("claymore_attackSpeed", "WeaponAttributes", ConfigDefaultValue.claymore_attackSpeed);
    static float greataxe_attackspeed = SimplySwordsConfigHelper.getFloat("greataxe_attackSpeed", "WeaponAttributes", ConfigDefaultValue.greataxe_attackSpeed);
    static float greathammer_attackspeed = SimplySwordsConfigHelper.getFloat("greathammer_attackSpeed", "WeaponAttributes", ConfigDefaultValue.greathammer_attackSpeed);
    static float chakram_attackspeed = SimplySwordsConfigHelper.getFloat("chakram_attackSpeed", "WeaponAttributes", ConfigDefaultValue.chakram_attackSpeed);
    static float scythe_attackspeed = SimplySwordsConfigHelper.getFloat("scythe_attackSpeed", "WeaponAttributes", ConfigDefaultValue.scythe_attackSpeed);
    static float halberd_attackspeed = SimplySwordsConfigHelper.getFloat("halberd_attackSpeed", "WeaponAttributes", ConfigDefaultValue.halberd_attackSpeed);


    public static final RegistryObject<Item> STEEL_LONGSWORD = registerSteel("longsword");
    public static final RegistryObject<Item> STEEL_TWINBLADE = registerSteel("twinblade");
    public static final RegistryObject<Item> STEEL_RAPIER = registerSteel("rapier");
    public static final RegistryObject<Item> STEEL_SAI = registerSteel("sai");
    public static final RegistryObject<Item> STEEL_SPEAR = registerSteel("spear");
    public static final RegistryObject<Item> STEEL_KATANA = registerSteel("katana");
    public static final RegistryObject<Item> STEEL_GLAIVE = registerSteel("glaive");
    public static final RegistryObject<Item> STEEL_WARGLAIVE = registerSteel("warglaive");
    public static final RegistryObject<Item> STEEL_CUTLASS = registerSteel("cutlass");
    public static final RegistryObject<Item> STEEL_CLAYMORE = registerSteel("claymore");
    public static final RegistryObject<Item> STEEL_GREATAXE = registerSteel("greataxe");
    public static final RegistryObject<Item> STEEL_GREATHAMMER = registerSteel("greathammer");
    public static final RegistryObject<Item> STEEL_CHAKRAM = registerSteel("chakram");
    public static final RegistryObject<Item> STEEL_SCYTHE = registerSteel("scythe");
    public static final RegistryObject<Item> STEEL_HALBERD = registerSteel("halberd");

    public static final RegistryObject<Item> DIARKRITE_LONGSWORD = registerDiarkrite("longsword");
    public static final RegistryObject<Item> DIARKRITE_TWINBLADE = registerDiarkrite("twinblade");
    public static final RegistryObject<Item> DIARKRITE_RAPIER = registerDiarkrite("rapier");
    public static final RegistryObject<Item> DIARKRITE_SAI = registerDiarkrite("sai");
    public static final RegistryObject<Item> DIARKRITE_SPEAR = registerDiarkrite("spear");
    public static final RegistryObject<Item> DIARKRITE_KATANA = registerDiarkrite("katana");
    public static final RegistryObject<Item> DIARKRITE_GLAIVE = registerDiarkrite("glaive");
    public static final RegistryObject<Item> DIARKRITE_WARGLAIVE = registerDiarkrite("warglaive");
    public static final RegistryObject<Item> DIARKRITE_CUTLASS = registerDiarkrite("cutlass");
    public static final RegistryObject<Item> DIARKRITE_CLAYMORE = registerDiarkrite("claymore");
    public static final RegistryObject<Item> DIARKRITE_GREATAXE = registerDiarkrite("greataxe");
    public static final RegistryObject<Item> DIARKRITE_GREATHAMMER = registerDiarkrite("greathammer");
    public static final RegistryObject<Item> DIARKRITE_CHAKRAM = registerDiarkrite("chakram");
    public static final RegistryObject<Item> DIARKRITE_SCYTHE = registerDiarkrite("scythe");
    public static final RegistryObject<Item> DIARKRITE_HALBERD = registerDiarkrite("halberd");

    public static final RegistryObject<Item> ANTHEKTITE_LONGSWORD = registerAnthektite("longsword");
    public static final RegistryObject<Item> ANTHEKTITE_TWINBLADE = registerAnthektite("twinblade");
    public static final RegistryObject<Item> ANTHEKTITE_RAPIER = registerAnthektite("rapier");
    public static final RegistryObject<Item> ANTHEKTITE_SAI = registerAnthektite("sai");
    public static final RegistryObject<Item> ANTHEKTITE_SPEAR = registerAnthektite("spear");
    public static final RegistryObject<Item> ANTHEKTITE_KATANA = registerAnthektite("katana");
    public static final RegistryObject<Item> ANTHEKTITE_GLAIVE = registerAnthektite("glaive");
    public static final RegistryObject<Item> ANTHEKTITE_WARGLAIVE = registerAnthektite("warglaive");
    public static final RegistryObject<Item> ANTHEKTITE_CUTLASS = registerAnthektite("cutlass");
    public static final RegistryObject<Item> ANTHEKTITE_CLAYMORE = registerAnthektite("claymore");
    public static final RegistryObject<Item> ANTHEKTITE_GREATAXE = registerAnthektite("greataxe");
    public static final RegistryObject<Item> ANTHEKTITE_GREATHAMMER = registerAnthektite("greathammer");
    public static final RegistryObject<Item> ANTHEKTITE_CHAKRAM = registerAnthektite("chakram");
    public static final RegistryObject<Item> ANTHEKTITE_SCYTHE = registerAnthektite("scythe");
    public static final RegistryObject<Item> ANTHEKTITE_HALBERD = registerAnthektite("halberd");


    private static RegistryObject<Item> registerSteel(String id) {
        return ITEMS.register(ModTiers.STEEL.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
                new SimplySwordItem(ModTiers.STEEL, (int) (steel_modifier + getDamageMod(id)),
                        getAttackSpeedMod(id) + steelSpeed, new Item.Properties()));
    }

    private static RegistryObject<Item> registerDiarkrite(String id) {
        return ITEMS.register(ModTiers.DIARKRITE.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
                new SimplySwordItem(ModTiers.DIARKRITE, (int) (diarkrite_modifier + getDamageMod(id)),
                        getAttackSpeedMod(id) + diarkriteSpeed, new Item.Properties().fireResistant()));
    }

    private static RegistryObject<Item> registerAnthektite(String id) {
        return ITEMS.register(ModTiers.ANTHEKTITE.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
                new SimplySwordItem(ModTiers.ANTHEKTITE, (int) (anthektite_modifier + getDamageMod(id)),
                        getAttackSpeedMod(id) + anthektiteSpeed, new Item.Properties().fireResistant()));
    }


    public static int getDamageMod(String weaponType) {
        return ATK_DAMAGE.get(weaponType);
    }

    public static float getAttackSpeedMod(String weaponType) {
        return ATK_SPEED.get(weaponType);
    }

    private static final HashMap<String, Integer> ATK_DAMAGE = new HashMap<>();
    private static final HashMap<String, Float> ATK_SPEED = new HashMap<>();

    static {
        ATK_DAMAGE.put("chakram", (int) (chakram_positive_modifier - chakram_negative_modifier));
        ATK_SPEED.put("chakram", chakram_attackspeed);

        ATK_DAMAGE.put("claymore", (int) (claymore_positive_modifier - claymore_negative_modifier));
        ATK_SPEED.put("claymore", claymore_attackspeed);

        ATK_DAMAGE.put("cutlass", (int) (cutlass_positive_modifier - cutlass_negative_modifier));
        ATK_SPEED.put("cutlass", cutlass_attackspeed);

        ATK_DAMAGE.put("glaive", (int) (glaive_positive_modifier - glaive_negative_modifier));
        ATK_SPEED.put("glaive", glaive_attackspeed);

        ATK_DAMAGE.put("greataxe", (int) (greataxe_positive_modifier - greataxe_negative_modifier));
        ATK_SPEED.put("greataxe", greataxe_attackspeed);

        ATK_DAMAGE.put("greathammer", (int) (greathammer_positive_modifier - greathammer_negative_modifier));
        ATK_SPEED.put("greathammer", greathammer_attackspeed);

        ATK_DAMAGE.put("halberd", (int) (halberd_positive_modifier - halberd_negative_modifier));
        ATK_SPEED.put("halberd", halberd_attackspeed);

        ATK_DAMAGE.put("katana", (int) (katana_positive_modifier - katana_negative_modifier));
        ATK_SPEED.put("katana", katana_attackspeed);

        ATK_DAMAGE.put("longsword", (int) (longsword_positive_modifier - longsword_negative_modifier));
        ATK_SPEED.put("longsword", longsword_attackspeed);

        ATK_DAMAGE.put("rapier", (int) (rapier_positive_modifier - rapier_negative_modifier));
        ATK_SPEED.put("rapier", rapier_attackspeed);

        ATK_DAMAGE.put("sai", (int) (sai_positive_modifier - sai_negative_modifier));
        ATK_SPEED.put("sai", twinblade_attackspeed);

        ATK_DAMAGE.put("scythe", (int) (scythe_positive_modifier - scythe_negative_modifier));
        ATK_SPEED.put("scythe", scythe_attackspeed);

        ATK_DAMAGE.put("spear", (int) (spear_positive_modifier - spear_negative_modifier));
        ATK_SPEED.put("spear", spear_attackspeed);

        ATK_DAMAGE.put("twinblade", (int) (twinblade_positive_modifier - twinblade_negative_modifier));
        ATK_SPEED.put("twinblade", twinblade_attackspeed);

        ATK_DAMAGE.put("warglaive", (int) (warglaive_positive_modifier - warglaive_negative_modifier));
        ATK_SPEED.put("warglaive", warglaive_attackspeed);
    }

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
