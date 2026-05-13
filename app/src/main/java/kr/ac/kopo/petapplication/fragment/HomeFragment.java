package kr.ac.kopo.petapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.adapter.RecommendAdapter;
import kr.ac.kopo.petapplication.model.RecommendItem;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecommendAdapter adapter;
    ArrayList<RecommendItem> itemList;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_recommend);

        itemList = new ArrayList<>();

        itemList.add(new RecommendItem("프리미엄 연어 사료", "알러지가 없는 반려동물에게 적합한 고단백 사료","사료","28000원"));
        itemList.add(new RecommendItem("로얄캐넌", "소형견 맞춤 사료","사료","25000원"));
        itemList.add(new RecommendItem("오리젠", "고단백 프리미엄 사료","사료","30000원"));

        adapter = new RecommendAdapter(itemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}