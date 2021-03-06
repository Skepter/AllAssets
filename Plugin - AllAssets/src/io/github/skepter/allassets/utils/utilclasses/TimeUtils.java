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

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TimeUtils {

	private final long time;

	public TimeUtils(final long time) {
		this.time = time;
	}

	public TimeUtils(final long past, final long present) {
		this.time = past - present;
	}

	public long getTime() {
		return time;
	}

	public long getDays() {
		return MILLISECONDS.toDays(time);
	}

	public long getHours() {
		return ((time / (1000 * 60 * 60)) % 24);
	}

	public long getMinutes() {
		return ((time / (1000 * 60)) % 60);
	}

	public long getSeconds() {
		return (time / 1000) % 60;
	}

	public long getTotalDays() {
		return MILLISECONDS.toDays(time);
	}

	public long getTotalHours() {
		return MILLISECONDS.toHours(time);
	}

	public long getTotalMinutes() {
		return MILLISECONDS.toMinutes(time);
	}

	public long getTotalSeconds() {
		return MILLISECONDS.toSeconds(time);
	}

	/** Time difference between NOW and dateTime */
	public static String formatDateAtASpecificPointInTime(final long dateTime) {
		return formatDate(new TimeUtils(System.currentTimeMillis(), dateTime).getTime());
	}

	/** Formats date. Format it in the format of 'you have played since <time>'
	 * Doesn't count the 0's
	 *
	 * @param date - The long date in milliseconds
	 * @return the formatted date in String form */
	public static String formatDate(final long date) {
		final TimeUtils utils = new TimeUtils(date);
		final long days = utils.getDays();
		final long hours = utils.getHours();
		final long minutes = utils.getMinutes();
		final long seconds = utils.getSeconds();
		final StringBuilder builder = new StringBuilder();
		if (days != 0)
			builder.append(days + (days == 1 ? " day " : " days "));
		if (hours != 0)
			builder.append(hours + (hours == 1 ? " hour " : " hours "));
		if (minutes != 0)
			builder.append(minutes + (minutes == 1 ? " minute " : " minutes "));
		if (seconds != 0)
			builder.append(seconds + (seconds == 1 ? " second " : " seconds "));
		return builder.toString();
	}

}
