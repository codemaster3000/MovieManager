package blackmediamanager.medialibrary.mediainfo;

import java.lang.reflect.Method;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import static java.util.Collections.singletonMap;

interface MediaInfoLibrary extends Library {
	// libmediainfo for linux depends on libzen, so we need to load dependencies
	// first
	Library LIB_ZEN = Platform.isLinux() ? (Library) Native.loadLibrary("zen", Library.class) : null;

	MediaInfoLibrary INSTANCE = (MediaInfoLibrary) Native.loadLibrary("mediainfo", MediaInfoLibrary.class,
			singletonMap(OPTION_FUNCTION_MAPPER, new FunctionMapper() {

				@Override
				public String getFunctionName(NativeLibrary lib, Method method) {
					// MediaInfo_New(), MediaInfo_Open() ...
					return "MediaInfo_" + method.getName();
				}
			}));

	Pointer New();

	int Open(Pointer handle, WString file);

	WString Option(Pointer handle, WString option, WString value);

	WString Inform(Pointer handle);

	WString Get(Pointer handle, int streamKind, int streamNumber, WString parameter, int infoKind, int searchKind);

	WString GetI(Pointer handle, int streamKind, int streamNumber, int parameterIndex, int infoKind);

	int Count_Get(Pointer handle, int streamKind, int streamNumber);

	void Close(Pointer handle);

	void Delete(Pointer handle);

	int Open_Buffer_Init(Pointer handle, long length, long offset);

	int Open_Buffer_Continue(Pointer handle, byte[] buffer, int size);

	long Open_Buffer_Continue_GoTo_Get(Pointer handle);

	int Open_Buffer_Finalize(Pointer handle);

}