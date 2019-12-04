package ga.softogi.moviecatalogue.ui.favorite.tv;

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
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.utils.TvShareCallback;
import ga.softogi.moviecatalogue.viewmodel.ViewModelFactory;

public class FavTvFragment extends Fragment implements TvShareCallback {
    private FavTvPagedAdapter adapter;
    private RecyclerView rvFavTv;
    private ProgressBar progressBar;

    private FavTvViewModel viewModel;
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
                TvEntity tvEntity = adapter.getTvById(swipedPosition);
                viewModel.setFavorite(tvEntity);
                Snackbar snackbar = Snackbar.make(getView(), "Unfavorited", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", view -> viewModel.setFavorite(tvEntity));
                snackbar.show();
            }
        }
    });

    public FavTvFragment() {
    }

    @NonNull
    private static FavTvViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavTvViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavTv = view.findViewById(R.id.rv_fav_tv);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            viewModel = obtainViewModel(getActivity());

            adapter = new FavTvPagedAdapter(this);

            viewModel.getFavTv().observe(this, tv -> {
                if (tv != null) {
                    switch (tv.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.submitList(tv.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            rvFavTv.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavTv.setHasFixedSize(true);
            rvFavTv.setAdapter(adapter);

            itemTouchHelper.attachToRecyclerView(rvFavTv);
        }
    }

    @Override
    public void onShareClick(TvEntity tvEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            String chooserTitle = "Share this TV Show NOW!";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle(chooserTitle)
                    .setText(tvEntity.getTitle())
                    .startChooser();
        }
    }
}
