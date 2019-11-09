package ga.softogi.moviecatalogue.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ga.softogi.moviecatalogue.ContentShareCallback;
import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.adapter.FilmAdapter;
import ga.softogi.moviecatalogue.data.FilmEntity;

public class MovieFragment extends Fragment implements ContentShareCallback {
    private RecyclerView rvMovie;

    public MovieFragment() {
    }

    public static Fragment newInstance() {
        return new MovieFragment();
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
            List<FilmEntity> movies = viewModel.getMovies();

            FilmAdapter adapter = new FilmAdapter(getActivity(), this);
            adapter.setListContent(movies);

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
