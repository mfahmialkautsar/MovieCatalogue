package ga.softogi.moviecatalogue.ui.tv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ga.softogi.moviecatalogue.ContentShareCallback;
import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.adapter.FilmAdapter;
import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.viewmodel.ViewModelFactory;

public class TvFragment extends Fragment implements ContentShareCallback {
    private RecyclerView rvTv;
    private ProgressBar progressBar;

    public TvFragment() {
    }

    public static Fragment newInstance() {
        return new TvFragment();
    }

    @NonNull
    private static TvViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity, factory).get(TvViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTv = view.findViewById(R.id.rv_tv);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            TvViewModel viewModel = obtainViewModel(getActivity());

            FilmAdapter adapter = new FilmAdapter(getActivity(), this);
            viewModel.getTvs().observe(this, tv -> {
                progressBar.setVisibility(View.GONE);
                adapter.setListContent(tv);
                adapter.notifyDataSetChanged();
            });

            rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTv.setHasFixedSize(true);
            rvTv.setAdapter(adapter);
        }
    }

    @Override
    public void onShareClick(FilmEntity filmEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            String chooserTitle = "Share this TV Show NOW!";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle(chooserTitle)
                    .setText(filmEntity.getTitle())
                    .startChooser();
        }
    }
}
