package com.example.tecktrove.view.Synthesis;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tecktrove.R;
import com.example.tecktrove.dao.Initializer;
import com.example.tecktrove.domain.OrderLine;
import com.example.tecktrove.domain.ProductType;
import com.example.tecktrove.domain.Synthesis;
import com.example.tecktrove.memorydao.MemoryInitializer;
import com.example.tecktrove.util.Pair;
import com.example.tecktrove.view.Authentication.LogIn.LogInActivity;
import com.example.tecktrove.view.Authentication.SignUp.SignUpActivity;
import com.example.tecktrove.view.Product.ProductActivity;
import com.example.tecktrove.view.ProductAdapter;
import com.example.tecktrove.view.SharedViewModel;

import java.util.ArrayList;

public class SynthesisContainerActivity extends AppCompatActivity implements SynthesisContainerView , ProductAdapter.OnProductClickListener {
    private RecyclerView recyclerView;

    private SynthesisContainerPresenter presenter;

    private ProductAdapter productAdapter;

    private Initializer init;
    private SharedViewModel sharedViewModel;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synthesis_container);

        sharedViewModel  = new ViewModelProvider(this).get(SharedViewModel.class);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView = findViewById(R.id.main_container);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new SynthesisContainerPresenter(this,sharedViewModel);


        init = new MemoryInitializer();

        productAdapter = new ProductAdapter(new ArrayList<ProductType>(presenter.getComponents()), this);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);


        recyclerView.setAdapter(productAdapter);



        findViewById(R.id.completeted_container).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onComplete();
            }

        });

        findViewById(R.id.completeted_container2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onSave();
            }

        });

    }
    @Override
    public void onProductClick(ProductType product) {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);

    }

    @Override
    public void showErrorMessage(String title, String msg) {
        new AlertDialog.Builder(SynthesisContainerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.ok, null).create().show();

    }

    @Override
    public void completeSynthesis() {
        if (presenter.completeSynthesis()) {
            showErrorMessage("Προσοχη!", "Προσοχή η παρούσα σύνθεση θα αποθηκευτεί στο καλάθι");
            sharedViewModel.getCustomer().getCart().add(new OrderLine(1, sharedViewModel.getSynthesis()));
        } else {
            showErrorMessage("Προσοχη!", "Προσοχή η παρούσα σύνθεση δεν είναι ολοκληρωμένη!");
        }
    }

    @Override
    public void save() {
        showErrorMessage("Προσοχη!", "Προσοχή η παρούσα σύνθεση θα αποθηκευτεί στην λίστα με τα αποθηκευμένα προϊόντα σας");
        sharedViewModel.getCustomer().getSavedSynthesis().add(sharedViewModel.getSynthesis());
    }

}