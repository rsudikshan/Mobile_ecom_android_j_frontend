package com.sr.a6semprojectfinal;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sr.a6semprojectfinal.APIRequests.LoginCheck;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.Fragments.Cart;
import com.sr.a6semprojectfinal.Fragments.ExploreFragment;
import com.sr.a6semprojectfinal.Fragments.Home;
import com.sr.a6semprojectfinal.Fragments.LoginScreen;
import com.sr.a6semprojectfinal.Fragments.UserInfo;

public class Main extends FragmentActivity {


    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.main);
        Home fragment = new Home();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        LoginCheck.check(this);
        handler();


    }

    public void handler(){

        ImageView explore = findViewById(R.id.main_explore);
        ImageView home = findViewById(R.id.main_home);
        ImageView cart = findViewById(R.id.main_cart);
        ImageView account = findViewById(R.id.main_account);
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExploreFragment fragment = new ExploreFragment();

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SessionReference.isLoggedIn) {
                    UserInfo fragment = new UserInfo();

                    FragmentManager manager = getSupportFragmentManager();
                    manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                else {
                    LoginScreen fragment = new LoginScreen();

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });

        cart.setOnClickListener((view)->{
            if(SessionReference.isLoggedIn){
                FragmentManager manager = getSupportFragmentManager();
                Cart cartFragment  = new Cart();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container,cartFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
            else {
                Toast.makeText(this,"You're logged out.",Toast.LENGTH_SHORT).show();;

            }


        });

        home.setOnClickListener((view)->{
            Home fragment = new Home();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });



        uiHandler(explore);
        uiHandler(home);
        uiHandler(cart);
        uiHandler(account);



    }

    public void uiHandler(ImageView imageView){
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Equivalent to "key pressed": Scale down when pressed
                        v.setScaleX(0.8f);  // Shrink to 80% of original size
                        v.setScaleY(0.8f);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Equivalent to "key released": Return to normal size when released
                        v.setScaleX(1.0f);  // Back to 100% size
                        v.setScaleY(1.0f);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        // Ensure the button returns to normal size even if touch is canceled
                        v.setScaleX(1.0f);
                        v.setScaleY(1.0f);
                        break;
                }
                return false;  // Return false to still register the click event
            }
        });
    }
}