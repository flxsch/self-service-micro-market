package micromarket.common.generic;

import jakarta.persistence.GeneratedValue;

@Getter
@Log4j2
@MappedSuperclass
public abstract class ExtendedEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Setter
    @JoinColumn(updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference("createdBy")
    protected IUser createdBy;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference("updatedBy")
    protected IUser updatedBy;

    @Setter
    @JoinColumn(updatable = false)
    protected LocalDateTime createdAt;

    @Setter
    @Column
    protected LocalDateTime updatedAt;
}
