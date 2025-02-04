package interface_adapter.search_results;

import entity.CommonMovie;
import use_case.search_results.SearchResultsInputBoundary;
import use_case.search_results.SearchResultsInputData;

/**
 * Controller for the Search Results Use Case.
 */
public class SearchResultsController {
    private final SearchResultsInputBoundary searchResultsInteractor;

    public SearchResultsController(SearchResultsInputBoundary searchResultsInteractor) {
        this.searchResultsInteractor = searchResultsInteractor;
    }

    /**
     * Executes the Search Results Use Case.
     * @param title the title of the movie
    */
    public void execute(String title) {
        final SearchResultsInputData searchResultsInputData = new SearchResultsInputData(title);
        searchResultsInteractor.execute(searchResultsInputData);
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        searchResultsInteractor.switchToHomeView();
    }

    /**
     * Executes the "switch to MovieView" Use Case.
     * @param user the user who is searching a movie
     * @param searchResult the result of the search query.
     */
    public void switchToMovieView(String user, CommonMovie searchResult) {
        searchResultsInteractor.switchToMovieView(user, searchResult);
    }
}
