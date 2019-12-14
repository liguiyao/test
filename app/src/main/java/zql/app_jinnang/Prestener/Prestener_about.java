package zql.app_jinnang.Prestener;

import java.util.List;

import zql.app_jinnang.UserSeting;
import zql.app_jinnang.UserSetingImp;
import zql.app_jinnang.View.AboutActivityImp;



public class Prestener_about implements PrestenerImp_about {
    private AboutActivityImp aboutActivityImp;
    private UserSeting userSeting;

    public Prestener_about(AboutActivityImp aboutActivityImp){
        this.aboutActivityImp=aboutActivityImp;
        userSeting=(UserSeting)aboutActivityImp.getAboutApplication();
    }

    @Override
    public boolean isnullthepasswordfromSeting() {
        return userSeting.isnullthepassword();
    }

    @Override
    public boolean isnullthequestionfromSeting() {
        return userSeting.isnullthequestion();
    }

    @Override
    public boolean iscurrentthepasswordfromSeting(String password) {
        return userSeting.iscurrentthePassword(password);
    }

    @Override
    public boolean iscurrentthequestionfromSeting(String question) {
        return userSeting.iscurrentthQuestion(question);
    }

    @Override
    public void putpasswordandquestionOnSeting(String password, String question) {
        userSeting.putpasswordonSeting(password);
        userSeting.putquestiononSeting(question);
    }

    @Override
    public void putpasswordOnSeting(String password) {
        userSeting.putpasswordonSeting(password);
    }

    @Override
    public void putquestionOnSeting(String question) {
        userSeting.putquestiononSeting(question);
    }

    @Override
    public void showthecurrentpasswordOnAboutactivity() {
        aboutActivityImp.showthecurrentPassword(userSeting.getpassswordfromSeting().toString());
    }

    @Override
    public void putcurrentcolorOnSeting(int color) {
        userSeting.putcurrentColor(color);
    }

    @Override
    public int getcurrentcolorNumfromSeting() {
        return userSeting.getcurrentColorNum();
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        aboutActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }
}
