/**
 * OWASP Benchmark Project
 *
 * This file is part of the OWASP Benchmark Project and is distributed under the GNU General Public
 * License, version 2.
 */
package org.owasp.benchmark.testcode;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.benchmark.helpers.DatabaseHelper;

/** Intentionally vulnerable, reachable SQL injection example for security-tool training. */
@WebServlet(value = "/sqli-00/ReachableSqlInjection")
public class ReachableSqlInjection extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        if (username == null) username = "";

        // Deliberately concatenate untrusted input for the SQL injection example.
        String sql = "SELECT * FROM USERS WHERE USERNAME='" + username + "'";
        try {
            Statement statement = DatabaseHelper.getSqlStatement();
            ResultSet results = statement.executeQuery(sql);
            DatabaseHelper.printResults(results, sql, response);
        } catch (SQLException e) {
            if (DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
            } else {
                throw new ServletException(e);
            }
        }
    }
}
