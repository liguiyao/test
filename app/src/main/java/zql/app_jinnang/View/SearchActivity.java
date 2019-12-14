package zql.app_jinnang.View;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.jaeger.library.StatusBarUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import zql.app_jinnang.Adapter.RecyclerViewSearchAdapter;
import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Prestener.PrestenerImp_seacher;
import zql.app_jinnang.Prestener.Prestener_seacher;
import zql.app_jinnang.R;

public class SearchActivity extends SwipeActivity implements SearchActivityImp {
    private SearchView searchView;
    private Toolbar toolbar_search;
    private RecyclerView searchrecyclerView;
    private PrestenerImp_seacher prestenerImpSeacher;
    private RecyclerViewSearchAdapter recyclerViewSearchAdapter;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StatusBarUtil.setColor(this, getColor(R.color.colorFloatingButton));
        prestenerImpSeacher=new Prestener_seacher(this);
        initview();
        prestenerImpSeacher.setBackgroundcolorfromSeting();
    }
    private void initview(){
        initsearchview();
        inittoolbarset();
        initrecyclerview();
    }
    private void inittoolbarset(){
        toolbar_search=(Toolbar)this.findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar_search);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_search.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initrecyclerview(){
        searchrecyclerView=(RecyclerView)this.findViewById(R.id.recycler_search);
    }
    private void initAdapter(String searth){
        List<NoteBean>list=prestenerImpSeacher.getNotebeanfromDatatoSearch(searth);
        if (list.size()>0){
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
            RecyclerViewSearchAdapter recyclerViewSearchAdapter=new RecyclerViewSearchAdapter(list,this);
            searchrecyclerView.setLayoutManager(layoutManager);
            searchrecyclerView.setAdapter(recyclerViewSearchAdapter);
        }else {
            searchrecyclerView.removeAllViews();
            Toast.makeText(this, "No Content", Toast.LENGTH_SHORT).show();
        }
    }
    private void initsearchview(){
        searchView=(SearchView)this.findViewById(R.id.searchview_search);
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                initAdapter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public Context getSearchActivityContext() {
        return this;
    }

    @Override
    public Application getSearchApplication() {
        return getApplication();
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        StatusBarUtil.setColor(this, colorlist.get(0));
        toolbar_search.setBackgroundColor(colorlist.get(0));
    }
}
