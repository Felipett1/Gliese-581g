package co.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;


@Entity
@SqlResultSetMapping(name = "ResultadoDiasHabiles",
entities = {
    @EntityResult(entityClass = ResultadoDiasHabiles.class,
    fields = {
        @FieldResult(name = "numeroDias", column = "NUMERO_DIAS"),
        @FieldResult(name = "fechaFinalEstimada", column = "FECHA_FINAL_ESTIMADA")})
})
public class ResultadoDiasHabiles implements Serializable {

    @Id
    private BigInteger numeroDias;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinalEstimada;

    public ResultadoDiasHabiles() {
    }

    public Date getFechaFinalEstimada() {
        return fechaFinalEstimada;
    }

    public void setFechaFinalEstimada(Date fechaFinalEstimada) {
        this.fechaFinalEstimada = fechaFinalEstimada;
    }

    public BigInteger getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(BigInteger numeroDias) {
        this.numeroDias = numeroDias;
    }
}
