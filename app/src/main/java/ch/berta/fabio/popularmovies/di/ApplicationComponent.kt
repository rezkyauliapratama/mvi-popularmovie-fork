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

package ch.berta.fabio.popularmovies.di

import android.app.Application
import android.content.SharedPreferences
import ch.berta.fabio.popularmovies.data.themoviedb.TheMovieDbService
import ch.berta.fabio.popularmovies.data.localmoviedb.MovieDb
import ch.berta.fabio.popularmovies.di.modules.ApplicationModule
import ch.berta.fabio.popularmovies.di.modules.MovieServiceModule
import ch.berta.fabio.popularmovies.features.details.view.DetailsActivity
import ch.berta.fabio.popularmovies.features.grid.view.GridActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Defines the dependency injection component for the Application.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, MovieServiceModule::class))
interface ApplicationComponent {

    val application: Application

    val movieDb: MovieDb

    val sharedPreferences: SharedPreferences

    val theMovieDbService: TheMovieDbService

    fun inject(gridActivity: GridActivity)

    fun inject(detailsActivity: DetailsActivity)
}
