package com.example.films.features.films.presentation;


import com.example.films.features.films.data.DataSource;
import com.example.films.features.films.data.Loader;
import com.example.films.features.films.data.RepositoryImpl;
import com.example.films.features.films.data.db.DataSourceImpl;
import com.example.films.features.films.data.network.LoaderImpl;
import com.example.films.features.films.data.network.client.Api;
import com.example.films.features.films.data.network.client.Client;
import com.example.films.features.films.domain.Interactor;
import com.example.films.features.films.domain.InteractorImpl;
import com.example.films.features.films.domain.Repository;


public class PresenterFactory {

    static FilmsPresenter createPresenter(){

        final Api api = Client.getInstance().getApi();
        final DataSource dataSource = new DataSourceImpl();
        final Loader loader = new LoaderImpl(api);
        final Repository repository = new RepositoryImpl(dataSource, loader);
        final Interactor interactor = new InteractorImpl(repository);

        return new FilmsPresenter(interactor);
    }
}
