/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.utils.utilclasses;

import java.io.FileInputStream;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class JavaUtils {

	/** Makes Bytes readable (converts to KB, MB etc.) */
	public static String humanReadableByteCount(final long bytes, final boolean si) {
		final int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		final int exp = (int) (Math.log(bytes) / Math.log(unit));
		final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	/** Gets the CPU load */
	public static double getProcessCpuLoad() {
		final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = null;
		try {
			name = ObjectName.getInstance("java.lang:type=OperatingSystem");
		} catch (MalformedObjectNameException | NullPointerException e) {
			e.printStackTrace();
		}
		AttributeList list = null;
		try {
			list = mbs.getAttributes(name, new String[] { "ProcessCpuLoad" });
		} catch (InstanceNotFoundException | ReflectionException e) {
			e.printStackTrace();
		}

		if (list.isEmpty())
			return Double.NaN;

		final Attribute att = (Attribute) list.get(0);
		final Double value = (Double) att.getValue();

		if (value == -1.0)
			return Double.NaN;

		return ((int) (value * 1000) / 10.0);
	}

	/** Gets all of the class names from a specific package name */
	public static List<String> getClassNamesInPackage(final String jarName, String packageName) {
		final ArrayList<String> classes = new ArrayList<String>();
		packageName = packageName.replaceAll("\\.", "/");
		try {
			final JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null)
					break;
				if ((jarEntry.getName().startsWith(packageName)) && (jarEntry.getName().endsWith(".class")))
					classes.add(jarEntry.getName().replaceAll("/", "\\."));
			}
			jarFile.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return classes;
	}
}
