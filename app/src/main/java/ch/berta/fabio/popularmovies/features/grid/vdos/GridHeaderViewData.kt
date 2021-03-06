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

package ch.berta.fabio.popularmovies.features.grid.vdos

import android.databinding.BaseObservable
import android.databinding.Bindable
import ch.berta.fabio.popularmovies.BR

class GridHeaderViewData : BaseObservable() {
    @get:Bindable
    var movieSelected: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.movieSelected)
        }

    @get:Bindable
    var favoured: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.favoured)
        }
}