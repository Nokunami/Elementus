package net.nokunami.elementus.common.compat.simplyswords;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.config.SSConfig;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;

import java.util.HashMap;
import java.util.Locale;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.registry.ModItems.*;

public class SSModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    static int steel_modifier = SSConfig.steelDamage;
    static int diarkrite_modifier = SSConfig.diarkriteDamage;
    static int anthektite_modifier = SSConfig.anthektiteDamage;

    static int longsword_positive_modifier = SSConfig.longswordDamageModifier;
    static int twinblade_positive_modifier = SSConfig.twinbladeDamageModifier;
    static int rapier_positive_modifier = SSConfig.rapierDamageModifier;
    static int katana_positive_modifier = SSConfig.katanaDamageModifier;
    static int sai_positive_modifier = SSConfig.saiDamageModifier;
    static int spear_positive_modifier = SSConfig.spearDamageModifier;
    static int glaive_positive_modifier = SSConfig.glaiveDamageModifier;
    static int warglaive_positive_modifier = SSConfig.warglaiveDamageModifier;
    static int cutlass_positive_modifier = SSConfig.cutlassDamageModifier;
    static int claymore_positive_modifier = SSConfig.claymoreDamageModifier;
    static int greataxe_positive_modifier = SSConfig.greataxeDamageModifier;
    static int greathammer_positive_modifier = SSConfig.greathammerDamageModifier;
    static int chakram_positive_modifier = SSConfig.chakramDamageModifier;
    static int scythe_positive_modifier = SSConfig.scytheDamageModifier;
    static int halberd_positive_modifier = SSConfig.halberdDamageModifier;

    static float longsword_attackspeed = (float) SSConfig.longswordAttackSpeed;
    static float twinblade_attackspeed = (float) SSConfig.twinbladeAttackSpeed;
    static float rapier_attackspeed = (float) SSConfig.rapierAttackSpeed;
    static float sai_attackspeed = (float) SSConfig.saiAttackSpeed;
    static float spear_attackspeed = (float) SSConfig.spearAttackSpeed;
    static float katana_attackspeed = (float) SSConfig.katanaAttackSpeed;
    static float glaive_attackspeed = (float) SSConfig.glaiveAttackSpeed;
    static float warglaive_attackspeed = (float) SSConfig.warglaiveAttackSpeed;
    static float cutlass_attackspeed = (float) SSConfig.cutlassAttackSpeed;
    static float claymore_attackspeed = (float) SSConfig.claymoreAttackSpeed;
    static float greataxe_attackspeed = (float) SSConfig.greataxeAttackSpeed;
    static float greathammer_attackspeed = (float) SSConfig.greathammerAttackSpeed;
    static float chakram_attackspeed = (float) SSConfig.chakramAttackSpeed;
    static float scythe_attackspeed = (float) SSConfig.scytheAttackSpeed;
    static float halberd_attackspeed = (float) SSConfig.halberdAttackSpeed;


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
                new SimplySwordItem(ModTiers.STEEL, (steel_modifier + getDamageMod(id)),
                        getAttackSpeedMod(id) + steelSpeed, new Item.Properties()));
    }

    private static RegistryObject<Item> registerDiarkrite(String id) {
        return ITEMS.register(ModTiers.DIARKRITE.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
                new SimplySwordItem(ModTiers.DIARKRITE, (diarkrite_modifier + getDamageMod(id)),
                        getAttackSpeedMod(id) + diarkriteSpeed, new Item.Properties().fireResistant()));
    }

    private static RegistryObject<Item> registerAnthektite(String id) {
        return ITEMS.register(ModTiers.ANTHEKTITE.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
                new SimplySwordItem(ModTiers.ANTHEKTITE, (anthektite_modifier + getDamageMod(id)),
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
        ATK_DAMAGE.put("chakram", chakram_positive_modifier);
        ATK_SPEED.put("chakram", chakram_attackspeed);

        ATK_DAMAGE.put("claymore", claymore_positive_modifier);
        ATK_SPEED.put("claymore", claymore_attackspeed);

        ATK_DAMAGE.put("cutlass", cutlass_positive_modifier);
        ATK_SPEED.put("cutlass", cutlass_attackspeed);

        ATK_DAMAGE.put("glaive", glaive_positive_modifier);
        ATK_SPEED.put("glaive", glaive_attackspeed);

        ATK_DAMAGE.put("greataxe", greataxe_positive_modifier );
        ATK_SPEED.put("greataxe", greataxe_attackspeed);

        ATK_DAMAGE.put("greathammer", greathammer_positive_modifier);
        ATK_SPEED.put("greathammer", greathammer_attackspeed);

        ATK_DAMAGE.put("halberd", halberd_positive_modifier);
        ATK_SPEED.put("halberd", halberd_attackspeed);

        ATK_DAMAGE.put("katana", katana_positive_modifier);
        ATK_SPEED.put("katana", katana_attackspeed);

        ATK_DAMAGE.put("longsword", longsword_positive_modifier);
        ATK_SPEED.put("longsword", longsword_attackspeed);

        ATK_DAMAGE.put("rapier", rapier_positive_modifier);
        ATK_SPEED.put("rapier", rapier_attackspeed);

        ATK_DAMAGE.put("sai", sai_positive_modifier);
        ATK_SPEED.put("sai", sai_attackspeed);

        ATK_DAMAGE.put("scythe", scythe_positive_modifier);
        ATK_SPEED.put("scythe", scythe_attackspeed);

        ATK_DAMAGE.put("spear", spear_positive_modifier);
        ATK_SPEED.put("spear", spear_attackspeed);

        ATK_DAMAGE.put("twinblade", twinblade_positive_modifier);
        ATK_SPEED.put("twinblade", twinblade_attackspeed);

        ATK_DAMAGE.put("warglaive", warglaive_positive_modifier);
        ATK_SPEED.put("warglaive", warglaive_attackspeed);
    }

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
