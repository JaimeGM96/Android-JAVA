package com.jaimegm.peliculasapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jaimegm.peliculasapp.R;
import com.jaimegm.peliculasapp.adapter.AdaptadorPelicula;
import com.jaimegm.peliculasapp.model.Pelicula;
import com.jaimegm.peliculasapp.model.PeliculaResponse;
import com.jaimegm.peliculasapp.rest.ApiClient;
import com.jaimegm.peliculasapp.rest.ApiInterface;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaPeliculas extends Fragment {

    private final static String API_KEY = "cd9b31ae50149fdf24f9642d2d0c08c4";
    private String Language = "es-ES";
    private ArrayList<Pelicula> datos;

    @BindView(R.id.peliculas)
    protected RecyclerView recView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = LayoutInflater.from(getContext()).inflate(R.layout.lista_peliculas, container, false);
        ButterKnife.bind(this, content);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<PeliculaResponse> call = apiService.getMoviesNowPlaying(API_KEY, Language);
        call.enqueue(new Callback<PeliculaResponse>() {
            @Override
            public void onResponse(Call<PeliculaResponse> call, Response<PeliculaResponse> response) {
                int statusCode = response.code();

                datos = response.body().getResults();
                recView.setHasFixedSize(true);
                recView.setLayoutManager(new LinearLayoutManager(getContext()));
                recView.setAdapter(new AdaptadorPelicula(datos, getFragmentManager()));

            }

            @Override
            public void onFailure(Call<PeliculaResponse> call, Throwable t) {

            }
        });
        return content;
    }
}
