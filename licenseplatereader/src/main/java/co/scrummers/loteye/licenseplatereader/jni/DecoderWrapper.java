package co.scrummers.loteye.licenseplatereader.jni;

import co.scrummers.loteye.licenseplatereader.interfaces.ReaderListener;

public class DecoderWrapper {

    static {
        System.loadLibrary("decoder");
    }

    public native void decodeImage(String path);

    void _decodeReady(String result){
        System.out.println(result);
    }
}

