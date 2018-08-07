package it.ingte.bricks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Domenico on 15/07/2018.
 */

public class ViewPagerAdapter extends PagerAdapter{

    private Button prev;
    private Button next;
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.foto1,R.drawable.foto2,R.drawable.foto3,R.drawable.foto4,R.drawable.foto5,R.drawable.foto6};
    private ViewPager vp;
    private View view;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.custom_layout, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        next = (Button) view.findViewById(R.id.next);
        prev = (Button) view.findViewById(R.id.prev);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = vp.getCurrentItem();
                tab++;
                vp.setCurrentItem(tab);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = vp.getCurrentItem();
                if(tab > 0){
                    tab--;
                    vp.setCurrentItem(tab);
                } else if(tab == 0){
                    vp.setCurrentItem(tab);
                }
            }
        });



        vp = (ViewPager) container;
        vp.addView(view, 0);

        TextView t = (TextView) view.findViewById(R.id.text01);
        TextView t1 = (TextView) view.findViewById(R.id.text1);
        switch (position) {
            case 0: prev.setVisibility(View.GONE);
                    t.setText("Modalità mappa");
                    t1.setText("In questa modalità è possibile visualizzare le sedi dei beneficiari con i relativi progetti navigando la cartina ed effettuando delle ricerche per beneficiario o provincia");
                break;
            case 1: t.setText("Filtri modalità mappa");
                t1.setText("Filtri che possono essere adoperati per restringere il campo di ricerca sulla mappa");
                break;
            case 2: t.setText("Modalità lista progetti");
                t1.setText("In questa modalità è possibile visualizzare la lista di tutti i progetti ed effettuare delle ricerche per beneficiario o provincia");
                break;
            case 3: t.setText("Filtri modalità lista progetti");
                t1.setText("Filtri che possono essere adoperati per restringere la lista dei progetti");
                break;
            case 4: t.setText("Modalità grafico beneficiari");
                t1.setText("In questa modalità è possibile visualizzare un grafico in base all'importo medio dei progetti per beneficiario");
                break;
            case 5: next.setVisibility(View.GONE);
                t.setText("Filtri modalità grafico beneficiari");
                t1.setText("Filtri che possono essere adoperati per restringere la visualizzazione dei dati del grafico");
                break;

        }
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
