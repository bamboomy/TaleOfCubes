/*
 * Copyright (c) 2016 Sander Theetaert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.bamboomy.taleofcubes.splash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import org.bamboomy.taleofcubes.R;
import org.bamboomy.taleofcubes.main.MenuActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gold_dice);

        new Timer().execute(null, null);
    }

    private class Timer extends AsyncTask {

        @Override
        protected Object doInBackground(Object... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // I don't care
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            setContentView(R.layout.digart);

            new Blinker(4, false).execute(null, null);
        }

    }

    private class Blinker extends AsyncTask {

        private ImageView splash;

        private boolean cursor;

        private int counter;

        public Blinker(int i, boolean c) {
            counter = i;
            cursor = c;
        }

        @Override
        protected Object doInBackground(Object... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // I don't care
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            Log.d("blinker", "blinkin...");

            splash = findViewById(R.id.digart);

            if (!cursor) {
                splash.setImageResource(R.drawable.dig_art_r0ks_1);
            } else {
                splash.setImageResource(R.drawable.dig_art_r0ks_0);
            }

            cursor = !cursor;

            counter--;

            if (counter > 0) {
                new Blinker(counter, cursor).execute(null, null);
            } else {

                Intent myIntent = new Intent(SplashActivity.this,
                        MenuActivity.class);
                startActivity(myIntent);
            }
        }
    }
}
