package zql.app_jinnang.Prestener;

import zql.app_jinnang.Bean.NoteBean;



public interface PrestenerImp_list {
    public void readNotefromDatatoList(int READ_TYPE);
    public void deleteNotebean(NoteBean noteBean);
    public void setBackgroundcolorfromSeting();//设置主题色
    public void changeNotetoPasswordFile(NoteBean noteBean);//转入秘密文件夹
}
