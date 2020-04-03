package co.scrummers.loteye.licenseplatereader;

import java.util.concurrent.TimeUnit;

import co.scrummers.loteye.licenseplatereader.jni.DecoderWrapper;

public class Reader implements Runnable {

    String path;
    DecoderWrapper wrapper ;
    public void read(String path){
        this.path = path;
        wrapper = new DecoderWrapper();
        this.run();
    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        System.out.println("decoding");
        wrapper.decodeImage(this.path);

        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("finished");
    }
}
