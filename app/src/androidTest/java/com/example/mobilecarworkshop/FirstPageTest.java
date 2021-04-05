package com.example.mobilecarworkshop;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class FirstPageTest {
    @Rule
    public ActivityTestRule<FirstPage> FirstPageRule= new ActivityTestRule<FirstPage>(FirstPage.class);
    private FirstPage fActivity = null;

    @Before
    public void setUp() throws Exception {

        fActivity = FirstPageRule.getActivity();
    }

    @Test
    public void firstPageTest() {
    View view = fActivity.findViewById(R.id.textView7);
    assertNotNull(view);


    }

    @After
    public void tearDown() throws Exception {
        fActivity = null;
    }


}