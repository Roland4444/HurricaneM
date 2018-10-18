package RawImplements;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class callEBS_sound {

    public Map<Integer, String> onLoadLibraryErrors;
    public callEBS_sound(){
        onLoadLibraryErrors=new HashMap<>();
        onLoadLibraryErrors.put(1, "error loading libcv.so");
        onLoadLibraryErrors.put(2, "error load v_create_session");
        onLoadLibraryErrors.put(3, "error create session");
        onLoadLibraryErrors.put(4, "bullshit in checking file");
        onLoadLibraryErrors.put(5, "error load v_check");
        onLoadLibraryErrors.put(0, "all fine aloaded (So config and checking file)");
    }
    public interface CLibrary extends Library {
        public static class ResultCheck extends Structure {
            @Override
            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[] { "checkResult", "lastErrorInSession","ResultLoadingSoSymbols" });

            }
            public static class ByReference extends CLibrary.ResultCheck implements Structure.ByReference {}
            public int checkResult;
            public int lastErrorInSession;
            public int ResultLoadingSoSymbols;

        }


        CLibrary INSTANCE = (CLibrary) Native.loadLibrary(("GBP"), CLibrary.class);
        CLibrary.ResultCheck lets_check(String config, String filename);

    }
    public CLibrary.ResultCheck call_ebs(String config, String filename){
        return CLibrary.INSTANCE.lets_check( config,  filename);
    }


}
