package coml.bawei.dell.yuekaolianxi2.activity;

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
import coml.bawei.dell.yuekaolianxi2.bean.RegBean;
import coml.bawei.dell.yuekaolianxi2.presenter.IPresenterImpl;
import coml.bawei.dell.yuekaolianxi2.view.IView;

public class RegActivity extends AppCompatActivity implements View.OnClickListener,IView{
     @BindView(R.id.regpassword)
     EditText password;
     @BindView(R.id.regphonenum)
     EditText phonenum;
     @BindView(R.id.regbut)
     Button regbut;
     private IPresenterImpl iPresenter;
     private String loginUrl="user/reg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        iPresenter = new IPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {

    }
   @OnClick(R.id.regbut)
   public void regButOnClcik(){
       String pwd = password.getText().toString();
       String phone = phonenum.getText().toString();
       Map<String,String> map = new HashMap<>();
       map.put("mobile",phone);
       map.put("password",pwd);
       iPresenter.requestDataPost(loginUrl,map,RegBean.class);
   }
    @Override
    public void success(Object data) {
         if (data instanceof RegBean){
             RegBean regBean = (RegBean) data;
             if (regBean.getCode().equals("0")){
                 Toast.makeText(RegActivity.this,regBean.getMsg(),Toast.LENGTH_SHORT).show();
             }
         }
    }
}
