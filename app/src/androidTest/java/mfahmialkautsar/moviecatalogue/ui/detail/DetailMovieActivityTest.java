package mfahmialkautsar.moviecatalogue.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

import mfahmialkautsar.moviecatalogue.R;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.MovieEntity;
import mfahmialkautsar.moviecatalogue.utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

//Test untuk DetailFilmActivity dari untuk Movie
public class DetailMovieActivityTest {
    private MovieEntity dummyMovie = FakeDataDummy.generateDummyMovie().get(0);

    //menetukan activity yang akan dites
    @Rule
    public ActivityTestRule<DetailFilmActivity> activityRule = new ActivityTestRule<DetailFilmActivity>(DetailFilmActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailFilmActivity.class);
            result.putExtra(DetailFilmActivity.EXTRA_FILM, dummyMovie.getMovieId());
            return result;
        }
    };

    @Test
    public void loadMovie() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.getTitle())));
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie.getOverview())));
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release)).check(matches(withText(dummyMovie.getRelease())));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyMovie.getGenre())));
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_runtime)).check(matches(withText(dummyMovie.getRunningTime())));

        double rating = dummyMovie.getRating();
        String theRating;
        NumberFormat numberFormat = new DecimalFormat("#.0");
        if (Objects.equals(rating, 0.0)) {
            //make sure your phone language is set to english
            theRating = "No Rating";
        } else {
            theRating = numberFormat.format(rating);
        }
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_rating)).check(matches(withText(theRating)));
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()));
    }
}