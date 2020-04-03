package co.scrummers.loteye.licenseplatereader;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import co.scrummers.loteye.licenseplatereader.interfaces.ReaderListener;
import co.scrummers.loteye.licenseplatereader.jni.DecoderWrapper;
import co.scrummers.loteye.licenseplatereader.util.MultipartUtility;

public class Reader {
    private final String token;
    private final String url;
    DecoderWrapper wrapper = null;

    public Reader(Context
                          context) {
        this.token = context.getString(R.string.PLATE_RECOGNIZER_TOKEN);
        this.url = context.getString(R.string.PLATE_RECOGNIZER_URL);
        this.wrapper = new DecoderWrapper();
    }
    public void setListener(ReaderListener listener){
        if(this.wrapper != null){
            this.wrapper.setListener(listener);
        }
    }
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
    public void read(final String path){

        final HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("Authorization","Token "+this.token);
        final String url = this.url;
        new Thread(new Runnable() {
            public void run(){
                //wrapper.decodeImage(path);
                MultipartUtility multipart = null;
                try {
                    multipart = new MultipartUtility(url, "utf-8",headers);
                    multipart.addFormField("regions","us");
                    multipart.addFilePart("upload", new File(path));
                    String response = multipart.finish();
                    if(Reader.this.wrapper != null){
                        Reader.this.wrapper._decodeReady(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
