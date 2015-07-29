package co.entidades;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-29T14:34:29")
@StaticMetamodel(Cronograma.class)
public class Cronograma_ { 

    public static volatile SingularAttribute<Cronograma, Date> fechaFinal;
    public static volatile SingularAttribute<Cronograma, BigDecimal> horas_baseline;
    public static volatile SingularAttribute<Cronograma, BigDecimal> ev;
    public static volatile SingularAttribute<Cronograma, BigDecimal> eac;
    public static volatile SingularAttribute<Cronograma, BigDecimal> horas_trabajadas;
    public static volatile SingularAttribute<Cronograma, BigDecimal> etc;
    public static volatile SingularAttribute<Cronograma, String> actividad;
    public static volatile SingularAttribute<Cronograma, String> actividad_Padre;
    public static volatile SingularAttribute<Cronograma, BigDecimal> cv;
    public static volatile SingularAttribute<Cronograma, BigInteger> idUnico;
    public static volatile SingularAttribute<Cronograma, BigInteger> duracion;
    public static volatile SingularAttribute<Cronograma, BigInteger> id;
    public static volatile SingularAttribute<Cronograma, BigDecimal> porcentaje_Real;
    public static volatile SingularAttribute<Cronograma, String> wbs;
    public static volatile SingularAttribute<Cronograma, BigDecimal> horas_re_trabajadas;
    public static volatile SingularAttribute<Cronograma, BigDecimal> spi;
    public static volatile SingularAttribute<Cronograma, BigDecimal> ac;
    public static volatile SingularAttribute<Cronograma, BigDecimal> sv;
    public static volatile SingularAttribute<Cronograma, BigDecimal> pv;
    public static volatile SingularAttribute<Cronograma, BigDecimal> vac;
    public static volatile SingularAttribute<Cronograma, Date> fechaInicio;
    public static volatile SingularAttribute<Cronograma, BigInteger> dias_corte;
    public static volatile SingularAttribute<Cronograma, BigDecimal> cpi;

}