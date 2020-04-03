package co.scrummers.loteye.decoder;

import android.app.Activity;
import android.os.Bundle;

import co.scrummers.loteye.licenseplatereader.Reader;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));

        Reader reader = new Reader();
        reader.read("http://scrummers.co");

        System.out.println("finish");
    }

}
