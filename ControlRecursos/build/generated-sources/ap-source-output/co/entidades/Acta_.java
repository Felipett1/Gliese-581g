package co.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-30T08:00:02")
@StaticMetamodel(Acta.class)
public class Acta_ { 

    public static volatile SingularAttribute<Acta, String> nombre;
    public static volatile SingularAttribute<Acta, BigDecimal> presupuesto;
    public static volatile SingularAttribute<Acta, String> estado;
    public static volatile SingularAttribute<Acta, BigDecimal> horas;
    public static volatile SingularAttribute<Acta, Date> fechaFin;
    public static volatile SingularAttribute<Acta, BigDecimal> numeroActa;
    public static volatile SingularAttribute<Acta, Date> fechaInicio;
    public static volatile SingularAttribute<Acta, Date> fechaFinEstimada;

}