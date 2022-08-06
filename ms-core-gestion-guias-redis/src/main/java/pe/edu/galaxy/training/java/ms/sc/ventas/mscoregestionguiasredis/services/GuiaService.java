package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.services;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionguiasredis.domain.Guia;

@Slf4j
@Repository
@SuppressWarnings("rawtypes")
public class GuiaService {

	public static final String GUIA_KEY = "GUIA";

	private HashOperations hashOperations;// PreparedStatemnt

	private RedisTemplate redisTemplate;  // Connection

	public GuiaService(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash(); //K,V
	}

	@SuppressWarnings("unchecked")
	public void save(Guia guia) {
		log.info("save...");
		hashOperations.put(GUIA_KEY, guia.getId(), guia);
	}

	@SuppressWarnings("unchecked")
	public List<Guia> findAll() {
		log.info("findAll...");
		return hashOperations.values(GUIA_KEY);
	}

	@SuppressWarnings("unchecked")
	public Guia findById(String id) {
		log.info("findById...");
		return (Guia) hashOperations.get(GUIA_KEY, id);
	}

	
	public void update(Guia guia) {
		log.info("update...");
		save(guia);
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) {
		log.info("delete...");
		hashOperations.delete(GUIA_KEY, id);
	}
}
