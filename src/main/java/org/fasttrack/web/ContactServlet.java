package org.fasttrack.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrack.service.ContactService;
import org.fasttrack.transfer.CreateContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {

    private ContactService contactService = new ContactService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateContactRequest createContactRequest = objectMapper.readValue(req.getReader(),CreateContactRequest.class);
        try{
            contactService.createContact(createContactRequest);
        }
        catch (Exception e){
            resp.sendError(500,"Internal server error: " + e.getMessage());
        }
    }

}
