package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(movies -> movies.getVideos().stream()
                        .map(videos -> ImmutableMap.of("id", videos.getId(),"title", videos.getTitle(),
                                "boxart Url", videos.getBoxarts().stream()
                                        .reduce((box1, box2) -> box1.getWidth() < box2.getWidth() ? box1 : box2)
                                        .map(box -> box.getUrl()).get()))
                ).collect(Collectors.toList());
    }
}
