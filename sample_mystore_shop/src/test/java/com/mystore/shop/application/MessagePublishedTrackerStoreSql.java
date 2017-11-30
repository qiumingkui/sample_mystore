package com.mystore.shop.application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mystore.common.message.MessagePublishedTracker;
import com.mystore.common.message.MessagePublishedTrackerStore;

@Component
public class MessagePublishedTrackerStoreSql implements MessagePublishedTrackerStore {

	private static final String SHOP = "com.mystore.shop";

	private String typeName = SHOP;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public MessagePublishedTracker messagePublishedTracker() {

		return this.messagePublishedTracker(getTypeName());
	}

	@Override
	public MessagePublishedTracker messagePublishedTracker(String typeName) {
		MessagePublishedTracker messagePublishedTracker = findMessagePublishedTracker(typeName);

		if (messagePublishedTracker == null) {
			// return new MessagePublishedTracker();
			return instanceMessagePublishedTracker();
		}

		return messagePublishedTracker;
	}

	private MessagePublishedTracker instanceMessagePublishedTracker() {
		return new MessagePublishedTracker(getTypeName());
	}

	private MessagePublishedTracker findMessagePublishedTracker(String typeName) {
		final String SQL = "SELECT id,recentmsgid,typename,version FROM msgpublishedtracker WHERE typename=?";
		List<MessagePublishedTracker> list = jdbcTemplate.query(SQL, new Object[] { typeName },
				new RowMapper<MessagePublishedTracker>() {
					@Override
					public MessagePublishedTracker mapRow(ResultSet rs, int rowNum) throws SQLException {
						MessagePublishedTracker tracker = new MessagePublishedTracker();
						tracker.setMessagePublishedTrackerId(rs.getLong("id"));
						tracker.setMostRecentMessagePublishedId(rs.getLong("recentmsgid"));
						tracker.setTypeName(rs.getString("typename"));
						tracker.setConcurrencyVersion(rs.getInt("version"));
						return tracker;
					}

				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void trackMostRecentPublishedMessage(MessagePublishedTracker messagePublishedTracker) {

		synchronized (this) {

			MessagePublishedTracker tracker = findMessagePublishedTracker(messagePublishedTracker.typeName());

			if (tracker == null) {
				final String INSERT_SQL = "INSERT INTO msgpublishedtracker(recentmsgid,typename,version) VALUES(?,?,?)";
				jdbcTemplate.update(INSERT_SQL, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						// ps.setLong(1,
						// messagePublishedTracker.messagePublishedTrackerId());
						ps.setLong(1, messagePublishedTracker.mostRecentMessagePublishedId());
						ps.setString(2, messagePublishedTracker.typeName());
						ps.setInt(3, messagePublishedTracker.concurrencyVersion());
					}
				});
			} else {
				final String UPDATE_SQL = "UPDATE msgpublishedtracker SET recentmsgid=?,typename=?,version=version+1 WHERE id=? AND version=?";
				jdbcTemplate.update(UPDATE_SQL, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setLong(1, messagePublishedTracker.mostRecentMessagePublishedId());
						ps.setString(2, messagePublishedTracker.typeName());
						ps.setLong(3, messagePublishedTracker.messagePublishedTrackerId());
						ps.setInt(4, messagePublishedTracker.concurrencyVersion());
					}
				});
			}
		}

	}

	@Override
	public String getTypeName() {
		return this.typeName;
	}

}
