package it.ingte.bricks;

import android.util.Log;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;


import java.util.List;

/**
 * Created by Domenico on 07/03/2018.
 */

public class MyBarDataSet extends BarDataSet{
        public MyBarDataSet(List<BarEntry> yVals, String label) {
            super(yVals, label);
        }

        @Override
        public int getColor(int index) {
            if(getEntryForXIndex(index).getVal() < TabBeneficiary.max / 3) { // less than 95 green
                //Log.i("valVERDE", "" + getEntryForXIndex(index).getVal());
                mColors.set(3, 0xBB00BB00);
                return mColors.get(3);
            }
            else if(getEntryForXIndex(index).getVal() >= TabBeneficiary.max / 3 && getEntryForXIndex(index).getVal() < (TabBeneficiary.max / 3)*2) { // less than 100 orange
                //Log.i("valGIALLO", "" + getEntryForXIndex(index).getVal());
                mColors.set(4, 0xEEDDDD11);
                return mColors.get(4);
            }
            else { // greater or equal than 100 red
                //Log.i("valROSSO", "" + getEntryForXIndex(index).getVal());
                mColors.set(5, 0xBBBB0000);
                return mColors.get(5);
            }
        }
}
