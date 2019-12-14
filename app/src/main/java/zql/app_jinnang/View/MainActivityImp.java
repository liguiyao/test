package zql.app_jinnang.View;


import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import java.util.List;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Bean.Noteinfo;



public interface MainActivityImp {
    public Context getActivity_this();//获取此Activity的this
    public void startListActivity();
    public void startListSecretActivity();
    public void startCalendarActivity();
    public void startSetingActivity();
    public void startSearchActivity();
    public void setMainBackground(Integer integer);
    public void setMainBackgroundIcon(int size);//如果数据库为空，界面加载一个图片显示。
    public void openSheetDialog(NoteBean noteBean);
    public void readNotefromData(List<NoteBean>noteBeanList);
    public Application getMainApplication();
    public void setBackgroundcolorfromSeting(List<Integer>mlist);
}
