package kr.ac.kopo.petapplication.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.adapter.CareAdapter;
import kr.ac.kopo.petapplication.data.CareRepository;
import kr.ac.kopo.petapplication.model.CareItem;

public class CareFragment extends Fragment {

    private RecyclerView recyclerView;
    private CareAdapter adapter;

    private String currentType = "WALK";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_care, container, false);

        // 1. RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerCare);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CareAdapter(
                CareRepository.getByType(currentType)
        );
        recyclerView.setAdapter(adapter);

        // 2. 버튼
        Button btnWalk = rootView.findViewById(R.id.btnWalk);
        Button btnVaccine = rootView.findViewById(R.id.btnVaccine);
        Button btnMemo = rootView.findViewById(R.id.btnMemo);
        Button btnAdd = rootView.findViewById(R.id.btnAdd);

        // 3. 탭
        btnWalk.setOnClickListener(v -> {
            currentType = "WALK";
            refresh();
        });

        btnVaccine.setOnClickListener(v -> {
            currentType = "VACCINE";
            refresh();
        });

        btnMemo.setOnClickListener(v -> {
            currentType = "MEMO";
            refresh();
        });

        // 4. 추가 버튼
        btnAdd.setOnClickListener(clickView -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View dialogView = getLayoutInflater()
                    .inflate(R.layout.dialog_care_add, null);
            updateDialogVisibility(dialogView);

            EditText etTitle = dialogView.findViewById(R.id.etTitle);
            EditText etDate = dialogView.findViewById(R.id.etDate);
            EditText etDetail = dialogView.findViewById(R.id.etDetail);

            EditText etTime = dialogView.findViewById(R.id.etTime);
            EditText etLocation = dialogView.findViewById(R.id.etLocation);
            EditText etNext = dialogView.findViewById(R.id.etNextVaccine);

            // ✅ 날짜 선택
            etDate.setFocusable(false);
            etDate.setClickable(true);

            etDate.setOnClickListener(dateView -> {

                Calendar calendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        (picker, year, month, dayOfMonth) -> {

                            String selectedDate =
                                    year + "-" +
                                            String.format("%02d", month + 1) + "-" +
                                            String.format("%02d", dayOfMonth);

                            etDate.setText(selectedDate);
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            });

            builder.setView(dialogView);

            builder.setPositiveButton("저장", (dialog, which) -> {

                CareItem item = new CareItem(
                        currentType,
                        etTitle.getText().toString(),
                        etDate.getText().toString(),
                        etDetail.getText().toString()
                );

                if (currentType.equals("WALK")) {
                    item.time = etTime.getText().toString();
                    item.location = etLocation.getText().toString();
                }

                if (currentType.equals("VACCINE")) {
                    item.nextVaccine = etNext.getText().toString();
                }

                CareRepository.addCareItem(item);
                refresh();
            });

            builder.setNegativeButton("취소", null);
            builder.show();
        });

        return rootView;
    }
    private void updateDialogVisibility(View dialogView) {

        EditText etTime = dialogView.findViewById(R.id.etTime);
        EditText etLocation = dialogView.findViewById(R.id.etLocation);
        EditText etNext = dialogView.findViewById(R.id.etNextVaccine);

        if (currentType.equals("WALK")) {

            etTime.setVisibility(View.VISIBLE);
            etLocation.setVisibility(View.VISIBLE);
            etNext.setVisibility(View.GONE);

        } else if (currentType.equals("VACCINE")) {

            etTime.setVisibility(View.GONE);
            etLocation.setVisibility(View.GONE);
            etNext.setVisibility(View.VISIBLE);

        } else { // MEMO

            etTime.setVisibility(View.GONE);
            etLocation.setVisibility(View.GONE);
            etNext.setVisibility(View.GONE);
        }
    }

    // refresh
    private void refresh() {
        adapter.updateList(
                CareRepository.getByType(currentType)
        );
    }
}