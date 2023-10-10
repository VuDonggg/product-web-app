package com.pm05.product_web_app.controllers;

import java.io.IOException;
import java.sql.Connection;

import com.pm05.product_web_app.models.Product;
import com.pm05.product_web_app.models.db.DBCrud;
import com.pm05.product_web_app.models.db.MySQLConnector;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteProduct")
public class DeleteServletController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get code from url query string
        String code = req.getParameter("code");

        // connect to DB
        Connection conn = MySQLConnector.getMySQLConnection();

        if (code != null) {
            // find the product by code
            Product product = DBCrud.findProductByCode(conn, code);

            if (product != null) {
                // delete the product from the database
                DBCrud.deleteProduct(conn, code);
            }
        }

        // close the database connection
        MySQLConnector.closeConnection(conn);

        // redirect the user to the product list page
        resp.sendRedirect(req.getContextPath() + "/productList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
