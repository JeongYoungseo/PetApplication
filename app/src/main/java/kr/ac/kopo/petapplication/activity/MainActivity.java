package kr.ac.kopo.petapplication.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.fragment.CareFragment;
import kr.ac.kopo.petapplication.fragment.HomeFragment;
import kr.ac.kopo.petapplication.fragment.ProfileFragment;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        replaceFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.menu_home){
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.menu_care) {
                replaceFragment(new CareFragment());
            } else if (item.getItemId() == R.id.menu_profile) {
                replaceFragment(new ProfileFragment());
            }

            return  true;
        });
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
    }
}