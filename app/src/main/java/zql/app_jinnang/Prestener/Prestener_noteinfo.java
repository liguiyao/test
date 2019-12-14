package zql.app_jinnang.Prestener;

import java.util.ArrayList;
import java.util.List;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Bean.Noteinfo;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.NoteinfoActivityImp;



public class Prestener_noteinfo implements PrestenerImp_noteinfo{
    private NoteinfoActivityImp noteinfoActivityImp;
    private UserSeting userSeting;
    public Prestener_noteinfo(NoteinfoActivityImp noteinfoActivityImp){
        this.noteinfoActivityImp=noteinfoActivityImp;
        userSeting=(UserSeting)noteinfoActivityImp.getNoteinfoApplication();
    }

    @Override
    public void readDatatoNoteinfo(Noteinfo noteinfo) {
        noteinfoActivityImp.readNoteinfotoNotetext(noteinfo.getNoteinfo());
       noteinfoActivityImp.readPhotopathtoNoteImageview(noteinfo.getPhotopath(),noteinfo.getNotetype());
        List<String>tags=new ArrayList<String>();
        if (noteinfo.getNotetype().equals("null")){
            tags.add("Type：none");
        }else {
            tags.add("Type："+noteinfo.getNotetype());
        }
        if (noteinfo.getPeople().equals("null")){

        }else {
            tags.add("About："+noteinfo.getPeople());
        }
        if (noteinfo.getDate().equals("null")){

        }else {
            tags.add("Date："+noteinfo.getDate());
        }
        if (noteinfo.getTime().equals("null")){

        }else {
            tags.add("Time："+noteinfo.getTime());
        }
        if (noteinfo.getLocation().equals("null")){

        }else {
            tags.add("Venue："+noteinfo.getLocation());
        }
        if (noteinfo.getCreatetime().equals("null")){

        }else {
            tags.add("Design By："+noteinfo.getCreatetime());
        }
        noteinfoActivityImp.readLabelinfotoNoteTagrroup(tags);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        noteinfoActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
