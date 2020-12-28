package com.herokuapp.cinematime.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herokuapp.cinematime.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class VkinoRepository {
    private final String VKINO = "https://vkino.com.ua";

    @SuppressWarnings("unchecked")
    public List<Movie> getFilmsList() {
        final String CINEMA = "kinotema-neoplaza";
        final String FILMS_LIST = "/ua/filter/ajax-showtimes?cinema=";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(new URL(VKINO+FILMS_LIST+CINEMA), Map.class);
            Elements elements = Jsoup.parse((String) map.get("result"))
                    .normalise()
                    .getElementsByClass("tab-1");

            List<Movie> movies = new ArrayList<>();

            Elements names = elements.get(0).getElementsByClass("film-name");
            Elements genres = elements.get(0).getElementsByClass("sub-title");
            Elements trailers = elements.get(0).getElementsByClass("btn-watch-trailer popup-video");
            Elements posters = elements.get(0).getElementsByClass("lazyload");
            Elements links = elements.get(0).getElementsByClass("film-logo-holder");

            for (int i = 0; i < names.size(); i++) {
                Movie movie = new Movie();
                Document doc = getAboutFilm(links.get(i));

                movie.setName(names.get(i).text());
                movie.setGenre(genres.get(i).text());
                movie.setTrailer(getTrailer(trailers.get(i)));
                movie.setImg(getImg(posters.get(i)));
                movie.setDescription(getDescription(doc));
                movie.setDuration(getDuration(doc));
                movies.add(movie);
            }
            return movies;
        } catch (IOException e) {
            log.error("Can't get movie list. Error details: {}", e.getMessage());
        }
        return null;
    }

    private Document getAboutFilm(Element element) throws IOException {
        String link = VKINO+element.attr("href")+"?date=#";
        return Jsoup.connect(link)
                .get();
    }

    private String getTrailer (Element element) {
        return element.attr("href");
    }

    private String getImg(Element element) {
        String temp = element
                .attr("data-srcset")
                .split(",")[1]
                .trim();
        return temp.substring(0, (temp.length()-3));
    }

    private String getDescription (Document doc) {
        return doc.getElementsByClass("text-block").text();
    }

    private Integer getDuration(Document doc) {
        Elements elements = doc.getElementsByClass("film-data-list")
                .get(0).getElementsByTag("dd");
        if (elements.size()>1) {
            String[] temp = elements.get(1).text().split(" ");
            int hours = Integer.parseInt(temp[0]);
            int minutes = Integer.parseInt(temp[2]);
            return hours*60+minutes;
        }
        return null;
    }
}
