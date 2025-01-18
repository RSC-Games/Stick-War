package appcode;

import velocity.util.Point;
import velocity.config.GlobalAppConfig;

/** 
 * Configure Velocity to how it best suits your game.
 */
public class AppConfig extends GlobalAppConfig {
    public AppConfig() {
        APP_NAME = "Stick War";
        ICON_PATH = "./velocity/resources/rsc_games.ico";
        APP_RES_DEFAULT = new Point(1280, 720);
        WINDOW_RESIZABLE = false;
        WINDOW_FULLSCREEN = false;

        /********************** RENDERER CONFIG *************************/
        DEFAULT_RENDERER = "LumaViper"; // TODO: Copperhead?
        RENDER_BACKEND = "OpenGL";  // CopperheadGL?
        ENABLE_ERP_FALLBACK = false;
        WARN_RENDERER_INIT_FAIL = true; 
        EN_DEBUG_RENDERER = false;
        REND_WORKER_COUNT = Runtime.getRuntime().availableProcessors() - 1; // CPUs

        /********************* SCENE LOAD CONFIG ************************/
        START_SCENE = "TitleScreen";  // Scene loaded by default by the engine.
        SCENE_LOAD_FAILURE_FATAL = true;

        /******************** WARNINGS AND ERRORS ***********************/
        MISSING_IMAGE_FATAL = false;
        WARNINGS_FATAL = false;

        /************************ DEBUGGING *****************************/
        LOG_GC = true;  // Enable GC logging messages. Useful for engine debugging.
        LOG_MEMORY = true;  // Log memory allocation/deallocations for sprites.

        /********************* RENDERER DEBUGGING ***********************/
        SUPPRESS_UNSTABLE_RENDERER_WARNING = true;
        EN_RENDERER_LOGS = false;  // Enable renderer swapchain and draw messages.
        EN_RENDERER_PROFILER = false;  // Track drawtime.
        PROFILE_SHADERTIME = false;  // Not implemented.
    }
}
