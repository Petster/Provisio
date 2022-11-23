package com.csd.beta.provisio.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.csd.beta.provisio.entity.Room;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.exception.ProvisioException.RoomRepositoryException;
import com.csd.beta.provisio.util.Logger;

public class RoomRepository implements Repository<Room> {
    public final String TALBE_ROOM = "rooms";
    public final String COLUMN_ROOM_ID = "id";
    public final String COLUMN_ROOM_TITLE = "title";
    public final String COLUMN_ROOM_BREAKFAST = "breakfast";
    public final String COLUMN_ROOM_WIFI = "wifi";
    public final String COLUMN_ROOM_FITNESS = "fitness";
    public final String COLUMN_ROOM_STORE = "store";
    public final String COLUMN_ROOM_NOSMOKE = "nosmoke";
    public final String COLUMN_ROOM_MOBILE = "mobile";
    public final String COLUMN_ROOM_ROOM_HIGHLIGHTS = "room_highlights";
    public final String COLUMN_ROOM_IMAGE = "image";
    public final String COLUMN_ROOM_PRICE = "price";
    public final String COLUMN_ROOM_LOYALTY_POINTS = "loyalty_points";

    private final Logger logger;

    public RoomRepository() {
        logger = new Logger(RoomRepository.class.getSimpleName());
    }

    @Override
    public Room insertOne(Room room) {
        try (Connection c = establishConnection()) {
            //@formatter:off
            String insert = "INSERT INTO rooms (" +
                    "title, breakfast, wifi, fitness, store, nosmoke, mobile, room_highlights, image, price, loyalty_points" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            //@formatter:on
            PreparedStatement statement = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, room.getTitle());
            statement.setBoolean(2, room.getBreakfast());
            statement.setBoolean(3, room.getWifi());
            statement.setBoolean(4, room.getFitness());
            statement.setBoolean(5, room.getStore());
            statement.setBoolean(6, room.getNoSmoke());
            statement.setBoolean(7, room.getMobile());
            statement.setString(8, room.getHighlights());
            statement.setString(9, room.getImage());
            statement.setInt(10, room.getPrice());
            statement.setInt(11, room.getLoyaltyPoints());
            statement.executeUpdate();

            return getRoomWithGeneratedId(c, statement, room);
        } catch (Exception e) {
            logger.e("insertOne", e);
        }

        return null;
    }


    @Override
    public List<Room> insertMany(List<Room> rooms) {
        return rooms.stream().map(this::insertOne).toList();
    }

    @Override
    public Room getById(long id) {
        Room result = null;
        try (Connection c = establishConnection()) {
            String q = "SELECT * FROM rooms WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(q);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            result = buildRoom(rs);
        } catch (Exception e) {
            logger.e("getById", e);
        }
        return result;
    }

    @Override
    public List<Room> getAll() {
        List<Room> result = new ArrayList<>(0);
        try (Connection c = establishConnection()) {
            String q = "SELECT * FROM rooms";
            PreparedStatement statement = c.prepareStatement(q);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setID(rs.getLong(COLUMN_ROOM_ID));
                room.setTitle(rs.getString(COLUMN_ROOM_TITLE));
                room.setBreakfast(rs.getBoolean(COLUMN_ROOM_BREAKFAST));
                room.setWifi(rs.getBoolean(COLUMN_ROOM_WIFI));
                room.setFitness(rs.getBoolean(COLUMN_ROOM_FITNESS));
                room.setStore(rs.getBoolean(COLUMN_ROOM_STORE));
                room.setNoSmoke(rs.getBoolean(COLUMN_ROOM_NOSMOKE));
                room.setMobile(rs.getBoolean(COLUMN_ROOM_MOBILE));
                room.setRoomHighlights(rs.getString(COLUMN_ROOM_ROOM_HIGHLIGHTS));
                room.setImage(rs.getString(COLUMN_ROOM_IMAGE));
                room.setPrice(rs.getInt(COLUMN_ROOM_PRICE));
                room.setLoyaltyPoints(rs.getInt(COLUMN_ROOM_LOYALTY_POINTS));
                result.add(room);
            }
        } catch (Exception e) {
            logger.e("getAll", e);
        }
        return result;
    }

    @Override
    public void updateById(Room room, long id) {
        try (Connection c = establishConnection()) {
            //@formatter:off
            String q = "UPDATE rooms SET title = ?, breakfast = ?, wifi = ?, fitness = ?, store = ?, nosmoke = ?, mobile = ?," +
                    "room_highlights = ?, image = ?, price = ?, loyalty_points = ? WHERE id = ?";
            //@formatter:on
            PreparedStatement statement = c.prepareStatement(q);
            statement.setString(1, room.getTitle());
            statement.setBoolean(2, room.getBreakfast());
            statement.setBoolean(3, room.getWifi());
            statement.setBoolean(4, room.getFitness());
            statement.setBoolean(5, room.getStore());
            statement.setBoolean(6, room.getNoSmoke());
            statement.setBoolean(7, room.getMobile());
            statement.setString(8, room.getHighlights());
            statement.setString(9, room.getImage());
            statement.setInt(10, room.getPrice());
            statement.setInt(11, room.getLoyaltyPoints());
            statement.setLong(12, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == -1) {
                throw new ProvisioException.RoomRepositoryException("Unable to update room");
            } else if (rowsAffected > 1) {
                throw new ProvisioException.RoomRepositoryException("More than 1 row was affected: " + rowsAffected);
            }
        } catch (Exception e) {
            logger.e("updateById", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (Connection c = establishConnection()) {
            String q = "DELETE FROM rooms WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(q);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.e("deleteById", e);
        }
    }

    private Room getRoomWithGeneratedId(Connection c, Statement statement, Room room) throws SQLException, RoomRepositoryException {
        ResultSet rs = statement.getGeneratedKeys();
        long roomID;

        if (rs == null || !rs.next()) {
            throw new ProvisioException.RoomRepositoryException("could not insert Room");
        }

        if (rs.next()) {
            roomID = rs.getLong(1);
            room.setID(roomID);
        }

        c.close();
        return room;
    }

    private Room buildRoom(ResultSet rs) throws SQLException {
        Room result = new Room();
        while (rs.next()) {
            result.setID(rs.getLong(COLUMN_ROOM_ID));
            result.setTitle(rs.getString(COLUMN_ROOM_TITLE));
            result.setBreakfast(rs.getBoolean(COLUMN_ROOM_BREAKFAST));
            result.setWifi(rs.getBoolean(COLUMN_ROOM_WIFI));
            result.setFitness(rs.getBoolean(COLUMN_ROOM_FITNESS));
            result.setStore(rs.getBoolean(COLUMN_ROOM_STORE));
            result.setNoSmoke(rs.getBoolean(COLUMN_ROOM_NOSMOKE));
            result.setMobile(rs.getBoolean(COLUMN_ROOM_MOBILE));
            result.setRoomHighlights(rs.getString(COLUMN_ROOM_ROOM_HIGHLIGHTS));
            result.setImage(rs.getString(COLUMN_ROOM_IMAGE));
            result.setPrice(rs.getInt(COLUMN_ROOM_PRICE));
            result.setLoyaltyPoints(rs.getInt(COLUMN_ROOM_LOYALTY_POINTS));
        }
        return result;
    }
}