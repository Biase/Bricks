package it.ingte.bricks;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Domenico on 24/08/2018.
 */

public class MyBarDataSetSpeBen extends BarDataSet{

    ArrayList<Info> a;
    //ArrayList<Info> imp;

    public MyBarDataSetSpeBen(List<BarEntry> yVals, String label, ArrayList<Info> a) {
        super(yVals, label);
        this.a = a;
        //this.imp = imp;
    }
    @Override
    public int getColor(int index) {

        if(a.get(index).getStartOperation().substring(6,8).equals("15")) { // less than 95 green
            mColors.set(3, 0xBB00008B);
            return mColors.get(3);
        }
        else if(a.get(index).getStartOperation().substring(6,8).equals("16")) { // less than 100 orange
            mColors.set(4, 0xBB7B68EE);
            return mColors.get(4);
            //0xBB0022CC
        }
        else { // greater or equal than 100 red
            mColors.set(5, 0x9900BEEE);
            return mColors.get(5);
        }

    }
}
