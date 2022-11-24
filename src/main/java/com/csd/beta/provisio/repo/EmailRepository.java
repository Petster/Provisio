package com.csd.beta.provisio.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.csd.beta.provisio.entity.Email;
import com.csd.beta.provisio.entity.Location;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.exception.ProvisioException.LocationRepositoryException;
import com.csd.beta.provisio.util.Logger;

public class EmailRepository implements Repository<Email> {
    public final String TABLE_EMAIL = "emails";
    public final String COLUMN_EMAIL_ID = "id";
    public final String COLUMN_EMAIL_DATE_SENT = "date_sent";
    public final String COLUMN_EMAIL_RESERVATION_NUM = "reservation_num";
    public final String COLUMN_EMAIL_USER_EMAIL = "user_email";
    public final String COLUMN_EMAIL_USER_FIRSTNAME = "user_firstname";
    public final String COLUMN_EMAIL_USER_PHONE = "user_phone";
    public final String COLUMN_EMAIL_SUBJECT = "subject";
    public final String COLUMN_EMAIL_MESSAGE = "message";
    private final Logger logger;

    public EmailRepository() { logger = new Logger(EmailRepository.class.getSimpleName()); }

    @Override
    public Email insertOne(Email email) {
        try (Connection c = establishConnection()) {
            //@formatter:off
            String insert = "INSERT INTO emails (" +
                    "date_sent, reservation_num, user_email, user_firstname, user_phone, subject, message" +
                    ") VALUES (?,?,?,?,?,?,?) ";
            //@formatter:on
            PreparedStatement statement = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, email.getDateSent());
            statement.setLong(2, email.getReservationNum());
            statement.setString(3, email.getUserEmail());
            statement.setString(4, email.getUserFirstName());
            statement.setString(5, email.getUserPhone());
            statement.setString(6, email.getSubject());
            statement.setString(7, email.getMessage());

            statement.executeUpdate();

            return getEmailWithGeneratedId(c, statement, email);
        } catch (Exception e) {
            logger.e("insertOne", e);
        }

        return null;
    }


    @Override
    public List<Email> insertMany(List<Email> email) {
        return email.stream().map(this::insertOne).toList();
    }

    @Override
    public Email getById(long id) {
        Email result = null;
        try (Connection c = establishConnection()) {
            String q = "SELECT * FROM emails WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(q);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            result = buildEmail(rs);
        } catch (Exception e) {
            logger.e("getById", e);
        }
        return result;
    }

    @Override
    public List<Email> getAll() {
        List<Email> result = new ArrayList<>(0);
        try (Connection c = establishConnection()) {
            String q = "SELECT * FROM emails";
            PreparedStatement statement = c.prepareStatement(q);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Email email = new Email();
                email.setID(rs.getLong(COLUMN_EMAIL_ID));
                email.setDateSent(rs.getString(COLUMN_EMAIL_DATE_SENT));
                email.setReservationNum(rs.getLong(COLUMN_EMAIL_RESERVATION_NUM));
                email.setUserEmail(rs.getString(COLUMN_EMAIL_USER_EMAIL));
                email.setUserFirstName(rs.getString(COLUMN_EMAIL_USER_FIRSTNAME));
                email.setUserPhone(rs.getString(COLUMN_EMAIL_USER_PHONE));
                email.setSubject(rs.getString(COLUMN_EMAIL_SUBJECT));
                email.setMessage(rs.getString(COLUMN_EMAIL_MESSAGE));
                result.add(email);
            }
        } catch (Exception e) {
            logger.e("getAll", e);
        }
        return result;
    }

    @Override
    public void updateById(Email email, long id) {}

    @Override
    public void deleteById(long id) {
        try (Connection c = establishConnection()) {
            String q = "DELETE FROM emails WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(q);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.e("deleteById", e);
        }
    }

    private Email getEmailWithGeneratedId(Connection c, Statement statement, Email email) throws SQLException, ProvisioException.EmailRepositoryException {
        ResultSet rs = statement.getGeneratedKeys();
        long emailID;

        if (rs == null || !rs.next()) {
            throw new ProvisioException.EmailRepositoryException("could not insert Email");
        }

        if (rs.next()) {
            emailID = rs.getLong(1);
            email.setID(emailID);
        }

        c.close();
        return email;
    }

    private Email buildEmail(ResultSet rs) throws SQLException {
        Email result = new Email();
        while (rs.next()) {
            result.setID(rs.getLong(COLUMN_EMAIL_ID));
            result.setDateSent(rs.getString(COLUMN_EMAIL_DATE_SENT));
            result.setReservationNum(rs.getLong(COLUMN_EMAIL_RESERVATION_NUM));
            result.setUserEmail(rs.getString(COLUMN_EMAIL_USER_EMAIL));
            result.setUserFirstName(rs.getString(COLUMN_EMAIL_USER_FIRSTNAME));
            result.setUserPhone(rs.getString(COLUMN_EMAIL_USER_PHONE));
            result.setSubject(rs.getString(COLUMN_EMAIL_SUBJECT));
            result.setMessage(rs.getString(COLUMN_EMAIL_MESSAGE));
        }
        return result;
    }
}
