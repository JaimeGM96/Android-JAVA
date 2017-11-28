package com.jaimegm.peliculasapp.rest;

import com.jaimegm.peliculasapp.model.Pelicula;
import com.jaimegm.peliculasapp.model.PeliculaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("movie/top_rated")
    Call<PeliculaResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String Language);

    @GET("movie/{id}")
    Call<Pelicula> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String Language);

    @GET("movie/{id}/similar")
    Call<PeliculaResponse> getSimilarMovies(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String Language);

    @GET("movie/now_playing")
    Call<PeliculaResponse> getMoviesNowPlaying(@Query("api_key") String apiKey, @Query("language") String Language);

    @GET("search/person")
    Call<PeliculaResponse> getPeople(@Query("api_key") String apiKey, @Query("query") String nombre);
}
