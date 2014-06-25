package de.pizzeria.gargano.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

public class IntentTools {

	private static final String TAG = IntentTools.class.getSimpleName();

	/**
	 * Send a message. An application chooser will be displayed if there are
	 * multiple applications that can handle this type of message.
	 * 
	 * @param chooserTitle
	 *            the title that will be displayed on the chooser.
	 * @param subject
	 *            the subject of the message. Some applications might not
	 *            support this.
	 * @param msg
	 *            the message to be sent.
	 * @param recipient
	 *            the recipient to send this message to. Some applications might
	 *            not support this.
	 * @param onlyViaEmail
	 *            send this message only via email.
	 * @param context
	 *            from where to fire the intent.
	 */
	public static void send(String chooserTitle, String subject, String msg,
			String recipient, boolean onlyViaEmail, Context context) {

		send(chooserTitle, subject, msg, recipient, onlyViaEmail, null, context);
	}

	public static void send(String chooserTitle, String subject, String msg,
			String recipient, boolean onlyViaEmail, Uri appendFile,
			Context context) {

		Intent mailIntent = new Intent(Intent.ACTION_SEND);
		mailIntent.addCategory(Intent.CATEGORY_DEFAULT);

		if (onlyViaEmail)
			mailIntent.setType("message/rfc822"); // <-- only email
		else
			mailIntent.setType("text/plain");

		if (recipient != null) {
			String[] mailto = { recipient };
			mailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
		}

		if (msg != null)
			mailIntent.putExtra(Intent.EXTRA_TEXT, msg);
		if (subject != null)
			mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

		if (appendFile != null) {
			mailIntent.putExtra(Intent.EXTRA_STREAM, appendFile);
			// if (appendFile.toString().endsWith(".vcf"))
			// int ptIndex = appendFile.getPath().lastIndexOf('.');

			String path = appendFile.getPath();
			String mimeType = null;
			String extension = null;
			if (path != null) {
				extension = MimeTypeMap.getFileExtensionFromUrl(path);
				MimeTypeMap mtm = MimeTypeMap.getSingleton();

				if (extension != null) {
					mimeType = mtm.getMimeTypeFromExtension(extension);
				}
			}

			// if the mime type of the given extension is known give it to the
			// intent
			if (mimeType != null) {
				Log.i(TAG, "Mimetype found: " + mimeType);
				// mailIntent.setType(mimeType);
			}
			// if we have the extension vcf ... its a vcard
			else if ("vcf".equals(extension)) {
				mimeType = "text/x-vcard";
				Log.i(TAG, "Setting mimetype manually: " + mimeType);
			}
			// in all other cases
			else {
				mimeType = "*/*";
				Log.i(TAG, "Setting mimetype manually to default: " + mimeType);
			}
			mailIntent.setType(mimeType);
		}

		context.startActivity(Intent.createChooser(mailIntent, chooserTitle));
	}

	private static final String REGEX_PROTOCOL = "(" //
			+ "http://" //
			+ ")";

	private static final String REGEX_DOMAIN = "(" //
			+ ".*\\..*" // dot in between
			+ ")";

	private static final String REGEX_PATH = "(" //
			+ ".*/" // 
			+ ")*"; // 

	private static final String REGEX_VIDEO_FILE = "(" //
			+ ".*" // arbitrary things
			+ "\\." // a dot
			+ "(mp4|3gp)" // the file-extension
			+ ")";

	private static final String REGEX_VIDEO_URL = "("//
			+ REGEX_PROTOCOL // protocol: http://
			+ REGEX_DOMAIN // domainname with dot in between
			+ "/" // slash to separate domain from path
			+ REGEX_PATH //
			+ REGEX_VIDEO_FILE //
			+ ")";//

	private static final String REGEX_VIDEO_URL_RELAXED = "" //
			+ ".*" // arbitrary things
			+ "\\." // one dot in between
			+ ".*/" // arbitrary things followed by a slash
			+ REGEX_VIDEO_FILE; // video file	
	
	
}
