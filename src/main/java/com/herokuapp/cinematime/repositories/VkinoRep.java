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

@Slf4j
@Repository
public class VkinoRep {
    private final String VKINO = "https://vkino.com.ua";

    private List<Document> movieDocs;

    public List<Movie> getFilmsList() {
        setFilmDocumentsList();

        List<Movie> movies = new ArrayList<>();
        for (Document doc: movieDocs) {
            Movie movie = new Movie();

            movie.setName(doc.getElementsByClass("lazyload").attr("alt"));
            movie.setImg(doc.getElementsByClass("lazyload").attr("data-src"));
            movie.setDuration(getDuration(getFilmDataList(doc)));
            movie.setGenre(getFilmDataList(doc).first().text());
            movie.setDescription(doc.getElementsByClass("text-block").text());
            movie.setTrailer(getYoutubeCode(doc));

            movies.add(movie);
        }
        return movies;
    }

    private String getYoutubeCode(Document doc) {
        String link = doc.getElementsByClass("player-thumbnail").attr("src");
        return link.split("/")[4];
    }

    private Elements getFilmDataList(Document doc) {
        return doc.getElementsByClass("film-data-list")
                .get(0).getElementsByTag("dd");
    }

    private Integer getDuration(Elements elements) {
        if (elements.size()>1) {
            String[] temp = elements.get(1).text().split(" ");
            int hours = Integer.parseInt(temp[0]);
            int minutes = Integer.parseInt(temp[2]);
            return hours*60+minutes;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void setFilmDocumentsList() {
        String CINEMA = "kinotema-neoplaza";
        String FILMS_LIST = "/ua/filter/ajax-showtimes?cinema=";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(new URL(VKINO+FILMS_LIST+CINEMA), Map.class);
            Elements elements = Jsoup.parse((String) map.get("result"))
                    .normalise()
                    .getElementsByClass("tab-1");

            movieDocs = new ArrayList<>();
            Elements links = elements.get(0).getElementsByClass("film-logo-holder");

            for (Element link : links) {
                Document doc = getAboutFilm(link);
                movieDocs.add(doc);
            }
        } catch (IOException e) {
            log.error("Can't get movie list. Error details: {}", e.getMessage());
        }
    }

    private Document getAboutFilm(Element element) throws IOException {
        String link = VKINO+element.attr("href")+"?date=#";
        return Jsoup.connect(link)
                .get();
    }
}