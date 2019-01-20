package coml.bawei.dell.yuekaolianxi2.adpter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import coml.bawei.dell.yuekaolianxi2.fragment.FourFragment;
import coml.bawei.dell.yuekaolianxi2.fragment.OneFragment;
import coml.bawei.dell.yuekaolianxi2.fragment.ThreeFragment;
import coml.bawei.dell.yuekaolianxi2.fragment.TwoFragment;

public class TabLayoutAdpter extends FragmentPagerAdapter {
    String[] page = new String[]{"首页","分类","购物车","个人"};
    public TabLayoutAdpter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
             return new OneFragment();
            case 1:
                return new TwoFragment();
            case 2:
                return new ThreeFragment();
            case 3:
                return new FourFragment();
                default:
                    return null;
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return page[position];
    }

    @Override
    public int getCount() {
        return page.length;
    }
}
