package coml.bawei.dell.yuekaolianxi2.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.yuekaolianxi2.R;
import coml.bawei.dell.yuekaolianxi2.adpter.TabLayoutAdpter;
import coml.bawei.dell.yuekaolianxi2.view.IView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IView {
   @BindView(R.id.viewpager)
    ViewPager viewPager;
   @BindView(R.id.tab)
    TabLayout tabLayout;
   private TabLayoutAdpter tabLayoutAdpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tabLayoutAdpter = new TabLayoutAdpter(getSupportFragmentManager());
        viewPager.setAdapter(tabLayoutAdpter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object data) {

    }
}
