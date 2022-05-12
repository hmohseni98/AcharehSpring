package com.spring.achareh;


import com.spring.achareh.repository.OfferRepository;
import com.spring.achareh.service.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class AcharehApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcharehApplication.class, args);
    }

    @Component
    class Start implements ApplicationRunner {

        private final CustomerService customerService;
        private final CategoryService categoryService;
        private final SpecialityService specialityService;
        private final OrderService orderService;
        private final ExpertService expertService;
        private final OfferService offerService;
        private final UserService userService;
        private final OfferRepository offerRepository;

        public Start(CustomerService customerService, CategoryService categoryService, SpecialityService specialityService, OrderService orderService, ExpertService expertService, OfferService offerService, UserService userService, OfferRepository offerRepository) {
            this.customerService = customerService;
            this.categoryService = categoryService;
            this.specialityService = specialityService;
            this.orderService = orderService;
            this.expertService = expertService;
            this.offerService = offerService;
            this.userService = userService;
            this.offerRepository = offerRepository;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
//
//            Customer customer;
//            customer = Customer.builder().firstName("hassan").lastName("mohseni").
//                    email("mohseni.7798@gmail.com").password("123456").build();
//            customerService.save(customer);
//            Category category;
//            category = Category.builder().name("نظافت و پذیرایی").build();
//
//            Category category1;
//            category1 = Category.builder().name("سرویس عادی نظافت").build();
//
//
//            categoryService.save(category);
//            categoryService.save(category1);
//
//
//            Speciality speciality;
//            speciality = Speciality.builder().name("واحد تخلیه / نوساز / بازسازی شده").description("ندارد")
//                    .basePrice(50000).category(category1).build();
//
//            Speciality speciality1;
//            speciality1 = Speciality.builder().name("مسکونی").description("ندارد")
//                    .basePrice(100000).category(category1).build();
//
//            Speciality speciality2;
//            speciality2 = Speciality.builder().name("تجاری / اداری").description("ندارد")
//                    .basePrice(120000).category(category1).build();
//
//            specialityService.save(speciality);
//            specialityService.save(speciality1);
//            specialityService.save(speciality2);
//
//            orderService.registerOrder(1,1,80000,
//                    "دیوار شویی - جارو - راه پله", LocalDate.of(2022, 5,30),"tehran");
//            userService.changePassword(1,"123456","123");
//            Customer customer1;
//            customer1 = Customer.builder().firstName("ali").lastName("karimi")
//                    .email("mohseni@iran.ir").password("123").build();
//
//            Expert expert;
//            expert = Expert.builder().firstName("mamad").lastName("mohseni")
//                    .email("sss@gmail.com").password("123").build();
//            expertService.save(expert);
//
//            Set<Speciality> specialities = new HashSet<>();
//            specialities.add(speciality);
//            specialities.add(speciality1);
//            Speciality spec = specialityService.findById(1).get();
//            Expert expert1;
//            expert1 = Expert.builder().firstName("rezaaa").lastName("mohsenii").status(AccountStatus.active)
//                    .email("mmmmmm@email.com").specialities(Set.of(spec)).build();
//
//            Expert expert2;
//            expert2 = Expert.builder().firstName("mamad").lastName("jafari").status(AccountStatus.active)
//                    .email("mmmmeedmm@email.com").specialities(Set.of(spec)).build();
//
//            Expert expert3;
//            expert3 = Expert.builder().firstName("hoda").lastName("aghaei").status(AccountStatus.active)
//                    .email("mmdmmmm@email.com").specialities(Set.of(spec)).build();
//
//            expertService.save(expert1);
//            expertService.save(expert2);
//            expertService.save(expert3);
//            orderService.registerOrder(5,1,100000,"description",
//                    LocalDate.of(2022,6,10),"tehran");
//
//            expertService.addExpertToSpeciality(4,2);
//            expertService.addExpertToSpeciality(4,1);
//            offerService.OfferRegister(4,2,500000,5, LocalTime.of(16,0));
//            offerService.OfferRegister(9,2,800000,5, LocalTime.of(16,0));
//            offerService.OfferRegister(10,2,900000,5, LocalTime.of(16,0));
//
//
//            userService.gridSearch(null,"","","j").forEach(System.out::println);
//            userService.gridSearch(null,"","","").forEach(System.out::println);
//
//            Specification<Offer> specification = sortOfferList.sortList(orderService.findById(2).get(),true,true);
//            offerService.findAllOfferByOrderAndSorting(2,false,false)
//                    .forEach(System.out::println);
//            var spec2 =
//                    sortOfferList.sortList(orderService.findById(1).get(), false, false);
//            offerRepository.findAll(spec2).forEach(System.out::println);

        }
    }
}
