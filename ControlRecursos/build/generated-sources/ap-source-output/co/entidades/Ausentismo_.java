package co.entidades;

import co.entidades.Recurso;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-29T14:34:27")
@StaticMetamodel(Ausentismo.class)
public class Ausentismo_ { 

    public static volatile SingularAttribute<Ausentismo, BigDecimal> id;
    public static volatile SingularAttribute<Ausentismo, Recurso> idRecurso;
    public static volatile SingularAttribute<Ausentismo, Date> fechaFin;
    public static volatile SingularAttribute<Ausentismo, Date> fechaInicio;

}