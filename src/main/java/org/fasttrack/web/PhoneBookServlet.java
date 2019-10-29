package org.fasttrack.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrack.config.ObjectMapperConfiguration;
import org.fasttrack.domain.PhoneBook;
import org.fasttrack.service.PhoneBookService;
import org.fasttrack.transfer.CreatePhoneBookRequest;
import org.fasttrack.transfer.UpdatePhoneBookRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/phone-books")
public class PhoneBookServlet extends HttpServlet {

    private PhoneBookService phoneBookService = new PhoneBookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreatePhoneBookRequest createPhoneBookRequest =
                ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(),CreatePhoneBookRequest.class);
        phoneBookService.createPhoneBook(createPhoneBookRequest);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UpdatePhoneBookRequest updatePhoneBookRequest =
                ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(),UpdatePhoneBookRequest.class);
        phoneBookService.updatePhoneBook(id,updatePhoneBookRequest);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PhoneBook> phoneBooks = phoneBookService.readPhoneBook();
        String response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(phoneBooks);
        resp.getWriter().print(response);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        phoneBookService.deletePhoneBook(id);
    }
}
