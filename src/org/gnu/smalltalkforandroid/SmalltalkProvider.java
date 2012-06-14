package org.gnu.smalltalkforandroid;

import android.database.Cursor;
import android.net.Uri;

import com.googlecode.android_scripting.interpreter.InterpreterDescriptor;
import com.googlecode.android_scripting.interpreter.InterpreterProvider;

public class SmalltalkProvider extends InterpreterProvider {
	@Override
		public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
			// TODO Auto-generated method stub
			return super.query(uri, projection, selection, selectionArgs, sortOrder);
		}
  @Override
  protected InterpreterDescriptor getDescriptor() {
    return new SmalltalkDescriptor();
  }
 
}
