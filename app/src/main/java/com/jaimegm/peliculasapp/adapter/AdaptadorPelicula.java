package com.jaimegm.peliculasapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jaimegm.peliculasapp.R;
import com.jaimegm.peliculasapp.activity.DetallesPeliculas;
import com.jaimegm.peliculasapp.model.Pelicula;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdaptadorPelicula
        extends RecyclerView.Adapter<AdaptadorPelicula.ViewHolder>{

    private ArrayList<Pelicula> datos;
    private FragmentManager fragmentManager;

    public class ViewHolder
            extends RecyclerView.ViewHolder {

        private Context context;
        private FragmentManager fragmentManager;

        @BindView(R.id.caratula)
        protected ImageView Caratula;

        protected Pelicula movie;

        public ViewHolder(View itemView, Context context, FragmentManager fragmentManager) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            this.fragmentManager = fragmentManager;
        }

        public void update(Pelicula t) {
            this.movie = t;

            Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).into(Caratula);
        }

        @OnClick(R.id.list_item)
        public void OnClick(){
            Fragment fragment = new DetallesPeliculas();
            Bundle bundle = new Bundle();
            bundle.putInt("movie_id", this.movie.getId());
            fragment.setArguments(bundle);
            this.fragmentManager.beginTransaction().replace(R.id.content_main, fragment).addToBackStack("Fragment_Detalles").commit();
        }
    }

    public AdaptadorPelicula(ArrayList<Pelicula> datos, FragmentManager fragmentManager) {this.datos = datos; this.fragmentManager = fragmentManager;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem_peliculas, viewGroup, false);

        ViewHolder tvh = new ViewHolder(itemView, viewGroup.getContext(), this.fragmentManager);

        return tvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        Pelicula item = datos.get(pos);

        viewHolder.update(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

}
