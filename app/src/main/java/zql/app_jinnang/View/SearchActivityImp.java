package zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import java.util.List;

import zql.app_jinnang.Bean.NoteBean;



public interface SearchActivityImp {
    public Context getSearchActivityContext();
    public Application getSearchApplication();
    public void setBackgroundcolorfromSeting(List<Integer>colorlist);
}
