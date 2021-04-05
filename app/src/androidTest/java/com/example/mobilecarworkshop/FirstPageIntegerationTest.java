package com.example.mobilecarworkshop;

import org.junit.After;
import org.junit.Before;

import android.app.Activity;
import android.app.Instrumentation;


import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class FirstPageIntegerationTest {
    @Rule
    public ActivityTestRule<FirstPage> FirstPageRule= new ActivityTestRule<FirstPage>(FirstPage.class);
    private FirstPage fActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Login.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        fActivity= FirstPageRule.getActivity();
    }
    @Test
    public void customerLaunchIntegerationTest() {

        assertNotNull(fActivity.findViewById(R.id.CustomerF));
        onView(withId(R.id.CustomerF)).perform(click());
        Activity launched = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(launched);
        launched.finish();
    }
    @After
    public void tearDown() throws Exception {
        fActivity=null;

    }
}