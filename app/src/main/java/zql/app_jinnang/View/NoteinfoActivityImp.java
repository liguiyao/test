package zql.app_jinnang.View;

import android.app.Application;
import android.graphics.Color;

import java.util.List;



public interface NoteinfoActivityImp {
    public void readNoteinfotoNotetext(String noteinfo);
    public void readLabelinfotoNoteTagrroup(List<String> tags);
    public void readPhotopathtoNoteImageview(String photopath,String type);
    public Application getNoteinfoApplication();
    public void setBackgroundcolorfromSeting(List<Integer>colorlist);
}
