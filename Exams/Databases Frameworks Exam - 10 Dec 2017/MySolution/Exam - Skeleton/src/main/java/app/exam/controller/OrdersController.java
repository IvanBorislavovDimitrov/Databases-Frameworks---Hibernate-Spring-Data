package app.exam.controller;

import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.parser.JSONParser;
import app.exam.parser.XMLParser;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class OrdersController {

    private final OrderService orderService;
    private final JSONParser jsonParser;
    private final XMLParser xmlParser;

    @Autowired
    public OrdersController(OrderService orderService, JSONParser jsonParser, XMLParser xmlParser) {
        this.orderService = orderService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        OrderWrapperXMLImportDTO orderItemXMLImportDTO = null;
        try {
            orderItemXMLImportDTO = this.xmlParser.read(OrderWrapperXMLImportDTO.class, xmlContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        for (OrderXMLImportDTO orderXMLImportDTO : orderItemXMLImportDTO.getOrders()) {
            try {
                this.orderService.create(orderXMLImportDTO);
                sb.append(String.format("Order for %s on %s added.\r\n", orderXMLImportDTO.getCustomer(),
                        orderXMLImportDTO.getDate()));
            } catch (ParseException e) {
                sb.append("Error: Invalid data.\r\n");
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        try {
            return this.jsonParser.write(this.orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
