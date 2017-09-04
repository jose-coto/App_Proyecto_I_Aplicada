package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cr.ac.ucr.app_proyecto_i_aplicada.R;
import domain.Category;

/**
 * Created by Machito on 2/9/2017.
 */

public class CategoryAdapter extends BaseAdapter implements Filterable {

    protected Fragment fragment;

    protected ArrayList<Category> categoryItems;
    protected ArrayList<Category> categoryItemsFiltered;

    public CategoryAdapter(Fragment fragment, ArrayList<Category> categoryItems) {
        this.fragment=fragment;
        this.categoryItems = categoryItems;
        this.categoryItemsFiltered=categoryItems;
    }

    public Fragment getActivity() {
        return fragment;
    }

    public void setActivity(Fragment activity) {
        this.fragment = activity;
    }

    public ArrayList<Category> getcategoryItems() {
        return categoryItems;
    }

    public void setcategoryItems(ArrayList<Category> categoryItems) {
        this.categoryItems = categoryItems;
    }

    @Override
    public int getCount() {
        return categoryItemsFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryItemsFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categoryItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(convertView== null){
            LayoutInflater inflater= (LayoutInflater)fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_category_list, null);
        }


        Category category = categoryItemsFiltered.get(position);

        //Se establecen los valores que llevaran cada componente en el listview segun la posicion que tengas en el array list
        ImageView ivCategory=view.findViewById(R.id.ivCategoryItem);
        TextView tvCategoryName=view.findViewById(R.id.tvCategoryName);


        ivCategory.setImageDrawable(category.getImage());
        tvCategoryName.setText(category.getName());

        //retorna un activity con los items que contendra el listview
        return view;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    //no constraint given, just return all the data. (no search)
                    results.count = categoryItems.size();
                    results.values = categoryItems;
                } else {//do the search
                    ArrayList<Category> resultsData = new ArrayList<>();
                    String searchStr = constraint.toString().toUpperCase();
                    for (Category s : categoryItems)
                        if (s.getName().toUpperCase().contains(searchStr)) resultsData.add(s);
                    results.count = resultsData.size();
                    results.values = resultsData;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                categoryItemsFiltered = (ArrayList<Category>) results.values;

                notifyDataSetChanged();
            }
        };
    }

}
