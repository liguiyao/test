package zql.app_jinnang.Prestener;

import android.database.Cursor;

import java.util.List;

import zql.app_jinnang.Bean.NoteBean;



public interface PrestenerImp_seacher {
    public Cursor getCursorfromtoSearch(String search);
    public List<NoteBean> getNotebeanfromDatatoSearch(String search);
    public void setBackgroundcolorfromSeting();//设置主题色
}
