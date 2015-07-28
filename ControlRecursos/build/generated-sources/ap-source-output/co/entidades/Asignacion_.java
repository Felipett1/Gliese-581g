package co.entidades;

import co.entidades.Acta;
import co.entidades.Recurso;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-28T16:58:49")
@StaticMetamodel(Asignacion.class)
public class Asignacion_ { 

    public static volatile SingularAttribute<Asignacion, BigDecimal> id;
    public static volatile SingularAttribute<Asignacion, String> estado;
    public static volatile SingularAttribute<Asignacion, BigDecimal> horas;
    public static volatile SingularAttribute<Asignacion, Recurso> idRecurso;
    public static volatile SingularAttribute<Asignacion, Acta> idActa;
    public static volatile SingularAttribute<Asignacion, Date> fechaInicio;
    public static volatile SingularAttribute<Asignacion, Date> fechaFinEstimada;

}