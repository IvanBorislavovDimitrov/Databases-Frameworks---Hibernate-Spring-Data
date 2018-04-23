package app.exam.controller;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.parser.JSONParser;
import app.exam.parser.XMLParser;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

@Controller
public class OrdersController {

    private final XMLParser xmlParser;
    private final OrderService orderService;
    private final JSONParser jsonParser;

    @Autowired
    public OrdersController(XMLParser xmlParser, OrderService orderService, JSONParser jsonParser) {
        this.xmlParser = xmlParser;
        this.orderService = orderService;
        this.jsonParser = jsonParser;
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
        EmployeeOrdersJSONExportDTO employeeOrdersJSONExportDTO =
                this.orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType);
        employeeOrdersJSONExportDTO.getOrderJSONExportDTOS().sort((x1, x2) -> {

            BigDecimal x1Sum = BigDecimal.ZERO;
            BigDecimal x2Sum = BigDecimal.ZERO;
            int x1Count = 0;
            int x2Count = 0;
            for (ItemJSONExportDTO itemJSONExportDTO : x1.getItemJSONExportDTOS()) {
                x1Sum = x1Sum.add(itemJSONExportDTO.getPrice());
                x1Count += itemJSONExportDTO.getQuantity();
            }
            for (ItemJSONExportDTO itemJSONExportDTO : x2.getItemJSONExportDTOS()) {
                x2Sum = x2Sum.add(itemJSONExportDTO.getPrice());
                x2Count += itemJSONExportDTO.getQuantity();
            }
            if (x2Sum.compareTo(x1Sum) ==  0) {
                return Integer.compare(x2Count, x1Count);
            }
            return x2Sum.compareTo(x1Sum);
        });

        for (OrderJSONExportDTO orderJSONExportDTO : employeeOrdersJSONExportDTO.getOrderJSONExportDTOS()) {
            // must sort every by id
            orderJSONExportDTO.getItemJSONExportDTOS().sort((x1, x2) -> x1.getId().compareTo(x2.getId()));
            break;
        }

        try {
            return this.jsonParser.write(employeeOrdersJSONExportDTO);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
