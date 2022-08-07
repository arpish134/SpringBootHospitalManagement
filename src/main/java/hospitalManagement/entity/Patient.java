package hospitalManagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "patients")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "room_No", nullable = false)
    private int roomNo;

    @Column(name = "doc_name", nullable = false, length = 20)
    private String docName;

    @Column(name = "admit_date", nullable = false)
    private String admitDate;

    @Column(name = "expenses", nullable = false)
    private int expenses;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

}
