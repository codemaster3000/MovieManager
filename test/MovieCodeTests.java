import com.omertron.themoviedbapi.MovieDbException;
import moviecode.helpers.AppConfig;
import moviecode.movieinfo.TmdbInfo;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MovieCodeTests {

    @Test
    public void configTests() {
        //AppConfig cfg = AppConfig.getInstance();

        //assertNotNull("check if api key exist",cfg.API_KEY);
        //assertNotNull("check if tmdb image path exist",cfg.TMDB_IMAGEROOTPATH);
    }
    
    @Test
    public void movieInfoTests() throws MovieDbException {
        // MyClass is tested
        //TmdbInfo test = new TmdbInfo(cfg.API_KEY);

        //assertNotNull("",test.getMovieInfoByID(9802));
    }

}
