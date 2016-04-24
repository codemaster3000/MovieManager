package moviecode.movieinfo;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.enumeration.SearchType;
import com.omertron.themoviedbapi.model.artwork.Artwork;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import moviecode.helpers.AppConfig;

/**
 *
 * @author fabian
 */
public class TmdbInfo {

    private TheMovieDbApi tmdb;
    private AppConfig cfg = AppConfig.getInstance();

    private static final String LANG = "de";

    public TmdbInfo(String apikey) throws MovieDbException {
        // setup tmdb
        tmdb = new TheMovieDbApi(apikey);
    }
    
    public void getMovieInfoByID(int movieId) throws MovieDbException{
        MovieInfo inf = tmdb.getMovieInfo(movieId, LANG, null);
        System.out.println("Get Movie by ID: " + inf.getTitle());
    }
    
    public void getMovieSearchResults(String searchKeyword, int year) throws MovieDbException{
        ResultList<MovieInfo> results = tmdb.searchMovie(searchKeyword, null, LANG, true, year, null, SearchType.PHRASE);
        System.out.println("SearchResults: " + results.getTotalResults());
        for (int i = 0; i < results.getTotalResults(); i++) {
            System.out.println("Result #" + i + ": " + results.getResults().get(i).getTitle() + "(" + results.getResults().get(i).getReleaseDate() + ")");
        }   
    }
    
    public void showMovieCoverPopup(int movieId) throws MovieDbException{
        ResultList<Artwork> imgs = tmdb.getMovieImages(movieId, "en");

        try {
            String path = cfg.TMDB_IMAGEROOTPATH + imgs.getResults().get(0).getFilePath();
            URL url = new URL(path);
            BufferedImage image = ImageIO.read(url);
            JLabel label = new JLabel(new ImageIcon(image));
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.getContentPane().add(label);
            f.pack();
            f.setLocation(200, 200);
            f.setVisible(true);
        } catch (Exception exp) {
            exp.printStackTrace();
        }    
    }
}
