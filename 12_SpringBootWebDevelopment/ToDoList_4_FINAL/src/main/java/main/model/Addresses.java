package main.model;

import lombok.Data;
import ru.dadata.api.entity.Address;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_user_addresses")
public class Addresses {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String fullAddress;

    private AddressType addressType;

    private String result;

    private String postalCode;

    private String country;

    private String regionFiasId;

    private String regionKladrId;

    private String regionWithType;

    private String regionType;

    private String regionTypeFull;

    private String region;

    private String areaFiasId;

    private String areaKladrId;

    private String areaWithType;

    private String areaType;

    private String areaTypeFull;

    private String area;

    private String cityFiasId;

    private String cityKladrId;

    private String cityWithType;

    private String cityArea;

    private String cityDistrict;

    private String cityType;

    private String cityTypeFull;

    private String city;

    private String settlementFiasId;

    private String settlementKladrId;

    private String settlementWithType;

    private String settlementType;

    private String settlementTypeFull;

    private String settlement;

    private String streetFiasId;

    private String streetKladrId;

    private String streetWithType;

    private String streetType;

    private String streetTypeFull;

    private String street;

    private String houseFiasId;

    private String houseKladrId;

    private String houseType;

    private String houseTypeFull;

    private String house;

    private String blockType;

    private String blockTypeFull;

    private String block;

    private String flatType;

    private String flatTypeFull;

    private String flat;

    private double flatArea;

    private double squareMeterPrice;

    private double flatPrice;

    private String postalBox;

    private String fiasId;

    private Address.FiasLevel fiasLevel;

    private String kladrId;

    private Address.CapitalMarker capitalMarker;

    private String okato;

    private String oktmo;

    private String taxOffice;

    private String taxOfficeLegal;

    private String timezone;

    private double geoLat;

    private double geoLon;

    private String beltwayHit;

    private String beltwayDistance;

    private Address.QcGeoAccuracy qcGeo;

    private Address.QcCompleteLevel qcComplete;

    private Address.QcHouse qcHouse;

    private Integer qc;

    private String unparsedParts;

    public Addresses() {
    }
}
