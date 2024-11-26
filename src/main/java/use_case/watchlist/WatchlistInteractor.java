package use_case.watchlist;

import data_access.DBUserDataAccessObject;
import entity.User;
import entity.UserFactory;

/**
 * The Watchlists Interactor.
 */
public class WatchlistInteractor implements WatchlistInputBoundary {
    private final DBUserDataAccessObject userDataAccessObject;
    private final WatchlistOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public WatchlistInteractor(DBUserDataAccessObject watchlistDataAccessInterface,
                               WatchlistOutputBoundary watchlistOutputBoundary,
                               UserFactory userFactory) {
        this.userDataAccessObject = watchlistDataAccessInterface;
        this.userPresenter = watchlistOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void switchToHomeView() {
        userPresenter.switchToHomeView();
    }

    /**
     * Executes the switch to watchlist view use case.
     *
     * @param username name of the user that is currently logged in
     */
    @Override
    public void switchToWatchlistsView(String username) {
        final User currentUser = this.userDataAccessObject.get(username);
        userPresenter.switchToWatchlistsView(currentUser);
    }

    /**
     * Executes the switch to PWL view use case.
     *
     * @param currentUser user that is currently logged in
     */
    @Override
    public void switchToPWL(User currentUser) {
        userPresenter.switchToPWLView(currentUser);
    }
}
