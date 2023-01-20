package be;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PlayerFunctions {
    public void playVideo(String filePath) throws IOException {
        //try to run the .mp4 file with command for Windows OS
        try {
            Runtime.getRuntime().exec( "CMD /C START " + filePath + " ");
        }
        //catches if it cannot run the .mp4 file with command for ios OS - not sure if it'll work bc
        // of the reason no one of this team is using ios :)
        catch (Exception exception){
            Runtime.getRuntime().exec( "open -a /Applications/VLC.app/Contents/MacOS/VLC/ " + filePath + " ");
        }
    }
}
