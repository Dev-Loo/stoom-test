package com.stoom.demo.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stoom.demo.api.advice.exception.CustomNotFoundException;
import com.stoom.demo.api.handler.ClientResponseHandler;
import com.stoom.demo.api.model.Adress;
import com.stoom.demo.api.repository.AdressRepository;

@Service
public class AdressService {

	private AdressRepository repo;
	
	private final String apiKey = "AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";
	
	@Autowired
	public AdressService(AdressRepository repo) {
		this.repo = repo;
	}
	
	@Transactional
	public Iterable<Adress> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Adress findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new CustomNotFoundException(String.format("Adress with id = %1$s not found", id.toString())));
	}
	
	@Transactional
	public Adress saveAdress(Adress ad) {
		setLatLngIfNeeded(ad);
		repo.save(ad);
		return ad;
	}
	
	@Transactional
	public Adress updateAdress(Long id, Adress ad) {
		Adress adToUpdate = findById(id);
		setLatLngIfNeeded(ad);
		BeanUtils.copyProperties(ad, adToUpdate, "id");
		repo.save(adToUpdate);
		return adToUpdate;
	}
	
	@Transactional
	public void deleteAdress(Long id) {
		Adress adToDelete = findById(id);
		repo.delete(adToDelete);
	}
	
	private String buildGeocodingRequestString(Adress ad) {
		String streetName = ad.getStreetName().replace(' ', '+');
		String city = ad.getCity().replace(' ', '+');
		String state = ad.getState();
		String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%1$s+%2$s,+%3$s,+%4$s&key=%5$s", ad.getNumber().toString(), streetName, city, state, apiKey);
		return url;
	}
	
	private void setLatLngIfNeeded(Adress ad) {
		if (ad.getLatitude() == null || ad.getLongitude() == null) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setErrorHandler(new ClientResponseHandler());
			JsonNode response = restTemplate.getForObject(buildGeocodingRequestString(ad), JsonNode.class);
			if(!response.get("status").asText().equals("OK"))
				throw new CustomNotFoundException("Geolocation not found with given info");
			ArrayNode arrNode = (ArrayNode) response.get("results");
			ad.setLatitude(arrNode.get(0).at("/geometry/location/lat").asDouble());
			ad.setLongitude(arrNode.get(0).at("/geometry/location/lng").asDouble());
		}
	}
	
}
