package zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;

import java.util.List;


public interface AboutActivityImp {
    public void showthecurrentPassword(String string);
    public Context getAboutActivityContext();
    public Application getAboutApplication();
    public void setBackgroundcolorfromSeting(List<Integer>colorlist);
}
