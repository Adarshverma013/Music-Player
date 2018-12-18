
package First;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.AudioDevice;

        
public class MyClass
{
    public AudioDevice audiodevice;
    FileInputStream Fis;
    BufferedInputStream Bis;
    public Player player;
    public long pauseLocation;
    public long songLength;
    public String file;
    
    public void Stop()
    {
        if(player!=null)
        {
            player.close();
            pauseLocation = 0;
            Display.songName.setText("");
    
        }
    }
    
    public void Pause()
    {
        if(player!=null)
        {
            try
            {
                pauseLocation = Fis.available();
                player.close();
            
            }
            catch(IOException ex)
            {
            
            }
        }
            
    }
 
   
 
    public void Play(String path)
    {
        
            try
            {
                Fis = new FileInputStream(path);
                Bis = new BufferedInputStream(Fis);
                songLength = Fis.available();
                file = path + "";
                player = new Player(Bis);
            }
            catch(FileNotFoundException | JavaLayerException ex)
            {
 
            } catch (IOException ex) {
                Logger.getLogger(MyClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            new Thread()
            {
               
               
                @Override
                public void run()
                {
                    
                    try
                    {
                        player.play();
                        if(player.isComplete()&&Display.count==1)
                        {
                            Play(file);
                        }   
                    }
                    catch(JavaLayerException ex)
                    {
                
                    }
                }
               
            }.start();
           
    }
     public void Resume()
    {
        if(player!=null)
        {
            try
            {
                Fis = new FileInputStream(file);
                Bis = new BufferedInputStream(Fis);
                player = new Player(Bis);
                Fis.skip(songLength - pauseLocation);
            }
            catch(FileNotFoundException | JavaLayerException ex)
            {
 
            } catch (IOException ex) {
                Logger.getLogger(MyClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        player.play();
                    
                    }
                    catch(JavaLayerException ex)
                    {
                
                    }
                }
            }.start();
        }
    }
}
