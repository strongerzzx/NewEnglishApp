package fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.englishapp_bishe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmptyFragment extends Fragment {


    public EmptyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.load_empty_error, container, false);
        return inflate;
    }

}
