/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
package io.github.Skepter.AllAssets.Permissions;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.CustomConfig;

import java.io.File;
import java.util.List;

public class PermissionDataManager extends CustomConfig {

	//manages data.
	//yeah :D
	//will use a bunch of methods to retrieve/set everything

	/* Sample format:
	 * 
	 * Worlds:
	 *   <WorldName>:
	 *     Groups:
	 *       <GroupName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *       <GroupName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *     Players:
	 *     	 <PlayerName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *       <PlayerName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 * Global:
	 *   Groups:
	 *     <GroupName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *     <GroupName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *   Players:
	 *     <PlayerName>:
	 *       Permissions:
	 *       - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *     <PlayerName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]     
	 */

	public PermissionDataManager() {
		super(new File(AllAssets.getStorage(), "PermissionData.yml"), "Permissions");
	}

	public List<String> getWorldPermissions(String worldName, GroupDataType type, String dataName) {
		String path = getPath(type, "Worlds.", worldName) + dataName + ".Permissions";
		return getDataFile().getStringList(path);
	}

	public List<String> getGlobalPermissions(GroupDataType type, String dataName) {
		String path = getPath(type, "Global.") + dataName + ".Permissions";
		return getDataFile().getStringList(path);
	}

	public String getWorldAffix(String worldName, GroupDataType type, String dataName, Affix affix) {
		String path = getAffix(affix, getPath(type, "Worlds.", worldName) + dataName + ".");
		return getDataFile().getString(path);
	}

	public String getGlobalAffix(GroupDataType type, String dataName, Affix affix) {
		String path = getAffix(affix, getPath(type, "Global.") + dataName + ".");
		return getDataFile().getString(path);
	}

	private String getAffix(Affix affix, String path) {
		switch (affix) {
		case PREFIX:
			path = path + "Prefix";
			break;
		case SUFFIX:
			path = path + "Suffix";
			break;
		}
		return path;
	}

	private String getPath(GroupDataType type, String path) {
		switch (type) {
		case GROUP:
			path = path + ".Groups.";
			break;
		case PLAYER:
			path = path + ".Players.";
			break;
		}
		return path;
	}

	private String getPath(GroupDataType type, String path, String worldName) {
		switch (type) {
		case GROUP:
			path = path + worldName + ".Groups.";
			break;
		case PLAYER:
			path = path + worldName + ".Players.";
			break;
		}
		return path;
	}

	public enum GroupDataType {
		GROUP, PLAYER;
	}

	/* For those unfamiliar with the word 'affix', it's the technical term for a
	 * prefix or suffix (unlike GroupManager which just calls them 'variables')*/
	public enum Affix {
		PREFIX, SUFFIX;
	}
}
