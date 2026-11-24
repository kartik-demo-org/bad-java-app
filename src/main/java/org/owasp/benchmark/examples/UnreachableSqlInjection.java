package org.owasp.benchmark.examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

/**
 * Intentionally vulnerable SAST example. This class is not registered or called by the application.
 */
final class UnreachableSqlInjection {
    private UnreachableSqlInjection() {}

    private static ResultSet findUser(Connection connection, HttpServletRequest request)
            throws SQLException {
        String name = request.getParameter("name");
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM users WHERE name = '" + name + "'");
    }
}
