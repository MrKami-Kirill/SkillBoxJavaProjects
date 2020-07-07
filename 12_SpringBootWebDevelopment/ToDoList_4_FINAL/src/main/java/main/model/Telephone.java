package main.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_user_phones")
public class Telephone {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "telephone")
    private User user;

    private String fullPhoneNumber;

    private String type;

    private String phone;

    private String countryCode;

    private String cityCode;

    private String number;

    private String extension;

    private String provider;

    private String region;

    private String timezone;

    private Integer Qc;

    public Telephone() {
    }
}
