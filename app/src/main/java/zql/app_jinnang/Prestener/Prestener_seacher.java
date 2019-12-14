package zql.app_jinnang.Prestener;

import android.app.Application;
import android.database.Cursor;

import java.util.List;

import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Model.NoteInfoModel;
import zql.app_jinnang.Model.NoteInfoModelImp;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.SearchActivityImp;



public class Prestener_seacher implements PrestenerImp_seacher{
    private SearchActivityImp searchActivityImp;
    private NoteInfoModelImp noteInfoModelImp;
    private UserSeting userSeting;

    public Prestener_seacher(SearchActivityImp msearchActivityImp){
        this.searchActivityImp=msearchActivityImp;
        this.noteInfoModelImp=new NoteInfoModel(searchActivityImp.getSearchActivityContext());
        userSeting=(UserSeting)searchActivityImp.getSearchApplication();
    }

    @Override
    public Cursor getCursorfromtoSearch(String search) {
        return null;
    }

    @Override
    public List<NoteBean> getNotebeanfromDatatoSearch(String search) {
        return noteInfoModelImp.getSearchfromData(search);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        searchActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
