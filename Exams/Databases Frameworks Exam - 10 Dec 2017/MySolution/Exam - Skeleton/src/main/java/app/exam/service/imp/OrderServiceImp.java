package app.exam.service.imp;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.ModelParser;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemsRepository itemsRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelParser mapper;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, ItemsRepository itemsRepository,
                           EmployeeRepository employeeRepository, OrderItemRepository orderItemRepository, ModelParser mapper) {
        this.orderRepository = orderRepository;
        this.itemsRepository = itemsRepository;
        this.employeeRepository = employeeRepository;
        this.orderItemRepository = orderItemRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        if (!ValidationUtil.validate(dto)) {
            throw new IllegalArgumentException();
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        Order order = new Order();
        Employee employee = this.employeeRepository.findByName(dto.getEmployee());
        if (employee == null) {
            throw new IllegalArgumentException();
        }
        BigDecimal totalPrice = BigDecimal.ZERO;
        order.setEmployee(employee);
        order.setCustomer(dto.getCustomer());
        order.setDate(new SimpleDateFormat("dd/MM/yyyy' 'hh:mm").parse(dto.getDate()));
        order.setOrderType(dto.getType().equals("ForHere") ? OrderType.ForHere : OrderType.ToGo);
        for (OrderItemXMLImportDTO itemDto : dto.getItems().getItems()) {
            OrderItem orderItem = new OrderItem();
            Item item = this.itemsRepository.findByName(itemDto.getName());
            if (item == null) {
                continue;
            }

            orderItem.setItem(item);
            orderItem.setOrder(order);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItemList.add(orderItem);
            order.getOrderItems().add(orderItem);
            totalPrice = totalPrice.add(item.getPrice());
        }
        if (order.getOrderItems() == null || order.getOrderItems().size() == 0) {
            throw new IllegalArgumentException();
        }
        order.setTotalPrice(totalPrice);
        this.orderRepository.saveAndFlush(order);
        this.orderItemRepository.save(orderItemList);
    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        List<Order> orders = this.orderRepository.findAll()
        .stream().filter(x -> x.getOrderType().toString()
                        .equals(orderType))
                .collect(Collectors.toList());

        Employee employee = null;
        for (Order order : orders) {
            if (order.getEmployee().getName().equals(employeeName)) {
                employee = order.getEmployee();
                break;
            }
        }
        if (employee == null) {
            throw new IllegalArgumentException();
        }

        EmployeeOrdersJSONExportDTO employeeDto = this.mapper.convert(employee, EmployeeOrdersJSONExportDTO.class);
        List<Order> employeeOrders = new ArrayList<>(employee.getOrders());

        employeeDto.setOrders(employeeOrders.stream().sorted((x2, x1) -> {
            if (x1.getTotalPrice().compareTo(x2.getTotalPrice()) == 0) {
                return Integer.compare(x1.getOrderItems().size(), x2.getOrderItems().size());
            }

            return x1.getTotalPrice().compareTo(x2.getTotalPrice());
        }).map(x1 -> {
            List<OrderItem> orderItems = new ArrayList<>(x1.getOrderItems());
            orderItems.sort(Comparator.comparingInt(OrderItem::getId));
            OrderJSONExportDTO orderJSONExportDTO = new OrderJSONExportDTO();
            orderJSONExportDTO.setCustomer(x1.getCustomer());

            orderJSONExportDTO.setItems(orderItems.stream().map((x) -> new ItemJSONExportDTO(
                    x.getItem().getName(),
                    x.getItem().getPrice(),
                    x.getQuantity()
            )).collect(Collectors.toList()));

            return orderJSONExportDTO;
        }).collect(Collectors.toList()));


        String stop = "";

        return employeeDto;
    }
}
