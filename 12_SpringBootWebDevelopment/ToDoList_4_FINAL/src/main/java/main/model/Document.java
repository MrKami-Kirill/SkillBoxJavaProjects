package main.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_user_documents")
public class Document {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "document")
    private User user;

    private String fullDocumentNumber;

    private String series;

    private String number;

    private Integer Qc;

    private String Code;

    private String issuedBy;

    private String issueDate;

    private String birthPlace;

    public Document() {
    }

}
