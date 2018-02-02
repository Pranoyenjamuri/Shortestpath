package com.pranoy.lowcostpath;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Pranoy on 02/01/2018.
 */

public class LowCostPathPageAdapter extends FragmentPagerAdapter {
    public LowCostPathPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LowCostPathFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Example Grids";
            default:
                return null;
        }
    }
}
