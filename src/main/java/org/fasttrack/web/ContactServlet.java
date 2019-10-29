package org.fasttrack.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrack.config.ObjectMapperConfiguration;
import org.fasttrack.domain.Contact;
import org.fasttrack.service.ContactService;
import org.fasttrack.transfer.CreateContactRequest;
import org.fasttrack.transfer.UpdateContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {

    private ContactService contactService = new ContactService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateContactRequest createContactRequest = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), CreateContactRequest.class);
        contactService.createContact(createContactRequest);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UpdateContactRequest updateContactRequest = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), UpdateContactRequest.class);
        try {
            contactService.updateContact(id, updateContactRequest);
        } catch (InvalidParameterException e) {
            resp.sendError(404, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        try {
            contactService.deleteContact(id);

        } catch (InvalidParameterException e) {
            resp.sendError(404, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String partialName = req.getParameter("partialName");
        String response = null;
        String phoneBookId = req.getParameter("phoneBookId");
        String contactId = req.getParameter("id");
        if (partialName != null) {
            try {
                List<Contact> contactList = contactService.readContact(partialName);
                response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(contactList);
            } catch (InvalidParameterException e) {
                resp.sendError(404, e.getMessage());
            }
        } else if (phoneBookId != null) {
            try {
                List<Contact> contactList = contactService.readContactsFromPhoneBook(Long.parseLong(phoneBookId));
                response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(contactList);
            } catch (InvalidParameterException e) {
                resp.sendError(404, e.getMessage());
            }
        } else if (contactId != null) {
            try {
                Contact contact = contactService.readContact(Long.parseLong(contactId));
                response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(contact);
            } catch (InvalidParameterException e) {
                resp.sendError(404, e.getMessage());
            }
        } else {
            List<Contact> contactList = contactService.readContact();
            response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(contactList);
        }
        resp.getWriter().print(response);
    }
}
