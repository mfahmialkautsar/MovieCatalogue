package ga.softogi.moviecatalogue.ui.favorite.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.utils.MovieShareCallback;
import ga.softogi.moviecatalogue.viewmodel.ViewModelFactory;

public class FavMovieFragment extends Fragment implements MovieShareCallback {
    private FavMoviePagedAdapter adapter;
    private RecyclerView rvFavMovie;
    private ProgressBar progressBar;

    private FavMovieViewModel viewModel;
    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                int swipedPosition = viewHolder.getAdapterPosition();
                MovieEntity movieEntity = adapter.getMovieById(swipedPosition);
                viewModel.setFavorite(movieEntity);
                Snackbar snackbar = Snackbar.make(getView(), "Unfavorited", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", view -> viewModel.setFavorite(movieEntity));
                snackbar.show();
            }
        }
    });

    public FavMovieFragment() {
    }

    @NonNull
    private static FavMovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavMovieViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavMovie = view.findViewById(R.id.rv_fav_movie);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            viewModel = obtainViewModel(getActivity());

            adapter = new FavMoviePagedAdapter(this);

            viewModel.getFavMovie().observe(this, movie -> {
                if (movie != null) {
                    switch (movie.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.submitList(movie.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            rvFavMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavMovie.setHasFixedSize(true);
            rvFavMovie.setAdapter(adapter);

            itemTouchHelper.attachToRecyclerView(rvFavMovie);
        }
    }

    @Override
    public void onShareClick(MovieEntity movieEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            String chooserTitle = "Share this Movie NOW!";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle(chooserTitle)
                    .setText(movieEntity.getTitle())
                    .startChooser();
        }
    }
}
