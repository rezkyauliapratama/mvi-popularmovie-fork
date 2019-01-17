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

package ch.berta.fabio.popularmovies.features.details.component

import ch.berta.fabio.popularmovies.data.dtos.YOU_TUBE
import io.reactivex.Observable

fun intention(sources: DetailsSources): Observable<DetailsAction> {
    val transientClears = sources.uiEvents.transientClears.map { DetailsAction.TransientClear }
    val movieSelections = sources.uiEvents.movieSelections
            .distinctUntilChanged()
            .map { DetailsAction.MovieSelected(it) }
    val sortSelections = sources.uiEvents.sortSelections
            .skip(1)
            .distinctUntilChanged()
            .map { DetailsAction.SortSelection }
    val favClicks = sources.uiEvents.favClicks.map { DetailsAction.FavClick }
    val updateSwipes = sources.uiEvents.updateSwipes.map { DetailsAction.UpdateSwipe }
    val videoClicks = sources.uiEvents.videoClicks
            .filter { it.site == YOU_TUBE }
            .map { DetailsAction.VideoClick(it) }

    val actions = listOf(transientClears, movieSelections, sortSelections, favClicks, updateSwipes, videoClicks)
    return Observable.merge(actions)
}