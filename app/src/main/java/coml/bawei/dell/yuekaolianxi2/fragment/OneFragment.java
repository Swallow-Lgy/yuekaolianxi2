package coml.bawei.dell.yuekaolianxi2.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;


import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.yuekaolianxi2.R;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

//视频播放
public class OneFragment extends Fragment {
    @BindView(R.id.ijk)
    IjkVideoView ijkVideoView;
    @BindView(R.id.time)
    TextView time;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        AndroidMediaController controller = new AndroidMediaController(getActivity(),false);
        ijkVideoView.setMediaController(controller);
        String url="http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
       ijkVideoView.setVideoURI((Uri.parse(url)));
       ijkVideoView.start();
        TimePickerView pickerView = new TimePickerView.Builder(getActivity(),new TimePickerView.OnTimeSelectListener(){
            @Override
            public void onTimeSelect(Date date, View v) {
                time.setText(date+"");
            }
         })
                .setType(new boolean[]{true,true,true,false,false,false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .isCenterLabel(true)
                .build();
        pickerView.show();
    }
}
