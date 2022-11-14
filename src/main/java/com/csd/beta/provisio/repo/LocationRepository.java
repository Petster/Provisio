package com.csd.beta.provisio.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.csd.beta.provisio.entity.Location;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.exception.ProvisioException.LocationRepositoryException;
import com.csd.beta.provisio.util.Logger;

public class LocationRepository implements Repository<Location> {
    public final String TABLE_LOCATION = "locations";
    public final String COLUMN_LOCATION_ID = "id";
    public final String COLUMN_LOCATION_ADDRESS = "address";
    public final String COLUMN_LOCATION_TITLE = "title";

    private final Logger logger;

    public LocationRepository() {
        logger = new Logger(LocationRepository.class.getSimpleName());
    }

    @Override
    public Location insertOne(Location location) {
        try (Connection c = establishConnection()) {
            //@formatter:off
            String insert = "INSERT INTO locations (" +
                    "title, address" +
                    ") VALUES (?, ?) ";
            //@formatter:on
            PreparedStatement statement = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, location.getTitle());
            statement.setString(2, location.getAddress());
            statement.executeUpdate();

            return getLocationWithGeneratedId(c, statement, location);
        } catch (Exception e) {
            logger.e("insertOne", e);
        }

        return null;
    }


    @Override
    public List<Location> insertMany(List<Location> locations) {
        return locations.stream().map(this::insertOne).toList();
    }

    @Override
    public Location getById(long id) {
        Location result = null;
        try (Connection c = establishConnection()) {
            String q = "SELECT * FROM locations WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(q);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            result = buildLocation(rs);
        } catch (Exception e) {
            logger.e("getById", e);
        }
        return result;
    }

    @Override
    public List<Location> getAll() {
        List<Location> result = new ArrayList<>(0);
        try (Connection c = establishConnection()) {
            String q = "SELECT * FROM locations";
            PreparedStatement statement = c.prepareStatement(q);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Location location = new Location();
                location.setID(rs.getLong(COLUMN_LOCATION_ID));
                location.setTitle(rs.getString(COLUMN_LOCATION_TITLE));
                location.setAddress(rs.getString(COLUMN_LOCATION_ADDRESS));
                result.add(location);
            }
        } catch (Exception e) {
            logger.e("getAll", e);
        }
        return result;
    }

    @Override
    public void updateById(Location location, long id) {
        try (Connection c = establishConnection()) {
            //@formatter:off
            String q = "UPDATE locations SET title = ?, address = ? " +
                    "WHERE id = ?";
            //@formatter:on
            PreparedStatement statement = c.prepareStatement(q);
            statement.setString(1, location.getTitle());
            statement.setString(2, location.getAddress());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == -1) {
                throw new ProvisioException.RoomRepositoryException("Unable to update location");
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
            String q = "DELETE FROM locations WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(q);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.e("deleteById", e);
        }
    }

    private Location getLocationWithGeneratedId(Connection c, Statement statement, Location location) throws SQLException, LocationRepositoryException {
        ResultSet rs = statement.getGeneratedKeys();
        long locationID;

        if (rs == null || !rs.next()) {
            throw new ProvisioException.LocationRepositoryException("could not insert Location");
        }

        if (rs.next()) {
            locationID = rs.getLong(1);
            location.setID(locationID);
        }

        c.close();
        return location;
    }

    private Location buildLocation(ResultSet rs) throws SQLException {
        Location result = new Location();
        while (rs.next()) {
            result.setID(rs.getLong(COLUMN_LOCATION_ID));
            result.setTitle(rs.getString(COLUMN_LOCATION_TITLE));
            result.setAddress(rs.getString(COLUMN_LOCATION_ADDRESS));
        }
        return result;
    }
}