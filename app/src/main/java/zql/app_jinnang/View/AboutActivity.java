package zql.app_jinnang.View;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.MessageEvent;
import zql.app_jinnang.Prestener.PrestenerImp_about;
import zql.app_jinnang.Prestener.Prestener_about;
import zql.app_jinnang.R;
import zql.app_jinnang.UserSeting;

import static zql.app_jinnang.R.color.colorFloatingButton;

public class AboutActivity extends SwipeActivity implements AboutActivityImp,View.OnClickListener{
    private PrestenerImp_about prestenerImp_about;
    private AlertDialog alertDialog_creatpassword;
    private NiceSpinner niceSpinner;
    private Toolbar toolbar_about;
    private UserSeting userSeting;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        prestenerImp_about=new Prestener_about(this);
        StatusBarUtil.setColor(this,getColor(colorFloatingButton));
        userSeting=(UserSeting)getApplication();
        initview();
        prestenerImp_about.setBackgroundcolorfromSeting();
    }
    private void initview(){
        inittoolbarseting();
        initAboutcode();
        initBackgroundcolorview();
        initSerectandQuestionview();
    }
    private void inittoolbarseting(){
        toolbar_about=(Toolbar)this.findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar_about);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_about.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initSerectandQuestionview(){
        TextView text_password=(TextView)this.findViewById(R.id.text_creatpasswordandquestion);
        TextView text_password_edit=(TextView)this.findViewById(R.id.text_editpassword);
        TextView text_question_edit=(TextView)this.findViewById(R.id.text_editquestion);
        TextView text_forgetpaswword=(TextView)this.findViewById(R.id.text_forgetpassword);
        text_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestenerImp_about.isnullthepasswordfromSeting()){
                    initpassworddialog();
                }else {
                    Toast.makeText(AboutActivity.this, "The password already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        text_password_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestenerImp_about.isnullthepasswordfromSeting()){
                    Toast.makeText(AboutActivity.this, "Password has not been created, please create a password first", Toast.LENGTH_SHORT).show();
                }else {
                    initEditpassworddialog();
                }
            }
        });
        text_question_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestenerImp_about.isnullthequestionfromSeting()){
                    Toast.makeText(AboutActivity.this, "Tips has not been created, please create a tips first", Toast.LENGTH_SHORT).show();
                }else {
                    initQuestionddialog();
                }
            }
        });
        text_forgetpaswword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestenerImp_about.isnullthequestionfromSeting()){
                    Toast.makeText(AboutActivity.this, "Tips has not been created, please create a tips first", Toast.LENGTH_SHORT).show();
                }else {
                    initiscurrentQuestiondialog();
                }
            }
        });
    }
    private void initEditnewpasssworddialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editpassworddialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_password_edit_password);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_password);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_password);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("Create New Password");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Means.isedittext_empty(materialEditText_password)){
                    Toast.makeText(AboutActivity.this, "Enter New Password", Toast.LENGTH_SHORT).show();
                }else {
                    if (materialEditText_password.getText().toString().length()!=6){
                        Toast.makeText(AboutActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    }else {
                        prestenerImp_about.putpasswordOnSeting(materialEditText_password.getText().toString());
                        alertDialog_editpassword.dismiss();
                    }
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initEditpassworddialog(){//实例化一个重新编辑密码的dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editpassworddialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_password_edit_password);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_password);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_password);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("Enter Old Password");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestenerImp_about.iscurrentthepasswordfromSeting(materialEditText_password.getText().toString())){
                    initEditnewpasssworddialog();
                    alertDialog_editpassword.dismiss();
                }else {
                    Toast.makeText(AboutActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initQuestionddialog(){//实例化一个重新编辑密保的dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editquestiondialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_question_edit_question);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_question);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_question);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("Enter Old tips");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prestenerImp_about.iscurrentthequestionfromSeting(materialEditText_password.getText().toString())){
                    alertDialog_editpassword.dismiss();
                    initeditnewQuestiondialog();
                }else {
                    Toast.makeText(AboutActivity.this, "Wrong tips", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initeditnewQuestiondialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editquestiondialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_question_edit_question);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_question);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_question);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("Enter New Tips");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Means.isedittext_empty(materialEditText_password)){
                    Toast.makeText(AboutActivity.this, "Enter Tips", Toast.LENGTH_SHORT).show();
                }else {
                    prestenerImp_about.putquestionOnSeting(materialEditText_password.getText().toString());
                    alertDialog_editpassword.dismiss();
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initiscurrentQuestiondialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editquestiondialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_question_edit_question);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_question);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_question);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("Enter Tips");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Means.isedittext_empty(materialEditText_password)){
                    Toast.makeText(AboutActivity.this, "Enter Tips", Toast.LENGTH_SHORT).show();
                }else {
                    if (prestenerImp_about.iscurrentthequestionfromSeting(materialEditText_password.getText().toString())){
                        alertDialog_editpassword.dismiss();
                        prestenerImp_about.showthecurrentpasswordOnAboutactivity();
                    }else {
                        Toast.makeText(AboutActivity.this, "Wrong Tips", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initBackgroundcolorview(){//颜色选择
        niceSpinner=(NiceSpinner)this.findViewById(R.id.spiner_color_set);
        List<String>data=new LinkedList<>(Arrays.asList("Default","Coral","Primrose","Neon","Gentleman"));
        niceSpinner.attachDataSource(data);
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        prestenerImp_about.putcurrentcolorOnSeting(0);
                        prestenerImp_about.setBackgroundcolorfromSeting();
                        break;
                    case 1:
                        prestenerImp_about.putcurrentcolorOnSeting(1);
                        prestenerImp_about.setBackgroundcolorfromSeting();
                        break;
                    case 2:
                        prestenerImp_about.putcurrentcolorOnSeting(2);
                        prestenerImp_about.setBackgroundcolorfromSeting();
                        break;
                    case 3:
                        prestenerImp_about.putcurrentcolorOnSeting(3);
                        prestenerImp_about.setBackgroundcolorfromSeting();
                        break;
                    case 4:
                        prestenerImp_about.putcurrentcolorOnSeting(4);
                        prestenerImp_about.setBackgroundcolorfromSeting();
                        break;
                    default:
                            break;

                }
                EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATA_COLOR));
            }
        });
    }
    private void initAboutcode(){

    }
    private void initpassworddialog(){//实例化一个创建密码和密保问题的Dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_passworddialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_password_edit);
        final MaterialEditText materialEditText_question=(MaterialEditText)centerview.findViewById(R.id.set_dialog_question_edit);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok);
        alertDialog_creatpassword=builder.setView(centerview).create();
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Means.isedittext_empty(materialEditText_password)&Means.isedittext_empty(materialEditText_question)){
                    Toast.makeText(AboutActivity.this, "Enter Password And Tips", Toast.LENGTH_SHORT).show();
                }else if (Means.isedittext_empty(materialEditText_password)|Means.isedittext_empty(materialEditText_question)){
                    Toast.makeText(AboutActivity.this, "Enter Password Or Tips", Toast.LENGTH_SHORT).show();
                }else {
                    if (materialEditText_password.getText().toString().length()!=6){
                        Toast.makeText(AboutActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    }else {
                        prestenerImp_about.putpasswordandquestionOnSeting(materialEditText_password.getText().toString(),
                                materialEditText_question.getText().toString());
                        alertDialog_creatpassword.dismiss();
                    }
                }
            }
        });
        alertDialog_creatpassword.show();
    }

    @Override
    public void showthecurrentPassword(String password) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Password");
        builder.setMessage(password);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public Context getAboutActivityContext() {
        return this;
    }

    @Override
    public Application getAboutApplication() {
        return this.getApplication();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer>colorlist) {
        niceSpinner.setSelectedIndex(prestenerImp_about.getcurrentcolorNumfromSeting());
        StatusBarUtil.setColor(this,colorlist.get(0));
        toolbar_about.setBackgroundColor(colorlist.get(0));
    }

    @Override
    public void finish() {
        super.finish();
    }
}
