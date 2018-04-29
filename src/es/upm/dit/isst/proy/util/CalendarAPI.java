package es.upm.dit.isst.proy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

public class CalendarAPI {

	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.home"), ".credentials/credentials-proy.googleapis-java");
	private static FileDataStoreFactory DATA_STORE_FACTORY;
	private static JacksonFactory JACKSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	private static CalendarAPI instance;
	private CalendarAPI() {}
	public static CalendarAPI getInstance() {
		if(instance==null) {
			instance = new CalendarAPI();
		}
		return instance;
	}

	private Credential getClientCredentials(String userId) throws IOException {		
		Credential credential = flow().loadCredential(userId);
		return credential;
	}

	private Calendar getCalendarService(String userId) throws IOException {
		return new Calendar.Builder(
				HTTP_TRANSPORT, JACKSON_FACTORY, getClientCredentials(userId))
				.build();
	}

	public String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath(req.getContextPath() + "/oauth2callback");
		return url.build();
	}

	public GoogleAuthorizationCodeFlow flow() throws IOException {
		// Load client secrets
		InputStream in = CalendarAPI.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

		DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		return new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JACKSON_FACTORY, clientSecrets,
				Collections.singleton(CalendarScopes.CALENDAR)
				).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
	}

	public Events getEvents(String userId, String id) throws IOException {
		DateTime time = new DateTime("2018-01-01T00:00:00-00:00");
		return getCalendarService(userId).events().list("primary")
				.setCalendarId(id)
				.setTimeMin(time)
				.setOrderBy("startTime")
				.setSingleEvents(true)
				.execute();
	}

	public Event insertEvents(String userId, String calendarId, String title, String description, String initialDate) throws IOException {
		//Modificar los parametros
		Event event = new Event()
				.setSummary(title)
				.setDescription(description);

		//Parsear la fecha
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
		Date dateSale = null;
		try {
			dateSale = dt.parse(initialDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		DateTime startDateTime = new DateTime(dt1.format(dateSale));
		EventDateTime start = new EventDateTime()
				.setDateTime(startDateTime)
				.setTimeZone("Europe/Madrid");
		event.setStart(start);

		DateTime endDateTime = new DateTime(dt1.format(dateSale));
		EventDateTime end = new EventDateTime()
				.setDateTime(endDateTime)
				.setTimeZone("Europe/Madrid");
		event.setEnd(end);

		getCalendarService(userId).events().insert(calendarId, event).execute();
		return event;
	}

	public List<CalendarListEntry> getCalendars(String userId) throws IOException {
		List<CalendarListEntry> items =  null;
		String pageToken = null;
		do {
			CalendarList calendarList = getCalendarService(userId).calendarList().list().setPageToken(pageToken).execute();
			items = calendarList.getItems();
			pageToken = calendarList.getNextPageToken();
		} while (pageToken != null);
		return items;
	}

	public String insertCalendars(String userId, String idCalendario) throws IOException {
		// Create a new calendar
		com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
		calendar.setSummary(idCalendario);

		// Insert the new calendar
		com.google.api.services.calendar.model.Calendar createdCalendar = getCalendarService(userId).calendars().insert(calendar).execute();
		return createdCalendar.getId();

	}


}
