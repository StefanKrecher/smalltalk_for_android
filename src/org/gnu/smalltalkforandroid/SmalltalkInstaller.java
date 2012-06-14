package org.gnu.smalltalkforandroid;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.googlecode.android_scripting.AsyncTaskListener;
import com.googlecode.android_scripting.InterpreterInstaller;
import com.googlecode.android_scripting.exception.Sl4aException;
import com.googlecode.android_scripting.interpreter.InterpreterDescriptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SmalltalkInstaller extends InterpreterInstaller {

	public SmalltalkInstaller(InterpreterDescriptor descriptor, Context context, AsyncTaskListener<Boolean> listener)
			throws Sl4aException {
		super(descriptor, context, listener);
		copyZIP(context.getAssets());
	}

	private void copyZIP(AssetManager assetManager) {	
		copyFile(assetManager, "Smalltalk_r0.zip");
		copyFile(assetManager, "Smalltalk_scripts_r0.zip");
	}

	private void copyFile(AssetManager assetManager, String filename) {
		InputStream in = null;
		OutputStream out = null;
		byte[] buffer = new byte[1024];

		try {
			in = assetManager.open(filename);
			out = new FileOutputStream("/mnt/sdcard/" + filename);
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (FileNotFoundException e) {
			Log.e("copyasset", e.getMessage());
		} catch (IOException e) {
			Log.e("copyasset", e.getMessage());
		}
	}

	@Override
	protected boolean setup() {
		return true;
	}

}
