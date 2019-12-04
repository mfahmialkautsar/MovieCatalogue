package ga.softogi.moviecatalogue.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

//Test DetailFilmActivity untuk TV Show
public class DetailTvActivityTest {
    private TvEntity dummyTv = FakeDataDummy.generateDummyTv().get(0);

    //menetukan activity yang akan dites
    @Rule
    public ActivityTestRule<DetailFilmActivity> activityRule = new ActivityTestRule<DetailFilmActivity>(DetailFilmActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailFilmActivity.class);
            result.putExtra(DetailFilmActivity.EXTRA_FILM, dummyTv.getTvId());
            return result;
        }
    };

    @Test
    public void loadTv() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTv.getTitle())));
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTv.getOverview())));
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release)).check(matches(withText(dummyTv.getRelease())));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyTv.getGenre())));
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_runtime)).check(matches(withText(dummyTv.getRunningTime())));

        double rating = dummyTv.getRating();
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