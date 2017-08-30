package cr.ac.ucr.app_proyecto_i_aplicada;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.ProductAdapter;
import domain.Product;

import static android.content.Context.SEARCH_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListFragment extends Fragment {

    private ListView lvProducts;
    private Toolbar toolbar;
    private ProductAdapter adapter;
    private SearchView svProducts;
    private ImageView ivCoreVisesLogo;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProductListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance(String param1, String param2) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_list, container, false);
        lvProducts = view.findViewById(R.id.lvProductList);
        toolbar=view.findViewById(R.id.toolbar);

        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ArrayList<Product> productsList = new ArrayList<Product>();

        Product prod = new Product(1, "Hola", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod);

        Product prod1 = new Product(1, "Luis", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod1);

        Product prod2 = new Product(1, "Ho", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod2);

        Product prod3 = new Product(1, "´dncjnc", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod3);

        Product prod4 = new Product(1, "nddjndj", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod4);

        Product prod5 = new Product(1, "hbfdds", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod5);

        Product prod6 = new Product(1, "aer", "Hola minfo", 123,500, getResources().getDrawable(R.drawable.youtube_logo));
        productsList.add(prod6);


        adapter = new ProductAdapter(this,productsList);

        lvProducts.setAdapter(adapter);

        //Se le añade el evento sobre el item
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Fragment fragment = new ProductDetailFragment();

                Product map = (Product) parent.getItemAtPosition(position);

                Bundle intent = new Bundle();

                intent.putInt("id", map.getIdProduct());
                intent.putString("name", map.getName());
                intent.putFloat("price", map.getPrice());
                intent.putFloat("usdPrice", map.getDollarPrice());
                intent.putString("description", map.getDescription());

                //al fragment se le añaden los parametros del item que se toco
                fragment.setArguments(intent);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_core_vises, fragment).addToBackStack(null).commit();

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        //MenuItem searchItem = menu.findItem(R.id.svProducts);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.svProducts));
        SearchManager searchManager =(SearchManager) getActivity().getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return;
    }






}
