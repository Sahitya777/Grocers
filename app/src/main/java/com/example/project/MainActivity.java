package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.project.adapter.CategoryAdapter;
import com.example.project.adapter.DiscountedProductAdapter;
import com.example.project.adapter.RecentlyViewedAdapter;
import com.example.project.model.Category;
import com.example.project.model.DiscountedProducts;
import com.example.project.model.RecentlyViewed;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {


    private EditText editTextEmail,editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    RecyclerView discountRecyclerView, categoryRecyclerView,recentlyViewedRecycler;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    ImageView allCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        signIn=(Button)findViewById(R.id.signin);
//        signIn.setOnClickListener(this);
//        editTextEmail=(EditText)findViewById(R.id.email);
//        editTextPassword=(EditText) findViewById(R.id.password);
//        progressBar =(ProgressBar) findViewById(R.id.progressBar);
//        mAuth=FirebaseAuth.getInstance();




        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory = findViewById(R.id.allCategoryImage);
        recentlyViewedRecycler = findViewById(R.id.recently_item);


        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AllCategory.class);
                startActivity(i);
            }
        });

        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts(1,R.drawable.discountberry));
        discountedProductsList.add(new DiscountedProducts(2,R.drawable.discountbrocoli));
        discountedProductsList.add(new DiscountedProducts(3,R.drawable.discountmeat));
        discountedProductsList.add(new DiscountedProducts(4,R.drawable.discountmango));
        discountedProductsList.add(new DiscountedProducts(5,R.drawable.discounttomato));
        discountedProductsList.add(new DiscountedProducts(6,R.drawable.discountonion));
        discountedProductsList.add(new DiscountedProducts(1,R.drawable.discountberry));
        discountedProductsList.add(new DiscountedProducts(2,R.drawable.discountbrocoli));
        discountedProductsList.add(new DiscountedProducts(3,R.drawable.discountmeat));
        discountedProductsList.add(new DiscountedProducts(4,R.drawable.discountmango));
        discountedProductsList.add(new DiscountedProducts(5,R.drawable.discounttomato));
        discountedProductsList.add(new DiscountedProducts(6,R.drawable.discountonion));

        setDiscountedRecycler(discountedProductsList);

        categoryList = new ArrayList<>();
        categoryList.add(new Category(1,R.drawable.ic_home_fruits));
        categoryList.add(new Category(2,R.drawable.ic_home_fish));
        categoryList.add(new Category(3,R.drawable.ic_home_meats));
        categoryList.add(new Category(4,R.drawable.ic_home_veggies));
        categoryList.add(new Category(5,R.drawable.ic_home_fruits));
        categoryList.add(new Category(6,R.drawable.ic_home_fish));
        categoryList.add(new Category(7,R.drawable.ic_home_meats));
        categoryList.add(new Category(8,R.drawable.ic_home_veggies));
        setCategoryRecycler(categoryList);



        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Watermelon", "Watermelon has high water content and also provides some fiber.", "??? 80", "1", "KG", R.drawable.card4,R.drawable.b4));
        recentlyViewedList.add(new RecentlyViewed("Papaya", "Papayas are spherical or pear-shaped fruits that can be as long as 20 inches.", "??? 85", "1", "KG", R.drawable.card3,R.drawable.b3));
        recentlyViewedList.add(new RecentlyViewed("Strawberry", "The strawberry is a highly nutritious fruit, loaded with vitamin C.", "??? 30", "1", "KG", R.drawable.card2,R.drawable.b1));
        recentlyViewedList.add(new RecentlyViewed("Kiwi", "Full of nutrients like vitamin C, vitamin K, vitamin E, folate, and potassium.", "??? 30", "1", "PC", R.drawable.card1,R.drawable.b2));
        recentlyViewedList.add(new RecentlyViewed("Carrot","Carrot is a rich source of vitamins, protein, carbohydrates, and Beta-carotene.","??? 30","1","KG",R.drawable.b5,R.drawable.design1));
        recentlyViewedList.add(new RecentlyViewed("Capsicum","It is a type of green pepper. It is a large and mildly flavored vegetable.","??? 40","1","KG",R.drawable.b6,R.drawable.design3));

        setRecentlyViewedRecycler(recentlyViewedList);


    }

    private void setDiscountedRecycler(List<DiscountedProducts> dataList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryDataList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this,recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.signin:
//                userLogin();
//                break;
//        }
//
//    }
//
//    private void userLogin() {
//        String email = editTextEmail. getText( ) . toString() . trim();
//        String password = editTextPassword. getText( ) . toString() . trim( );
//        if(email. isEmpty ( ) ) {
//
//            editTextEmail.setError("Email is required!");
//            editTextEmail.requestFocus();
//            return;
//        }
//            if(!Patterns. EMAIL_ADDRESS.matcher(email).matches()){
//                editTextEmail.setError("please provide a valid email");
//                editTextEmail.requestFocus();
//                return;
//            }
//            if(password.isEmpty()){
//                editTextPassword.setError("password is required");
//                editTextPassword.requestFocus();
//                return;
//
//            }
//            if(password.length()<6){
//                editTextPassword.setError("password should be 6 character or more");
//                editTextPassword.requestFocus();
//
//            }
//            progressBar.setVisibility(View.VISIBLE);
//            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        startActivity(new Intent(MainActivity.this,MainActivity.class));
//
//                    }
//                    else{
//                        Toast.makeText(MainActivity.this, "Failed to login! please check your credentials", Toast.LENGTH_LONG).show();
//                    }
//                }
//            });

//   }
}