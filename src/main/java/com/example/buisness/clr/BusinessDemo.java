package com.example.buisness.clr;

import com.example.buisness.beans.*;
import com.example.buisness.repository.ProductRepository;
import com.example.buisness.service.AddressService;
import com.example.buisness.service.BusinessService;
import com.example.buisness.service.ProductService;
import com.example.buisness.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
@Order(1)
@Component
public class BusinessDemo implements CommandLineRunner {

    @Autowired
    private BusinessService businessService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private Random random;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}")
    private String URL;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {

        autoFillDatabase();

        Business business = restTemplate.getForObject(URL + "business/5", Business.class);
        System.out.println(business);

        Business[] businesses = restTemplate.getForObject(URL + "business", Business[].class);
        List<Business> businessList = Arrays.asList(businesses);
        businessList.forEach(System.out::println);

        Address address = Address.builder().city("City_RT").country("Country_RT").street("Street_RT").build();

        OpeningHours openingHours1= OpeningHours.builder().isOpen(true).day(DayOfWeek.SUNDAY).openingTime(LocalTime.of(8,00)).closingTime(LocalTime.of(18, 00)).build();
        OpeningHours openingHours2 = OpeningHours.builder().isOpen(true).day(DayOfWeek.MONDAY).openingTime(LocalTime.of(10,00)).closingTime(LocalTime.of(20, 00)).build();
        OpeningHours openingHours3 = OpeningHours.builder().isOpen(true).day(DayOfWeek.TUESDAY).openingTime(LocalTime.of(9,30)).closingTime(LocalTime.of(15, 30)).build();
        OpeningHours openingHours4 = OpeningHours.builder().isOpen(false).day(DayOfWeek.WEDNESDAY).openingTime(LocalTime.of(0,00)).closingTime(LocalTime.of(0, 00)).build();
        OpeningHours openingHours5 = OpeningHours.builder().isOpen(true).day(DayOfWeek.THURSDAY).openingTime(LocalTime.of(8,00)).closingTime(LocalTime.of(12, 00)).build();
        OpeningHours openingHours6 = OpeningHours.builder().isOpen(true).day(DayOfWeek.FRIDAY).openingTime(LocalTime.of(7,00)).closingTime(LocalTime.of(14, 00)).build();
        OpeningHours openingHours7 = OpeningHours.builder().isOpen(false).day(DayOfWeek.SATURDAY).openingTime(LocalTime.of(0,00)).closingTime(LocalTime.of(0, 00)).build();
        List<OpeningHours> hoursList = new ArrayList<>();
        hoursList.addAll(Arrays.asList(openingHours1, openingHours2, openingHours3, openingHours4, openingHours5, openingHours6, openingHours7));

        String name = "Company_RT";

        Business businessToSaveAtRT = Business.builder().address(address).openings(hoursList).name(name).build();

        Business businessFromRT = restTemplate.postForObject(URL + "business", businessToSaveAtRT, Business.class);

        try {
            restTemplate.delete(URL + "business/12");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void autoFillDatabase() {
        for (int i = 1; i <= 10; i++) {
            Address address = Address.builder().city("City_" + i).country("Country_" + i).street("Street_" + i).build();

            OpeningHours openingHours1= OpeningHours.builder().isOpen(true).day(DayOfWeek.SUNDAY).openingTime(LocalTime.of(8,00)).closingTime(LocalTime.of(18, 00)).build();
            OpeningHours openingHours2 = OpeningHours.builder().isOpen(true).day(DayOfWeek.MONDAY).openingTime(LocalTime.of(10,00)).closingTime(LocalTime.of(20, 00)).build();
            OpeningHours openingHours3 = OpeningHours.builder().isOpen(true).day(DayOfWeek.TUESDAY).openingTime(LocalTime.of(9,30)).closingTime(LocalTime.of(15, 30)).build();
            OpeningHours openingHours4 = OpeningHours.builder().isOpen(false).day(DayOfWeek.WEDNESDAY).openingTime(LocalTime.of(0,00)).closingTime(LocalTime.of(0, 00)).build();
            OpeningHours openingHours5 = OpeningHours.builder().isOpen(true).day(DayOfWeek.THURSDAY).openingTime(LocalTime.of(8,00)).closingTime(LocalTime.of(12, 00)).build();
            OpeningHours openingHours6 = OpeningHours.builder().isOpen(true).day(DayOfWeek.FRIDAY).openingTime(LocalTime.of(7,00)).closingTime(LocalTime.of(14, 00)).build();
            OpeningHours openingHours7 = OpeningHours.builder().isOpen(false).day(DayOfWeek.SATURDAY).openingTime(LocalTime.of(0,00)).closingTime(LocalTime.of(0, 00)).build();
            List<OpeningHours> hoursList = new ArrayList<>();
            hoursList.addAll(Arrays.asList(openingHours1, openingHours2, openingHours3, openingHours4, openingHours5, openingHours6, openingHours7));

            String name = "Company_" + i;

            Business business = Business.builder().address(address).openings(hoursList).name(name).build();
            Business savedBusiness = businessService.add(business);
            int businessId = savedBusiness.getId();
            System.out.println("#####" + businessId);

            for (int j = 1; j <= 10; j++) {
                Worker worker = Worker.builder().age(18 + random.nextInt(50)).firstName("First_Name_" + j).lastName("Last_Name_" + j).business(savedBusiness).build();
                workerService.add(worker, businessId);
            }
            for (int j = 1; j <= 10 ; j++) {
                Product product = new Product();
                product.setPrice(50 + random.nextInt(500));
                product.setName("Name_" + j);
                product.setDuration(500 + random.nextInt(2000));
                productService.add(product, businessId);


            }
        }
    }
}
