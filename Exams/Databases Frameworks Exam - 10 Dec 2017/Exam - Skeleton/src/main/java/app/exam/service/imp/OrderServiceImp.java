package app.exam.service.imp;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.ValidationUtil;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderItemRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemsRepository itemsRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, ItemsRepository itemsRepository,
                           EmployeeRepository employeeRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.itemsRepository = itemsRepository;
        this.employeeRepository = employeeRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        if (! ValidationUtil.isValid(dto)) {
            throw new IllegalArgumentException();
        }

        List<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order();
        order.setCustomer(dto.getCustomer());
        order.setDate(new SimpleDateFormat("dd/MM/yyyy' 'hh:mm").parse(dto.getDate()));
        if (dto.getType().equals("ForHere")) {
            order.setOrderType(OrderType.ForHere);
        } else if (dto.getType().equals("ToGo")) {
            order.setOrderType(OrderType.ToGo);
        }
        Employee employee = this.employeeRepository.findByName(dto.getEmployee());
        order.setEmployee(employee);
        BigDecimal price = BigDecimal.ZERO;
        for (OrderItemXMLImportDTO orderItemXMLImportDTO : dto.getItems().getOrders()) {
            Item item = this.itemsRepository.findByName(orderItemXMLImportDTO.getName());
            if (item == null) {
                continue;
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setQuantity(orderItemXMLImportDTO.getQuantity());
            order.getOrderItems().add(orderItem);
            price = price.add(item.getPrice());
            orderItems.add(orderItem);
        }
        order.setTotalPrice(price);
        if (order.getOrderItems().size() == 0) {
            throw new IllegalArgumentException();
        }

        if (! ValidationUtil.isValid(order)) {
            throw new IllegalArgumentException();
        }
        this.orderRepository.save(order);
        this.orderItemRepository.save(orderItems);
    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        Employee employee = this.employeeRepository.findByName(employeeName);
        EmployeeOrdersJSONExportDTO employeeOrdersJSONExportDTO = new EmployeeOrdersJSONExportDTO();
        employeeOrdersJSONExportDTO.setEmployeeName(employeeName);
        for (Order order : employee.getOrders()) {
            if (order.getOrderType().toString().equals(orderType)) {
                OrderJSONExportDTO orderJSONExportDTO = new OrderJSONExportDTO();
                orderJSONExportDTO.setCustomer(order.getCustomer());
                for (OrderItem orderItem : order.getOrderItems()) {
                    ItemJSONExportDTO itemJSONExportDTO = new ItemJSONExportDTO();
                    itemJSONExportDTO.setName(orderItem.getItem().getName());
                    itemJSONExportDTO.setPrice(orderItem.getItem().getPrice());
                    itemJSONExportDTO.setQuantity(orderItem.getQuantity());
                    itemJSONExportDTO.setId(orderItem.getId());
                    orderJSONExportDTO.getItemJSONExportDTOS().add(itemJSONExportDTO);
                }
                employeeOrdersJSONExportDTO.getOrderJSONExportDTOS().add(orderJSONExportDTO);
            }
        }

        return employeeOrdersJSONExportDTO;
    }
}
