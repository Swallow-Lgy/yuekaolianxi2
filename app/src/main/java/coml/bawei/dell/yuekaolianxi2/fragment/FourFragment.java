package coml.bawei.dell.yuekaolianxi2.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.yuekaolianxi2.R;
import coml.bawei.dell.yuekaolianxi2.bean.UserMessageBean;
import coml.bawei.dell.yuekaolianxi2.presenter.IPresenterImpl;
import coml.bawei.dell.yuekaolianxi2.util.BitmapToFeil;
import coml.bawei.dell.yuekaolianxi2.view.IView;
import static android.app.Activity.RESULT_OK;

public class FourFragment extends Fragment implements View.OnClickListener,IView {
    @BindView(R.id.headimage)
    SimpleDraweeView image;
    @BindView(R.id.name)
    TextView name;
    private IPresenterImpl iPresenter;
    private String userurl="user/getUserInfo";
    private TextView cream;
    private TextView photo;
    private PopupWindow popupWindow;
    private String path= Environment.getExternalStorageDirectory()+"/image.png";
    private String PATH_FILE=Environment.getExternalStorageDirectory()+"/file.png";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourfragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iPresenter = new IPresenterImpl(this);
        ButterKnife.bind(this,view);
        //请求个人信息
        getData();
        //图片的点击事件
        onClicImage();
       /* //打开相近
        opencream();
        //打开相册
        openphoto();*/
    }
    //图片的点击事件
    public void  onClicImage(){
      /*  View view = View.inflate(getContext(),R.layout.pop_item,null);
        cream = view.findViewById(R.id.cream);
        photo = view.findViewById(R.id.photo);
        popupWindow = new PopupWindow(view,LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        ColorDrawable cdw  =  new ColorDrawable(getResources().getColor(R.color.popcolor));
        popupWindow.setBackgroundDrawable(cdw);
        popupWindow.setFocusable(true);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(v,1,0);
            }
        });
        popupWindow.dismiss();*/
        String[] items = {"相册","相机"};
        AlertDialog.Builder listDiaLog =
                new AlertDialog.Builder(getContext());
        listDiaLog.setItems(items, new DatePickerDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 switch (which){
                     case 0:
                         Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                         intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(path)));
                         startActivityForResult(intent,1);
                         break;
                     case 1:
                         Intent intent1 = new Intent(Intent.ACTION_PICK);
                         intent1.setType("image/*");
                         startActivityForResult(intent1,2);
                         break;
                         default:
                             break;
                 }
            }
        }).show();
    }

  /*  public void opencream(){
      cream.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(path)));
              startActivityForResult(intent,1);
          }
      });
    }
  public void openphoto(){
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });
  }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode == RESULT_OK)
        {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile(new File(path)),"image/*");
            //相机裁剪
            intent.putExtra("CROP",true);
            //设置宽高
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            //设置输出照片的大小
            intent.putExtra("outputX",200);
            intent.putExtra("outputY",200);

            intent.putExtra("return-data",true);
            //启动
            startActivityForResult(intent,3);
        }
        if (requestCode==2)
        {
            Intent intent = new Intent("com.android.camera.action.CROP");
            Uri uri = data.getData();
            intent.setDataAndType(uri,"image/*");
            //相机裁剪
            intent.putExtra("CROP",true);
            //设置宽高
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            //设置输出照片的大小
            intent.putExtra("outputX",200);
            intent.putExtra("outputY",200);

            intent.putExtra("return-data",true);
            //启动
            startActivityForResult(intent,3);
        }
        else if (requestCode==3 && resultCode == RESULT_OK){
                     Bitmap bitmap = data.getParcelableExtra("data");
                     image.setImageBitmap(bitmap);
                    /* if (bitmap!=null){
                         Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity()
                                 .getContentResolver(),bitmap,null,null));
                         image.setImageURI(uri);
                         try {
                             BitmapToFeil.saveBitmap(bitmap,PATH_FILE,50);
                         } catch (IOException e) {
                             e.printStackTrace();
                         }

                     }*/
                  }
    }
    //请求网络数据
    public void getData(){
        Map<String,String> map = new HashMap<>();
        map.put("uid",23421+"");
        iPresenter.requestDataPost(userurl,map,UserMessageBean.class);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object data) {
        if (data instanceof UserMessageBean)
        {
            UserMessageBean messageBean = (UserMessageBean) data;
            name.setText(messageBean.getData().getUsername());
        }
    }
}
