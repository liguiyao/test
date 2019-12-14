package zql.app_jinnang.View;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jaeger.library.StatusBarUtil;

import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Bean.Noteinfo;
import zql.app_jinnang.Prestener.PrestenerImp_noteinfo;
import zql.app_jinnang.Prestener.Prestener_noteinfo;
import zql.app_jinnang.R;

public class NoteinfoActivity extends SwipeActivity implements NoteinfoActivityImp{
    PrestenerImp_noteinfo prestenerImpNoteinfo;
    private TagGroup tagGroup_noteinfo;
    private TextView textView_noteinfo;
    private ImageView imageview_noteinfo;
    private CoordinatorLayout coordinatorLayout_noteinfo;
    private Integer maincolor;
    private Toolbar toolbar;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteinfo);
        prestenerImpNoteinfo=new Prestener_noteinfo(this);
        initView();
        getintentExtra();
        prestenerImpNoteinfo.setBackgroundcolorfromSeting();
    }
    private void initView(){
        initToolbarSeting();
        initTaggroupView();
        initTextview();
        initImageview();
    }
    private void getintentExtra(){//获取传递过来的信息，并通过PrestenerImpNoteInfo读取显示在NoteinfoActivity上
        Intent mintent=getIntent();
        Bundle bundle=mintent.getExtras();
        Noteinfo noteinfo= (Noteinfo) bundle.getSerializable("noteinfo");
        prestenerImpNoteinfo.readDatatoNoteinfo(noteinfo);
    }
    private void initToolbarSeting(){//toolbard的设置
        toolbar=(Toolbar)this.findViewById(R.id.toolbar_noteinfo);
        coordinatorLayout_noteinfo=(CoordinatorLayout)this.findViewById(R.id.coordinator_noteinfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.cool_noteinfo);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }
    private void initTaggroupView(){//实例化TagGroup
        tagGroup_noteinfo=(TagGroup)this.findViewById(R.id.taggroup_noteinfo_item);
    }
    private void initTextview(){//实例化TextView
        textView_noteinfo=(TextView)this.findViewById(R.id.text_noteinfo_item);
    }
    private void initImageview(){//实例化ImagView
        imageview_noteinfo=(ImageView)this.findViewById(R.id.noteinfo_imageview);
    }


    @Override
    public void readNoteinfotoNotetext(String noteinfo) {
        if (noteinfo.isEmpty()){
            textView_noteinfo.setText("No Content");
        }else {
            textView_noteinfo.setText(noteinfo);
            toolbar.setTitle(Means.getNoteTitleOnNoteinfoActivity(noteinfo));
        }
    }

    @Override
    public void readLabelinfotoNoteTagrroup(List<String> tags) {
        if (tags.isEmpty()){
            tagGroup_noteinfo.setTags("No Tips");
        }else {
            tagGroup_noteinfo.setTags(tags);
        }
    }

    @Override
    public void readPhotopathtoNoteImageview(String photopath,String type) {
        if (photopath.equals("<image>")||photopath.equals("null")){
            switch (type){
                case "Vegetables":
                    imageview_noteinfo.setImageResource(R.drawable.photo_travel);
                    break;
                case "Fruits":
                    imageview_noteinfo.setImageResource(R.drawable.photo_study);
                    break;
                case "Foods":
                    imageview_noteinfo.setImageResource(R.drawable.photo_work);
                    break;
                case "Groceries":
                    imageview_noteinfo.setImageResource(R.drawable.photo_dilary);
                    break;
                case "Others":
                    imageview_noteinfo.setImageResource(R.drawable.photo_live);
                    break;
                default:
                    imageview_noteinfo.setImageResource(R.drawable.photo_live);
                    break;
            }
        }else {
            Glide.with(this).load(photopath).into(imageview_noteinfo);
        }
    }

    @Override
    public Application getNoteinfoApplication() {
        return getApplication();
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        StatusBarUtil.setColor(this, colorlist.get(0));
        maincolor=colorlist.get(0);
    }
}
