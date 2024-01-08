package com.example.tecktrove.view.Synthesis;

import com.example.tecktrove.domain.ProductType;

import java.util.ArrayList;

public interface SynthesisView {
    void showErrorMessage(String title, String msg);

    void displayProducts(ArrayList<ProductType> products);


    void synthesisDisplay();


    void home();

    void Cart();

    void Saved();

    void MyAcount();
}
