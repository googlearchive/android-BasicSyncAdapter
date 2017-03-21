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

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.content.SyncStatusObserver;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.CursorLoader;

import com.example.android.basicsyncadapter.EntryListActivity;
import com.example.android.basicsyncadapter.EntryListFragment;
import com.example.android.basicsyncadapter.SyncService;
import com.example.android.basicsyncadapter.SyncUtils;
import com.example.android.basicsyncadapter.provider.FeedContract;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
* Tests for BasicSyncAdapter sample.
*/
@RunWith(AndroidJUnit4.class)
public class SyncTests {
    @Rule
    public Timeout globalTimeout = new Timeout(10, TimeUnit.SECONDS);

    @Test
    public void testSync() throws InterruptedException {
        ContentResolver mContentResolver = InstrumentationRegistry.getTargetContext().getContentResolver();

        // Delete all records
        mContentResolver.delete(FeedContract.Entry.CONTENT_URI, null, null);
        int count = mContentResolver.query(FeedContract.Entry.CONTENT_URI, null, null, null, null).getCount();
        Assert.assertEquals(0, count);

        // Asks SyncAdapter to fetch it all again
        SyncUtils.TriggerRefresh();

        // Wait until it has fetched, up to the globalTimeout
        // We could add a listener or something, but this is simpler, and the test has to block anyway
        while (count == 0) {
            Thread.sleep(100);
            count = mContentResolver.query(FeedContract.Entry.CONTENT_URI, null, null, null, null).getCount();
        }

        Assert.assertNotEquals(0, count);
    }

}
