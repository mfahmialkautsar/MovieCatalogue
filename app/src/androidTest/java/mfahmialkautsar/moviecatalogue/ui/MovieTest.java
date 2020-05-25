package mfahmialkautsar.moviecatalogue.ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import mfahmialkautsar.moviecatalogue.R;
import mfahmialkautsar.moviecatalogue.ui.home.HomeActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MovieTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void toDetailActivityTest() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        //this is why I prefer to use local data instead of on this project, the data is static and easier to be tested.
        // No need to check the data manually first
        onView(withId(R.id.tv_title)).check(matches(withText("Fantastic Beasts: The Crimes of Grindelwald")));
    }
}
