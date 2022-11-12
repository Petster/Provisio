package repo;

import Database.News;
import exception.ProvisioException;
import util.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository implements Repository<News> {
	public final String TABLE_NEWS = "news";
	public final String COLUMN_NEWS_ID = "id";
	public final String COLUMN_NEWS_USER_ID = "userID";
	public final String COLUMN_NEWS_TITLE = "title";
	public final String COLUMN_NEWS_PUBLISH_DATE = "publishDate";
	public final String COLUMN_NEWS_DESCRIPTION = "description";
	public final String COLUMN_NEWS_IMAGE = "image";
	
	private final Logger logger;
	
	public NewsRepository() {
		logger = new Logger(NewsRepository.class.getSimpleName());
	}
	
	@Override
	public News insertOne(News news) {
		try (Connection c = establishConnection()) {
			//@formatter:off
			String insert = "INSERT INTO news (" +
					                "userID, title, publishDate, description, image" +
					                ") VALUES (?, ?, ?, ?, ?)";
			//@formatter:on
			PreparedStatement statement = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, news.getUserID());
			statement.setString(2, news.getTitle());
			statement.setString(3, news.getPublishDate());
			statement.setString(4, news.getDescription());
			statement.setString(5, news.getImage());
			statement.executeUpdate();
			
			return getNewsWithGeneratedId(c, statement, news);
		} catch (Exception e) {
			logger.e("insertOne", e);
		}

		return null;
	}

	@Override
	public List<News> insertMany(List<News> news) {
		return news.stream().map(this::insertOne).toList();
	}

	@Override
	public News getById(long id) {
		News result = null;
		try (Connection c = establishConnection()) {
			String q = "SELECT * FROM news where id = ?";
			PreparedStatement statement = c.prepareStatement(q);
			ResultSet rs = statement.executeQuery();
			result = buildNews(rs);
		} catch (Exception e) {
			logger.e("getById", e);
		}
		return result;
	}

	@Override
	public List<News> getAll() {
		List<News> result = new ArrayList<>(0);
		try (Connection c = establishConnection()) {
			String q = "SELECT * FROM news";
			PreparedStatement statement = c.prepareStatement(q);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setID(rs.getLong(COLUMN_NEWS_ID));
				news.setUserID(rs.getLong(COLUMN_NEWS_USER_ID));
				news.setTitle(rs.getString(COLUMN_NEWS_TITLE));
				news.setPublishDate(rs.getString(COLUMN_NEWS_PUBLISH_DATE));
				news.setDescription(rs.getString(COLUMN_NEWS_DESCRIPTION));
				news.setImage(rs.getString(COLUMN_NEWS_IMAGE));;
				result.add(news);
			}
		} catch (Exception e) {
			logger.e("getAll", e);
		}
		return result;
	}

	@Override
	public void updateById(News news, long id) {
		try (Connection c = establishConnection()) {
			//@formatter:off
			String q = "UPDATE news SET title = ?, publishDate = ?, description = ?, image = ?" +
					           "WHERE id = ?";
			//@formatter:on
			PreparedStatement statement = c.prepareStatement(q);
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getPublishDate());
			statement.setString(3, news.getDescription());
			statement.setString(4, news.getImage());
			statement.setLong(5, news.getID());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == -1) {
				throw new ProvisioException.NewsRepositoryException("Unable to update News Article");
			} else if (rowsAffected > 1) {
				throw new ProvisioException.NewsRepositoryException("More than 1 row was affected: " + rowsAffected);
			}
		} catch (Exception e) {
			logger.e("updateById", e);
		}
	}

	@Override
	public void deleteById(long id) {
		try (Connection c = establishConnection()) {
			String q = "DELETE FROM news WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(q);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			logger.e("deleteById", e);
		}
	}

	private News buildNews(ResultSet rs) throws SQLException {
		News result = new News();
		while(rs.next()) {
			result.setID(rs.getLong(COLUMN_NEWS_ID));
			result.setUserID(rs.getLong(COLUMN_NEWS_USER_ID));
			result.setTitle(rs.getString(COLUMN_NEWS_TITLE));
			result.setPublishDate(rs.getString(COLUMN_NEWS_PUBLISH_DATE));
			result.setDescription(rs.getString(COLUMN_NEWS_DESCRIPTION));
			result.setImage(rs.getString(COLUMN_NEWS_IMAGE));;
		}
		return result;
	}

	private News getNewsWithGeneratedId(Connection c, Statement statement, News news) throws Exception {
		ResultSet rs = statement.getGeneratedKeys();
		long userId;
		
		if (rs == null || !rs.next()) {
			throw new ProvisioException.UserRepositoryException("could not insert News Article");
		} 

		if(rs.next()) {
			userId = rs.getLong(1);
			news.setID(userId);
		}

		c.close();
		return news;
	}

	@Override
	public News selectOne(String u, String p) {
		// TODO Auto-generated method stub
		return null;
	}
}
