package org.fasttrack.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrack.service.PhoneBookService;
import org.fasttrack.transfer.CreatePhoneBookRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/phone-books")
public class PhoneBookServlet extends HttpServlet {

    private PhoneBookService phoneBookService = new PhoneBookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CreatePhoneBookRequest createPhoneBookRequest =
                objectMapper.readValue(req.getReader(),CreatePhoneBookRequest.class);
        try{
            phoneBookService.createPhoneBook(createPhoneBookRequest);
        }
        catch (Exception e){
            resp.sendError(500,"Internal server error: " + e.getMessage());
        }
    }
}
