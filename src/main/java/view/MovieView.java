package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

import javax.swing.*;

import interface_adapter.movie.MovieController;
import interface_adapter.movie.MovieState;
import interface_adapter.movie.MovieViewModel;

/**
 * The View for when the user views a movie.
 */
public class MovieView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Movie Information";

    private final MovieViewModel movieViewModel;
    private MovieController movieController;

    // buttons
    private final JButton home;
    private final JButton watchedButton;
    private final JButton addToListButton;
    private final JButton userReviewsButton;

    // labels for info
    private final JLabel titleLabel;
    private final JLabel posterLabel;
    private final JLabel overviewLabel;
    private final JLabel genreLabel;
    private final JLabel ourRatingsLabel;
    private final JLabel voterAverageLabel;

    public MovieView(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
        this.movieViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(MovieViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // home button
        this.home = new JButton(MovieViewModel.HOME_LABEL);
        home.addActionListener(
                evt -> movieController.switchToHomeView()
        );

        final JPanel homeButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        homeButton.add(home);
        homeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // title and poster panel

        this.titleLabel = new JLabel(MovieViewModel.MOVIE_LABEL);
        // Center align the poster
        this.posterLabel = new JLabel();
        this.posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // middle panel with info about movie
        this.overviewLabel = new JLabel(MovieViewModel.OVERVIEW_LABEL);
        this.genreLabel = new JLabel(MovieViewModel.GENRE_LABEL);
        this.ourRatingsLabel = new JLabel(MovieViewModel.OUR_RATINGS_LABEL);
        this.voterAverageLabel = new JLabel(MovieViewModel.VOTER_AVERAGE_LABEL);

        final JPanel infoPanel = new JPanel();
        infoPanel.add(titleLabel);
        infoPanel.add(overviewLabel);
        infoPanel.add(genreLabel);
        infoPanel.add(ourRatingsLabel);
        infoPanel.add(voterAverageLabel);

        // Poster Panel
        final JPanel posterPanel = new JPanel();
        posterPanel.add(this.posterLabel);

        // bottom buttons
        // TODO add functionality for the bottom buttons
        this.watchedButton = new JButton(MovieViewModel.PWL_LABEL);
        this.addToListButton = new JButton(MovieViewModel.ADD_TO_LIST_LABEL);
        this.userReviewsButton = new JButton(MovieViewModel.USER_REVIEWS_LABEL);

        final JPanel bottomButtons = new JPanel();
        bottomButtons.add(watchedButton);
        bottomButtons.add(addToListButton);
        bottomButtons.add(userReviewsButton);

        // add all components
        this.add(homeButton);
        this.add(title);
        this.add(infoPanel);
        this.add(posterPanel);
        this.add(bottomButtons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MovieState state = (MovieState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(MovieState state) {
        //
    }

    public void setMovieController(MovieController movieController) {
        this.movieController = movieController;
    }

    private void setPoster(String posterUrl) {
        try {
            // Load image from URL
            final ImageIcon icon = new ImageIcon(new URL(posterUrl));
            // Optionally scale the image to fit the JLabel size
            final Image scaledImage = icon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
            this.posterLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            // Set a default placeholder if the image fails to load
            this.posterLabel.setText("Poster not available");
            e.printStackTrace();
        }
    }


}
