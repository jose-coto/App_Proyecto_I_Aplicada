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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import adapter.ProductAdapter;
import connection.BCCRConection;
import data.ProductData;
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
    public ProductAdapter adapter;
    private SearchView svProducts;
    private ImageView ivCoreVisesLogo;
    private TextView prueba;
    private EditText etSearch;
    private Button btnSearch;

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

        setHasOptionsMenu(true);
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
        etSearch=view.findViewById(R.id.eTSearch);
        btnSearch=view.findViewById(R.id.btnSearch);

        final ProductData productData=new ProductData(getContext());
        ArrayList<Product> productsList = null;
        String valueCategory="";


        valueCategory=getArguments().getString("name");

        if(valueCategory.equals("")){
            productsList=productData.getAllProducts();
        }else{
            productsList=productData.getProductsByCategory(valueCategory);
        }



        lvProducts.setAdapter(getProductAdapter(this,productsList));

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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueSearch=etSearch.getText().toString();
                if(valueSearch.equalsIgnoreCase("")){
                    Toast.makeText(getContext(),"Debes de ingresar un criterio para realizar la busqueda",Toast.LENGTH_SHORT);
                }else{
                    ArrayList<Product> productsList=productData.getProductByName(valueSearch);

                    lvProducts.setAdapter(getProductAdapter(getParentFragment(),productsList));
                }
            }
        });
        return view;
    }

    private ProductAdapter getProductAdapter(Fragment fragment, ArrayList<Product> productsList){
        return adapter = new ProductAdapter(this,productsList);
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




}
