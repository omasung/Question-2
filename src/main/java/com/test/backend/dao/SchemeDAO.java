package com.test.backend.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.backend.model.Scheme;

@Service
@Transactional
public class SchemeDAO {
	
	/**
	 * Dummy database. Initialize with some dummy values.
	 * @return
	 */
	private List<Scheme> schemeDatabase() {

		List<Scheme> scheme = new ArrayList<Scheme>();

		scheme.add(new Scheme("visa", "debit", "uba", "4812528568495704", 12));
		scheme.add(new Scheme("visa", "debit", "uba", "5842606347234274", 24));
		scheme.add(new Scheme("visa", "debit", "uba", "2647781411841719", 45));
		scheme.add(new Scheme("visa", "debit", "uba", "4320541540243344", 55));
		scheme.add(new Scheme("master", "credit", "zenith", "6765519974311979", 27));
		scheme.add(new Scheme("master", "credit", "zenith", "7001502525158985", 40));
		scheme.add(new Scheme("master", "credit", "zenith", "7425868116792045", 16));
		scheme.add(new Scheme("master", "credit", "zenith", "8485900120655945", 78));
		scheme.add(new Scheme("verve", "debit", "gtb", "6072556383712448", 115));
		scheme.add(new Scheme("verve", "debit", "gtb", "7669225969791173", 22));
		scheme.add(new Scheme("verve", "debit", "gtb", "6546225617385211", 84));
		scheme.add(new Scheme("verve", "debit", "gtb", "1735840336749937", 54));

		return scheme;

	}
	
	/**
	 * Method to convert a list to stream
	 * @param <T>
	 * @param list
	 * @return
	 */
	private static <T> Stream<T> listToStream(List<T> list) {

		return list.stream();
	}

	/**
	 * Return scheme object for given pan from dummy database. If scheme is not found for id, returns null. 
	 * @param pan
	 * @return
	 */
	public Scheme verifyCard(String pan) {

		SchemeDAO schemeDAO = new SchemeDAO();
		Scheme scheme = null;

		try {

			scheme = schemeDAO.schemeDatabase().stream().filter(x -> pan.equals(x.getPan())).findAny().orElse(null);

		} catch (NullPointerException | IndexOutOfBoundsException e) {

			e.printStackTrace();
		}

		return scheme;
	}

	/**
	 * Fetch a specific range of scheme with start and limit	
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Scheme> numberOfHits(int start, int limit) {

		List<Scheme> schemeList = new ArrayList<>();

		try {

			SchemeDAO schemeDAO = new SchemeDAO();
			schemeList = listToStream(schemeDAO.schemeDatabase()).collect(Collectors.toList()).subList(start - 1, limit);

		} catch (NullPointerException | IndexOutOfBoundsException e) {

			e.printStackTrace();
		}

		return schemeList;
	}

	/**
	 * 	Get the size of the scheme
	 * @return
	 */
	public Integer schemeSize() {

		int totalScheme = 0;

		try {

			SchemeDAO schemeDAO = new SchemeDAO();
			totalScheme = (int) schemeDAO.schemeDatabase().stream().count();

		} catch (NullPointerException | IndexOutOfBoundsException e) {

			e.printStackTrace();
		}

		return totalScheme;
	}	

}
