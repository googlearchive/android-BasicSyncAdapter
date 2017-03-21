/*
* Copyright (C) 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.android.basicsyncadapter.tests;

import com.example.android.basicsyncadapter.*;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
* Tests for BasicSyncAdapter sample.
*/
@RunWith(AndroidJUnit4.class)
public class SampleTests {
    @Rule
    public ActivityTestRule<EntryListActivity> mActivityRule = new ActivityTestRule<>(EntryListActivity.class);

    private EntryListActivity mTestActivity;
    private EntryListFragment mTestFragment;


    @Before
    public void setUp() throws Exception {
        mTestActivity = mActivityRule.getActivity();
        mTestFragment = (EntryListFragment)
            mTestActivity.getSupportFragmentManager().getFragments().get(0);
    }

    /**
    * Test if the test fixture has been set up correctly.
    */
    @Test
    public void testPreconditions() {
        Assert.assertNotNull("mTestActivity is null", mTestActivity);
        Assert.assertNotNull("mTestFragment is null", mTestFragment);
    }

    /**
    * Add more tests below.
    */

}
