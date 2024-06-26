package com.example.tecktrove.view.Customer.HomeScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.tecktrove.R;
import com.example.tecktrove.dao.Initializer;
import com.example.tecktrove.domain.ProductType;
import com.example.tecktrove.memorydao.ComponentDAOMemory;
import com.example.tecktrove.memorydao.CustomerDAOMemory;
import com.example.tecktrove.memorydao.EmployerDAOMemory;
import com.example.tecktrove.memorydao.MemoryInitializer;
import com.example.tecktrove.memorydao.SynthesisDAOMemory;
import com.example.tecktrove.view.CategoryAdapter;
import com.example.tecktrove.view.Customer.SavedProducts.SavedProductsActivity;
import com.example.tecktrove.view.MyAccount.MyAccountActivity;
import com.example.tecktrove.view.Product.ProductActivity;
import com.example.tecktrove.view.ProductAdapter;
import com.example.tecktrove.view.Customer.Cart.CartActivity;
import com.example.tecktrove.view.Synthesis.SynthesisActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreenView, CategoryAdapter.OnCategoryClickListener, ProductAdapter.OnProductClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private Initializer init;
    private HomeScreenPresenter presenter;
    private SearchView searchListView;

    /**
     * Initializes the classes attributes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_TeckTrove);
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_home_screen_customer);

        recyclerView = findViewById(R.id.recyclerView);
        init = new MemoryInitializer();


        // Sample product list (replace with your actual product data)
        ArrayList<String> categoryList = generateCategories();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        // Initialize the adapter with the product list
        categoryAdapter = new CategoryAdapter(new ArrayList<String>(categoryList), this);

        recyclerView.setAdapter(categoryAdapter);


        presenter = new HomeScreenPresenter(this, new CustomerDAOMemory(), new EmployerDAOMemory(), new ComponentDAOMemory(), new SynthesisDAOMemory());

        TabLayout tabLayout = findViewById(R.id.CustomerHomePageTabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Handle tab selection
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        presenter.onHome();
                        break;
                    case 1:
                        presenter.onCart();
                        break;
                    case 2:
                        presenter.onSaved();
                        break;
                    case 3:
                        presenter.onMyAccount();
                        break;
                    // Add cases for other tabs as needed
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab unselection
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab reselection
            }
        });

        findViewById(R.id.home_screen_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onBack();
            }
        });

        searchListView = (SearchView) findViewById(R.id.home_screen_search_bar);
        searchListView.setIconifiedByDefault(false);
        searchListView.setOnQueryTextListener(this);
    }

    /**
     * Puts in an ArrayList the categories of the eshop products
     * @return
     */
    private ArrayList<String> generateCategories() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("makeyourownsynthesis");
        categories.add("all");
        categories.add("box");
        categories.add("cpu");
        categories.add("motherboard");
        categories.add("ram");
        categories.add("gpu");
        categories.add("disk");
        categories.add("psu");
        categories.add("cooler");
        categories.add("synthesis");
        return categories;
    }

    /**
     * Displays the clicked categories products
     * @param category
     */
    @Override
    public void onCategoryClick(String category) {
        if(category.equals("makeyourownsynthesis")) {
            presenter.onDIYSyntesis();
        }else {
            presenter.onDisplayProducts(category);
        }

    }

    /**
     * Displayes the products
     * @param products the products
     */
    @Override
    public void displayProducts(ArrayList<ProductType> products) {
        updateUI(products);
    }

    /**
     * Navigates the user to the cart
     */
    @Override
    public void Cart() {
        Log.d("HomeScreenActivity", "Cart");
        Intent intent = new Intent(HomeScreenActivity.this, CartActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates the user to the home screen
     */
    @Override
    public void goToHome() {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates the user to the my account
     */
    @Override
    public void goToMyAccount() {
        Intent intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }
    /**
     * Navigates the user to the make your own synthesis page
     */
    @Override
    public void diySynthesis() {
        Intent intent = new Intent(this, SynthesisActivity.class);
        startActivity(intent);
    }

    /**
     * Goes on the previous page
     */
    @Override
    public void goBack() {
        recyclerView.setAdapter(categoryAdapter);
    }

    /**
     * Navigates the user to the saved synthesis page
     */
    @Override
    public void saved() {
        Intent intent = new Intent(this, SavedProductsActivity.class);
        startActivity(intent);
    }

    /**
     * If the search is empty show the categories
     * @param text
     * @return
     */
    public boolean onQueryTextChange(String text)
    {
        if (text.isEmpty()) {
            recyclerView.setAdapter(categoryAdapter);
        }
        return true;
    }


    /**
     * When search input is put it displays the products cooresponding to the input
     * @param query
     * @return
     */
    public boolean onQueryTextSubmit(String query) {
        presenter.onDisplayProducts(query);
        return true;
    }

    /**
     * When x is clicked it show the categories
     * @return
     */
    public boolean onClose(){
        recyclerView.setAdapter(categoryAdapter);
        return true;
    }

    /**
     * Updates the screen based on the search results
     * @param searchResults
     */
    private void updateUI(ArrayList<ProductType> searchResults) {
        if (searchResults.isEmpty()) {
            TextView noResultsTextView = findViewById(R.id.homeScreen_noResultsTextView);
            noResultsTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            findViewById(R.id.homeScreen_noResultsTextView).setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            productAdapter = new ProductAdapter(searchResults, this);
            recyclerView.setAdapter(productAdapter);
        }
    }

    /**
     * Navigates the user to the product information page of the product they clicked
     * @param product
     */
    @Override
    public void onProductClick(ProductType product) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("modelNo",product.getModelNo() );
        startActivity(intent);
    }

}

