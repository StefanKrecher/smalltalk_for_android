package org.gnu.smalltalkforandroid;

import android.content.Context;

import com.googlecode.android_scripting.interpreter.InterpreterConstants;
import com.googlecode.android_scripting.interpreter.InterpreterDescriptor;
import com.googlecode.android_scripting.interpreter.Sl4aHostedInterpreter;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmalltalkDescriptor implements InterpreterDescriptor {
	private static final String ENV_DATA = "ANDROID_DATA";

	private String GST_BIN = "gst";
	private String GST_PATH = "/data/data/org.gnu.smalltalkforandroid/files/Smalltalk/";

	@Override
	public String getName() {
		return "Smalltalk";
	}

	@Override
	public String getNiceName() {
		return "GNU Smalltalk 3.4.2";
	}

	@Override
	public String getExtension() {
		return ".st";
	}

	@Override
	public File getBinary(Context context) {

		return new File(GST_PATH + GST_BIN);
	}

	@Override
	public List<String> getArguments(Context context) {

		return Arrays.asList("--kernel-directory", GST_PATH + "kernel");
	}

	@Override
	public String getInteractiveCommand(Context context) {
		return "";
	}

	@Override
	public String getScriptCommand(Context context) {

		return "%s";
	}

	@Override
	public int getVersion() {
		return 0;
	}

	@Override
	public Map<String, String> getEnvironmentVariables(Context arg0) {
		Map<String, String> settings = new HashMap<String, String>(1);
		settings.put(ENV_DATA, InterpreterConstants.SDCARD_ROOT + getClass().getPackage().getName());
		return settings;
	}

	@Override
	public boolean hasInteractiveMode() {
		return true;
	}

	@Override
	public boolean hasExtrasArchive() {
		return false;
	}

	@Override
	public boolean hasInterpreterArchive() {
		return true;
	}

	@Override
	public String getInterpreterArchiveUrl() {
		return "file:///mnt/sdcard/Smalltalk_r0.zip";
	}
	
	@Override
	public boolean hasScriptsArchive() {
		return true;
	}

	@Override
	public String getExtrasArchiveName() {
		return null;
	}

	@Override
	public String getExtrasArchiveUrl() {
		return null;
	}

	@Override
	public String getInterpreterArchiveName() {
		return "Smalltalk_r0.zip";
	}

	@Override
	public String getScriptsArchiveName() {
		return "Smalltalk_scripts_r0.zip";
	}

	@Override
	public String getScriptsArchiveUrl() {
		return "file:///mnt/sdcard/Smalltalk_scripts_r0.zip";
	}

}
