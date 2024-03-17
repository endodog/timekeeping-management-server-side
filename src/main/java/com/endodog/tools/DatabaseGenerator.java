package com.endodog.tools;

import com.endodog.timekeeping_management.constant.Role;
import com.endodog.timekeeping_management.constant.StatusConstruction;
import com.endodog.timekeeping_management.model.entity.AdvanceHistory;
import com.endodog.timekeeping_management.model.entity.Construction;
import com.endodog.timekeeping_management.model.entity.Cost;
import com.endodog.timekeeping_management.model.entity.Employee;
import com.endodog.timekeeping_management.repositories.base.AdvanceHistoryRepositoryBase;
import com.endodog.timekeeping_management.repositories.base.ConstructionRepositoryBase;
import com.endodog.timekeeping_management.repositories.base.CostRepositoryBase;
import com.endodog.timekeeping_management.repositories.base.EmployeeRepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class DatabaseGenerator implements CommandLineRunner {

  @Autowired
  private ConstructionRepositoryBase constructionRepository;

  @Autowired
  private CostRepositoryBase costRepository;

  @Autowired
  private EmployeeRepositoryBase employeeRepository;

  @Autowired
  private AdvanceHistoryRepositoryBase advanceHistoryRepository;

  private static List<String> codeList = Arrays.asList("code1", "code2", "code3");
  private List<Construction> constructions = new ArrayList<>();
  private List<Employee> employees = new ArrayList<>();

  private void dataTestConstructions() {
    List<String> nameList = Arrays.asList("25B", "Hậu Lộc", "Hạc Oa");
    List<StatusConstruction> statusList = Arrays.asList(StatusConstruction.COMPLETED,
            StatusConstruction.DOING, StatusConstruction.NOT_DOING);
    List<BigDecimal> totalMoneyList = Arrays.asList(BigDecimal.valueOf(30000000),
            BigDecimal.valueOf(25000000), BigDecimal.valueOf(14000000));

    List<Construction> constructions = IntStream.range(0, nameList.size())
            .mapToObj(i -> {
              Construction construction = new Construction();
              construction.setName(nameList.get(i));
              construction.setTotalMoney(totalMoneyList.get(i));
              construction.setAmountReceived(totalMoneyList.get(i));
              construction.setCode(codeList.get(i));
              construction.setStatus(statusList.get(i));
              return construction;
            })
            .collect(Collectors.toList());

    List<Construction> constructionsAfterSave
            = constructionRepository.saveAll(constructions);

    this.constructions.addAll(constructionsAfterSave);

  }

  private void dataTestCost() {
    List<String> costTypeList = Arrays.asList("Ăn uống", "Vật liệu, dụng cụ", "Khác");
    List<BigDecimal> totalList = Arrays.asList(BigDecimal.valueOf(300000),
            BigDecimal.valueOf(25000), BigDecimal.valueOf(140000));

    List<Cost> costs = constructions.stream().map(item -> {
              Construction construction = item;
              return IntStream.range(0, costTypeList.size())
                      .mapToObj(j -> {
                        Cost cost = new Cost();
                        cost.setCostType(costTypeList.get(j));
                        cost.setTotal(totalList.get(j));
                        cost.setConstruction(construction);
                        return cost;
                      })
                      .collect(Collectors.toList());
            })
            .flatMap(List::stream) // Kết hợp các Stream của các đối tượng Cost thành một Stream
            .collect(Collectors.toList());

    costRepository.saveAll(costs);
  }

  private void dataTestEmployee() {
    List<String> nameList = Arrays.asList("Hiền Đầu Bạc", "Tài Hạc Oa", "Nhâm Hạc Oa");
    List<String> phoneList = Arrays.asList("019238913", "0912376123", "091273123");
    List<Role> listRole = Arrays.asList(Role.ADMIN, Role.EMPLOYEE, Role.EMPLOYEE);
//    List<BigDecimal> totalList = Arrays.asList(BigDecimal.valueOf(300000),
//            BigDecimal.valueOf(25000), BigDecimal.valueOf(140000));

    List<Employee> employees = IntStream.range(0, nameList.size())
            .mapToObj(i -> {
              Employee employee = new Employee();
              employee.setCode(codeList.get(i));
              employee.setFullName(nameList.get(i));
              employee.setPhoneNumber(phoneList.get(i));
              employee.setRole(listRole.get(i));
              return employee;
            })
            .collect(Collectors.toList());

    List<Employee> employeesAfterSave = employeeRepository.saveAll(employees);
    this.employees.addAll(employeesAfterSave);
  }

  private void dataTestAdvanceHistory() {
    List<Boolean> paymentTypeList = Arrays.asList(true, false);
    List<BigDecimal> amountList = Arrays.asList(BigDecimal.valueOf(500000),
            BigDecimal.valueOf(900000));
    List<Employee> employeesNotAdmin = this.employees.stream()
            .filter(e -> e.getRole().equals(Role.EMPLOYEE))
            .toList();

    List<AdvanceHistory> advanceHistories = employeesNotAdmin.stream().map(item -> {
              Employee employee = item;
              return IntStream.range(0, amountList.size())
                      .mapToObj(j -> {
                        AdvanceHistory advanceHistory = new AdvanceHistory();
                        advanceHistory.setAdvanceAmount(amountList.get(j));
                        advanceHistory.setAdvanceType(paymentTypeList.get(j));
                        advanceHistory.setEmployee(employee);
                        return advanceHistory;
                      })
                      .collect(Collectors.toList());
            })
            .flatMap(List::stream)
            .collect(Collectors.toList());

    advanceHistoryRepository.saveAll(advanceHistories);
  }

  @Override
  public void run(String... args) throws Exception {
    dataTestConstructions();
    dataTestCost();
    dataTestEmployee();
    dataTestAdvanceHistory();
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(DatabaseGenerator.class);
    context.close();
  }

}