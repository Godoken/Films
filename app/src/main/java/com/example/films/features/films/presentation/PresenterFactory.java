package com.example.films.features.films.presentation;



import com.example.films.features.films.data.*;
import com.example.films.features.films.domain.Interactor;
import com.example.films.features.films.domain.InteractorImpl;
import com.example.films.features.films.domain.Repository;


public class PresenterFactory {

    static FilmsPresenter createPresenter(){

        final DataSource dataSource = new DataSourceImpl();
        final Loader loader = new LoaderImpl();
        final Repository repository = new RepositoryImpl(dataSource, loader);
        final Interactor interactor = new InteractorImpl(repository);

        return new FilmsPresenter(interactor);
    }
}
