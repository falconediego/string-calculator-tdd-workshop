package com.xpeppers.workshop.tdd;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Repository {
	private Connection connection;

	public Repository(Connection connection) {
		this.connection = connection;
	}

	public void save(String delimiterAndNumbers, int result) throws Exception {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("insert into StringCalculator values(?, ?)");
			statement.setObject(1, delimiterAndNumbers);
			statement.setObject(2, result);
			statement.executeUpdate();
		} finally {
			close(statement);
		}
	}

	private void close(PreparedStatement statement) {
		if (statement != null)
			try {
				statement.close();
			} catch (Exception ignored) {
			}
	}

}
