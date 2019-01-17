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

package ch.berta.fabio.popularmovies.features.grid.view

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.berta.fabio.popularmovies.R
import ch.berta.fabio.popularmovies.databinding.RowMovieBinding
import ch.berta.fabio.popularmovies.features.base.BaseBindingViewHolder
import ch.berta.fabio.popularmovies.features.common.viewholders.DefaultViewHolder
import ch.berta.fabio.popularmovies.features.grid.vdos.rows.GridRowMovieViewData
import ch.berta.fabio.popularmovies.features.grid.vdos.rows.GridRowViewData
import ch.berta.fabio.popularmovies.setHeight
import com.jakewharton.rxrelay2.PublishRelay
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

class MovieViewHolder(binding: RowMovieBinding) : BaseBindingViewHolder<RowMovieBinding>(binding)

@PaperParcel
data class SelectedMovie @JvmOverloads constructor(
        val id: Int,
        val title: String,
        val releaseDate: String,
        val overview: String,
        val voteAverage: Double,
        val poster: String?,
        val backdrop: String?,
        val fromFav: Boolean,
        @Transient val posterView: View? = null
) : PaperParcelable {
    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR = PaperParcelSelectedMovie.CREATOR
    }
}

/**
 * Provides the adapter for a movie poster images grid.
 */
class GridRecyclerAdapter(
        private val posterHeight: Int,
        val movieSelections: PublishRelay<SelectedMovie>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movies = mutableListOf<GridRowViewData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.row_movie -> RowMovieBinding.inflate(inflater, parent, false).let {
                MovieViewHolder(it).apply {
                    binding.root.setOnClickListener {
                        val rowData = movies[adapterPosition] as GridRowMovieViewData
                        val selectedMovie = SelectedMovie(
                                rowData.id,
                                rowData.title,
                                rowData.releaseDate,
                                rowData.overview,
                                rowData.voteAverage,
                                rowData.poster,
                                rowData.backdrop,
                                rowData.fromFav,
                                binding.ivPoster
                        )
                        movieSelections.accept(selectedMovie)
                    }
                }
            }
            R.layout.row_progress -> inflater.inflate(R.layout.row_progress, parent, false).let {
                DefaultViewHolder(it)
            }
            else -> throw RuntimeException("there is no type that matches the type $viewType, " +
                    "make sure you are using types correctly")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == R.layout.row_movie) {
            val movieRow = holder as MovieViewHolder
            movieRow.binding.flContainer.setHeight(posterHeight)
            movieRow.binding.viewData = movies[position] as GridRowMovieViewData
            movieRow.binding.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int = movies[position].viewType

    override fun getItemCount(): Int = movies.size

    fun swapData(newMovies: List<GridRowViewData>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = movies[oldItemPosition]
                val newItem = newMovies[newItemPosition]

                return when {
                    oldItem.viewType != newItem.viewType -> false
                    oldItem.viewType == R.layout.row_progress -> true
                    else -> (oldItem as GridRowMovieViewData).id == (newItem as GridRowMovieViewData).id
                }
            }

            override fun getOldListSize(): Int = movies.size

            override fun getNewListSize(): Int = newMovies.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    movies[oldItemPosition] == newMovies[newItemPosition]
        })

        movies.clear()
        movies.addAll(newMovies)
        diffResult.dispatchUpdatesTo(this)
    }
}
