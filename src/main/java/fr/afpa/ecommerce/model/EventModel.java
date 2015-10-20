package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.BookEvent;
import fr.afpa.ecommerce.bean.Event;
import fr.afpa.ecommerce.jdbc.ConnectionFactory;
import fr.afpa.ecommerce.jdbc.ConnectionUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Riad YOUSFI
 */
public class EventModel implements Crud<Event> {

    public EventModel() {
    }

    @Override
    public Integer save(Event event) throws IOException, SQLException, ClassNotFoundException {

        String req = "INSERT INTO event (name, start_date, end_date) VALUES (?,?,?)";

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, event.getName());
        pstm.setDate(2, new Date(event.getStartDate().getTime()));
        pstm.setDate(3, new Date(event.getEndDate().getTime()));

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        Integer id = null;

        try (ResultSet rs = pstm.getGeneratedKeys()) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
            ConnectionUtil.close(rs);
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return id;
    }

    @Override
    public Event find(Integer id) throws IOException, SQLException, ClassNotFoundException {
        Event event = null;

        String req = "SELECT id, name, start_date, end_date FROM event WHERE deleted = false AND id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            event = new Event();
            event.setId(rs.getInt("id"));
            event.setName(rs.getString("name"));
            event.setStartDate(rs.getDate("start_date"));
            event.setEndDate(rs.getDate("end_date"));
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return event;
    }

    public List<Event> findCurrentEvent() throws IOException, SQLException, ClassNotFoundException {
        List<Event> events = new ArrayList();

        String req = "SELECT ev.id, ev.name FROM event ev WHERE ev.deleted = false AND curdate() >= ev.start_date AND curdate() <= ev.end_date";
        Connection cnt = ConnectionFactory.getConnection();
        Statement statement = cnt.createStatement();
        ResultSet rs = statement.executeQuery(req);

        while (rs.next()) {
            Event event = new Event();
            event.setId(rs.getInt("id"));
            event.setName(rs.getString("name"));
            events.add(event);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(statement);
        ConnectionUtil.close(cnt);

        return events;
    }

    public List<BookEvent> findBooks(Integer id) throws IOException, SQLException, ClassNotFoundException {

        List<BookEvent> bookEvents = new ArrayList();

        String req = "SELECT * FROM book_event WHERE event_id = ?";

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            BookEvent bookEvent = new BookEvent();
            bookEvent.setId(rs.getInt("id"));
            bookEvent.setEventId(rs.getInt("event_id"));
            bookEvent.setBookId(rs.getInt("book_id"));
            bookEvents.add(bookEvent);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return bookEvents;
    }

    @Override
    public List<Event> findAll() throws IOException, SQLException, ClassNotFoundException {
        List<Event> events = new ArrayList<>();

        String req = "SELECT id, name, start_date, end_date, created, updated FROM event WHERE deleted = false";

        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Event event = new Event();
            event.setId(rs.getInt("id"));
            event.setName(rs.getString("name"));
            event.setStartDate(rs.getDate("start_date"));
            event.setEndDate(rs.getDate("end_date"));
            event.setCreated(rs.getTimestamp("created"));
            event.setUpdated(rs.getTimestamp("updated"));
            events.add(event);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return events;
    }

    @Override
    public void update(Event event) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE event SET name = ?, start_date = ?, end_date = ? WHERE id = ?";

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, event.getName());
        pstm.setDate(2, new Date(event.getStartDate().getTime()));
        pstm.setDate(3, new Date(event.getEndDate().getTime()));
        pstm.setInt(4, event.getId());

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

    @Override
    public void delete(Integer id) throws IOException, SQLException, ClassNotFoundException {
        String req = "UPDATE event SET deleted = 1 WHERE id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

    public void addBooks(Event event) throws IOException, SQLException, ClassNotFoundException {

        Connection cnt = ConnectionFactory.getConnection();

        String queryDelete = "DELETE FROM book_event WHERE event_id=?";
        PreparedStatement statement = cnt.prepareStatement(queryDelete);
        statement.setInt(1, event.getId());
        statement.executeUpdate();
        ConnectionUtil.close(statement);

        for (Integer bookId : event.getBookIds()) {
            String req = "INSERT INTO book_event (book_id, event_id) VALUES (?, ?)";
            PreparedStatement pstm = cnt.prepareStatement(req);
            pstm.setInt(1, bookId);
            pstm.setInt(2, event.getId());
            if (pstm.executeUpdate() == 0) {
                throw new SQLException();
            }
            ConnectionUtil.close(pstm);
        }

        ConnectionUtil.close(cnt);
    }

}
