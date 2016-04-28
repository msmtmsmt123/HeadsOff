package com.rong.xposed.headsoff.utils;

/**
 * HeadsOff
 * Created by Rong on 2016/2/25.
 */
public class SettingValues {

	//keys
	public static final String PREF_FILE = "HeadsOffSettings";

	public static final String KEY_APP_NAME = "app_name";
	public static final String KEY_APP_ICON = "app_icon";
	public static final String KEY_PKG_NAME = "pkg_name";
	public static final String KEY_ACTIVE_SUFFIX = "_on";

	public static final String KEY_PREF_VERSION_NAME = "pref_version_name";
	public static final String KEY_PREF_AUTHOR = "pref_author";
	public static final String KEY_PREF_XPOSED = "pref_thanks_to_xposed";
	public static final String KEY_PREF_DOWNLOAD = "pref_download";

	public static final String KEY_PREF_MOD_ACTIVE = "pref_mod_active";
	public static final String KEY_PREF_SHOW_SYS_APP = "pref_show_system_app";

	public static final String KEY_SHOW_SYS_APP = "show_sys_app";
	public static final String KEY_MOD_ACTIVE = "mod_active";

	//default values
	public static final boolean DEFAULT_SHOW_SYS_APP = false;
	public static final boolean DEFAULT_PACKAGE_ACTIVE = false;
	public static final boolean DEFAULT_MOD_ACTIVE = false;

	public static final String KEY_GLOBAL_ACTIVE = "global_active";
	public static final String KEY_PREF_GLOBAL_ACTIVE = "pref_global_enable";
	public static final boolean DEFAULT_GLOBAL_ACTIVE = false;

	//white list
	public static final String KEY_SUFFIX_WHITELIST_COUNT = "/c";
	public static final String KEY_SUFFIX_WHITELIST = "/wl";
	public static final int DEFAULT_WHITELIST_COUNT = 0;

	public static final int WHITE_LIST_MAX = 5;

	public static final String KEY_PREF_ENABLE_LOG = "pref_log";
	public static final String KEY_ENABLE_LOG = "enable_log";

}
