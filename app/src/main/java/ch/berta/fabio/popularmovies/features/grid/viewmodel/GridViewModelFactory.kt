/*
 * Copyright (c) 2017 Fabio Berta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.berta.fabio.popularmovies.features.grid.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ch.berta.fabio.popularmovies.data.MovieStorage
import ch.berta.fabio.popularmovies.data.SharedPrefs
import ch.berta.fabio.popularmovies.features.grid.Sort

class GridViewModelFactory(
        private val sharedPrefs: SharedPrefs,
        private val movieStorage: MovieStorage,
        private val useTwoPane: Boolean,
        private val sortOptions: List<Sort>
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (useTwoPane) {
            return GridViewModelTwoPane(sharedPrefs, movieStorage, sortOptions) as T
        }

        @Suppress("UNCHECKED_CAST")
        return GridViewModelOnePane(sharedPrefs, movieStorage, sortOptions) as T
    }
}