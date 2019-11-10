package ga.softogi.moviecatalogue.ui.movie;

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

public class MovieFragment extends Fragment implements ContentShareCallback {
    private RecyclerView rvMovie;
    private FilmAdapter adapter;
    private ProgressBar progressBar;

    public MovieFragment() {
    }

    public static Fragment newInstance() {
        return new MovieFragment();
    }

    @NonNull
    private static MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            MovieViewModel viewModel = obtainViewModel(getActivity());

            adapter = new FilmAdapter(getActivity(), this);
            viewModel.getMovies().observe(this, movie -> {
                progressBar.setVisibility(View.GONE);
                adapter.setListContent(movie);
                adapter.notifyDataSetChanged();
            });

            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(adapter);
        }
    }

    @Override
    public void onShareClick(FilmEntity filmEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            String chooserTitle = "Share this Movie NOW!";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle(chooserTitle)
                    .setText(filmEntity.getTitle())
                    .startChooser();
        }
    }
}
