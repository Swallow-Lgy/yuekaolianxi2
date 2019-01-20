package coml.bawei.dell.yuekaolianxi2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coml.bawei.dell.yuekaolianxi2.R;
import coml.bawei.dell.yuekaolianxi2.bean.LoginBean;
import coml.bawei.dell.yuekaolianxi2.presenter.IPresenterImpl;
import coml.bawei.dell.yuekaolianxi2.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    @BindView(R.id.phonenum)
    EditText phonenum;
    @BindView(R.id.password)
    EditText pasword;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.reg)
    Button reg;
    private IPresenterImpl iPresenter;
    private String loginUrl="user/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //绑定
        iPresenter = new IPresenterImpl(this);

    }
    @OnClick(R.id.login)
    public void loginOnClick(){
        String pwd = pasword.getText().toString();
        String phone = phonenum.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("mobile",phone);
        map.put("password",pwd);
        iPresenter.requestDataPost(loginUrl,map,LoginBean.class);
    }
    @OnClick(R.id.reg)
    public void regOnClick(){
       Intent intent = new Intent(MainActivity.this,RegActivity.class);
       startActivity(intent);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object data) {
             if (data instanceof LoginBean){
                 LoginBean loginBean = (LoginBean) data;
                /* if (loginBean.getCode().equals("0")){*/
                     Toast.makeText(MainActivity.this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                     startActivity(intent);
                 /*}*/
             }
    }
    //销毁

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.des();
    }
}
