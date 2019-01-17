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

package ch.berta.fabio.popularmovies.features.details.vdos.rows

import android.databinding.BaseObservable
import android.databinding.Bindable
import ch.berta.fabio.popularmovies.BR
import ch.berta.fabio.popularmovies.R

data class DetailsInfoRowViewData(
        @get:Bindable val poster: String?,
        @get:Bindable val date: String?,
        @get:Bindable val rating: Double?,
        @get:Bindable val plot: String?,
        override val viewType: Int = R.layout.row_details_info
) : BaseObservable(), DetailsRowViewData {

    @get:Bindable
    var transitionEnabled = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.transitionEnabled)
        }
}