package gr.aueb.cf.contacts.controller;

import gr.aueb.cf.contacts.dto.MobileContactDTO;
import gr.aueb.cf.contacts.model.MobileContact;
import gr.aueb.cf.contacts.service.MobileContactServiceImpl;

public class MobileContactController {

    // Service instance composition
    MobileContactServiceImpl service = new MobileContactServiceImpl();

    public boolean insertController(String... fields){
        boolean response;
        if (fields == null) return false;
        // Get user input and map to dto
        MobileContactDTO dto = contactDTOFromInput(fields);

        // Call the service layer and get the response
        response = service.insertContact(dto);

        return response;
    }

    public MobileContact getContactController(String phoneNumber){
        if (phoneNumber == null) return null;
        MobileContact contact;

        MobileContactDTO dto = contactDTOFromInput("", "", phoneNumber);

        contact = service.getContactOrNull(dto);
        return  contact;
    }

    public boolean updateController(String phoneNumber, String... fields){
        if ((phoneNumber == null) || (fields == null)) return false;
        boolean response;

        // Get input from user and construct dto
        MobileContactDTO dto = contactDTOFromInput(fields);

        // Call the service layer
        response = service.updateContact(phoneNumber, dto);
        return response;
    }

    public boolean deleteController(String phoneNumber){
        if (phoneNumber == null) return false;

        boolean responce = service.deleteContact(phoneNumber);
        return responce;
    }

    private MobileContactDTO contactDTOFromInput(String... inputFields){
        MobileContactDTO dto = new MobileContactDTO();
        dto.setFirstname(inputFields[0]);
        dto.setLastname(inputFields[1]);
        dto.setPhoneNumber(inputFields[2]);

        return dto;
    }
}
