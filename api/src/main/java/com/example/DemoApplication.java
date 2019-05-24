package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		try {
			Document document = Jsoup.connect("https://www.imdb.com/movies-in-theaters/").userAgent("Mozilla/17.0").get();
			Elements temp = document.select("tbody");
			int i = 0;

			for (Element movieList: temp) {
				if (movieList.childNodeSize() == 5) {
					i++;
					System.out.println("----------------------------------------------------------");
					System.out.println(i + ":");
					System.out.println("Title: " + movieList.getElementsByTag("h4").text());
					System.out.println("Duration: " + movieList.getElementsByTag("time").text());

					String genders = "";
					for (Element gender : movieList.getElementsByTag("span")) {
						if (!gender.hasClass("ghost")
								&& !gender.hasClass("certRating")
								&& gender.parent().hasClass("cert-runtime-genre")) genders = genders.concat(gender.text() + " ");
					}
					System.out.println("Genders: " + genders);
					System.out.println("Rating: " + movieList.getElementsByClass("metascore").text());

					System.out.println("Director: " + movieList.getElementsByClass("txt-block").first().getElementsByTag("span").first().text());
					String actors = "";
					for (Element actor : movieList.getElementsByClass("txt-block").get(1).getElementsByTag("a")) {
						actors = actors.concat(actor.text());
					}
					System.out.println("Actors: " + actors);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
