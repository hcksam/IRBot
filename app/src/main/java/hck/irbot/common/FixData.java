package hck.irbot.common;

import android.Manifest;
import android.os.Environment;

/**
 * Created by hck on 9/4/2016.
 */
public class FixData {
    public final static String AppName = "IRBot";
    public final static String DefaultPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + AppName;
    public final static String DBName = AppName+".db";
    public final static String PFName = "Setting";
    public final static String PFID_Phone = "Phone";
    public final static String PFID_SearchTag_Mode = "SearchTag_Mode";
    public final static String PFID_SearchTag_Method = "SearchTag_Method";

    public final static String URL_Main = "https://reserve-hk.apple.com/HK/zh_HK/reserve/iPhone?partNumber=MNQK2ZP%2FA&channel=1&rv=&path=&sourceID=&iPP=false&appleCare=&iUID=&iuToken=&carrier=&store=R428";

    public final static int PERMISSIONS_REQUEST_INDEX = 0;
    public final static String[] Permissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
}
