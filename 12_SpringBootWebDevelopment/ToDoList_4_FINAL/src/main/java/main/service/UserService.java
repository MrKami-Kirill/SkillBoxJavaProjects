package main.service;

import main.model.*;
import main.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dadata.api.DaData;
import ru.dadata.api.entity.Address;
import ru.dadata.api.entity.Passport;
import ru.dadata.api.entity.Phone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private TelephoneRepository telephoneRepository;
    @Autowired
    private AddressesRepository addressesRepository;

    private DaData daData = new DaData("d865401c5d7d617a0acfee77996bcf3791cd673b", "4be61136d678baedd1a0fddb7879be0fb6a9ec28");


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }
        if (user.getAdminCode() == "") {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
        if (user.getAdminCode().equals("sZ9fbHVFVn8J")) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        }
        user.setActive(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        saveDocument(user);
        saveTelephone(user);
        userRepository.save(user);
        saveRegistrationAddress(user);
        saveActualAddress(user);
        return true;
    }

    public boolean saveDocument(User user) {

        Passport passportDaData = daData.cleanPassport(user.getPassport());
        Document document = new Document();
        document.setFullDocumentNumber(user.getPassport());
        document.setSeries(passportDaData.getSeries());
        document.setNumber(passportDaData.getNumber());
        document.setQc(passportDaData.getQc());
        document.setCode(user.getCode());
        document.setIssuedBy(user.getIssuedBy());
        document.setIssueDate(user.getIssueDate());
        document.setBirthPlace(user.getBirthPlace());
        user.setDocument(document);
        return true;
    }

    public boolean saveTelephone(User user) {

        Phone phoneDaData = daData.cleanPhone(user.getPhone());
        Telephone phone = new Telephone();
        phone.setFullPhoneNumber(user.getPhone());
        phone.setType(phoneDaData.getType());
        phone.setPhone(phoneDaData.getPhone());
        phone.setCountryCode(phoneDaData.getCountryCode());
        phone.setCityCode(phoneDaData.getCityCode());
        phone.setNumber(phoneDaData.getNumber());
        phone.setExtension(phoneDaData.getExtension());
        phone.setProvider(phoneDaData.getProvider());
        phone.setRegion(phoneDaData.getRegion());
        phone.setTimezone(phoneDaData.getTimezone());
        phone.setQc(phoneDaData.getQc());
        user.setTelephone(phone);
        return true;
    }

    public boolean saveRegistrationAddress(User user) throws UnsupportedOperationException {
        Address addressDaData = daData.cleanAddress(user.getRegistrationAddress());
        Addresses registrationAddress = new Addresses();
        registrationAddress.setFullAddress(user.getRegistrationAddress());
        registrationAddress.setAddressType(AddressType.REGISTRATION);
        registrationAddress.setResult(addressDaData.getResult());
        registrationAddress.setPostalCode(addressDaData.getPostalCode());
        registrationAddress.setCountry(addressDaData.getCountry());
        registrationAddress.setRegionFiasId(addressDaData.getRegionFiasId());
        registrationAddress.setRegionKladrId(addressDaData.getRegionKladrId());
        registrationAddress.setRegionWithType(addressDaData.getRegionWithType());
        registrationAddress.setRegionType(addressDaData.getRegionType());
        registrationAddress.setRegionTypeFull(addressDaData.getRegionTypeFull());
        registrationAddress.setRegion(addressDaData.getRegion());
        registrationAddress.setAreaFiasId(addressDaData.getAreaFiasId());
        registrationAddress.setAreaKladrId(addressDaData.getAreaKladrId());
        registrationAddress.setAreaWithType(addressDaData.getAreaWithType());
        registrationAddress.setAreaType(addressDaData.getAreaType());
        registrationAddress.setAreaTypeFull(addressDaData.getAreaTypeFull());
        registrationAddress.setArea(addressDaData.getArea());
        registrationAddress.setCityFiasId(addressDaData.getCityFiasId());
        registrationAddress.setCityKladrId(addressDaData.getCityKladrId());
        registrationAddress.setCityWithType(addressDaData.getCityWithType());
        registrationAddress.setCityArea(addressDaData.getCityArea());
        registrationAddress.setCityDistrict(addressDaData.getCityDistrict());
        registrationAddress.setCityType(addressDaData.getCityType());
        registrationAddress.setCityTypeFull(addressDaData.getCityTypeFull());
        registrationAddress.setCity(addressDaData.getCity());
        registrationAddress.setResult(addressDaData.getResult());
        registrationAddress.setSettlementFiasId(addressDaData.getSettlementFiasId());
        registrationAddress.setSettlementKladrId(addressDaData.getSettlementKladrId());
        registrationAddress.setSettlementWithType(addressDaData.getSettlementWithType());
        registrationAddress.setSettlementType(addressDaData.getSettlementType());
        registrationAddress.setSettlementTypeFull(addressDaData.getSettlementTypeFull());
        registrationAddress.setSettlement(addressDaData.getSettlement());
        registrationAddress.setStreetFiasId(addressDaData.getStreetFiasId());
        registrationAddress.setStreetKladrId(addressDaData.getStreetKladrId());
        registrationAddress.setStreetWithType(addressDaData.getStreetWithType());
        registrationAddress.setStreetType(addressDaData.getStreetType());
        registrationAddress.setStreetTypeFull(addressDaData.getStreetTypeFull());
        registrationAddress.setStreet(addressDaData.getStreet());
        registrationAddress.setHouseFiasId(addressDaData.getHouseFiasId());
        registrationAddress.setHouseKladrId(addressDaData.getHouseKladrId());
        registrationAddress.setResult(addressDaData.getResult());
        registrationAddress.setHouseType(addressDaData.getHouseType());
        registrationAddress.setHouseTypeFull(addressDaData.getHouseTypeFull());
        registrationAddress.setHouse(addressDaData.getHouse());
        registrationAddress.setBlockType(addressDaData.getBlockType());
        registrationAddress.setBlockTypeFull(addressDaData.getBlockTypeFull());
        registrationAddress.setBlock(addressDaData.getBlock());
        registrationAddress.setFlatType(addressDaData.getFlatType());
        registrationAddress.setFlatTypeFull(addressDaData.getFlatTypeFull());
        registrationAddress.setFlat(addressDaData.getFlat());
        registrationAddress.setFlatArea(addressDaData.getFlatArea());
        registrationAddress.setSquareMeterPrice(addressDaData.getSquareMeterPrice());
        registrationAddress.setFlatPrice(addressDaData.getFlatPrice());
        registrationAddress.setPostalBox(addressDaData.getPostalBox());
        registrationAddress.setFiasId(addressDaData.getFiasId());
        registrationAddress.setFiasLevel(addressDaData.getFiasLevel());
        registrationAddress.setKladrId(addressDaData.getKladrId());
        registrationAddress.setCapitalMarker(addressDaData.getCapitalMarker());
        registrationAddress.setOkato(addressDaData.getOkato());
        registrationAddress.setOktmo(addressDaData.getOktmo());
        registrationAddress.setTaxOffice(addressDaData.getTaxOffice());
        registrationAddress.setTaxOfficeLegal(addressDaData.getTaxOfficeLegal());
        registrationAddress.setTimezone(addressDaData.getTimezone());
        registrationAddress.setGeoLat(addressDaData.getGeoLat());
        registrationAddress.setGeoLon(addressDaData.getGeoLon());
        registrationAddress.setBeltwayHit(addressDaData.getBeltwayDistance());
        registrationAddress.setBeltwayDistance(addressDaData.getBeltwayDistance());
        registrationAddress.setQcGeo(addressDaData.getQcGeo());
        registrationAddress.setQcComplete(addressDaData.getQcComplete());
        registrationAddress.setQcHouse(addressDaData.getQcHouse());
        registrationAddress.setQc(addressDaData.getQc());
        registrationAddress.setUnparsedParts(addressDaData.getUnparsedParts());
        registrationAddress.setUser(user);
        addressesRepository.save(registrationAddress);
        return true;
    }

    public boolean saveActualAddress(User user) throws UnsupportedOperationException {
        Address addressDaData = daData.cleanAddress(user.getActualAddress());
        Addresses actualAddress = new Addresses();
        actualAddress.setFullAddress(user.getActualAddress());
        actualAddress.setAddressType(AddressType.ACTUAL);
        actualAddress.setResult(addressDaData.getResult());
        actualAddress.setPostalCode(addressDaData.getPostalCode());
        actualAddress.setCountry(addressDaData.getCountry());
        actualAddress.setRegionFiasId(addressDaData.getRegionFiasId());
        actualAddress.setRegionKladrId(addressDaData.getRegionKladrId());
        actualAddress.setRegionWithType(addressDaData.getRegionWithType());
        actualAddress.setRegionType(addressDaData.getRegionType());
        actualAddress.setRegionTypeFull(addressDaData.getRegionTypeFull());
        actualAddress.setRegion(addressDaData.getRegion());
        actualAddress.setAreaFiasId(addressDaData.getAreaFiasId());
        actualAddress.setAreaKladrId(addressDaData.getAreaKladrId());
        actualAddress.setAreaWithType(addressDaData.getAreaWithType());
        actualAddress.setAreaType(addressDaData.getAreaType());
        actualAddress.setAreaTypeFull(addressDaData.getAreaTypeFull());
        actualAddress.setArea(addressDaData.getArea());
        actualAddress.setCityFiasId(addressDaData.getCityFiasId());
        actualAddress.setCityKladrId(addressDaData.getCityKladrId());
        actualAddress.setCityWithType(addressDaData.getCityWithType());
        actualAddress.setCityArea(addressDaData.getCityArea());
        actualAddress.setCityDistrict(addressDaData.getCityDistrict());
        actualAddress.setCityType(addressDaData.getCityType());
        actualAddress.setCityTypeFull(addressDaData.getCityTypeFull());
        actualAddress.setCity(addressDaData.getCity());
        actualAddress.setResult(addressDaData.getResult());
        actualAddress.setSettlementFiasId(addressDaData.getSettlementFiasId());
        actualAddress.setSettlementKladrId(addressDaData.getSettlementKladrId());
        actualAddress.setSettlementWithType(addressDaData.getSettlementWithType());
        actualAddress.setSettlementType(addressDaData.getSettlementType());
        actualAddress.setSettlementTypeFull(addressDaData.getSettlementTypeFull());
        actualAddress.setSettlement(addressDaData.getSettlement());
        actualAddress.setStreetFiasId(addressDaData.getStreetFiasId());
        actualAddress.setStreetKladrId(addressDaData.getStreetKladrId());
        actualAddress.setStreetWithType(addressDaData.getStreetWithType());
        actualAddress.setStreetType(addressDaData.getStreetType());
        actualAddress.setStreetTypeFull(addressDaData.getStreetTypeFull());
        actualAddress.setStreet(addressDaData.getStreet());
        actualAddress.setHouseFiasId(addressDaData.getHouseFiasId());
        actualAddress.setHouseKladrId(addressDaData.getHouseKladrId());
        actualAddress.setResult(addressDaData.getResult());
        actualAddress.setHouseType(addressDaData.getHouseType());
        actualAddress.setHouseTypeFull(addressDaData.getHouseTypeFull());
        actualAddress.setHouse(addressDaData.getHouse());
        actualAddress.setBlockType(addressDaData.getBlockType());
        actualAddress.setBlockTypeFull(addressDaData.getBlockTypeFull());
        actualAddress.setBlock(addressDaData.getBlock());
        actualAddress.setFlatType(addressDaData.getFlatType());
        actualAddress.setFlatTypeFull(addressDaData.getFlatTypeFull());
        actualAddress.setFlat(addressDaData.getFlat());
        actualAddress.setFlatArea(addressDaData.getFlatArea());
        actualAddress.setSquareMeterPrice(addressDaData.getSquareMeterPrice());
        actualAddress.setFlatPrice(addressDaData.getFlatPrice());
        actualAddress.setPostalBox(addressDaData.getPostalBox());
        actualAddress.setFiasId(addressDaData.getFiasId());
        actualAddress.setFiasLevel(addressDaData.getFiasLevel());
        actualAddress.setKladrId(addressDaData.getKladrId());
        actualAddress.setCapitalMarker(addressDaData.getCapitalMarker());
        actualAddress.setOkato(addressDaData.getOkato());
        actualAddress.setOktmo(addressDaData.getOktmo());
        actualAddress.setTaxOffice(addressDaData.getTaxOffice());
        actualAddress.setTaxOfficeLegal(addressDaData.getTaxOfficeLegal());
        actualAddress.setTimezone(addressDaData.getTimezone());
        actualAddress.setGeoLat(addressDaData.getGeoLat());
        actualAddress.setGeoLon(addressDaData.getGeoLon());
        actualAddress.setBeltwayHit(addressDaData.getBeltwayDistance());
        actualAddress.setBeltwayDistance(addressDaData.getBeltwayDistance());
        actualAddress.setQcGeo(addressDaData.getQcGeo());
        actualAddress.setQcComplete(addressDaData.getQcComplete());
        actualAddress.setQcHouse(addressDaData.getQcHouse());
        actualAddress.setQc(addressDaData.getQc());
        actualAddress.setUnparsedParts(addressDaData.getUnparsedParts());
        actualAddress.setUser(user);
        addressesRepository.save(actualAddress);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            System.out.println(userRepository.findById(userId) + "Удален");
            return true;
        }
        return false;
    }

    public Optional getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
