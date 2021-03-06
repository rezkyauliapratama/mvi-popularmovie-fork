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

import android.arch.lifecycle.LiveData
import ch.berta.fabio.popularmovies.features.base.ActivityResult
import ch.berta.fabio.popularmovies.features.grid.view.SelectedMovie
import com.jakewharton.rxrelay2.PublishRelay

interface GridViewModel {
    val transientClears: PublishRelay<Unit>
    val activityResults: PublishRelay<ActivityResult>
    val sortSelections: PublishRelay<Int>
    val movieSelections: PublishRelay<SelectedMovie>
    val loadMore: PublishRelay<Unit>
    val refreshSwipes: PublishRelay<Unit>
    val state: LiveData<MoviesState>
}