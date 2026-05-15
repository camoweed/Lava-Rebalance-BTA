package camoweed.lavarebalance;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class LavaRebalance implements ModInitializer, RecipeEntrypoint, GameStartEntrypoint {
	public static final String MOD_ID = "lavarebalance";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static final TomlConfigHandler CFG;

	public static final boolean preventCobbleGenerator;
	public static final boolean preventRedstoneTransmutation;
	public static final boolean preventIgneousTransmutation;

	static {
		Toml cfg = new Toml();
		cfg.addCategory("Options");
		cfg.addEntry("Options.Cobblestone", "Set to true to disable cobblestone generation when lava touches water.", true);
		cfg.addEntry("Options.Redstone", "Set to true to disable redstone from turning into lava when touching lava.", true);
		cfg.addEntry("Options.Igneous", "Set to true to disable igenous cobbled netherrack from turning into lava when touching lava.", true);

		CFG = new TomlConfigHandler(MOD_ID, cfg);
		preventCobbleGenerator = CFG.getBoolean("Options.Cobblestone");
		preventRedstoneTransmutation = CFG.getBoolean("Options.Redstone");
		preventIgneousTransmutation = CFG.getBoolean("Options.Igneous");
	}

	@Override
	public void onInitialize() {LOGGER.info("Lava: Rebalanced.");}

	@Override
	public void onRecipesReady() {}

	@Override
	public void initNamespaces() {}

	@Override
	public void beforeGameStart() {}

	@Override
	public void afterGameStart() {}
}
