package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream().flatMap(movies -> movies.getVideos().stream()
                .map(video -> ImmutableMap.of("id", video.getId(), "title", video.getTitle(),
                        "time", video.getInterestingMoments().stream()
                                .filter(moment -> moment.getType().equalsIgnoreCase("middle"))
                                .findFirst().map(InterestingMoment::getTime).get(),
                        "url", video.getBoxarts().stream()
                                .reduce((video1,video2) -> {
                                    return video1.getWidth() < video2.getWidth() ? video1 : video2;
                                })
                                .map(BoxArt::getUrl).get()))).collect(Collectors.toList());
    }
}
