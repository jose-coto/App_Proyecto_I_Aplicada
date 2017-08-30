package adapter;

import android.app.Activity;
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
import java.util.List;

import cr.ac.ucr.app_proyecto_i_aplicada.R;
import domain.Product;

/**
 * Created by Machito on 16/8/2017.
 */

public class ProductAdapter extends BaseAdapter implements Filterable{

    protected Fragment fragment;

    protected ArrayList<Product> productsItems;
    protected ArrayList<Product> productsItemsFiltered;

    public ProductAdapter(Fragment fragment, ArrayList<Product> productsItems) {
        this.fragment=fragment;
        this.productsItems = productsItems;
        this.productsItemsFiltered=productsItems;
    }

    public Fragment getActivity() {
        return fragment;
    }

    public void setActivity(Fragment activity) {
        this.fragment = activity;
    }

    public ArrayList<Product> getProductsItems() {
        return productsItems;
    }

    public void setProductsItems(ArrayList<Product> productsItems) {
        this.productsItems = productsItems;
    }

    @Override
    public int getCount() {
        return productsItemsFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return productsItemsFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productsItems.get(position).getIdProduct();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(convertView== null){
            LayoutInflater inflater= (LayoutInflater)fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_product_list, null);
        }


        Product product = productsItemsFiltered.get(position);

        //Se establecen los valores que llevaran cada componente en el listview segun la posicion que tengas en el array list
        ImageView ivProduct=(ImageView) view.findViewById(R.id.ivProduct);
        TextView tvProductName=(TextView) view.findViewById(R.id.tvProductName);
        TextView tvCrcPrice=(TextView) view.findViewById(R.id.tvCrcPrice);
        TextView tvUSDPrice=(TextView) view.findViewById(R.id.tvDollarPrice);


        ivProduct.setImageDrawable(product.getImageProduct());
        tvProductName.setText(product.getName());

        ///Se debe de llamar a la funcion para convertir el precio a dolar
        tvCrcPrice.setText("¢: "+product.getPrice());
        tvUSDPrice.setText("$: "+product.getDollarPrice());

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
                    results.count = productsItems.size();
                    results.values = productsItems;
                } else {//do the search
                    ArrayList<Product> resultsData = new ArrayList<>();
                    String searchStr = constraint.toString().toUpperCase();
                    for (Product s : productsItems)
                        if (s.getName().toUpperCase().contains(searchStr)) resultsData.add(s);
                    results.count = resultsData.size();
                    results.values = resultsData;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                productsItemsFiltered = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
