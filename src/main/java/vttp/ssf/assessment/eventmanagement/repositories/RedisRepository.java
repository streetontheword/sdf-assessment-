package vttp.ssf.assessment.eventmanagement.repositories;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	// TODO: Task 2

	public void saveRecord(String retrieved, List<Event> eventList) {
		ListOperations<String, String> list = redisTemplate.opsForList();
		eventList.stream()
				.forEach(event -> {
					String rec = "%s,%d,%d,%d".formatted(event.getEventName(), event.getEventId(), event.getEventSize(),
							event.getParticipants());
					list.leftPush(retrieved, rec);
				});


	}

	// TODO: Task 3

	public Long getNumberOfEvents(String retrieved) {

		Long sizeOfEvent = redisTemplate.opsForList().size(retrieved);

		return sizeOfEvent;

	}

	// TODO: Task 4

	public List<Event> retrieveEvents(String retrieved) {
		ListOperations<String, String> list = redisTemplate.opsForList();
		Long size = list.size(retrieved);

		List<Event> listOfEvents = new LinkedList<>();
		for (String i : list.range(retrieved, 0, size)) {
			String[] terms = i.split(",");
			Event event = new Event();
			event.setEventName(terms[0]);
			event.setEventId(Integer.parseInt(terms[1]));
			event.setEventSize(Integer.parseInt(terms[2]));
			event.setParticipants(Integer.parseInt(terms[2]));
			listOfEvents.add(event);
		}
		return listOfEvents;
	}
}

