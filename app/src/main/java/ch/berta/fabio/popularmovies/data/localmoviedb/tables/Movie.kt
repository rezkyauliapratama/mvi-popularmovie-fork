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

package ch.berta.fabio.popularmovies.data.localmoviedb.tables

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.reactivex.Flowable
import java.util.*

@Entity(tableName = "movie")
data class MovieEntity(
        @PrimaryKey val id: Int,
        val title: String,
        @ColumnInfo(name = "release_date") val releaseDate: Date,
        @ColumnInfo(name = "vote_average") val voteAverage: Double,
        val overview: String,
        val poster: String,
        val backdrop: String
)

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getById(id: Int): Flowable<MovieEntity>

    @Query("SELECT EXISTS(SELECT id FROM movie WHERE id = :id)")
    fun existsById(id: Int): Flowable<Int>

    @Insert(onConflict = REPLACE)
    fun insert(movie: MovieEntity): Long

    @Update
    fun update(movie: MovieEntity)

    @Query("DELETE FROM movie WHERE id = :id")
    fun deleteById(id: Int): Int
}
