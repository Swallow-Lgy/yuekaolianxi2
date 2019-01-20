package coml.bawei.dell.yuekaolianxi2.util;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapToFeil {
    public static void saveBitmap(Bitmap bitmap,String path,int quality) throws IOException {
        String dir = path.substring(0, path.lastIndexOf("/"));
        File dirFile = new File(dir);
        if (!dirFile.exists()||!dirFile.isDirectory()){
            try
            {
            if (!dirFile.mkdirs()){
                return;
            }

            }
            catch (Exception e){

                Log.e("TAG",e.getMessage());
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
            if (bitmap.compress(Bitmap.CompressFormat.PNG,quality,out)){
                out.flush();
                out.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("TAG",e.getMessage());
        }

    }
}
