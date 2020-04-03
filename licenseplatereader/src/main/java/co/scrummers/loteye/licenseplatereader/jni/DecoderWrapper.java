package co.scrummers.loteye.licenseplatereader.jni;

import co.scrummers.loteye.licenseplatereader.interfaces.ReaderListener;

public class DecoderWrapper {

    ReaderListener listener = null;
    static {
        System.loadLibrary("decoder");
    }

    public native void decodeImage(String path);
    public void setListener(ReaderListener listener){
        this.listener = listener;
    }
    public void _decodeReady(String result){
        if(this.listener != null){
            this.listener.onResult(result);
        }
    }
}

