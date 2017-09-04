package data;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

import cr.ac.ucr.app_proyecto_i_aplicada.R;
import domain.Category;

/**
 * Created by Machito on 3/9/2017.
 */

public class CategoryData {

    private Context context;

    public CategoryData(Context context){
        this.context=context;
    }

    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> categoryList = new ArrayList<Category>();

        Category whiteLine= new Category();
        whiteLine.setId(1);
        whiteLine.setName("Linea Blanca");
        whiteLine.setImage(context.getResources().getDrawable(R.drawable.white_line_category));
        categoryList.add(whiteLine);

        Category books= new Category();
        books.setId(2);
        books.setName("Libros");
        books.setImage(context.getResources().getDrawable(R.drawable.book_category));
        categoryList.add(books);

        Category clothes= new Category();
        clothes.setId(3);
        clothes.setName("Ropa");
        clothes.setImage(context.getResources().getDrawable(R.drawable.clothes_category));
        categoryList.add(clothes);

        Category tecnology= new Category();
        tecnology.setId(4);
        tecnology.setName("Tecnología");
        tecnology.setImage(context.getResources().getDrawable(R.drawable.tecnology_category));
        categoryList.add(tecnology);

        Category accesories= new Category();
        accesories.setId(5);
        accesories.setName("Accesorios");
        accesories.setImage(context.getResources().getDrawable(R.drawable.accesories_category));
        categoryList.add(accesories);


        return categoryList;
    }
}
